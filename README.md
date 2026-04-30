# Spring Boot Demo

- After starting the project, open the following link in your web browser to verify that the project is running correctly.
-  You can check out different features by switching branches..

### Link
[Swagger UI](http://localhost:8080/spring-boot-demo/swagger-ui/index.html)

---

## 分散式追蹤 (Distributed Tracing)

### 核心概念

分散式追蹤是一種提升系統可觀測性的技術，用來追蹤一個請求 (Request) 在多個服務 (Service) 之間的完整流程。

### 追蹤識別碼

| 識別碼 | 說明 |
|--------|------|
| Trace ID | 代表一次完整的請求旅程，從起點到終點全程唯一。跨服務呼叫時，Trace ID 會透過 HTTP Header 傳遞，確保所有環節共用同一個 ID。 |
| Span ID | 代表一個工作單元 (Unit of Work)，例如一次 HTTP 請求、一次資料庫查詢。一個 Trace 由多個 Span 組成，形成樹狀結構。 |

### 傳播機制 (Propagation)

當 Service A 呼叫 Service B 時，追蹤資訊透過 HTTP Header 傳遞（W3C TraceContext 格式）：

```
traceparent: 00-5f3a2b1c4d6e78901a2b3c4d5e6f7890-1a2b3c4d5e6f7890-01
             ^^ ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ ^^^^^^^^^^^^^^^^ ^^
             版本  Trace ID (32 hex chars)          Span ID        flag
```

### 實作

使用 **Micrometer Tracing + OpenTelemetry Bridge**（`micrometer-tracing-bridge-otel`）實作。

每次 HTTP 請求進來，Trace ID 與 Span ID 會自動注入至 MDC (Mapped Diagnostic Context)，並顯示在 Log 中：

```
INFO [http-nio-8080-exec-1] [T-5f3a2b1c4d6e7890,S-1a2b3c4d] c.e.d.c.TestController.hello() - ...
```

| | Brave Bridge | **OTel Bridge（目前）** |
|---|---|---|
| 依賴 | `micrometer-tracing-bridge-brave` | `micrometer-tracing-bridge-otel` |
| 傳播格式 | B3 | W3C TraceContext |
| 後端相容性 | Zipkin | OTLP Collector、Jaeger、Zipkin 等 |

### References
- https://docs.spring.io/spring-boot/reference/actuator/tracing.html
- https://opentelemetry.io/docs/concepts/signals/traces/

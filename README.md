# Spring Boot MongoDB CRUD Demo

示範如何透過 Spring Boot 串接 MongoDB，實作學生資料的 CRUD 操作。

## 概念說明

### MongoDB 基礎概念

MongoDB 是一個 NoSQL Document Database，以 Flexible Schema 設計，不需要像 MySQL 事先定義 Table Schema。

| 關聯式資料庫 | MongoDB |
|------------|---------|
| Table | Collection |
| Row | Document |
| Column | Field |
| Primary Key | `_id`（ObjectId）|

**ObjectId**：MongoDB 自動產生的 12 bytes 唯一識別碼，由 timestamp、機器 ID、PID 和隨機值組成，不需要手動維護。

**Flexible Schema**：同一個 Collection 中的 Document 可以有不同的欄位，不像關聯式資料庫強制每筆資料符合同一個結構。

### 底層實作

- **儲存引擎（Storage Engine）**：WiredTiger
- **索引結構（Index）**：B-Tree（與 MySQL InnoDB 的 B+ Tree 不同，葉節點之間沒有 linked list，range query 走法不同）
- **文件格式（Document Format）**：BSON（Binary JSON），帶有型別資訊和長度前綴，儲存時透過 Snappy 壓縮

### Spring Data MongoDB

Spring Data MongoDB 透過 `@Document` annotation 將 Java 物件對應到 MongoDB 的 Collection，並自動加上 `_class` 欄位用於 Java 型別識別。

Repository 繼承 `MongoRepository` 後，Spring Data 會自動產生 CRUD 方法，也支援方法名稱推導查詢（Derived Query），例如 `findByMajor(String major)` 會自動轉換成對應的 MongoDB 查詢。

## 開發環境

- Java 21
- Spring Boot 3.3.2
- MongoDB 7.0（Docker）

## 快速啟動

### 1. 啟動 MongoDB

```bash
docker compose up -d
```

### 2. 啟動 Spring Boot

```bash
./mvnw spring-boot:run
```

### 3. 開啟 Swagger UI

```
http://localhost:8080/spring-boot-demo/swagger-ui/index.html
```

## API 說明

| Method | Path | 說明 |
|--------|------|------|
| GET | `/students` | 查詢所有學生 |
| GET | `/students/{id}` | 依 ID 查詢學生 |
| GET | `/students/major/{major}` | 依科系查詢學生 |
| POST | `/students` | 新增學生 |
| PUT | `/students/{id}` | 更新學生資料 |
| DELETE | `/students/{id}` | 刪除學生 |

## 參考資料

- [Spring Data MongoDB - Getting Started](https://docs.spring.io/spring-data/mongodb/reference/mongodb/getting-started.html)
- [Spring Data MongoDB - Repositories](https://docs.spring.io/spring-data/mongodb/reference/mongodb/repositories/repositories.html)
- [MongoDB BSON Types](https://www.mongodb.com/docs/manual/reference/bson-types/)
- [WiredTiger Storage Engine](https://www.mongodb.com/docs/manual/core/wiredtiger/)
- [WiredTiger Compression - MongoDB Docs](https://www.mongodb.com/docs/manual/core/wiredtiger/#compression)

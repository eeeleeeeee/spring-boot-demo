# Spring Boot Demo

- After starting the project, open the following link in your web browser to verify that the project is running correctly.
-  You can check out different features by switching branches..

### Link
[Swagger UI](http://localhost:8080/spring-boot-demo/swagger-ui/index.html)

---

## Git Commit ID

### 概念

`git-commit-id-maven-plugin` 是一個 Maven plugin，在 project build stage 讀取 `.git`，將 Git 資訊（如 branch name、commit hash value）寫入 `git.properties`。

Spring Boot Actuator 會自動讀取 classpath 上的 `git.properties`，透過 `/actuator/info` 對外公開，讓維運人員能快速確認目前部署的版本。

### 實作

**1. 加入 dependency**

在 `pom.xml` 加入 `spring-boot-starter-actuator` 與 `git-commit-id-maven-plugin`：

```xml
<!-- Actuator -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<!-- Plugin -->
<plugin>
    <groupId>io.github.git-commit-id</groupId>
    <artifactId>git-commit-id-maven-plugin</artifactId>
    <version>10.0.0</version>
    <executions>
        <execution>
            <id>get-the-git-infos</id>
            <goals><goal>revision</goal></goals>
            <phase>initialize</phase>
        </execution>
    </executions>
    <configuration>
        <generateGitPropertiesFile>true</generateGitPropertiesFile>
        <generateGitPropertiesFilename>${project.build.outputDirectory}/git.properties</generateGitPropertiesFilename>
        <includeOnlyProperties>
            <includeOnlyProperty>^git.branch$</includeOnlyProperty>
            <includeOnlyProperty>^git.commit.id$</includeOnlyProperty>
            <includeOnlyProperty>^git.commit.id.abbrev$</includeOnlyProperty>
            <includeOnlyProperty>^git.commit.message.short$</includeOnlyProperty>
            <includeOnlyProperty>^git.commit.time$</includeOnlyProperty>
            <includeOnlyProperty>^git.build.time$</includeOnlyProperty>
        </includeOnlyProperties>
        <commitIdGenerationMode>flat</commitIdGenerationMode>
    </configuration>
</plugin>
```

**2. 設定 Actuator 公開 info endpoint**

在 `application.yaml` 加入：

```yaml
management:
  endpoints:
    web:
      exposure:
        include: info
  info:
    git:
      mode: full
```

**3. 啟動監聽器**

建立 `ApplicationStartupListener`，在 application 啟動時印出 Git 資訊：

```java
@Slf4j
@Component
@RequiredArgsConstructor
public class ApplicationStartupListener {

    private final GitProperties gitProperties;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        log.info("Git branch: {}", gitProperties.getBranch());
        log.info("Git commit id: {}", gitProperties.getCommitId());
        log.info("Git commit time: {}", gitProperties.getCommitTime());
    }
}
```

**4. 驗證**

啟動後呼叫：

```bash
curl http://localhost:8080/{context-path}/actuator/info
```

Response Example：

```json
{
  "git": {
    "branch": "main",
    "commit": {
      "id": "7692a49",
      "time": "2026-04-30T07:55:37Z"
    }
  }
}
```

### References

- [git-commit-id-maven-plugin GitHub](https://github.com/git-commit-id/git-commit-id-maven-plugin)
- [Spring Boot Actuator - Git Info](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.endpoints.info.git-commit-information)

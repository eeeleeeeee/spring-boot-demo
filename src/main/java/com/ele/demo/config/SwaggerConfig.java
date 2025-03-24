package com.ele.demo.config;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    GroupedOpenApi apis(
            @Value("${spring.application.name}") String appName) {
        return GroupedOpenApi.builder()
                .group(appName)
                .displayName(appName.toUpperCase())
                .addOpenApiCustomizer(openApi -> {
                    var info = new Info();
                    info.setTitle(appName.toUpperCase().replace("-", " "));
                    openApi.info(info);
                })
                .build();
    }
}

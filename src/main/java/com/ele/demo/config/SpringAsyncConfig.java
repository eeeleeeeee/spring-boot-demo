package com.ele.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync // detects Spring’s @Async annotation
public class SpringAsyncConfig {

    @Bean(name = "taskExecutor1")
    public Executor taskExecutor1() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(16);
        threadPoolTaskExecutor.setMaxPoolSize(32);
        return threadPoolTaskExecutor;
    }

    @Bean(name = "taskExecutor2")
    public Executor taskExecutor2() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(4);
        threadPoolTaskExecutor.setMaxPoolSize(32);
        return threadPoolTaskExecutor;
    }
}

package com.ele.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private final ConfigurableApplicationContext configurableApplicationContext;

    @Override
    public void run(String... args) {
        try {
            log.info("{} works!", getClass().getSimpleName());
            configurableApplicationContext.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

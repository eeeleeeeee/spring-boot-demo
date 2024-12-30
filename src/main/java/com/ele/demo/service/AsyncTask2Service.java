package com.ele.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncTask2Service {

    @Async("taskExecutor2")
    public CompletableFuture<String> start(String value2) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.completedFuture("AsyncTask2 value = " + value2);
    }
}

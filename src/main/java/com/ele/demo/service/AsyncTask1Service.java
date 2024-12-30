package com.ele.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class AsyncTask1Service {

    @Async("taskExecutor1")
    public CompletableFuture<String> start(String value1) {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return CompletableFuture.completedFuture("AsyncTask1 value = " + value1);
    }
}

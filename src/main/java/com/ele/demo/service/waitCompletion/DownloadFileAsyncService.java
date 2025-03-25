package com.ele.demo.service.waitCompletion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class DownloadFileAsyncService {

    @Async("taskExecutor2")
    public CompletableFuture<String> start(String url) throws InterruptedException {

        log.info("Downloading url: {}", url);

        String fileName;

        Thread.sleep(5000);
        fileName = UUID.randomUUID() + ".png";
        log.info("File {} downloaded.", fileName);

        return CompletableFuture.completedFuture(fileName);
    }
}
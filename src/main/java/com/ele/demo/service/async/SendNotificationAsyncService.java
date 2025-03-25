package com.ele.demo.service.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SendNotificationAsyncService {

    @Async("taskExecutor1")
    public void start() throws InterruptedException {

        String threadName = Thread.currentThread().getName();

        log.info("The async task is running on thread {}.", threadName);

        Thread.sleep(10000);

        log.info("Notification sent!");
        log.info("The async task is running on thread {} and has completed.", threadName);
    }
}
package com.ele.demo.service.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationService {

    private final SendNotificationAsyncService sendNotificationAsyncService;

    public void send() throws InterruptedException {
        sendNotificationAsyncService.start();
    }
}

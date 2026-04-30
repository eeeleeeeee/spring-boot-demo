package com.ele.demo.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

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

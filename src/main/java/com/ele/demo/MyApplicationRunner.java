package com.ele.demo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MyApplicationRunner implements ApplicationRunner {

    private final ConfigurableApplicationContext configurableApplicationContext;

    private final static String JOB = "JOB";

    @Override
    public void run(ApplicationArguments args) {

        log.info("Hello Application Runner");

        var jobValues = args.getOptionValues(JOB);

        if (jobValues != null && jobValues.size() > 0) {
            String jobName = jobValues.get(0);
            try {
                log.info("Run the task {} start.", jobName);
                log.info("Run the task {} end.", jobName);
            } catch (Exception e) {
                log.error("The task failed to run.", e);
            } finally {
                configurableApplicationContext.close();
            }
        }
    }
}
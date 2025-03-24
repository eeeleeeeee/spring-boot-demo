package com.ele.demo.schedule;

import com.ele.demo.service.AsyncTask1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Slf4j
@RequiredArgsConstructor
@Component
public class TaskSchedule {

    @Qualifier("taskExecutor1")
    private final Executor taskExecutor1;

    private final AsyncTask1Service asyncTask1;

    @Scheduled(initialDelay = 1000, fixedRate = 1000)
    public void example1() {
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        List<CompletableFuture<String>> resultList = new ArrayList<>();
        for (int i = 0; i < 20000; i++) {
            int queueSize = ((ThreadPoolTaskExecutor) taskExecutor1).getThreadPoolExecutor().getQueue().size();
            long activeCount = ((ThreadPoolTaskExecutor) taskExecutor1).getThreadPoolExecutor().getActiveCount();

            System.out.println("queueSize:" + queueSize);
            System.out.println("activeCount:" + activeCount);

            if (queueSize >= 16) {
                continue;
            }
            CompletableFuture<String> result = asyncTask1.start("test" + i);
            resultList.add(result);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        CompletableFuture.allOf(resultList.toArray(new CompletableFuture[0])).join();
    }
}

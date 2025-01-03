package com.ele.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

    @Async("taskExecutor1") // 標為非同步，此方法被調用時，會由另個thread執行，不占用主thread。
    public void task() {

        // 印出此方法執行之thread名，確認與主thread不同
        System.out.println("非同步任務：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000); // 模擬Thread暫停三秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("完成" + Thread.currentThread().getName()); ;
    }
}
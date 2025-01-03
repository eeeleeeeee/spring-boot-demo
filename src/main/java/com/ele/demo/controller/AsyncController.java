package com.ele.demo.controller;

import com.ele.demo.service.AsyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncController {

    private final AsyncService asyncService;

    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/startTask")
    public String startAsyncTask() {
        asyncService.task();
        return "非同步任務已啟動";
    }
}

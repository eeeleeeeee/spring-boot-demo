package com.ele.demo.rabbitmq.routing;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/routing")
public class RoutingController {

    private final LogProducer producer;

    @PostMapping("/log")
    public void sendLog(@RequestBody LogMsg logMsg) {
        producer.sendLog(logMsg);
    }
}

package com.ele.demo.rabbitmq.workqueue;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/work-queue")
public class WorkQueueController {

    private final OrderProducer producer;

    @PostMapping("/order")
    public void sendOrder(@RequestBody OrderMsg order) {
        producer.sendOrder(order);
    }
}

package com.ele.demo.rabbitmq.topics;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/topics")
public class TopicsController {

    private final EventProducer producer;

    @PostMapping("/event")
    public void sendEvent(@RequestBody EventMsg event) {
        producer.sendEvent(event);
    }
}

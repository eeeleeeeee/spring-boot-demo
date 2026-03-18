package com.ele.demo.rabbitmq.pubsub;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pub-sub")
public class PubSubController {

    private final ProductProducer producer;

    @PostMapping("/product-launch")
    public void broadcastProductLaunch(@RequestBody ProductMsg product) {
        producer.broadcastProductLaunch(product);
    }
}

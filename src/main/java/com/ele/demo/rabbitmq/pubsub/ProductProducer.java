package com.ele.demo.rabbitmq.pubsub;

import com.ele.demo.config.PubSubConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ProductProducer {

    private final RabbitTemplate rabbitTemplate;

    public void broadcastProductLaunch(ProductMsg product) {
        // routing key is ignored for fanout exchange
        rabbitTemplate.convertAndSend(PubSubConfig.PRODUCT_FANOUT_EXCHANGE, "", product);
        log.info("Product [{}] launch event broadcast to all subscribers.", product.getProductId());
    }
}

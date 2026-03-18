package com.ele.demo.rabbitmq.pubsub;

import com.ele.demo.config.PubSubConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailNotificationConsumer {

    @RabbitListener(queues = PubSubConfig.EMAIL_QUEUE)
    public void handleProductLaunch(@Payload ProductMsg product) {
        log.info("[Email Service] Sending launch email for product [{}]: {}", product.getProductId(), product.getName());
    }
}

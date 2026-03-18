package com.ele.demo.rabbitmq.topics;

import com.ele.demo.config.TopicsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Receives all order-related events from any region.
 * Binding pattern: "*.order.*"
 * Matches: asia.order.created, europe.order.cancelled, us.order.shipped ...
 */
@Slf4j
@Component
public class OrderEventConsumer {

    @RabbitListener(queues = TopicsConfig.ORDER_QUEUE)
    public void handleOrderEvent(@Payload EventMsg event) {
        log.info("[Order Service] Received event [{}]: {}", event.getRoutingKey(), event.getPayload());
    }
}

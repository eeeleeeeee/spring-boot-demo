package com.ele.demo.rabbitmq.topics;

import com.ele.demo.config.TopicsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Receives every event regardless of routing key.
 * Binding pattern: "#"
 * Matches: everything
 */
@Slf4j
@Component
public class AllEventConsumer {

    @RabbitListener(queues = TopicsConfig.ALL_QUEUE)
    public void handleAllEvents(@Payload EventMsg event) {
        log.info("[Audit Log] Received event [{}]: {}", event.getRoutingKey(), event.getPayload());
    }
}

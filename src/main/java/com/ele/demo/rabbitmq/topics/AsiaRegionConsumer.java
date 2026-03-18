package com.ele.demo.rabbitmq.topics;

import com.ele.demo.config.TopicsConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * Receives all events from the Asia region.
 * Binding pattern: "asia.#"
 * Matches: asia.order.created, asia.user.deleted, asia.anything.anything ...
 */
@Slf4j
@Component
public class AsiaRegionConsumer {

    @RabbitListener(queues = TopicsConfig.ASIA_QUEUE)
    public void handleAsiaEvent(@Payload EventMsg event) {
        log.info("[Asia Region] Received event [{}]: {}", event.getRoutingKey(), event.getPayload());
    }
}

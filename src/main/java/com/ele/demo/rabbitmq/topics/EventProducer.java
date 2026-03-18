package com.ele.demo.rabbitmq.topics;

import com.ele.demo.config.TopicsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendEvent(EventMsg event) {
        rabbitTemplate.convertAndSend(TopicsConfig.EVENT_TOPIC_EXCHANGE, event.getRoutingKey(), event);
        log.info("Event sent with routing key: [{}]", event.getRoutingKey());
    }
}

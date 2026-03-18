package com.ele.demo.rabbitmq.workqueue;

import com.ele.demo.config.WorkQueueConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrderProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendOrder(OrderMsg order) {
        rabbitTemplate.convertAndSend(WorkQueueConfig.ORDER_QUEUE, order);
        log.info("Order [{}] sent to work queue.", order.getOrderId());
    }
}

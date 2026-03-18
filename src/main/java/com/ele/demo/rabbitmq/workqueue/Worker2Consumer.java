package com.ele.demo.rabbitmq.workqueue;

import com.ele.demo.config.WorkQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Worker2Consumer {

    @RabbitListener(queues = WorkQueueConfig.ORDER_QUEUE)
    public void processOrder(@Payload OrderMsg order) {
        log.info("[Worker 2] Processing order [{}]: {} x{}", order.getOrderId(), order.getProduct(), order.getQuantity());
    }
}

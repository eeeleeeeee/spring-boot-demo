package com.ele.demo.rabbitmq.routing;

import com.ele.demo.config.RoutingConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ErrorLogConsumer {

    @RabbitListener(queues = RoutingConfig.ERROR_QUEUE)
    public void handleErrorLog(@Payload LogMsg logMsg) {
        log.info("[ERROR Handler] Source: {} | Message: {}", logMsg.getSource(), logMsg.getMessage());
        // e.g. send alert to PagerDuty / Slack
    }
}

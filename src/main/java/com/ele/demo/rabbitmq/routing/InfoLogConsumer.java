package com.ele.demo.rabbitmq.routing;

import com.ele.demo.config.RoutingConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InfoLogConsumer {

    @RabbitListener(queues = RoutingConfig.INFO_QUEUE)
    public void handleInfoLog(@Payload LogMsg logMsg) {
        log.info("[INFO Handler] Source: {} | Message: {}", logMsg.getSource(), logMsg.getMessage());
        // e.g. write to info log file
    }
}

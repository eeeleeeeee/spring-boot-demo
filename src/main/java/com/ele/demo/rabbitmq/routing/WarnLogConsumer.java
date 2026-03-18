package com.ele.demo.rabbitmq.routing;

import com.ele.demo.config.RoutingConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WarnLogConsumer {

    @RabbitListener(queues = RoutingConfig.WARN_QUEUE)
    public void handleWarnLog(@Payload LogMsg logMsg) {
        log.info("[WARN Handler] Source: {} | Message: {}", logMsg.getSource(), logMsg.getMessage());
        // e.g. write to warn log file
    }
}

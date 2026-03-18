package com.ele.demo.rabbitmq.routing;

import com.ele.demo.config.RoutingConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class LogProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendLog(LogMsg logMsg) {
        rabbitTemplate.convertAndSend(RoutingConfig.LOG_DIRECT_EXCHANGE, logMsg.getLevel(), logMsg);
        log.info("Log [{}] from [{}] sent to routing exchange.", logMsg.getLevel(), logMsg.getSource());
    }
}

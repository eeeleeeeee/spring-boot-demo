package com.ele.demo.rabbitmq.simple;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendNewCommentNotification(CommentMsg comment) {
        rabbitTemplate.convertAndSend("Comment Notification Queue", comment);
        log.info("{} sends message successfully.", getClass().getSimpleName());
    }
}

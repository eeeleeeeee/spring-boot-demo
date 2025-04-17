package com.ele.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitHealthCheck implements ApplicationRunner {

    private final ConnectionFactory connectionFactory;

    public RabbitHealthCheck(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public void run(ApplicationArguments args) {
        try (Connection connection = connectionFactory.createConnection()) {
            if (connection.isOpen()) {
                log.info("✅ RabbitMQ is connected successfully.");
            } else {
                log.info("❌ RabbitMQ connection is not open.");
            }
        } catch (Exception e) {
            log.error("❌ Failed to connect to RabbitMQ: {}", e.getMessage());
        }
    }
}


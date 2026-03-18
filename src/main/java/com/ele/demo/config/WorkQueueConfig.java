package com.ele.demo.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Pattern 2: Work Queue
 * Multiple consumers (workers) share a single queue.
 * Messages are distributed among workers in round-robin fashion.
 */
@Configuration
public class WorkQueueConfig {

    public static final String ORDER_QUEUE = "work.order.queue";

    @Bean
    public Queue orderWorkQueue() {
        return new Queue(ORDER_QUEUE, true);
    }
}

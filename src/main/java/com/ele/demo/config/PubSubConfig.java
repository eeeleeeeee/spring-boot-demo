package com.ele.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Pattern 3: Publish/Subscribe (Fanout)
 * One message is broadcast to ALL bound queues.
 * Routing key is ignored.
 */
@Configuration
public class PubSubConfig {

    public static final String PRODUCT_FANOUT_EXCHANGE = "pubsub.product.fanout";
    public static final String EMAIL_QUEUE = "pubsub.email.queue";
    public static final String PUSH_QUEUE = "pubsub.push.queue";

    @Bean
    public FanoutExchange productFanoutExchange() {
        return new FanoutExchange(PRODUCT_FANOUT_EXCHANGE);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(EMAIL_QUEUE, true);
    }

    @Bean
    public Queue pushQueue() {
        return new Queue(PUSH_QUEUE, true);
    }

    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(productFanoutExchange());
    }

    @Bean
    public Binding pushBinding() {
        return BindingBuilder.bind(pushQueue()).to(productFanoutExchange());
    }
}

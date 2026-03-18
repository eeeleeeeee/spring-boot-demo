package com.ele.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Pattern 4: Routing (Direct Exchange)
 * Messages are routed to queues based on an exact routing key match.
 */
@Configuration
public class RoutingConfig {

    public static final String LOG_DIRECT_EXCHANGE = "routing.log.direct";
    public static final String ERROR_QUEUE = "routing.error.queue";
    public static final String INFO_QUEUE = "routing.info.queue";
    public static final String WARN_QUEUE = "routing.warn.queue";

    @Bean
    public DirectExchange logDirectExchange() {
        return new DirectExchange(LOG_DIRECT_EXCHANGE);
    }

    @Bean
    public Queue errorQueue() {
        return new Queue(ERROR_QUEUE, true);
    }

    @Bean
    public Queue infoQueue() {
        return new Queue(INFO_QUEUE, true);
    }

    @Bean
    public Queue warnQueue() {
        return new Queue(WARN_QUEUE, true);
    }

    @Bean
    public Binding errorBinding() {
        return BindingBuilder.bind(errorQueue()).to(logDirectExchange()).with("ERROR");
    }

    @Bean
    public Binding infoBinding() {
        return BindingBuilder.bind(infoQueue()).to(logDirectExchange()).with("INFO");
    }

    @Bean
    public Binding warnBinding() {
        return BindingBuilder.bind(warnQueue()).to(logDirectExchange()).with("WARN");
    }
}

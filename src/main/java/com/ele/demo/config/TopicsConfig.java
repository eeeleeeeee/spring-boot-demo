package com.ele.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Pattern 5: Topics (Topic Exchange)
 * Messages are routed based on routing key patterns.
 * '*' matches exactly one word, '#' matches zero or more words.
 *
 * Routing key format: {region}.{entity}.{action}
 * e.g. asia.order.created, europe.user.registered
 *
 * Bindings:
 *   topics.asia.queue  ← "asia.#"       (any event from asia region)
 *   topics.order.queue ← "*.order.*"    (any order event from any region)
 *   topics.all.queue   ← "#"            (all events)
 */
@Configuration
public class TopicsConfig {

    public static final String EVENT_TOPIC_EXCHANGE = "topics.event.topic";
    public static final String ASIA_QUEUE = "topics.asia.queue";
    public static final String ORDER_QUEUE = "topics.order.queue";
    public static final String ALL_QUEUE = "topics.all.queue";

    @Bean
    public TopicExchange eventTopicExchange() {
        return new TopicExchange(EVENT_TOPIC_EXCHANGE);
    }

    @Bean
    public Queue asiaQueue() {
        return new Queue(ASIA_QUEUE, true);
    }

    @Bean
    public Queue topicOrderQueue() {
        return new Queue(ORDER_QUEUE, true);
    }

    @Bean
    public Queue allQueue() {
        return new Queue(ALL_QUEUE, true);
    }

    @Bean
    public Binding asiaBinding() {
        return BindingBuilder.bind(asiaQueue()).to(eventTopicExchange()).with("asia.#");
    }

    @Bean
    public Binding topicOrderBinding() {
        return BindingBuilder.bind(topicOrderQueue()).to(eventTopicExchange()).with("*.order.*");
    }

    @Bean
    public Binding allBinding() {
        return BindingBuilder.bind(allQueue()).to(eventTopicExchange()).with("#");
    }
}

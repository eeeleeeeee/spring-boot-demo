package com.ele.demo.rabbitmq.topics;

import lombok.Data;

import java.io.Serializable;

@Data
public class EventMsg implements Serializable {

    /**
     * Routing key in format: {region}.{entity}.{action}
     * e.g. asia.order.created, europe.user.registered, asia.user.deleted
     */
    private String routingKey;
    private String payload;
}

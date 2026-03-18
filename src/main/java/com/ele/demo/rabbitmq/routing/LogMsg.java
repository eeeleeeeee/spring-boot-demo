package com.ele.demo.rabbitmq.routing;

import lombok.Data;

import java.io.Serializable;

@Data
public class LogMsg implements Serializable {

    /** Routing key — must be one of: ERROR, INFO, WARN */
    private String level;
    private String source;
    private String message;
}

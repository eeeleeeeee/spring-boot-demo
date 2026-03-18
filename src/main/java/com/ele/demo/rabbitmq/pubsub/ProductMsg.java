package com.ele.demo.rabbitmq.pubsub;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductMsg implements Serializable {

    private Integer productId;
    private String name;
    private String description;
}

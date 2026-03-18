package com.ele.demo.rabbitmq.workqueue;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderMsg implements Serializable {

    private Integer orderId;
    private String product;
    private Integer quantity;
}

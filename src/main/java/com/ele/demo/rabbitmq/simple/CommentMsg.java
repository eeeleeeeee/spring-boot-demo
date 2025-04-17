package com.ele.demo.rabbitmq.simple;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentMsg implements Serializable {

    private Integer postId;
    private Integer creatorId;
    private String content;
}

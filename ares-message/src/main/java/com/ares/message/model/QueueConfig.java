package com.ares.message.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/08
 * @see: com.ares.message.controller QueueConfig.java
 **/
@Data
public class QueueConfig implements Serializable {
    private String exchangeType;
    private String exchangeName;
    private String queueName;
    private String routingKey;
}

package com.ares.message.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/08
 * @see: com.ares.message.model ProduceInfo.java
 **/
@Data
public class ProduceInfo implements Serializable {
    private String exchangeName;
    private String routingKey;
    private String msg;
}

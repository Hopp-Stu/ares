package com.ares.message.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/08
 * @see: com.ares.message.model ConsumerInfo.java
 **/
@Data
public class ConsumerInfo implements Serializable {
    private String queueName;
}

package com.ares.message.controller;

import com.ares.core.utils.JsonUtils;
import com.ares.message.model.ConsumerInfo;
import com.ares.message.utils.RabbitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/08
 * @see: com.ares.message.controller ConsumerController.java
 **/
@Slf4j
@RestController
@RequestMapping("/consumer/*")
public class ConsumerController {
    @Resource
    private SimpleMessageListenerContainer container;

    @Resource
    private RabbitUtil rabbitUtil;

    @PostMapping("addQueue")
    public void addQueue(@RequestBody ConsumerInfo consumerInfo) {
        boolean existQueue = rabbitUtil.existQueue(consumerInfo.getQueueName());
        if (!existQueue) {
            throw new RuntimeException("当前队列不存在！");
        }
        container.addQueueNames(consumerInfo.getQueueName());
    }

    @PostMapping("removeQueue")
    public void removeQueue(@RequestBody ConsumerInfo consumerInfo) {
        container.removeQueueNames(consumerInfo.getQueueName());
    }

    @GetMapping("getQueue")
    public void getQueue() {
        log.info("container-queue:{}", JsonUtils.toJson(container.getQueueNames()));
    }

}

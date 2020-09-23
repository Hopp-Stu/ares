package com.ares.message.controller;

import com.ares.message.model.ProduceInfo;
import com.ares.message.utils.RabbitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/08
 * @see: com.ares.message.controller ProduceController.java
 **/
@Slf4j
@RestController
@RequestMapping("/produce/*")
public class ProduceController {
    @Resource
    private RabbitUtil rabbitUtil;

    @PostMapping("sendMessage")
    public void sendMessage(@RequestBody ProduceInfo produceInfo) {
        rabbitUtil.convertAndSend(produceInfo.getExchangeName(), produceInfo.getRoutingKey(), produceInfo.getMsg());
    }
}

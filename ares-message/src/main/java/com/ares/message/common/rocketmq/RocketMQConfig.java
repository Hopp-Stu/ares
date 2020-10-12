package com.ares.message.common.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * @description:
 * @author: yy
 * @date: 2020/09/29
 * @see: com.ares.message.common.rocketmq RocketMQConfig.java
 **/
@Configuration
public class RocketMQConfig {

    @Autowired
    private RocketMQProperties properties;
    @Autowired
    private RocketMsgListener rocketMsgListener;

    @Bean
    public DefaultMQProducer defaultMQProducer() {
        Assert.hasText(properties.getProducerGroup(), "[rocketmq.producer.group] must not be null");
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer(properties.getProducerGroup());
        defaultMQProducer.setNamesrvAddr(properties.getNameServer());
        defaultMQProducer.setMaxMessageSize(properties.getMaxMessageSize());
        defaultMQProducer.setRetryTimesWhenSendFailed(properties.getRetryTimesWhenSendFailed());
        defaultMQProducer.setVipChannelEnabled(false);
        defaultMQProducer.setRetryTimesWhenSendAsyncFailed(properties.getRetryTimesWhenSendAsyncFailed());
        defaultMQProducer.setSendMsgTimeout(properties.getSendMsgTimeout());

        try {
            defaultMQProducer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return defaultMQProducer;
    }

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(properties.getProducerGroup());
        consumer.setNamesrvAddr(properties.getNameServer());
        consumer.setConsumeThreadMax(properties.getConsumeThreadMax());
        consumer.setConsumeThreadMin(properties.getConsumeThreadMin());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.setConsumeMessageBatchMaxSize(properties.getConsumeMessageBatchMaxSize());
        consumer.registerMessageListener(rocketMsgListener);

        try {
            String[] topics = properties.getTopics().split(";");
            for (String topicTags : topics) {
                String[] topicTag = topicTags.split("~");
                consumer.subscribe(topicTag[0], topicTag[1]);
            }
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consumer;
    }
}

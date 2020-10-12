package com.ares.message.common.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/10
 * @see: com.ares.message.common.rocketmq RocketMsgListener.java
 **/
@Slf4j
@Component
public class RocketMsgListener implements MessageListenerConcurrently {
    @Autowired
    private RocketMQProperties properties;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (CollectionUtils.isEmpty(list)) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt messageExt = list.get(0);
        log.info("接受到的消息为：" + new String(messageExt.getBody()));
        int reConsume = messageExt.getReconsumeTimes();
        // 消息已经重试了3次，如果不需要再次消费，则返回成功
        if (reConsume == 3) {
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        if (messageExt.getTopic().equals(properties.getPlatTopic())) {
            String tags = messageExt.getTags();
            switch (tags) {
                case "AresAccountTag":
                    log.info("开户 tag == >>" + tags);
                    break;
                default:
                    log.info("未匹配到Tag == >>" + tags);
                    break;
            }
        }
        // 消息消费成功
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}

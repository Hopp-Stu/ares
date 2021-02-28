package com.ares.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

/**
 * @description:
 * @author: yy
 * @date: 2021/01/29
 * @see: com.ares.core.listener DefaultEventListener.java
 **/
public abstract class DefaultEventListener<T extends ApplicationEvent> implements ApplicationListener<T>, Ordered {
    private Logger logger = LoggerFactory.getLogger(DefaultEventListener.class);

    @Override
    public int getOrder() {
        return 99;
    }
}

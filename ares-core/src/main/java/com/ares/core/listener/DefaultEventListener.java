/*******************************************************************************
 * Copyright (c) 2021 - 9999, ARES
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.ares.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;

/**
 * @description:
 * @author: Young
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

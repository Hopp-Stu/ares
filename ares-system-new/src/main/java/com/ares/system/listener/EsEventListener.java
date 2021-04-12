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

package com.ares.system.listener;

import com.ares.core.listener.DefaultEventListener;
import com.ares.core.persistence.model.listener.ElasticsearchEvent;
import com.ares.core.utils.SpringUtils;
import com.ares.message.persistence.dao.AresDocumentRepository;
import com.ares.message.persistence.model.AresDocument;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 将数据同步至elasticsearch
 * @author: Young
 * @date: 2021/01/29
 * @see: com.ares.system.listener EsEventListener.java
 **/
@Slf4j
public class EsEventListener extends DefaultEventListener<ElasticsearchEvent> {

    @Override
    public void onApplicationEvent(ElasticsearchEvent event) {
        try {
            AresDocument aresDocument = (AresDocument) event.getSource();
            AresDocumentRepository repository = SpringUtils.getBean(AresDocumentRepository.class);
            repository.save(aresDocument);
        } catch (Exception e) {
            log.error("sync data to elasticsearch error:{}", e);
        }
    }
}

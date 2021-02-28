package com.ares.system.listener;

import com.ares.core.listener.DefaultEventListener;
import com.ares.core.persistence.model.listener.ElasticsearchEvent;
import com.ares.core.utils.SpringUtils;
import com.ares.message.persistence.dao.AresDocumentRepository;
import com.ares.message.persistence.model.AresDocument;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 将数据同步至elasticsearch
 * @author: yy
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

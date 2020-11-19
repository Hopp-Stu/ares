package com.ares.message.dao;

import com.ares.message.model.AresDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @description:
 * @author: yy
 * @date: 2020/11/11
 * @see: com.ares.message.dao ESDemoRepository.java
 **/
public interface AresDocumentRepository extends ElasticsearchRepository<AresDocument, String> {
    Page<AresDocument> findByContentIsContaining(String content, Pageable pageable);

    Page<AresDocument> findByBodyIsContaining(String body, Pageable pageable);

    Page<AresDocument> findByNameLikeOrKeyIsLike(String name, String key, Pageable pageable);

    Page<AresDocument> findByContentIsContainingOrBodyIsContaining(String name, String key, Pageable pageable);
}

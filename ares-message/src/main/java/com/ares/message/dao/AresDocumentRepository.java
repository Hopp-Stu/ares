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
    /**
     *  在内容中模糊查询
     * @param content
     * @param pageable
     * @return
     */
    Page<AresDocument> findByContentIsContaining(String content, Pageable pageable);

    /**
     * 在body中模糊查询
     * @param body
     * @param pageable
     * @return
     */
    Page<AresDocument> findByBodyIsContaining(String body, Pageable pageable);

    /**
     * 根据名称查询
     * @param name
     * @param key
     * @param pageable
     * @return
     */
    Page<AresDocument> findByNameLikeOrKeyIsLike(String name, String key, Pageable pageable);

    /**
     * 根据内容或body查询
     * @param name
     * @param key
     * @param pageable
     * @return
     */
    Page<AresDocument> findByContentIsContainingOrBodyIsContaining(String name, String key, Pageable pageable);
}

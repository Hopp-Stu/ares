package com.ares.neo4j.service;


import com.ares.neo4j.dao.INeo4jCommonDao;
import com.ares.neo4j.model.Neo4jCommon;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: yy
 * @date: 2020/07/10
 * @see: com.system.springbootv1.project.service Neo4jCommonService.java
 **/
@Service
public class Neo4jCommonService {

    @Resource
    INeo4jCommonDao neo4jCommonDao;

    public void test() {
        Neo4jCommon book = new Neo4jCommon();
        book.setName("11");
        book.setAuthor("11");
        neo4jCommonDao.add(book);
    }

    public void create(Neo4jCommon common){
        neo4jCommonDao.create(common);
    }

}

package com.ares.neo4j.persistence.service;


import com.ares.neo4j.persistence.repository.DepartmentGraphRepository;
import com.ares.neo4j.persistence.dao.INeo4jCommonDao;
import com.ares.neo4j.persistence.repository.RelationShipRepository;
import com.ares.neo4j.persistence.repository.UserGraphRepository;
import com.ares.neo4j.persistence.model.DepartmentGraph;
import com.ares.neo4j.persistence.model.Neo4jCommon;
import com.ares.neo4j.persistence.model.RelationShip;
import com.ares.neo4j.persistence.model.UserGraph;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserGraphRepository userGraphRepository;
    @Autowired
    DepartmentGraphRepository departmentGraphRepository;
    @Autowired
    RelationShipRepository relationShipRepository;

    public void test() {
        Neo4jCommon book = new Neo4jCommon();
        book.setName("11");
        book.setAuthor("11");
        neo4jCommonDao.add(book);
    }

    public void create(Neo4jCommon common) {
        neo4jCommonDao.create(common);
    }


    public void user() {
        String username = "张三";
        String department = "财务";
        String post = "经理";

        UserGraph userGraph = UserGraph.builder().name(username).build();
        userGraphRepository.save(userGraph);

        DepartmentGraph departmentGraph = DepartmentGraph.builder().name(department).build();
        departmentGraphRepository.save(departmentGraph);

        RelationShip relationShip = RelationShip.builder().userGraph(userGraph).departmentGraph(departmentGraph).post(post).indexName(department + " " + username).build();
        relationShipRepository.save(relationShip);
    }
}

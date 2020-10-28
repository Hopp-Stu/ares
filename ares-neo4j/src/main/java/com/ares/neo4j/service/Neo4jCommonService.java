package com.ares.neo4j.service;


import com.ares.neo4j.repository.DepartmentGraphRepository;
import com.ares.neo4j.dao.INeo4jCommonDao;
import com.ares.neo4j.repository.RelationShipRepository;
import com.ares.neo4j.repository.UserGraphRepository;
import com.ares.neo4j.model.DepartmentGraph;
import com.ares.neo4j.model.Neo4jCommon;
import com.ares.neo4j.model.RelationShip;
import com.ares.neo4j.model.UserGraph;
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

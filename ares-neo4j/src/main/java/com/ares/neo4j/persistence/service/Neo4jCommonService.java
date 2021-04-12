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
 * @author: Young
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

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

package com.ares.neo4j.persistence.dao;


import com.ares.core.common.datasource.DataSource;
import com.ares.core.common.datasource.DataSourceType;
import com.ares.neo4j.persistence.model.Neo4jCommon;
import org.apache.ibatis.annotations.Param;


/**
 * @description:
 * @author: Young
 * @date: 2020/07/10
 * @see: com.system.springbootv1.project.dao INeo4jCommonDao.java
 **/
public interface INeo4jCommonDao {

    @DataSource(DataSourceType.NEO4J)
    int add(@Param("model") Neo4jCommon common);

    @DataSource(DataSourceType.NEO4J)
    int create(Neo4jCommon common);

    @DataSource(DataSourceType.NEO4J)
    int createRelationShip(Neo4jCommon common);

}

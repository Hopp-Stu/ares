package com.ares.neo4j.persistence.dao;


import com.ares.core.common.datasource.DataSource;
import com.ares.core.common.datasource.DataSourceType;
import com.ares.neo4j.persistence.model.Neo4jCommon;
import org.apache.ibatis.annotations.Param;


/**
 * @description:
 * @author: yy
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

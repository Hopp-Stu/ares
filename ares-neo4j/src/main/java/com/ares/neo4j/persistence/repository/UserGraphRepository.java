package com.ares.neo4j.persistence.repository;

import com.ares.neo4j.persistence.model.UserGraph;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/28
 * @see: com.ares.neo4j.persistence.dao UserGraphRepository.java
 **/
@Repository
public interface UserGraphRepository extends Neo4jRepository<UserGraph,Long> {
}

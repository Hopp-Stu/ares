package com.ares.neo4j.repository;

import com.ares.neo4j.model.RelationShip;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/28
 * @see: com.ares.neo4j.dao RelationShipRepository.java
 **/
@Repository
public interface RelationShipRepository extends Neo4jRepository<RelationShip, Long> {
}

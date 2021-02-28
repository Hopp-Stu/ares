package com.ares.neo4j.persistence.model;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/28
 * @see: com.ares.neo4j.persistence.model RelationShip.java
 **/
@Data
@Builder
@RelationshipEntity(type = "in")
public class RelationShip {

    @Id
    @GeneratedValue
    private Long id;

    private String indexName;

    @StartNode
    private UserGraph userGraph;

    @EndNode
    private DepartmentGraph departmentGraph;

    private String post;
}

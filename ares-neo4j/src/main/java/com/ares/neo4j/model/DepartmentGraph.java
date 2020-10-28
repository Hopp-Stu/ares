package com.ares.neo4j.model;

import lombok.Builder;
import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/28
 * @see: com.ares.neo4j.model DepartmentGraph.java
 **/
@NodeEntity
@Builder
@Data
public class DepartmentGraph {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}

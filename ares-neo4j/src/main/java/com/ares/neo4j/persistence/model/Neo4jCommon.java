package com.ares.neo4j.persistence.model;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: yy
 * @date: 2020/07/10
 * @see: com.system.springbootv1.project.model.neo4j Noe4jCommon.java
 **/
@Data
public class Neo4jCommon {

    private String nodeName;
    private List<String> labelName;
    private List<String> propertyName;
    private String relationshipLabelName;
    private String relationshipName;

    private String nodeLabelName1;
    private String nodeName1;
    private String nodeLabelName2;
    private String nodeName2;

    private String name;
    private String author;
}

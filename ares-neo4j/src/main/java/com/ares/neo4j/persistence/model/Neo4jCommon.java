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

package com.ares.neo4j.persistence.model;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: Young
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

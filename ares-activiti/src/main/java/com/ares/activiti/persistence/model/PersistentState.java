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

package com.ares.activiti.persistence.model;

import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: Young
 * @date: 2020/08/28
 * @see: com.ares.activiti.persistence.model PersistentState.java
 **/
@Data
public class PersistentState {
    private String category;
    private Date createTime;
    private String deploymentId;
    private String editorSourceExtraValueId;
    private String editorSourceValueId;
    private String key;
    private Date lastUpdateTime;
    private String metaInfo;
    private String name;
    private int version;
}

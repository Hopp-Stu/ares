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
import org.activiti.engine.impl.persistence.entity.ModelEntityImpl;

/**
 * @description:
 * @author: Young
 * @date: 2020/08/27
 * @see: com.ares.activiti.persistence.model NewModel.java
 **/
@Data
public class CommonModel extends ModelEntityImpl {
    private String desc;
    private int revisionNext;
    private PersistentState persistentState;

}

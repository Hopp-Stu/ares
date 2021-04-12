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

package com.ares.activiti.controller;

import cn.hutool.core.util.StrUtil;
import org.activiti.engine.ActivitiException;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

/**
 * @description:
 * @author: Young
 * @date: 2020/08/26
 * @see: com.ares.activiti.controller StencilsetController.java
 **/
@RestController
@RequestMapping("/editor/*")
public class StencilsetController {

    @GetMapping("stencilset")
    public String getStencilset() {
        String path = StencilsetController.class.getResource("/").getPath();
        path = StrUtil.sub(path, 1, path.indexOf("/target"));
        String jsonPath = path.replace("ares-system-new", "ares-activiti") + "/src/main/java/com/ares/activiti/common/json";

        try {
            InputStream inputStream = new FileInputStream(jsonPath + "/stencilset.json");
            return IOUtils.toString(Objects.requireNonNull(inputStream), "UTF-8");
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
        }
    }
}

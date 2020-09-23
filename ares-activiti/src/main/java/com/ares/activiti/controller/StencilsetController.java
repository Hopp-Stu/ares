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
 * @author: yy
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
        String jsonPath = path.replace("ares-system", "ares-activiti") + "/src/main/java/com/ares/activiti/common/json";

        try {
            InputStream inputStream = new FileInputStream(jsonPath + "/stencilset.json");
            return IOUtils.toString(Objects.requireNonNull(inputStream), "UTF-8");
        } catch (Exception e) {
            throw new ActivitiException("Error while loading stencil set", e);
        }
    }
}

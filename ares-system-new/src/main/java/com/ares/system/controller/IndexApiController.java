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

package com.ares.system.controller;

import com.ares.core.persistence.model.base.AjaxResult;
import com.ares.system.persistence.service.IndexService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 主页
 * @author: Young
 * @date: 2020/09/14
 * @see: com.ares.system.controller IndexApiController.java
 **/
@Slf4j
@RestController
@RequestMapping("/index/*")
@Api(value = "index",tags = {"index"})
public class IndexApiController {

    @Resource
    private IndexService indexService;

    @GetMapping("panelGroup")
    public Object getPanelGroup(){
        return AjaxResult.successData(indexService.getPanelGroup());
    }

    @GetMapping("lineChartData")
    public Object getLineChartData(){
        return AjaxResult.successData(indexService.getLineChartData());
    }

    @GetMapping("lineChart")
    public Object getLineChart(){
        return AjaxResult.successData(indexService.getLineChart());
    }
}

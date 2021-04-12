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
import com.ares.core.persistence.model.server.Server;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 工具
 * @author: Young 2020/05/06
 **/
@Controller
@RequestMapping("/")
@Api(value = "通用工具API", tags = {"通用工具"})
public class ToolApiController {

    @RequestMapping("monitor/druid/index")
    @ApiOperation(value = "druid监控", response = String.class)
    public String druid() {
        return "redirect:/druid";
    }

    @RequestMapping("monitor/actuator/index")
    @ApiOperation(value = "actuator监控", response = String.class)
    public String actuator() {
        return "redirect:/actuator";
    }

    @RequestMapping("monitor/server")
    @ResponseBody
    @ApiOperation(value = "服务器信息", response = Object.class)
    public Object server() throws Exception {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.successData(server);
    }

    @RequestMapping("tool/swagger/index")
    @ApiOperation(value = "swagger接口", response = String.class)
    public String swagger() {
        return "redirect:/swagger-ui.html";
    }

    @RequestMapping("tool/knife4j/index")
    @ApiOperation(value = "knife4j接口", response = String.class)
    public String knife4j() {
        return "redirect:/doc.html";
    }

}

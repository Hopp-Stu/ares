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


import com.ares.core.controller.BaseController;
import com.ares.core.persistence.model.system.SysProperties;
import com.ares.core.persistence.model.base.AjaxResult;
import com.ares.core.persistence.model.page.TableDataInfo;
import com.ares.core.persistence.service.SysPropertiesService;
import com.ares.core.utils.StringUtils;
import com.ares.system.common.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/sysProperties/*")
@Api(value = "系统参数API",tags = {"系统参数"})
public class SysPropertiesApiController extends BaseController {

    @Resource
    SysPropertiesService sysPropertiesService;


    @RequiresPermissions("sysProperties:list")
    @RequestMapping("list")
    @ApiOperation(value = "系统参数列表",response = TableDataInfo.class)
    public TableDataInfo list(SysProperties sysProperties) {
        startPage();
        List<SysProperties> sysPropertiesList = sysPropertiesService.list(sysProperties);
        return getDataTable(sysPropertiesList);
    }

    @GetMapping("{sysPropertiesId}")
    @ApiOperation(value = "根据参数Id获取系统参数",response = Object.class)
    public Object getInfo(@PathVariable String sysPropertiesId) {
        return AjaxResult.successData(sysPropertiesService.getById(sysPropertiesId));
    }

    @RequiresPermissions("sysProperties:edit")
    @PostMapping("edit")
    @ApiOperation(value = "新增/修改系统参数",response = Object.class)
    public Object edit(@Validated @RequestBody SysProperties sysProperties) throws Exception {
        if (StringUtils.isEmpty(sysProperties.getId())) {
            sysProperties.setCreator(ShiroUtils.getUserId());
            sysPropertiesService.insert(sysProperties);
        } else {
            sysProperties.setModifier(ShiroUtils.getUserId());
            sysPropertiesService.update(sysProperties);
        }
        return AjaxResult.success();
    }

    @RequiresPermissions("sysProperties:delete")
    @DeleteMapping("{sysPropertiesIds}")
    @ApiOperation(value = "删除系统参数",response = Object.class)
    public Object remove(@PathVariable String[] sysPropertiesIds) {
        sysPropertiesService.deleteByIds(Arrays.asList(sysPropertiesIds));
        return AjaxResult.success();
    }
}

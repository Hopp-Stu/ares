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
import com.ares.core.persistence.model.base.AjaxResult;
import ${entityPackage}.${entityName};
import com.ares.core.persistence.model.page.TableDataInfo;
import ${servicePackage}.${entityName}Service;
import com.ares.core.utils.StringUtils;
import com.ares.system.common.security.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/${entityName1}/*")
@Api(value = "API", tags = {"管理"})
public class ${entityName}ApiController extends BaseController {

    @Autowired
    private ${entityName}Service ${entityName1}Service;


    @PreAuthorize("hasAnyAuthority('${entityName1}:list')")
    @RequestMapping("list")
    @ApiOperation(value = "列表", response = TableDataInfo.class)
    public TableDataInfo list(${entityName} ${entityName1}) {
        startPage();
        List<${entityName}> ${entityName1}List = ${entityName1}Service.list(${entityName1});
        return getDataTable(${entityName1}List);
    }

    @GetMapping("{${entityName1}Id}")
    @ApiOperation(value = "根据Id获取信息", response = Object.class)
    public Object getInfo(@PathVariable String ${entityName1}Id) {
        return AjaxResult.successData(${entityName1}Service.getById(${entityName1}Id));
    }

    @PreAuthorize("hasAnyAuthority('${entityName1}:edit')")
    @PostMapping("edit")
    @ApiOperation(value = "编辑信息", response = Object.class)
    public Object edit(@Validated @RequestBody ${entityName} ${entityName1}) throws Exception{
        if (StringUtils.isEmpty(${entityName1}.getId())) {
            ${entityName1}.setCreator(SecurityUtils.getUser().getId());
            ${entityName1}Service.insert(${entityName1});
        } else {
            ${entityName1}.setModifier(SecurityUtils.getUser().getId());
            ${entityName1}Service.update(${entityName1});
        }
        return AjaxResult.success();
    }

    @PreAuthorize("hasAnyAuthority('${entityName1}:delete"'))
    @DeleteMapping("{${entityName1}Ids}")
    @ApiOperation(value = "删除信息", response = Object.class)
    public Object remove(@PathVariable String[] ${entityName1}Ids) {
        ${entityName1}Service.deleteByIds(Arrays.asList(${entityName1}Ids));
        return AjaxResult.success();
    }
}

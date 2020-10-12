package com.ares.system.controller;

import com.ares.core.controller.BaseController;
import com.ares.core.model.base.BaseResult;
import ${entityPackage}.${entityName};
import com.ares.core.model.page.TableDataInfo;
import ${servicePackage}.${entityName}Service;
import com.ares.core.utils.StringUtils;
import com.ares.system.common.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/${entityName1}/*")
@Api(value = "API", tags = {""})
public class ${entityName}ApiController extends BaseController {

    @Resource
    ${entityName}Service ${entityName1}Service;


    @RequiresPermissions("${entityName1}:list")
    @RequestMapping("list")
    @ApiOperation(value = "", response = TableDataInfo.class)
    public TableDataInfo list(${entityName} ${entityName1}) {
        startPage();
        List<${entityName}> ${entityName1}List = ${entityName1}Service.list(${entityName1});
        return getDataTable(${entityName1}List);
    }

    @GetMapping("{${entityName1}Id}")
    @ApiOperation(value = "", response = Object.class)
    public Object getInfo(@PathVariable String ${entityName1}Id) {
        return BaseResult.successData(${entityName1}Service.getById(${entityName1}Id));
    }

    @RequiresPermissions("${entityName1}:edit")
    @PostMapping("edit")
    @ApiOperation(value = "", response = Object.class)
    public Object edit(@Validated @RequestBody ${entityName} ${entityName1}) throws Exception{
        if (StringUtils.isEmpty(${entityName1}.getId())) {
            ${entityName1}.setCreator(ShiroUtils.getUserId());
            ${entityName1}Service.insert(${entityName1});
        } else {
            ${entityName1}.setModifier(ShiroUtils.getUserId());
            ${entityName1}Service.update(${entityName1});
        }
        return BaseResult.success();
    }

    @RequiresPermissions("${entityName1}:delete")
    @DeleteMapping("{${entityName1}Ids}")
    @ApiOperation(value = "", response = Object.class)
    public Object remove(@PathVariable String[] ${entityName1}Ids) {
        ${entityName1}Service.deleteByIds(Arrays.asList(${entityName1}Ids));
        return BaseResult.success();
    }
}

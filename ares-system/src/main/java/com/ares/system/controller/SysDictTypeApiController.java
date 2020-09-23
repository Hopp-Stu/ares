package com.ares.system.controller;

import com.ares.core.controller.BaseController;
import com.ares.core.model.SysDictType;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.page.TableDataInfo;
import com.ares.core.service.SysDictTypeService;
import com.ares.core.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/system/dict/type/*")
@Api(value = "字典类别API", tags = {"字典类别"})
public class SysDictTypeApiController extends BaseController {

    @Resource
    SysDictTypeService sysDictTypeService;


    @RequiresPermissions("sysDictType:list")
    @RequestMapping("list")
    @ApiOperation(value = "字典类别列表", response = TableDataInfo.class)
    public TableDataInfo list(SysDictType sysDictType) {
        startPage();
        List<SysDictType> sysDictTypeList = sysDictTypeService.list(sysDictType);
        return getDataTable(sysDictTypeList);
    }

    @GetMapping("{sysDictTypeId}")
    @ApiOperation(value = "根据Id获取字典类别", response = Object.class)
    public Object getInfo(@PathVariable String sysDictTypeId) {
        return BaseResult.successData(sysDictTypeService.getById(sysDictTypeId));
    }

    @RequiresPermissions("sysDictType:edit")
    @PostMapping("edit")
    @ApiOperation(value = "编辑字典类别", response = Object.class)
    public Object edit(@Validated @RequestBody SysDictType sysDictType) {
        if (StringUtils.isEmpty(sysDictType.getId())) {
            sysDictTypeService.insert(sysDictType);
        } else {
            sysDictTypeService.update(sysDictType);
        }
        return BaseResult.success();
    }

    @RequiresPermissions("sysDictType:delete")
    @DeleteMapping("{sysDictTypeIds}")
    @ApiOperation(value = "删除字典类别", response = Object.class)
    public Object remove(@PathVariable String[] sysDictTypeIds) {
        sysDictTypeService.deleteByIds(Arrays.asList(sysDictTypeIds));
        return BaseResult.success();
    }

}

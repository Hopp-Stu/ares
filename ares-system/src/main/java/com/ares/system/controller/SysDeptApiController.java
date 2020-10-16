package com.ares.system.controller;

import com.ares.core.controller.BaseController;
import com.ares.core.model.SysDept;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.page.TableDataInfo;
import com.ares.core.service.SysDeptService;
import com.ares.core.utils.StringUtils;
import com.ares.system.common.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/sysDept/*")
@Api(value = "API", tags = {""})
public class SysDeptApiController extends BaseController {

    @Resource
    SysDeptService sysDeptService;

    @RequiresPermissions("sysDept:list")
    @RequestMapping("list")
    @ApiOperation(value = "", response = TableDataInfo.class)
    public TableDataInfo list(SysDept sysDept) {
        startPage();
        List<SysDept> sysDeptList = sysDeptService.list(sysDept);
        return getDataTable(sysDeptList);
    }

    @GetMapping("{sysDeptId}")
    @ApiOperation(value = "", response = Object.class)
    public Object getInfo(@PathVariable String sysDeptId) {
        return BaseResult.successData(sysDeptService.getByDeptId(sysDeptId));
    }

    //@RequiresPermissions("sysDept:edit")
    @PostMapping("edit")
    @ApiOperation(value = "", response = Object.class)
    public Object edit(@Validated @RequestBody SysDept sysDept) throws Exception{
        if (StringUtils.isEmpty(sysDept.getId())) {
            sysDept.setCreator(ShiroUtils.getUserId());
            sysDeptService.insert(sysDept);
        } else {
            sysDept.setModifier(ShiroUtils.getUserId());
            sysDeptService.update(sysDept);
        }
        return BaseResult.success();
    }

    //@RequiresPermissions("sysDept:delete")
    @DeleteMapping("{sysDeptIds}")
    @ApiOperation(value = "", response = Object.class)
    public Object remove(@PathVariable String[] sysDeptIds) {
        sysDeptService.deleteByIds(Arrays.asList(sysDeptIds));
        return BaseResult.success();
    }

    @RequestMapping("treeselect")
    @ApiOperation(value = "", response = Object.class)
    public Object tree(HttpServletRequest request, HttpServletResponse response) {
        return BaseResult.successData(sysDeptService.buildDeptTree());
    }
}

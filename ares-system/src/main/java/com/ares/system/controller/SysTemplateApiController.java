package com.ares.system.controller;


import com.ares.core.controller.BaseController;
import com.ares.core.model.SysTemplate;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.page.TableDataInfo;
import com.ares.core.service.SysTemplateService;
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
@RequestMapping("/sysTemplate/*")
@Api(value = "系统模版API",tags = {"系统模版"})
public class SysTemplateApiController extends BaseController {

    @Resource
    SysTemplateService sysTemplateService;

    @RequiresPermissions("sysTemplate:list")
    @RequestMapping("list")
    @ApiOperation(value = "模版列表",response = TableDataInfo.class)
    public TableDataInfo list(SysTemplate sysTemplate) {
        startPage();
        List<SysTemplate> sysTemplateList = sysTemplateService.list(sysTemplate);
        return getDataTable(sysTemplateList);
    }

    @GetMapping("{sysTemplateId}")
    @ApiOperation(value = "根据模版Id获取模版",response = Object.class)
    public Object getInfo(@PathVariable String sysTemplateId) {
        return BaseResult.successData(sysTemplateService.getById(sysTemplateId));
    }

    @RequiresPermissions("sysTemplate:edit")
    @PostMapping("edit")
    @ApiOperation(value = "新增/修改模版",response = Object.class)
    public Object edit(@Validated @RequestBody SysTemplate sysTemplate) throws Exception {
        if (StringUtils.isEmpty(sysTemplate.getId())) {
            sysTemplate.setCreator(ShiroUtils.getUserId());
            sysTemplateService.insert(sysTemplate);
        } else {
            sysTemplate.setModifier(ShiroUtils.getUserId());
            sysTemplateService.update(sysTemplate);
        }
        return BaseResult.success();
    }

    @RequiresPermissions("sysTemplate:delete")
    @DeleteMapping("{sysTemplateIds}")
    @ApiOperation(value = "删除模版",response = Object.class)
    public Object remove(@PathVariable String[] sysTemplateIds) {
        sysTemplateService.deleteByIds(Arrays.asList(sysTemplateIds));
        return BaseResult.success();
    }
}

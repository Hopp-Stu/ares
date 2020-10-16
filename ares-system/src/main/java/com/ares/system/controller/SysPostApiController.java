package com.ares.system.controller;

import com.ares.core.controller.BaseController;
import com.ares.core.model.SysPost;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.page.TableDataInfo;
import com.ares.core.service.SysPostService;
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
@RequestMapping("/sysPost/*")
@Api(value = "API", tags = {""})
public class SysPostApiController extends BaseController {

    @Resource
    SysPostService sysPostService;


    @RequiresPermissions("sysPost:list")
    @RequestMapping("list")
    @ApiOperation(value = "", response = TableDataInfo.class)
    public TableDataInfo list(SysPost sysPost) {
        startPage();
        List<SysPost> sysPostList = sysPostService.list(sysPost);
        return getDataTable(sysPostList);
    }

    @GetMapping("{sysPostId}")
    @ApiOperation(value = "", response = Object.class)
    public Object getInfo(@PathVariable String sysPostId) {
        return BaseResult.successData(sysPostService.getById(sysPostId));
    }

    @RequiresPermissions("sysPost:edit")
    @PostMapping("edit")
    @ApiOperation(value = "", response = Object.class)
    public Object edit(@Validated @RequestBody SysPost sysPost) throws Exception {
        if (StringUtils.isEmpty(sysPost.getId())) {
            sysPost.setCreator(ShiroUtils.getUserId());
            sysPostService.insert(sysPost);
        } else {
            sysPost.setModifier(ShiroUtils.getUserId());
            sysPostService.update(sysPost);
        }
        return BaseResult.success();
    }

    @RequiresPermissions("sysPost:delete")
    @DeleteMapping("{sysPostIds}")
    @ApiOperation(value = "", response = Object.class)
    public Object remove(@PathVariable String[] sysPostIds) {
        sysPostService.deleteByIds(Arrays.asList(sysPostIds));
        return BaseResult.success();
    }

    @GetMapping("optionselect")
    @ApiOperation(value = "角色下拉选项", response = Object.class)
    public Object optionselect() {
        return BaseResult.successData(sysPostService.getAll());
    }
}

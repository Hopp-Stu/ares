package com.ares.system.controller;

import com.ares.core.controller.BaseController;
import com.ares.core.persistence.model.system.SysNotice;
import com.ares.core.persistence.model.base.AjaxResult;
import com.ares.core.persistence.model.page.TableDataInfo;
import com.ares.core.persistence.service.SysNoticeService;
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
@RequestMapping("/system/notice/*")
@Api(value = "通知公告API", tags = {"通知公告"})
public class SysNoticeApiController extends BaseController {

    @Resource
    SysNoticeService sysNoticeService;


    @RequiresPermissions("notice:list")
    @RequestMapping("list")
    @ApiOperation(value = "通知公告列表", response = TableDataInfo.class)
    public TableDataInfo list(SysNotice sysNotice) {
        startPage();
        List<SysNotice> sysNoticeList = sysNoticeService.list(sysNotice);
        return getDataTable(sysNoticeList);
    }

    @GetMapping("{noticeId}")
    @ApiOperation(value = "根据Id获取通知公告", response = Object.class)
    public Object getInfo(@PathVariable String noticeId) {
        return AjaxResult.successData(sysNoticeService.getById(noticeId));
    }

    @RequiresPermissions("notice:edit")
    @PostMapping("edit")
    @ApiOperation(value = "新增/修改通知公告", response = Object.class)
    public Object edit(@Validated @RequestBody SysNotice sysNotice) throws Exception {
        if (StringUtils.isEmpty(sysNotice.getId())) {
            sysNotice.setCreator(ShiroUtils.getUserId());
            sysNoticeService.insert(sysNotice);
        } else {
            sysNotice.setModifier(ShiroUtils.getUserId());
            sysNoticeService.update(sysNotice);
        }
        return AjaxResult.success();
    }

    @RequiresPermissions("notice:delete")
    @DeleteMapping("{noticeIds}")
    @ApiOperation(value = "删除通知公告", response = Object.class)
    public Object remove(@PathVariable String[] noticeIds) {
        sysNoticeService.deleteByIds(Arrays.asList(noticeIds));
        return AjaxResult.success();
    }

    @GetMapping("noticeNum")
    @ApiOperation(value = "获取通知公告数量", response = Object.class)
    public Object noticeNum() throws Exception {
        return AjaxResult.successData(sysNoticeService.noticeNum(ShiroUtils.getUserId()));
    }

    @GetMapping("getNotices")
    @ApiOperation(value = "通知公告时间线", response = Object.class)
    public Object getNotices() throws Exception {
        return AjaxResult.successData(sysNoticeService.getNotices(ShiroUtils.getUserId()));
    }
}

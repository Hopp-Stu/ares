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
import com.ares.core.persistence.model.system.SysUser;
import com.ares.core.persistence.model.base.AjaxResult;
import com.ares.core.persistence.model.page.TableDataInfo;
import com.ares.core.persistence.service.SysPostService;
import com.ares.core.persistence.service.SysRoleService;
import com.ares.core.persistence.service.SysUserService;
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

/**
 * @description:
 * @author: Young 2020/05/05
 **/
@RestController
@RequestMapping("/system/user/*")
@Api(value = "系统用户API", tags = {"系统用户"})
public class SysUserApiController extends BaseController {

    @Resource
    SysUserService userService;
    @Resource
    SysRoleService roleService;
    @Resource
    SysPostService postService;

    @RequiresPermissions("user:list")
    @RequestMapping("list")
    @ApiOperation(value = "用户列表", response = TableDataInfo.class)
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> userList = userService.selectUserList(user);
        return getDataTable(userList);
    }

    @GetMapping(value = {"", "{userId}"})
    @ApiOperation(value = "根据用户Id获取用户", response = Object.class)
    public Object getInfo(@PathVariable(value = "userId", required = false) String userId) {
        AjaxResult result = new AjaxResult();
        result.put("code", HttpStatus.OK.value());
        result.put("data", userService.getById(userId));
        result.put("roles", roleService.getAll());
        result.put("posts", postService.getAll());
        if (StringUtils.isNotEmpty(userId)) {
            result.put("roleIds", roleService.getRoleIdsByUser(userId));
        }
        return result;
    }

    @RequiresPermissions("user:edit")
    @PostMapping("edit")
    @ApiOperation(value = "新增/修改用户", response = Object.class)
    public Object edit(@Validated @RequestBody SysUser user) throws Exception {
        String userId = "";
        if (StringUtils.isEmpty(user.getId())) {
            if (userService.checkAccount(user.getAccount()) != 0) {
                return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
            }
            user.setCreator(ShiroUtils.getUserId());
            userId = userService.insertUser(user);
        } else {
            userId = user.getId();
            user.setModifier(ShiroUtils.getUserId());
            userService.update(user);
        }
        if (StringUtils.isNotEmpty(user.getRoleIds())) {
            roleService.saveRoleUser(user.getRoleIds(), userId);
        }
        return AjaxResult.success();
    }

    @RequiresPermissions("user:delete")
    @DeleteMapping("{userIds}")
    @ApiOperation(value = "删除用户", response = Object.class)
    public Object remove(@PathVariable String[] userIds) {
        userService.deleteByIds(Arrays.asList(userIds));
        return AjaxResult.success();
    }

    /**
     * 重置密码
     */
    @PutMapping("resetPwd")
    @ApiOperation(value = "重置密码", response = Object.class)
    public Object resetPwd(@RequestBody SysUser user) {
        userService.resetPassword(user.getId());
        return AjaxResult.success();
    }
}

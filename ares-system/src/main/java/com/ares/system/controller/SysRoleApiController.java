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
import com.ares.core.persistence.model.system.SysRole;
import com.ares.core.persistence.model.system.SysUser;
import com.ares.core.persistence.model.base.AjaxResult;
import com.ares.core.persistence.model.page.TableDataInfo;
import com.ares.core.persistence.service.SysRoleService;
import com.ares.core.persistence.service.SysUserService;
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

/**
 * @description:
 * @author: Young 2020/05/06
 **/
@RestController
@RequestMapping("/system/role/*")
@Api(value = "系统角色API", tags = {"系统角色"})
public class SysRoleApiController extends BaseController {
    @Resource
    SysRoleService roleService;
    @Resource
    SysUserService userService;

    @RequiresPermissions("role:list")
    @RequestMapping("list")
    @ApiOperation(value = "角色列表", response = TableDataInfo.class)
    public TableDataInfo list(SysRole role) {
        startPage();
        List<SysRole> roleList = roleService.selectRoleList(role);
        return getDataTable(roleList);
    }

    @GetMapping("{roleId}")
    @ApiOperation(value = "根据角色Id获取用户", response = Object.class)
    public Object getInfo(@PathVariable String roleId) {
        return AjaxResult.successData(roleService.getById(roleId));
    }

    @RequiresPermissions("role:edit")
    @PostMapping("edit")
    @ApiOperation(value = "新增/修改角色", response = Object.class)
    public Object edit(@Validated @RequestBody SysRole role) throws Exception {
        String roleId = "";
        if (StringUtils.isEmpty(role.getId())) {
            if (roleService.checkRoleName(role.getRoleName())) {
                return AjaxResult.error("新增角色'" + role.getRoleName() + "'失败，角色名称已存在");
            }
            role.setCreator(ShiroUtils.getUserId());
            roleId = roleService.insertRole(role);
        } else {
            roleId = role.getId();
            role.setModifier(ShiroUtils.getUserId());
            roleService.updateRole(role);
        }

        return AjaxResult.success();
    }

    @RequiresPermissions("role:delete")
    @DeleteMapping("{roleIds}")
    @ApiOperation(value = "删除用户", response = Object.class)
    public Object remove(@PathVariable String[] roleIds) {
        roleService.deleteByIds(Arrays.asList(roleIds));
        return AjaxResult.success();
    }

    @PutMapping("dataScope")
    @ApiOperation(value = "角色权限分配", response = Object.class)
    public Object dataScope(@RequestBody SysRole role) {
        roleService.authDataScope(role);
        return AjaxResult.success();
    }

    @GetMapping("optionselect")
    @ApiOperation(value = "角色下拉选项", response = Object.class)
    public Object optionSelect() {
        return AjaxResult.successData(roleService.getAll());
    }

    @GetMapping("roleUserselect/{roleId}")
    @ApiOperation(value = "根据角色Id获取用户", response = Object.class)
    public Object roleUserselect(@PathVariable String roleId) {
        AjaxResult result = AjaxResult.success();
        result.put("allUser", userService.selectUserList(new SysUser()));
        result.put("checkedKeys", userService.getUserByRole(roleId));
        return result;
    }
}

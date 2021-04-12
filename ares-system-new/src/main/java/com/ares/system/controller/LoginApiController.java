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


import com.ares.config.base.BaseConfig;
import com.ares.core.persistence.model.system.SysMenu;
import com.ares.core.persistence.model.system.SysRole;
import com.ares.core.persistence.model.system.SysUser;
import com.ares.core.persistence.model.base.AjaxResult;
import com.ares.core.persistence.model.base.Constants;
import com.ares.core.persistence.service.SysMenuService;
import com.ares.core.persistence.service.SysRoleService;
import com.ares.core.persistence.service.SysUserService;
import com.ares.core.utils.ServletUtils;
import com.ares.redis.utils.RedisUtil;
import com.ares.system.common.jwt.JwtAuthenticationToken;
import com.ares.system.common.security.SecurityUtils;
import com.ares.system.utils.AresCommonUtils;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * @description: 登录
 * @author: Young 2020/05/04
 **/
@RestController
@Api(value = "系统登录API", tags = {"系统登录"})
public class LoginApiController {
    private Logger logger = LoggerFactory.getLogger(LoginApiController.class);

    private String prefix = "";
    // token 过期时间一个月
    private static final long EXPIRE = 30 * 24 * 60 * 60;

    @Resource
    private SysUserService userService;
    @Resource
    private SysRoleService roleService;
    @Resource
    private SysMenuService menuService;
    @Resource
    private BaseConfig config;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Producer producer;

    @ApiOperation(value = "登录", response = Object.class)
    @PostMapping("login")
    public Object login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = ServletUtils.getParameter();
        String userName = String.valueOf(map.get("username"));
        String password = String.valueOf(map.get("password"));
        String code = String.valueOf(map.get("code"));
        String uuid = String.valueOf(map.get("uuid"));
        Boolean rememberMe = Boolean.parseBoolean(String.valueOf(map.get("rememberMe")));

        if (!AresCommonUtils.checkVerifyCode(code, uuid)) {
            return AjaxResult.error(500, "验证码错误");
        }

        // 系统登录认证
        JwtAuthenticationToken token = SecurityUtils.login(request, userName, password, authenticationManager);
        if (rememberMe) {
            RedisUtil.set(Constants.LOGIN_INFO + userName, token, EXPIRE);
        } else {
            RedisUtil.set(Constants.LOGIN_INFO + userName, token, config.getTimeout());
        }
        return AjaxResult.success().put("token", token.getToken());
    }


    @RequestMapping("unAuth")
    @ApiOperation(value = "未登录", response = Object.class)
    public Object unAuth(HttpServletRequest request, HttpServletResponse response) {
        return AjaxResult.unLogin();
    }

    @RequestMapping("unauthorized")
    @ApiOperation(value = "无权限", response = Object.class)
    public Object unauthorized(HttpServletRequest request, HttpServletResponse response) {
        return AjaxResult.error(HttpStatus.UNAUTHORIZED.value(), "用户无权限！");
    }

    @RequestMapping("getInfo")
    @ApiOperation(value = "获取登录信息", response = Object.class)
    public Object getInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SysUser user = SecurityUtils.getUser();
        List<SysRole> roleList = roleService.getRoleByUserId(user.getId());
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        for (SysRole role : roleList) {
            List<String> perms = new ArrayList<>();
            if ("gly".equalsIgnoreCase(role.getRoleName())) {
                perms = roleService.getPermsByRoleId(null);
            } else {
                perms = roleService.getPermsByRoleId(role.getId());
            }
            for (String perm : perms) {
                permissions.add(perm);
            }
            roles.add(role.getRoleName());
        }
        return AjaxResult.success().put("user", user).put("roles", roles).put("permissions", permissions);
    }

    @RequestMapping("getRouters")
    @ApiOperation(value = "获取路由", response = Object.class)
    public Object getRouters() throws Exception {
        SysUser user = SecurityUtils.getUser();
        List<SysMenu> menus = menuService.getAll(user.getId());
        return AjaxResult.successData(HttpStatus.OK.value(), menuService.buildMenus(menus, "0"));
    }

    @RequestMapping("/kaptcha")
    public Object getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //生成验证码
        String capText = producer.createText();
        String uuid = UUID.randomUUID().toString();
        RedisUtil.set(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY + uuid, capText, 120);
        //向客户端写出
        BufferedImage bi = producer.createImage(capText);
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(bi, "jpg", byteArrayOutputStream);
            return AjaxResult.success().put("img", Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray())).put("uuid", uuid);
        }
    }
}

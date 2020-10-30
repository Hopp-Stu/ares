package com.ares.system.controller;


import com.ares.core.common.config.BaseConfig;
import com.ares.core.model.SysMenu;
import com.ares.core.model.SysRole;
import com.ares.core.model.SysUser;
import com.ares.core.model.base.BaseResult;
import com.ares.core.model.base.Constants;
import com.ares.core.service.SysMenuService;
import com.ares.core.service.SysRoleService;
import com.ares.core.service.SysUserService;
import com.ares.core.utils.MD5Util;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.*;

/**
 * @description:
 * @author: yy 2020/05/04
 **/
@Controller
@Api(value = "系统登录API", tags = {"系统登录"})
public class LoginApiController {
    private Logger logger = LoggerFactory.getLogger(LoginApiController.class);

    private String prefix = "";

    @Resource
    SysUserService userService;
    @Resource
    SysRoleService roleService;
    @Resource
    SysMenuService menuService;
    @Resource
    BaseConfig config;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private Producer producer;


    @ApiOperation(value = "登录", response = Object.class)
    @PostMapping("login")
    @ResponseBody
    public Object login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = ServletUtils.getParameter();
        String userName = String.valueOf(map.get("username"));
        String password = String.valueOf(map.get("password"));
        String code = String.valueOf(map.get("code"));
        String uuid = String.valueOf(map.get("uuid"));

        if (!AresCommonUtils.checkVerifyCode(code, uuid)) {
            return BaseResult.error(500, "验证码错误");
        }

        // 系统登录认证
        JwtAuthenticationToken token = SecurityUtils.login(request, userName, password, authenticationManager);

        SysUser user = userService.getUserByName(userName);
        if (MD5Util.encode(password).equals(user.getPassword())) {
            RedisUtil.set(token.getToken(), SecurityUtils.getUsername(), config.getTimeout());
            RedisUtil.set(Constants.LOGIN_INFO + userName, token, config.getTimeout());
            return BaseResult.success().put("token", token.getToken());
        } else {
            return BaseResult.error(500, "用户名或密码不正确");
        }
    }


    @RequestMapping("unAuth")
    @ResponseBody
    @ApiOperation(value = "未登录", response = Object.class)
    public Object unAuth(HttpServletRequest request, HttpServletResponse response) {
        return BaseResult.unLogin();
    }

    @RequestMapping("unauthorized")
    @ResponseBody
    @ApiOperation(value = "无权限", response = Object.class)
    public Object unauthorized(HttpServletRequest request, HttpServletResponse response) {
        return BaseResult.error(HttpStatus.UNAUTHORIZED.value(), "用户无权限！");
    }

    @RequestMapping("getInfo")
    @ResponseBody
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
        return BaseResult.success().put("user", user).put("roles", roles).put("permissions", permissions);
    }

    @RequestMapping("getRouters")
    @ResponseBody
    @ApiOperation(value = "获取路由", response = Object.class)
    public Object getRouters() throws Exception {
        SysUser user = SecurityUtils.getUser();
        List<SysMenu> menus = menuService.getAll(user.getId());
        return BaseResult.successData(HttpStatus.OK.value(), menuService.buildMenus(menus, "0"));
    }

    @RequestMapping("/kaptcha")
    @ResponseBody
    public Object getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //生成验证码
        String capText = producer.createText();
        String uuid = UUID.randomUUID().toString();
        RedisUtil.set(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY + uuid, capText, 120);
        //向客户端写出
        BufferedImage bi = producer.createImage(capText);
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            ImageIO.write(bi, "jpg", byteArrayOutputStream);
            return BaseResult.success().put("img", Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray())).put("uuid", uuid);
        }
    }
}

package com.ares.system.controller;


import com.ares.core.controller.BaseController;
import com.ares.core.model.SysUser;
import com.ares.core.model.base.BaseResult;
import com.ares.core.service.SysPropertiesService;
import com.ares.core.service.SysUserService;
import com.ares.core.service.UploadService;
import com.ares.core.utils.MD5Util;
import com.ares.system.common.shiro.ShiroUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 个人信息 业务处理
 */
@RestController
@RequestMapping("/system/user/profile/*")
@Api(value = "个人信息API", tags = {"个人信息"})
public class SysProfileApiController extends BaseController {
    @Autowired
    private SysUserService userService;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private SysPropertiesService propertiesService;

    /**
     * 个人信息
     */
    @GetMapping("info")
    @ApiOperation(value = "获取个人信息", response = Object.class)
    public Object profile() throws Exception {
        SysUser user = ShiroUtils.getUser();
        BaseResult result = BaseResult.successData(user);
        result.put("roleGroup", userService.selectUserRoleGroup(user.getId()));
        result.put("postGroup", "");
        return result;
    }

    /**
     * 修改用户
     */
    @PutMapping("update")
    @ApiOperation(value = "修改用户信息", response = Object.class)
    public Object updateProfile(@RequestBody SysUser user) {
        userService.update(user);
        ShiroUtils.setUser(user);
        return BaseResult.success();
    }

    /**
     * 重置密码
     */
    @PutMapping("updatePwd")
    @ApiOperation(value = "重置密码", response = Object.class)
    public Object updatePwd(String oldPassword, String newPassword) throws Exception {
        SysUser user = ShiroUtils.getUser();
        if (!user.getPassword().equals(MD5Util.encode(oldPassword))) {
            return BaseResult.error("修改密码失败，旧密码错误");
        }
        if (user.getPassword().equals(MD5Util.encode(newPassword))) {
            return BaseResult.error("新密码不能与旧密码相同");
        }
        if (userService.updatePassword(user, newPassword) > 0) {
            user.setPassword(MD5Util.encode(newPassword));
            ShiroUtils.setUser(user);
            // 更新缓存用户密码
            return BaseResult.success();
        }
        return BaseResult.error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @PostMapping("avatar")
    @ApiOperation(value = "头像上传", response = Object.class)
    public Object avatar(@RequestParam("avatarfile") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            SysUser user = ShiroUtils.getUser();
            String path = propertiesService.getValueByAlias("avatar.path");
            String avatar = uploadService.upload(path, file);
            user.setAvatar(avatar);
            userService.update(user);
            return BaseResult.success().put("imgUrl", avatar);
        }
        return BaseResult.error("上传图片异常，请联系管理员!");
    }
}

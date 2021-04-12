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

package com.ares.system.common.shiro;

import com.alibaba.fastjson.JSON;
import com.ares.core.common.config.BaseConfig;
import com.ares.core.persistence.dao.ISysRoleDao;
import com.ares.core.persistence.dao.ISysUserDao;
import com.ares.core.persistence.model.system.SysRole;
import com.ares.core.persistence.model.system.SysUser;
import com.ares.redis.utils.RedisUtil;
import com.ares.system.common.jwt.JwtToken;
import com.ares.system.common.jwt.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Young 2020/01/22
 **/
@Component
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    ISysUserDao sysUserDao;
    @Resource
    ISysRoleDao sysRoleDao;
    @Resource
    BaseConfig config;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser user = new SysUser();
        Object object = principalCollection.getPrimaryPrincipal();
        if (object instanceof SysUser) {
            user = (SysUser) object;
        } else {
            user = JSON.parseObject(JSON.toJSON(object).toString(), SysUser.class);
        }
        List<SysRole> roleList = sysRoleDao.getRoleByUserId(user.getId());
        for (SysRole role : roleList) {
            authorizationInfo.addRole(role.getRoleName());
            List<String> perms = new ArrayList<>();
            if ("gly".equalsIgnoreCase(role.getRoleName())) {
                perms = sysRoleDao.getPermsByRoleId(null);
            } else {
                perms = sysRoleDao.getPermsByRoleId(role.getId());
            }
            for (String perm : perms) {
                authorizationInfo.addStringPermission(perm);
            }
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = String.valueOf(authenticationToken.getCredentials());
        String userName = JwtUtil.getUsername(token);
        if (null == userName) {
            return null;
        }
        SysUser user = sysUserDao.getUserByName(userName);
        if (null == user) {
            return null;
        } else {
            RedisUtil.expire(token, config.getTimeout());
            return new SimpleAuthenticationInfo(user, token, getName());
        }
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}

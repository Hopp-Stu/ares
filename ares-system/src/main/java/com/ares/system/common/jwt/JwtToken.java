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

package com.ares.system.common.jwt;


import com.ares.core.persistence.model.system.SysUser;
import com.ares.core.persistence.service.SysUserService;
import com.ares.core.utils.SpringUtils;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @description:
 * @author: Young 2020/05/09
 **/
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        String userName = JwtUtil.getUsername(token);
        SysUserService userService = SpringUtils.getBean(SysUserService.class);
        SysUser user = userService.getUserByName(userName);
        return user;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}

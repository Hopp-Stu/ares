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

import com.ares.core.persistence.model.base.AjaxResult;
import com.ares.core.persistence.model.base.ResultCode;
import com.ares.system.common.security.SecurityUtils;
import com.ares.system.utils.AresCommonUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: Young
 * @date: 2020/10/19
 * @see: com.ares.system.common.jwt JwtAuthenticationFilter.java
 **/
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取token, 并检查登录状态
        try {
            SecurityUtils.checkAuthentication(request);
            chain.doFilter(request, response);
        } catch (Exception e) {
            AresCommonUtils.writeResponse(response, AjaxResult.unLogin(), ResultCode.NOLOGIN.getCode());
        }
    }
}

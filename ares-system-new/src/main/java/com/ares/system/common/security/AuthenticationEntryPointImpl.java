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

package com.ares.system.common.security;

import com.ares.core.persistence.model.base.AjaxResult;
import com.ares.core.persistence.model.base.ResultCode;
import com.ares.system.utils.AresCommonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @description:
 * @author: Young
 * @date: 2020/10/20
 * @see: com.ares.system.common.security AuthenticationEntryPointImpl.java
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final String NO_AUTH = "Full authentication is required to access this resource";

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        if (e.getMessage().contains(NO_AUTH)) {
            AresCommonUtils.writeResponse(httpServletResponse, AjaxResult.unAuth(), ResultCode.NOAUTH.getCode());
        } else {
            AresCommonUtils.writeResponse(httpServletResponse, AjaxResult.error(ResultCode.FAILED.getCode(), e.getMessage()));
        }
    }
}

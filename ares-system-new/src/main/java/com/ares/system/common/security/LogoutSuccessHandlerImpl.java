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
import com.ares.core.persistence.model.base.Constants;
import com.ares.redis.utils.RedisUtil;
import com.ares.system.common.jwt.JwtTokenUtils;
import com.ares.system.utils.AresCommonUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: Young
 * @date: 2020/10/19
 * @see: com.ares.system.common.security LogoutSuccessHandlerImpl.java
 **/
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String token = JwtTokenUtils.getToken(httpServletRequest);
        if (null != token) {
            String userName = JwtTokenUtils.getUsernameFromToken(token);
            if (RedisUtil.hasKey(Constants.LOGIN_INFO + userName)) {
                RedisUtil.del(Constants.LOGIN_INFO + userName);
            }
        }
        AresCommonUtils.writeResponse(httpServletResponse, AjaxResult.success());
    }
}

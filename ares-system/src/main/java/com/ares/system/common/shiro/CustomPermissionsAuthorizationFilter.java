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

import com.alibaba.fastjson.JSONObject;
import com.ares.core.persistence.model.base.AjaxResult;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author: Young 2020/05/04
 **/
public class CustomPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter {

    /**
     * 根据请求接口路径进行验证
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws IOException
     */
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        // 获取接口请求路径
        String servletPath = WebUtils.toHttp(request).getServletPath();
        mappedValue = new String[]{servletPath};
        return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 解决权限不足302问题
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        Subject subject = getSubject(request, response);
        if (subject.getPrincipal() == null) {
            saveRequestAndRedirectToLogin(request, response);
        } else {
            WebUtils.toHttp(response).setContentType("application/json; charset=utf-8");
            WebUtils.toHttp(response).getWriter().print(JSONObject.toJSONString(AjaxResult.unAuth()));
        }
        return false;
    }

}

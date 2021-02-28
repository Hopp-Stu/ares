package com.ares.system.common.security;

import com.ares.core.persistence.model.base.AjaxResult;
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
 * @author: yy
 * @date: 2020/10/20
 * @see: com.ares.system.common.security AuthenticationEntryPointImpl.java
 **/
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        AresCommonUtils.writeResponse(httpServletResponse, AjaxResult.error(500, e.getMessage()));
    }
}

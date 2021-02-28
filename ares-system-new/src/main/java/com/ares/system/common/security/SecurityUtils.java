package com.ares.system.common.security;

import com.ares.core.common.exception.UserException;
import com.ares.core.persistence.model.system.SysUser;
import com.ares.core.persistence.model.base.Constants;
import com.ares.core.persistence.model.exception.ErrorCode;
import com.ares.core.persistence.service.SysUserService;
import com.ares.core.utils.SpringUtils;
import com.ares.redis.utils.RedisUtil;
import com.ares.system.common.jwt.JwtAuthenticationToken;
import com.ares.system.common.jwt.JwtTokenUtils;
import com.ares.system.common.jwt.JwtUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: yy
 * @date: 2020/10/19
 * @see: com.ares.system.common.security SecurityUtils.java
 **/
public class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * 系统登录认证
     *
     * @param request
     * @param username
     * @param password
     * @param authenticationManager
     * @return
     */
    public static JwtAuthenticationToken login(HttpServletRequest request, String username, String password, AuthenticationManager authenticationManager) throws AuthenticationException {
        JwtAuthenticationToken token = new JwtAuthenticationToken(username, password);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(token);
        // 认证成功存储认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌并返回给客户端
        token.setToken(JwtTokenUtils.generateToken(authentication));
        return token;
    }

    /**
     * 获取令牌进行认证
     *
     * @param request
     */
    public static void checkAuthentication(HttpServletRequest request) throws UserException {
        // 获取令牌并根据令牌获取登录认证信息
        Authentication authentication = JwtTokenUtils.getAuthenticationFromToken(request);
        if (authentication instanceof JwtAuthenticationToken) {
            String token = ((JwtAuthenticationToken) authentication).getToken();
            String userName = JwtTokenUtils.getUsernameFromToken(token);
            if (JwtTokenUtils.isTokenExpired(token)) {
                throw new UserException(ErrorCode.LOGINTIMEOUT.getCode(), "登录过期！");
            }
            if (RedisUtil.hasKey(Constants.LOGIN_INFO + userName)) {
                // 设置登录认证信息到上下文
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new UserException(ErrorCode.NOLOGIN.getCode(), "用户未登录！");
            }
        }
    }

    /**
     * 获取当前用户名
     *
     * @return
     */
    public static String getUsername() {
        String username = null;
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal != null) {
                if (principal instanceof JwtUserDetails) {
                    username = ((UserDetails) principal).getUsername();
                } else if (principal instanceof String) {
                    username = (String) principal;
                }
            }
        }
        return username;
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public static String getUsername(Authentication authentication) {
        String username = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal != null && principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            }
        }
        return username;
    }

    /**
     * 获取当前登录信息
     *
     * @return
     */
    public static Authentication getAuthentication() {
        if (SecurityContextHolder.getContext() == null) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }


    public static SysUser getUser() throws UserException {
        String userName = getUsername();
        if (null == userName) {
            throw new UserException(ErrorCode.NOUSER.getCode(), "用户不存在！");
        }
        SysUserService userService = SpringUtils.getBean(SysUserService.class);
        SysUser user = userService.getUserByName(userName);
        return user;
    }

}

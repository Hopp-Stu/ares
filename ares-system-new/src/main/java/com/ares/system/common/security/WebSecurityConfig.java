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

import com.ares.system.common.jwt.JwtAuthenticationFilter;
import com.ares.system.common.jwt.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description:
 * @author: Young
 * @date: 2020/10/19
 * @see: com.ares.system.common.security WebSecurityConfig.java
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private LogoutSuccessHandlerImpl logoutSuccessHandler;
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService,
                             LogoutSuccessHandlerImpl logoutSuccessHandler,
                             AuthenticationEntryPointImpl authenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义登录身份认证组件
        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "*/**").permitAll()
                .antMatchers(HttpMethod.GET, "*/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/kaptcha").permitAll()
                .antMatchers("/system/user/profile/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/*/api-docs").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/doc.html").permitAll()
                .antMatchers("/model/**", "/editor/**").permitAll()
                .antMatchers("/blog/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable();
        http.logout().logoutUrl("/loginOut").logoutSuccessHandler(logoutSuccessHandler);
        // 访问控制时登录状态检查过滤器
        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}

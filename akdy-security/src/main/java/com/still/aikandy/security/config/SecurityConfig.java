package com.still.aikandy.security.config;

import com.still.aikandy.security.authention.DynamicSecurityFilter;
import com.still.aikandy.security.authention.JwtAuthenticationTokenFilter;
import com.still.aikandy.security.authention.VisitorFilter;
import com.still.aikandy.security.authorize.AuthorizeConfigManager;
import com.still.aikandy.security.handler.*;
import com.still.aikandy.security.properties.SecurityConstants;
import com.still.aikandy.security.properties.SecurityProperties;
import com.still.aikandy.security.validatecode.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @Author FishAndFlower
 * @Description 对SpringSecurity的配置的扩展，支持自定义白名单资源路径和查询用户逻辑
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DynamicSecurityFilter dynamicSecurityFilter;
    @Autowired
    private FormAuthenticationConfig formAuthenticationConfig;
    @Autowired
    private AuthorizeConfigManager authorizeConfigManager;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private RestAccessDeniedHandler restAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;
    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private VisitorFilter visitorFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //配置表单登录
        formAuthenticationConfig.config(httpSecurity);

        httpSecurity
                //验证码过滤器
                .apply(validateCodeSecurityConfig)
                .and()
                // 自定义权限拦截器JWT过滤器
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                //有动态权限配置时添加动态权限校验过滤器
                .addFilterBefore(dynamicSecurityFilter, FilterSecurityInterceptor.class)
                .addFilterBefore(visitorFilter,DynamicSecurityFilter.class)
                //关闭Session
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 自定义权限拒绝处理类
                .and()
                .exceptionHandling()
                .accessDeniedHandler(restAccessDeniedHandler)
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                //设置登出操作
                .and()
                .logout()
                .logoutUrl(securityProperties.getBrowser().getLogOutUrl())
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .csrf()
                .disable();

        authorizeConfigManager.config(httpSecurity.authorizeRequests());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}

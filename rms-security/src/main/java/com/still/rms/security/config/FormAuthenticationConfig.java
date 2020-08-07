package com.still.rms.security.config;

import com.still.rms.security.handler.RestAuthenticationFailureHandler;
import com.still.rms.security.handler.RestAuthenticationSuccessHandler;
import com.still.rms.security.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

/**
 * @Author FishAndFlower
 * @Description FORM表单登录配置
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Component
@Slf4j
public class FormAuthenticationConfig {
    @Autowired
    private RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    @Autowired
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;
    @Autowired
    private SecurityProperties securityProperties;

    public void config(HttpSecurity http) throws Exception {
        http.formLogin()
                //发送登录验证时请求的地址
                .loginProcessingUrl(securityProperties.getBrowser().getLogInUrlForm())
                .successHandler(restAuthenticationSuccessHandler)
                .failureHandler(restAuthenticationFailureHandler);
    }
}

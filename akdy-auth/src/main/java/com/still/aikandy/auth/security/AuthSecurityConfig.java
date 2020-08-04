package com.still.aikandy.auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @Author FishAndFlower
 * @Description akdy-security模块相关配置
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Configuration
//@EnableWebSecurity
public class AuthSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

package com.still.rms.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author FishAndFlower
 * @Description 全局配置类
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
@ConfigurationProperties(prefix = "security")
@Component
public class SecurityProperties {
    /**
     * 浏览器环境配置
     */
    private BrowserProperties browser = new BrowserProperties();
    /**
     * 验证码配置
     */
    private ValidateCodeProperties code = new ValidateCodeProperties();

    /**
     * jwt配置
     */
    private JwtProperties jwt = new JwtProperties();
}

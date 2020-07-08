package com.still.aikandy.auth.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Lee
 * @Description 配置类
 * @Date 2020/6/29 10:08
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {
    private String postIconUrl;
    private String defaultIcon;
    private String basePath;
    private String iconPath;
    private String imageServer;
}

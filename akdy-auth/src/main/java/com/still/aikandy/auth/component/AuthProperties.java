package com.still.aikandy.auth.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @Author FishAndFlower
 * @Description 权限模块配置项
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {
    private String defaultIcon;
    private String basePath;
    private String iconPath;
}

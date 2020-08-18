package com.still.rms.superstar.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @Author FishAndFlower
 * @Description superstar配置项
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "superstar")
public class SuperstarProperties {
    private String basePath;
}

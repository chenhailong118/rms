package com.still.aikandy.security.authention;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @Author FishAndFlower
 * @Description 动态权限相关业务类
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}

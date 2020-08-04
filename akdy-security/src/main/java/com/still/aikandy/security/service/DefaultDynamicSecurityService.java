package com.still.aikandy.security.service;

import com.still.aikandy.security.authention.DynamicSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @Author FishAndFlower
 * @Description 默认动态权限，不设置任何权限，默认放行全部
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public class DefaultDynamicSecurityService implements DynamicSecurityService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Map<String, ConfigAttribute> loadDataSource() {
        logger.warn("请配置 UserDetailsService 接口的实现.");
        return null;
    }
}

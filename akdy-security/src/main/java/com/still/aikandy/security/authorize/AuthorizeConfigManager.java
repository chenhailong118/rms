package com.still.aikandy.security.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * @Author FishAndFlower
 * @Description 授权信息管理器,用于收集系统中所有 AuthorizeConfigProvider 并加载其配置
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface AuthorizeConfigManager {
	
	/**
	 * @param config
	 */
	void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}

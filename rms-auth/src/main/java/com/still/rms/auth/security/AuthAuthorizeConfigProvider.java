package com.still.rms.auth.security;

import com.still.rms.security.authorize.AuthorizeConfigProvider;
import com.still.rms.security.properties.SecurityConstants;
import com.still.rms.security.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @Author FishAndFlower
 * @Description 核心模块的授权配置提供器，安全模块涉及的url的授权配置在这里。
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Component
/**最小值,最先读取这个配置**/
@Order(Integer.MIN_VALUE)
public class AuthAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		//支持本地应用查询资源信息
//		config.antMatchers(HttpMethod.GET,"/auth/security/resourceall").access("hasIpAddress('0:0:0:0:0:0:0:1') or hasIpAddress('127.0.0.1')");
		config.antMatchers(HttpMethod.GET,"/security/resourceall","/security/user","/security/resources").permitAll()
				.antMatchers(HttpMethod.POST,"/auth/icon").permitAll();
//		//使用access配置动态权限
//		config.anyRequest().access("@authUserServiceImpl.hasPermission(request, authentication)");
		return false;
	}

}

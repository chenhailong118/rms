package com.still.aikandy.security.authorize;

import com.still.aikandy.security.properties.SecurityConstants;
import com.still.aikandy.security.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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
public class DefaultAuthorizeConfigProvider implements AuthorizeConfigProvider {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers(
				SecurityConstants.DEFAULT_LOG_IN_PROCESSING_URL_FORM,
				SecurityConstants.DEFAULT_LOGIN_IN_PROCESSING_URL_MOBILE,
				SecurityConstants.REFRESH_TOKEN_RUL,
				SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/**",
				SecurityConstants.VISIT_TOKEN_RUL,
				SecurityConstants.DEFAULT_LOG_OUT_URL,
				securityProperties.getBrowser().getLogInUrlForm(),
				securityProperties.getBrowser().getLogOutUrl()).permitAll();
		securityProperties.getBrowser().getIgnoreUrls().stream().forEach(url -> {
			config.antMatchers(url).permitAll();
		});
		if (StringUtils.isNotBlank(securityProperties.getBrowser().getLogOutUrl())) {
			config.antMatchers(securityProperties.getBrowser().getLogOutUrl()).permitAll();
		}
        // 表示本配置中没有anyRequest的配置
		return false;
	}

}
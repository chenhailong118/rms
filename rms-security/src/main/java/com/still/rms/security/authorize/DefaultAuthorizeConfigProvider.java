package com.still.rms.security.authorize;

import com.still.rms.security.properties.SecurityConstants;
import com.still.rms.security.properties.SecurityProperties;
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
				SecurityConstants.DEFAULT_LOG_IN_PROCESSING_URL_FORM,//用户名密码登录请求URL
				SecurityConstants.DEFAULT_LOGIN_IN_PROCESSING_URL_MOBILE,//手机号码登录请求URL
				SecurityConstants.REFRESH_TOKEN_RUL,//TOKEN刷新请求URL
				SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/**"//图片验证码和短信验证码请求URL
				).permitAll();
        // 表示本配置中没有anyRequest的配置
		return false;
	}

}

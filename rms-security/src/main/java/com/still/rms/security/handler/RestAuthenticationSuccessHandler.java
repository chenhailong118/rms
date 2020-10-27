package com.still.rms.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.still.rms.common.api.CommonResponse;
import com.still.rms.security.properties.SecurityProperties;
import com.still.rms.security.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author FishAndFlower
 * @Description 自定义FORM表单登录成功的处理器
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Component("restAuthenticationSuccessHandler")
public class RestAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	private static final String REDIS_KEY_PREFIX_TOKEN = "redis_token";

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private SecurityProperties securityProperties;

	/*
	 * 登录成功后调用这个方法
	 * authentication封装了认证信息
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

		logger.info("登录成功");
		//生成返回TOKEN
		Map resultMap = new HashMap<>();
		resultMap.put("tokenPrefix", securityProperties.getJwt().getTokenPrefix());
		resultMap.put("token",jwtTokenUtil.generateToken((UserDetails)authentication.getPrincipal(), securityProperties.getJwt().getSecret(), securityProperties.getJwt().getExpiration()));
		resultMap.put("expireTime", LocalDateTime.now().plusSeconds(securityProperties.getJwt().getExpiration()).toEpochSecond(ZoneOffset.of("+8")));

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(CommonResponse.success(resultMap)));
	}

}

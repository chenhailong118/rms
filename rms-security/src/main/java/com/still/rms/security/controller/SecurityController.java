package com.still.rms.security.controller;

import com.still.rms.common.api.CommonResponse;
import com.still.rms.security.properties.SecurityConstants;
import com.still.rms.security.properties.SecurityProperties;
import com.still.rms.security.utils.JwtTokenUtil;
import com.still.rms.security.validatecode.ValidateCodeProcessor;
import com.still.rms.security.validatecode.ValidateCodeProcessorHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


/**
 * @Author FishAndFlower
 * @Description 浏览器环境下与安全相关的服务
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@RestController
@Slf4j
public class SecurityController {

	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;


	/**
	 * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
	 *
	 * @param request
	 * @param response
	 * @param type
	 * @throws Exception
	 */
	@GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}/{validateCodeToken}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type, @PathVariable String validateCodeToken)
			throws Exception {
		request.setAttribute("deviceId",validateCodeToken);
		validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
	}

//	/**
//	 * 游客token获取，登录前获取用于验证码校验使用
//	 * @return
//	 */
//	@GetMapping(SecurityConstants.VISIT_TOKEN_RUL)
//	public CommonResponse getValidateCodeToken(){
//		String validatecodetoken = UUID.randomUUID().toString() + new Date().getTime();
//		redisTemplate.opsForValue().set(SecurityConstants.VISIT_TOKEN_PREFIX + validatecodetoken, validatecodetoken,60, TimeUnit.SECONDS);
//		return CommonResponse.success(new HashMap(){
//			{
//				put("validatecodetoken",validatecodetoken);
//			}
//		});
//	}

	/**
	 * 登录用户刷新TOKEN
	 * @return
	 */
	@GetMapping(SecurityConstants.REFRESH_TOKEN_RUL)
	public CommonResponse refreshToken(@RequestHeader(SecurityConstants.DEFAULT_TOKEN_HEADER) String oldToken){
		//生成返回TOKEN
		Map resultMap = new HashMap<>();
		resultMap.put("tokenPrefix", securityProperties.getJwt().getTokenPrefix());
		resultMap.put("token",jwtTokenUtil.refreshHeadToken(oldToken.replaceFirst(securityProperties.getJwt().getTokenPrefix(),""), securityProperties.getJwt().getSecret(), securityProperties.getJwt().getExpiration()));
		resultMap.put("expireTime", LocalDateTime.now().plusSeconds(securityProperties.getJwt().getExpiration()).toEpochSecond(ZoneOffset.of("+8")));
		return CommonResponse.success(resultMap);
	}
}

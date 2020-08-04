package com.still.aikandy.security.controller;

import com.still.aikandy.common.api.CommonResponse;
import com.still.aikandy.security.properties.SecurityConstants;
import com.still.aikandy.security.utils.JwtTokenUtil;
import com.still.aikandy.security.validatecode.ValidateCodeProcessor;
import com.still.aikandy.security.validatecode.ValidateCodeProcessorHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
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
	private JwtTokenUtil jwtTokenUtil;

	@Value("${security.jwt.secret}")
	private String secret = "defaultJwtSecret"; //jwt加密秘钥
	@Value("${security.jwt.expiration}")
	private Long expiration = 60*30L;  //过期时间，单位：秒
	@Value("${security.jwt.tokenPrefix}")
	private String tokenPrefix = "Bearer"; //token头

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
		request.setAttribute("VALIDATE_CODE_TOKEN",validateCodeToken);
		validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
	}

	/**
	 * 游客token获取，登录前获取用于验证码校验使用
	 * @return
	 */
	@GetMapping(SecurityConstants.VISIT_TOKEN_RUL)
	public CommonResponse getValidateCodeToken(){
		String validatecodetoken = UUID.randomUUID().toString() + new Date().getTime();
		redisTemplate.opsForValue().set(SecurityConstants.VISIT_TOKEN_PREFIX + validatecodetoken, validatecodetoken,60, TimeUnit.SECONDS);
		return CommonResponse.success(new HashMap(){
			{
				put("validatecodetoken",validatecodetoken);
			}
		});
	}

	/**
	 * 登录用户刷新TOKEN
	 * @return
	 */
	@GetMapping(SecurityConstants.REFRESH_TOKEN_RUL)
	public CommonResponse refreshToken(@RequestHeader(SecurityConstants.DEFAULT_TOKEN_HEADER) String oldToken){
		//生成返回TOKEN
		Map resultMap = new HashMap<>();
		resultMap.put("tokenPrefix", tokenPrefix);
		resultMap.put("token",jwtTokenUtil.refreshHeadToken(oldToken.replaceFirst(tokenPrefix,""), secret, expiration));
		resultMap.put("expireTime", LocalDateTime.now().plusSeconds(expiration).toEpochSecond(ZoneOffset.of("+8")));
		return CommonResponse.success(resultMap);
	}
}
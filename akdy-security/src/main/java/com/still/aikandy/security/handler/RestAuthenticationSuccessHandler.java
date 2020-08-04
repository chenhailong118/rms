package com.still.aikandy.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.still.aikandy.common.api.CommonResponse;
import com.still.aikandy.security.utils.JwtTokenUtil;
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
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author FishAndFlower
 * @Description 自定义FORM表单登录成功的处理器
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Component("restAuthenticationSuccessHandler")
public class RestAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Value("${security.jwt.secret}")
    private String secret = "defaultJwtSecret"; //jwt加密秘钥
    @Value("${security.jwt.expiration}")
    private Long expiration = 60*30L;  //过期时间，单位：秒
    @Value("${security.jwt.tokenPrefix}")
    private String tokenPrefix = "Bearer"; //token头

	private static final String REDIS_KEY_PREFIX_TOKEN = "redis_token";

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private RequestCache requestCache = new HttpSessionRequestCache();

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
		resultMap.put("tokenPrefix", tokenPrefix);
		resultMap.put("token",jwtTokenUtil.generateToken((UserDetails)authentication.getPrincipal(), secret, expiration));
		resultMap.put("expireTime", LocalDateTime.now().plusSeconds(expiration).toEpochSecond(ZoneOffset.of("+8")));

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(CommonResponse.success(resultMap)));
	}

	public static void main(String[] args) {
		System.out.println();
		System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
		System.out.println(new Date().getTime());
	}
}

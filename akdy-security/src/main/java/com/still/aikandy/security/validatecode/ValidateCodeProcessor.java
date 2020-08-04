package com.still.aikandy.security.validatecode;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author FishAndFlower
 * @Description 校验码处理器，封装不同校验码的处理逻辑
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface ValidateCodeProcessor {

	/**
	 * 创建校验码
	 * 
	 * @param request
	 * @throws Exception
	 */
	void create(ServletWebRequest request) throws Exception;

	/**
	 * 校验验证码
	 * 
	 * @param servletWebRequest
	 * @throws Exception
	 */
	void validate(ServletWebRequest servletWebRequest);

}

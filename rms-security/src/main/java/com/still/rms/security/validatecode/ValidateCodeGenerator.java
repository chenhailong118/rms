package com.still.rms.security.validatecode;

import org.springframework.web.context.request.ServletWebRequest;


/**
 * @Author FishAndFlower
 * @Description 校验码生成器
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface ValidateCodeGenerator {

	/**
	 * 生成校验码
	 * @param request
	 * @return
	 */
	ValidateCode generate(ServletWebRequest request);
	
}

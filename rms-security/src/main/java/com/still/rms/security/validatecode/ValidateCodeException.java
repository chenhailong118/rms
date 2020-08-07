package com.still.rms.security.validatecode;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author FishAndFlower
 * @Description 验证码异常
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7285211528095468156L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}

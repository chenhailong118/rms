package com.still.rms.security.properties;

import lombok.Data;


/**
 * @Author FishAndFlower
 * @Description 短信验证码相关配置信息
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class SmsCodeProperties {
	
	/**
	 * 验证码长度
	 */
	private int length = 4;
	/**
	 * 过期时间，单位：秒
	 */
	private int expireIn = 60;
	/**
	 * 要拦截的url，多个url用逗号隔开，ant pattern
	 */
	private String url;
}

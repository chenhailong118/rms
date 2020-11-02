package com.still.rms.security.validatecode.sms;

/**
 * @Author FishAndFlower
 * @Description 短信验证码发送器
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface SmsCodeSender {
	
	/**
	 * @param mobile
	 * @param code
	 */
	void send(String mobile, String code);

}

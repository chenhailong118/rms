package com.still.rms.security.validatecode.sms;


import com.still.rms.security.properties.SecurityProperties;
import com.still.rms.security.validatecode.ValidateCode;
import com.still.rms.security.validatecode.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author FishAndFlower
 * @Description 短信验证码生成器
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
@Component("smsValidateCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.durian.security.core.validate.code.ValidateCodeGenerator#generate(org.
	 * springframework.web.context.request.ServletWebRequest)
	 */
	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}
}

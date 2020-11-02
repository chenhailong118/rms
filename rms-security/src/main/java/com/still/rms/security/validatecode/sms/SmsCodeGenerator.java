package com.still.rms.security.validatecode.sms;


import com.still.rms.security.properties.SecurityConstants;
import com.still.rms.security.properties.SecurityProperties;
import com.still.rms.security.validatecode.ValidateCode;
import com.still.rms.security.validatecode.ValidateCodeException;
import com.still.rms.security.validatecode.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
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

	@Autowired
	private UserDetailsService userDetailsService;

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.durian.security.core.validate.code.ValidateCodeGenerator#generate(org.
	 * springframework.web.context.request.ServletWebRequest)
	 */
	@Override
	public ValidateCode generate(ServletWebRequest request) {
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		String mobile;
		try {
			mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
			if(StringUtils.isAllEmpty(mobile)){
				throw new ValidateCodeException("请输入手机号码!");
			}
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("请输入手机号码!");
		}
		if(userDetailsService.loadUserByUsername(mobile) == null){
			throw new ValidateCodeException("手机号码未绑定!");
		}
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}
}

package com.still.rms.security.validatecode.sms;

import cn.hutool.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.still.rms.common.api.CommonResponse;
import com.still.rms.security.properties.SecurityConstants;
import com.still.rms.security.validatecode.ValidateCode;
import com.still.rms.security.validatecode.ValidateCodeException;
import com.still.rms.security.validatecode.impl.AbstractValidateCodeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author FishAndFlower
 * @Description 短信验证码处理器
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * 短信验证码发送器
	 */
	@Autowired
	private SmsCodeSender smsCodeSender;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
		String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
		String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
		if(StringUtils.isEmpty(mobile)){
			throw new ValidateCodeException("请输入正确的手机号!");
		}
		if(userDetailsService.loadUserByUsername(mobile) == null){
			throw new ValidateCodeException("手机号码未绑定!");
		}
		smsCodeSender.send(mobile, validateCode.getCode());
		logger.info("短信验证码发送成功");
		HttpServletResponse response = request.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(CommonResponse.success("短信验证码发送成功:" + validateCode.getCode())));
	}

}

package com.still.rms.security.validatecode;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author FishAndFlower
 * @Description 验证码信息封装类
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class ValidateCode implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1588203828504660915L;

	private String code;
	
	private LocalDateTime expireTime;
	
	public ValidateCode(String code, int expireIn){
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	
	public ValidateCode(String code, LocalDateTime expireTime){
		this.code = code;
		this.expireTime = expireTime;
	}
	
	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
}

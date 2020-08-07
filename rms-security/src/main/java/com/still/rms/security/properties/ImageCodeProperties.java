package com.still.rms.security.properties;

import lombok.Data;

/**
 * @Author FishAndFlower
 * @Description 图片验证码配置项
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties {
	
	/**
	 * 图片宽
	 */
	private int width = 80;
	/**
	 * 图片高
	 */
	private int height = 40;
}

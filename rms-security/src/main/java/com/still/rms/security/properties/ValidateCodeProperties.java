package com.still.rms.security.properties;

import lombok.Data;

/**
 * @Author FishAndFlower
 * @Description 验证码配置
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class ValidateCodeProperties {
    /**
     * 图片验证码配置
     */
    private ImageCodeProperties image = new ImageCodeProperties();
    /**
     * 短信验证码配置
     */
    private SmsCodeProperties sms = new SmsCodeProperties();
}

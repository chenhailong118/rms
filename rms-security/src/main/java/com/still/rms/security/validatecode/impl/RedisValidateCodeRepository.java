package com.still.rms.security.validatecode.impl;

import com.still.rms.security.properties.SecurityConstants;
import com.still.rms.security.validatecode.ValidateCode;
import com.still.rms.security.validatecode.ValidateCodeException;
import com.still.rms.security.validatecode.ValidateCodeRepository;
import com.still.rms.security.validatecode.ValidateCodeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @Author FishAndFlower
 * @Description 基于redis的验证码存取器，避免由于没有session导致无法存取验证码的问题
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * (non-Javadoc)
     *
     * @see ValidateCodeRepository#save(ServletWebRequest,
     * ValidateCode,
     * ValidateCodeType)
     */
    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType type) {
        redisTemplate.opsForValue().set(buildKey(request, type), code, 30, TimeUnit.MINUTES);
    }

    /**
     * (non-Javadoc)
     *
     * @see ValidateCodeRepository#get(ServletWebRequest,
     * ValidateCodeType)
     */
    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType type) {
        Object value = redisTemplate.opsForValue().get(buildKey(request, type));
        if (value == null) {
            return null;
        }
        return (ValidateCode) value;
    }

    /**
     * (non-Javadoc)
     *
     * @see ValidateCodeRepository#remove(ServletWebRequest,
     * ValidateCodeType)
     */
    @Override
    public void remove(ServletWebRequest request, ValidateCodeType type) {
        redisTemplate.delete(buildKey(request, type));
    }

    /**
     * @param request
     * @param type
     * @return
     */
    private String buildKey(ServletWebRequest request, ValidateCodeType type) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            deviceId = (String) request.getAttribute("deviceId", 0);
            if(StringUtils.isBlank(deviceId)){
                throw new ValidateCodeException("请在请求头中携带deviceId参数");
            }
        }
        return "code:" + type.toString().toLowerCase() + ":" + deviceId;
    }

}

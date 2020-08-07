package com.still.rms.security.properties;


import lombok.Data;

/**
 * @Author FishAndFlower
 * @Description Jwt配置信息
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class JwtProperties {
    /**
     * secret
     */
    private String secret = SecurityConstants.DEFAULT_JWT_SECRET;

    /**
     * token超时时间，单位：秒
     */
    private Long expiration = SecurityConstants.DEFAULT_JWT_EXPIRATION;

    /**
     * token前缀
     */
    private String tokenPrefix = SecurityConstants.DEFAULT_JWT_TOKEN_PREFIX;
}

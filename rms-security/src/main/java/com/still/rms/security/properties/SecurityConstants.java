package com.still.rms.security.properties;


/**
 * @Author FishAndFlower
 * @Description 权限模块默认参数
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface SecurityConstants {
    /**
     * 默认的用户名密码登录请求处理url
     */
    String DEFAULT_LOG_IN_PROCESSING_URL_FORM = "/auth/form";

    /**
     * 默认登出地址
     */
    String DEFAULT_LOG_OUT_URL = "/auth/logout";

    /**
     * validatecodetoken请求地址
     */
    String VISIT_TOKEN_RUL = "/auth/validatecodetoken";

    /**
     * validatecodetoken存储前缀
     */
    String VISIT_TOKEN_PREFIX = "validate_code_token";

    /**
     * 登录用户刷新TOKEN请求地址
     */
    String REFRESH_TOKEN_RUL = "/auth/refreshtoken";

    /**
     * 默认的手机验证码登录请求处理url
     */
    String DEFAULT_LOGIN_IN_PROCESSING_URL_MOBILE = "/auth/mobile";



    /**
     * JWT默认秘钥
     */
    String DEFAULT_JWT_SECRET = "defaultJwtSecret";

    /**
     * JWT默认超时时间，单位秒，默认30分钟
     */
    Long DEFAULT_JWT_EXPIRATION = 60*30L;

    /**
     * JWT默认TOKEN_HEAD
     */
    String DEFAULT_JWT_TOKEN_PREFIX = "Bearer";

    /**
     * 默认的处理验证码的url前缀
     */
    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/auth/code";

    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    /**
     * token在请求头headers里的属性名称
     */
    String DEFAULT_TOKEN_HEADER = "Authorization";
}

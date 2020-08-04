package com.still.aikandy.security.properties;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 浏览器环境配置
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class BrowserProperties {

    /**
     * 用户名密码登录处理请求URL
     */
    private String logInUrlForm = SecurityConstants.DEFAULT_LOG_IN_PROCESSING_URL_FORM;

    /**
     * token在请求头headers里的属性名称
     */
    private String tokenHeader = SecurityConstants.DEFAULT_TOKEN_HEADER;
    /**
     * 退出处理请求URL
     */
    private String logOutUrl = SecurityConstants.DEFAULT_LOG_OUT_URL;

    /**
     * 配置不需要保护的资源路径
     */
    private List<String> ignoreUrls = new ArrayList<>();

}

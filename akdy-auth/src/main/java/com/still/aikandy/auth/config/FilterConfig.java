package com.still.aikandy.auth.config;

import com.still.aikandy.auth.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Lee
 * @Description 拦截器配置
 * @Date 2020/5/27 21:45
 * @Version 1.0
 */
//@Configuration
public class FilterConfig {

    /**
     * 配置跨域拦截器，允许跨域请求
     */
    @Bean
    public FilterRegistrationBean CorsFilterRegister(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        //注入过滤器
        registration.setFilter(new CorsFilter());
        //拦截规则
        registration.addUrlPatterns("/*");
        //过滤器名称
        registration.setName("CorsFilter");
        //过滤器顺序
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }
}

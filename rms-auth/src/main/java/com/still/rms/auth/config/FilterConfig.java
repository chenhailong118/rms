package com.still.rms.auth.config;

import com.still.rms.auth.filter.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;


/**
 * @Author FishAndFlower
 * @Description 过滤器配置
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Configuration
public class FilterConfig {

    /**
     * 配置跨域拦截器，允许跨域请求
     * 如果通过网关调用则注释掉此处的跨域，直接在网关里配置就行
     */
//    @Bean
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

package com.still.rms.security.handler;

import cn.hutool.json.JSONUtil;
import com.still.rms.common.api.CommonResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author FishAndFlower
 * @Description 自定义返回结果：没有权限访问时
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Component("restfulAccessDeniedHandler")
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setHeader("Cache-Control","no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResponse.forbidden(e.getMessage())));
        response.getWriter().flush();
    }
}

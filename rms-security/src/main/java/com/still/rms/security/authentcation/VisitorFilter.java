package com.still.rms.security.authentcation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.still.rms.common.api.CommonResponse;
import com.still.rms.common.api.ResultCode;
import com.still.rms.security.properties.SecurityConstants;
import com.still.rms.security.properties.SecurityProperties;
import com.still.rms.security.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author FishAndFlower
 * @Description 游客登录权限限制
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Component
public class VisitorFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(VisitorFilter.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private SecurityProperties securityProperties;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(SecurityConstants.DEFAULT_TOKEN_HEADER);
        if (authHeader != null && authHeader.startsWith(securityProperties.getJwt().getTokenPrefix())) {
            String authToken = authHeader.substring(securityProperties.getJwt().getTokenPrefix().length());// The part after "Bearer "
            String username = jwtTokenUtil.getUserNameFromToken(authToken, securityProperties.getJwt().getSecret());
            if ("visitor".equals(username)) {
                LOGGER.info("游客登录");
                String method = request.getMethod();
                if(!"GET".equalsIgnoreCase(method)){
                    response.setContentType("application/json;charset=UTF-8");
                    response.getWriter().write(objectMapper.writeValueAsString(CommonResponse.error(ResultCode.FORBIDDEN)));
                    response.getWriter().flush();
                    return;
                }

            }
        }

        chain.doFilter(request, response);
    }
}

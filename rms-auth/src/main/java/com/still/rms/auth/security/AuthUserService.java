package com.still.rms.auth.security;

import com.still.rms.mbg.model.AuthUser;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author FishAndFlower
 * @Description 用户Service接口
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface AuthUserService {

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    Map<String,Object> getUserInfo(String token);

//    /**
//     * 判断请求是否有权限
//     * @param request
//     * @param authentication
//     * @return
//     */
//    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}

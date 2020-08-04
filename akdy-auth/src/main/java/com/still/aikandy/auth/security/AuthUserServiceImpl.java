package com.still.aikandy.auth.security;

import com.still.aikandy.auth.component.AuthProperties;
import com.still.aikandy.auth.service.MenuService;
import com.still.aikandy.auth.service.RoleService;
import com.still.aikandy.common.api.ApiException;
import com.still.aikandy.common.api.ResultCode;
import com.still.aikandy.mbg.model.AuthUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author FishAndFlower
 * @Description 用户Service实现类
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private AuthProperties authProperties;


    /**
     * 获取登录用户信息
     * @param token
     * @return
     */
    @Override
    public Map<String,Object> getUserInfo(String token) {
        if(StringUtils.isEmpty(token)){
            throw new ApiException(ResultCode.TOKEN_ERROR);
        }
        Map map = new HashMap();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser user = ((AuthUserDetails)authentication.getPrincipal()).getAuthUser();
        map.put("username",user.getUsername());
        map.put("nickname",user.getNickname());
        map.put("icon",user.getIcon());
        map.put("roles", roleService.getAuthRoleByUserId(user.getId()));
        map.put("menus",menuService.getMenuBYUserId(user.getId()));
        map.put("imageServer",authProperties.getImageServer());
        return map;
    }

}

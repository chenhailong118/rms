package com.still.rms.auth.security;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.URLUtil;
import com.google.inject.internal.util.$SourceProvider;
import com.still.rms.auth.service.MenuService;
import com.still.rms.auth.service.ResourceService;
import com.still.rms.auth.service.RoleService;
import com.still.rms.common.api.ApiException;
import com.still.rms.common.api.ResultCode;
import com.still.rms.common.dto.AuthResourceDto;
import com.still.rms.common.dto.AuthUserDto;
import com.still.rms.common.querycondition.AuthResourceQueryCondition;
import com.still.rms.mbg.model.AuthResource;
import com.still.rms.mbg.model.AuthUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author FishAndFlower
 * @Description 用户Service实现类
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Service("authUserServiceImpl")
public class AuthUserServiceImpl implements AuthUserService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @Autowired
    private ResourceService resourceService;



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
        return map;
    }

//    /**
//     * 动态权限的两种方案之一，勿删除
//     */
//    @Override
//    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
//        boolean hasPermission = false;
//        //获取请求路径
//        String url = request.getRequestURI();
//        String path = URLUtil.getPath(url);
//
//        //获取访问该路径所需资源权限
//        PathMatcher pathMatcher = new AntPathMatcher();
//        Collection<ConfigAttribute> configAttributes = new ArrayList<>();
//        List<AuthResourceDto> resourceList = resourceService.queryAuthResources(new AuthResourceQueryCondition());
//        for (AuthResource resource : resourceList) {
//            if(pathMatcher.match(resource.getUrl(), path)){
//                configAttributes.add(new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
//            }
//        }
//
//        //判断是否有权限
//        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
//        while (iterator.hasNext()) {
//            ConfigAttribute configAttribute = iterator.next();
//            //将访问所需资源或用户拥有资源进行比对
//            String needAuthority = configAttribute.getAttribute();
//            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
//                if (needAuthority.trim().equals(grantedAuthority.getAuthority())) {
//                    hasPermission = true;
//                }
//            }
//        }
//        return hasPermission;
//    }

}

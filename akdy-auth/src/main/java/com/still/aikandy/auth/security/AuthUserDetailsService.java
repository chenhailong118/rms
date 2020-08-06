package com.still.aikandy.auth.security;

import com.still.aikandy.auth.service.UserService;
import com.still.aikandy.mbg.model.AuthResource;
import com.still.aikandy.mbg.model.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author FishAndFlower
 * @title: AkdyUserDetailsService
 * @projectName aikandy
 * @description: UserDetailsService实现类
 * @date 2020/7/30 9:56
 */
@Component
public class AuthUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户信息
        AuthUser user = userService.getUserByUsername(username);
        if (user != null) {
            List<AuthResource> resourceList = userService.getResourcesByUserId(user.getId());
            return new AuthUserDetails(user,resourceList);
        }
        throw new UsernameNotFoundException("用户名不存在");
    }
}

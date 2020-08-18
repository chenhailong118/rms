package com.still.rms.superstar.security;

import com.still.rms.common.api.CommonResponse;
import com.still.rms.mbg.model.AuthResource;
import com.still.rms.mbg.model.AuthUser;
import com.still.rms.superstar.security.rpc.AuthFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author FishAndFlower
 * @title: rmsUserDetailsService
 * @projectName rms
 * @description: UserDetailsService实现类
 * @date 2020/7/30 9:56
 */
@Service
public class AuthUserDetailsService implements UserDetailsService {
    @Autowired
    private AuthFeignClient authFeignClient;

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获取用户信息
        CommonResponse<AuthUser> response = authFeignClient.getUserByUsername(username);
        AuthUser user = response.getData();
        if (user != null) {
            List<AuthResource> resourceList = authFeignClient.getResourcesByUserId(user.getId()).getData();
            return new AuthUserDetails(user,resourceList);
        }
        throw new UsernameNotFoundException("该用户不存在");
    }
}

package com.still.rms.auth.security;

import com.still.rms.mbg.model.AuthResource;
import com.still.rms.mbg.model.AuthUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author FishAndFlower
 * @title: rmsUserDetails
 * @projectName rms
 * @description: UserDetails实现类，Spring Security判断用户权限中需要的组件
 * @date 2020/7/30 9:44
 */
@Data
public class AuthUserDetails implements UserDetails {

    private AuthUser authUser;
    private List<AuthResource> resourceList;

    public AuthUserDetails(AuthUser authUser, List<AuthResource> resourceList) {
        this.authUser = authUser;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return resourceList.stream()
                .map(resource ->new SimpleGrantedAuthority(resource.getId() + ":" + resource.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return authUser.getStatus().equals(1);
    }
}

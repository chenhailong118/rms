package com.still.rms.superstar.security;

import com.github.pagehelper.PageInfo;
import com.still.rms.common.api.CommonResponse;
import com.still.rms.common.dto.AuthResourceDto;
import com.still.rms.mbg.model.AuthResource;
import com.still.rms.security.authentcation.DynamicSecurityService;
import com.still.rms.superstar.security.rpc.AuthFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author FishAndFlower
 * @title: rmsDynamicSecurityService
 * @projectName rms
 * @description: 用户动态权限加载
 * @date 2020/7/30 10:01
 */
@Service
public class AuthDynamicSecurityService implements DynamicSecurityService {
    @Autowired
    private AuthFeignClient authFeignClient;

    @Override
    public Map<String, ConfigAttribute> loadDataSource() {
        Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
        CommonResponse<PageInfo<AuthResourceDto>> response = authFeignClient.queryResources();
        List<AuthResourceDto> resourceList = response.getData().getList();
        for (AuthResource resource : resourceList) {
            map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
        }
        return map;
    }
}

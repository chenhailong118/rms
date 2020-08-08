package com.still.rms.auth.security;

import com.still.rms.auth.service.ResourceService;
import com.still.rms.common.dto.AuthResourceDto;
import com.still.rms.common.querycondition.AuthResourceQueryCondition;
import com.still.rms.mbg.model.AuthResource;
import com.still.rms.security.authentcation.DynamicSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Component;

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
@Component
public class AuthDynamicSecurityService implements DynamicSecurityService {
    @Autowired
    private ResourceService resourceService;

    @Override
    public Map<String, ConfigAttribute> loadDataSource() {
        Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
        List<AuthResourceDto> resourceList = resourceService.queryAuthResources(new AuthResourceQueryCondition());
        for (AuthResource resource : resourceList) {
            map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
        }
        return map;
    }
}

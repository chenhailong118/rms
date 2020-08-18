package com.still.rms.superstar.security.rpc;

import com.github.pagehelper.PageInfo;
import com.still.rms.common.api.CommonResponse;
import com.still.rms.common.dto.AuthResourceDto;
import com.still.rms.common.querycondition.AuthResourceQueryCondition;
import com.still.rms.mbg.model.AuthResource;
import com.still.rms.mbg.model.AuthUser;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 权限校验远程调用
 * @Date 2020/8/17 17:28
 * @Version 1.0
 */
@Component
@FeignClient(value = "auth")
public interface AuthFeignClient {
    /**
     * 获取所有资源列表
     * @return
     */
    @GetMapping(value = "/auth/security/resourceall")
    CommonResponse<PageInfo<AuthResourceDto>> queryResources();

    /**
     * 根据用户名获取用户信息
     * @return
     */
    @GetMapping(value = "/auth/security/user")
    CommonResponse<AuthUser> getUserByUsername(@RequestParam("username") String username);

    /**
     * 根据用户名ID获取用户权限信息
     * @return
     */
    @GetMapping(value = "/auth/security/resources")
    CommonResponse<List<AuthResource>> getResourcesByUserId(@RequestParam("userId") Long userId);

}

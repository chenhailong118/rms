package com.still.rms.auth.security;

import com.github.pagehelper.PageInfo;
import com.still.rms.auth.service.ResourceService;
import com.still.rms.auth.service.UserService;
import com.still.rms.common.api.CommonResponse;
import com.still.rms.common.dto.AuthResourceDto;
import com.still.rms.common.querycondition.AuthResourceQueryCondition;
import com.still.rms.mbg.model.AuthResource;
import com.still.rms.mbg.model.AuthUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author FishAndFlower
 * @Description 鉴权
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */

@Slf4j
@RestController
@Api(value = "鉴权", description = "鉴权API")
@Validated
public class AuthController {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/auth/security/info", method = RequestMethod.GET)
    public CommonResponse getUserInfo(@RequestHeader("Authorization") String token) {
        return CommonResponse.success(authUserService.getUserInfo(token));
    }

    @ApiOperation(value = "根据用户名获取用户信息")
    @RequestMapping(value = "/security/user", method = RequestMethod.GET)
    public CommonResponse<AuthUser> getUserByUsername(@RequestParam("username") String username) {
        return CommonResponse.success(userService.getUserByUsername(username));
    }

    @ApiOperation(value = "根据用户名ID获取用户权限信息")
    @RequestMapping(value = "/security/resources", method = RequestMethod.GET)
    public CommonResponse<List<AuthResource>> getResourcesByUserId(@RequestParam("userId") Long userId) {
        return CommonResponse.success(userService.getResourcesByUserId(userId));
    }

    /**
     * 查询资源信息
     * @return
     */
    @ApiOperation(value = "获取资源列表", notes = "获取资源列表")
    @GetMapping(value = "/security/resourceall")
    public CommonResponse<PageInfo<AuthResourceDto>> queryResources(){
        return CommonResponse.success(resourceService.queryAuthResources(null, null, null));
    }

}

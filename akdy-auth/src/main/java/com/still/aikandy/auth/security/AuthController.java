package com.still.aikandy.auth.security;

import com.still.aikandy.common.api.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @Author FishAndFlower
 * @Description 鉴权
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/auth")
@Api(value = "鉴权", description = "鉴权API")
@Validated
public class AuthController {

    @Autowired
    private AuthUserService userService;

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResponse getUserInfo(@RequestHeader("Authorization") String token) {
        return CommonResponse.success(userService.getUserInfo(token));
    }

}

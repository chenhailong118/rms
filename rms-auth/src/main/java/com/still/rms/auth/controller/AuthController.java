package com.still.rms.auth.controller;

import com.still.rms.auth.component.AuthProperties;
import com.still.rms.auth.service.MenuService;
import com.still.rms.auth.service.RoleService;
import com.still.rms.auth.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @Author FishAndFlower
 * @Description 鉴权
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */

@Slf4j
//@RestController
@Api(value = "鉴权", description = "鉴权API")
@Validated
public class AuthController {

    private final UserService userService;
    private final MenuService menuService;
    private final RoleService roleService;

    private final AuthProperties authProperties;

    private String tokenHead = "rms";

    public AuthController(UserService userService, MenuService menuService, RoleService roleService, AuthProperties authProperties) {
        this.userService = userService;
        this.menuService = menuService;
        this.roleService = roleService;
        this.authProperties = authProperties;
    }

//    @ApiOperation(value = "用户登录", notes = "登录以后返回token")
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResponse login(@Validated @RequestBody AuthUserLoginParam authUserLoginParam) {
//        String token = userService.login(authUserLoginParam);
//        return CommonResponse.success(tokenHead + token);
//    }
//
//    @ApiOperation(value = "用户登出", notes = "用户登出")
//    @RequestMapping(value = "/logout", method = RequestMethod.POST)
//    @ResponseBody
//    public CommonResponse logout(@RequestHeader("Authorization") String token) {
//        userService.logout(token);
//        return CommonResponse.success(null);
//    }

//    @ApiOperation(value = "获取当前登录用户信息")
//    @RequestMapping(value = "/info", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResponse getUserInfo(@RequestHeader("Authorization") String token) {
//        return CommonResponse.success(userService.getUserInfo(token));
//    }

}

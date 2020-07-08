package com.still.aikandy.auth.controller;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.auth.config.AuthProperties;
import com.still.aikandy.auth.service.MenuService;
import com.still.aikandy.auth.service.RoleService;
import com.still.aikandy.auth.service.UserService;
import com.still.aikandy.common.api.RestCode;
import com.still.aikandy.common.api.RestResponse;
import com.still.aikandy.common.dto.*;
import com.still.aikandy.common.querycondition.AuthUserQueryCondition;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author Lee
 * @Description 用户
 * @Date 2020/6/22 17:48
 * @Version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "用户", description = "用户API")
@Validated
public class UserController {

    private final UserService userService;
    private final MenuService menuService;
    private final RoleService roleService;

    private final AuthProperties authProperties;

    public UserController(UserService userService, MenuService menuService, RoleService roleService, AuthProperties authProperties) {
        this.userService = userService;
        this.menuService = menuService;
        this.roleService = roleService;
        this.authProperties = authProperties;
    }

    /**
     * 添加用户信息
     * @param authUserDto 用户信息
     * @return
     */
    @ApiOperation(value = "添加用户信息",notes = "添加用户信息")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public RestResponse<Integer> addUser(@RequestBody @Validated(AuthUserDto.AddAuthUserGroup.class) AuthUserDto authUserDto) throws IOException {
        Integer count = userService.addAuthUser(authUserDto);
        return RestResponse.success(count);
    }

    /**
     * 删除用户信息
     * @param userId 用户id
     * @return
     */
    @ApiOperation(value = "删除用户信息",notes = "删除用户信息")
    @ApiImplicitParam(name = "userId", value = "用户id")
    @RequestMapping(value = "/{userId}",method = RequestMethod.DELETE)
    public RestResponse<Integer> deleteUser(@PathVariable(value = "userId") Long userId){
        Integer count = userService.deleteAuthUser(userId);
        return RestResponse.success(count);
    }

    /**
     * 修改用户信息
     * @param userId 用户ID
     * @param authUserDto 用户信息
     * @return
     */
    @ApiOperation(value = "修改用户信息",notes = "修改用户信息")
    @ApiImplicitParam(name = "userId", value = "用户id")
    @RequestMapping(value = "/{userId}",method = RequestMethod.PUT)
    public RestResponse<Integer> editUser(@NotNull @PathVariable(value = "userId") Long userId, @RequestBody AuthUserDto authUserDto){
        Integer count = userService.updateAuthUser(userId,authUserDto);
        return RestResponse.success(count);
    }

    /**
     * 根据条件查询用户信息
     * @param authUserQueryCondition 用户查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @ApiOperation(value = "获取用户列表", notes = "根据条件查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "Integer")
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public RestResponse<PageInfo<List<AuthUserDto>>> queryUsers(
            AuthUserQueryCondition authUserQueryCondition,
            @RequestParam(value = "pageNum",required = false) Integer pageNum,
            @RequestParam(value = "pageSize",required = false) Integer pageSize){
        return RestResponse.success(userService.queryAuthUsers(authUserQueryCondition, pageNum, pageSize));
    }

    /**
     * 用户头像上传
     * @param icon
     * @return
     */
    @ApiOperation(value = "用户头像上传",notes = "创建用户前，先提交头像信息")
    @ApiParam(name = "file", value = "用户头像",required = true)
    @RequestMapping(value = "icon",method = RequestMethod.POST)
    public RestResponse<String> iconUpload(@RequestParam("file") MultipartFile icon){
        return RestResponse.success(userService.iconUpload(icon));
    }

    /**
     * 获取URLS
     * @return
     */
    @ApiOperation(value = "获取URLS",notes = "获取URLS")
    @GetMapping(value = "urls")
    public RestResponse<UrlsDto> getPostIconUrl(){
        return RestResponse.success(userService.getUrls());
    }


    @ApiOperation("分配用户角色")
    @PostMapping("/role/update")
    public RestResponse<Integer> updateUserRole(AuthUserRoleDto authUserRoleDto){
        int count = userService.updateUserRole(authUserRoleDto.getUserId(), authUserRoleDto.getRoleIds());
        if (count >= 0) {
            return RestResponse.success(count);
        }
        return RestResponse.error(RestCode.UNKNOWN_ERROR);
    }

    @ApiOperation(value = "用户登录", notes = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse login(@Validated @RequestBody AuthUserLoginParam authUserLoginParam) {
        String token = userService.login(authUserLoginParam);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", "akdy");
        return RestResponse.success(tokenMap);
    }

    @ApiOperation(value = "用户登出", notes = "用户登出")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public RestResponse logout(@RequestHeader("Authorization") String token) {
        userService.logout(token);
        return RestResponse.success(null);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public RestResponse getUserInfo(@RequestHeader("Authorization") String token) {
        return RestResponse.success(userService.getUserInfo(token));
    }
}

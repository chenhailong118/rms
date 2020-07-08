package com.still.aikandy.apigateway.controller;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.apigateway.service.auth.AuthUserService;
import com.still.aikandy.common.api.RestCode;
import com.still.aikandy.common.api.RestResponse;
import com.still.aikandy.common.dto.AuthUserDto;
import com.still.aikandy.common.dto.AuthUserLoginParam;
import com.still.aikandy.common.dto.AuthUserRoleDto;
import com.still.aikandy.common.dto.UrlsDto;
import com.still.aikandy.common.querycondition.AuthUserQueryCondition;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

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
public class AuthUserController {

    private final AuthUserService userService;

    public AuthUserController(AuthUserService userService) {
        this.userService = userService;
    }

    /**
     * 添加用户信息
     * @param authUserDto 用户信息
     * @return
     */
    @ApiOperation(value = "添加用户信息",notes = "添加用户信息")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public RestResponse<Integer> addUser(@RequestBody AuthUserDto authUserDto) throws IOException {
        return userService.addAuthUser(authUserDto);
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
        return userService.deleteAuthUser(userId);
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
        return userService.updateAuthUser(userId,authUserDto);
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
    public RestResponse<PageInfo> queryUsers(
            AuthUserQueryCondition authUserQueryCondition,
            @RequestParam(value = "pageNum",required = false) Integer pageNum,
            @RequestParam(value = "pageSize",required = false) Integer pageSize){
        return userService.queryAuthUsers(authUserQueryCondition, pageNum, pageSize);
    }

//    /**
//     * 用户头像上传
//     * @param icon
//     * @return
//     */
//    @ApiOperation(value = "用户头像上传",notes = "创建用户前，先提交头像信息")
//    @ApiParam(name = "file", value = "用户头像",required = true)
//    @RequestMapping(value = "icon",method = RequestMethod.POST)
//    public RestResponse<String> iconUpload(@RequestParam("file") MultipartFile icon){
//        return RestResponse.success(userService.iconUpload(icon));
//    }
//
//    /**
//     * 获取用户头像上传地址
//     * @return
//     */
//    @ApiOperation(value = "获取用户头像上传地址",notes = "先提交头像信息前，先获取头像上传地址")
//    @GetMapping(value = "urls")
//    public RestResponse<UrlsDto> getPostIconUrl(){
//        return RestResponse.success(userService.getUrls());
//    }


    @ApiOperation("分配用户角色")
    @PostMapping("/role/update")
    public RestResponse<Integer> updateUserRole(AuthUserRoleDto authUserRoleDto){
        return userService.updateUserRole(authUserRoleDto);
    }
}

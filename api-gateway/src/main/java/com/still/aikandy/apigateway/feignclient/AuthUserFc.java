package com.still.aikandy.apigateway.feignclient;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.common.api.RestResponse;
import com.still.aikandy.common.dto.AuthUserDto;
import com.still.aikandy.common.dto.AuthUserRoleDto;
import com.still.aikandy.common.dto.UrlsDto;
import com.still.aikandy.common.querycondition.AuthUserQueryCondition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @Author Lee
 * @Description 用户远程调用
 * @Date 2020/7/6 17:20
 * @Version 1.0
 */

@FeignClient(value = "auth", fallback = AuthUserFcFallback.class)
public interface AuthUserFc {

    /**
     * 添加用户信息
     * @param authUserDto 用户信息
     * @return
     */
    @PostMapping(value = "/user")
    RestResponse<Integer> addUser(@RequestBody AuthUserDto authUserDto) throws IOException;

    /**
     * 删除用户信息
     * @param userId 用户id
     * @return
     */
    @DeleteMapping(value = "/user/{userId}")
    RestResponse<Integer> deleteUser(@PathVariable(value = "userId") Long userId);

    /**
     * 修改用户信息
     * @param userId 用户ID
     * @param authUserDto 用户信息
     * @return
     */
    @PutMapping(value = "/user/{userId}")
    RestResponse<Integer> editUser(@PathVariable(value = "userId") Long userId, @RequestBody AuthUserDto authUserDto);

    /**
     * 根据条件查询用户信息
     * @param authUserQueryCondition 用户查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @GetMapping(value = "/user")
    RestResponse<PageInfo> queryUsers(
            @RequestBody AuthUserQueryCondition authUserQueryCondition,
            @RequestParam(value = "pageNum",required = false) Integer pageNum,
            @RequestParam(value = "pageSize",required = false) Integer pageSize);

//    /**
//     * 用户头像上传
//     * @param icon
//     * @return
//     */
//    @PostMapping(value = "icon")
//    RestResponse<String> iconUpload(@RequestParam("file") MultipartFile icon);
//
//    /**
//     * 获取用户头像上传地址
//     * @return
//     */
//    @GetMapping(value = "urls")
//    RestResponse<UrlsDto> getPostIconUrl();


    /**
     * 分配用户角色
     * @param authUserRoleDto
     * @return
     */
    @PostMapping("/user/role/update")
    RestResponse<Integer> updateUserRole(@RequestBody AuthUserRoleDto authUserRoleDto);

}

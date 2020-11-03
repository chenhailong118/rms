package com.still.rms.auth.controller;

import com.github.pagehelper.PageInfo;
import com.still.rms.auth.component.AuthProperties;
import com.still.rms.auth.service.MenuService;
import com.still.rms.auth.service.RoleService;
import com.still.rms.auth.service.UserService;
import com.still.rms.common.api.ResultCode;
import com.still.rms.common.api.CommonResponse;
import com.still.rms.common.dto.*;
import com.still.rms.common.querycondition.AuthUserQueryCondition;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 用户
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/auth/user")
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
    public CommonResponse<Integer> addUser(@Validated(AuthUserDto.AddAuthUserGroup.class) @RequestBody AuthUserDto authUserDto) throws IOException {
        Integer count = userService.addAuthUser(authUserDto);
        return CommonResponse.success(count);
    }

    /**
     * 删除用户信息
     * @param userId 用户id
     * @return
     */
    @ApiOperation(value = "删除用户信息",notes = "删除用户信息")
    @ApiImplicitParam(name = "userId", value = "用户id")
    @RequestMapping(value = "/{userId}",method = RequestMethod.DELETE)
    public CommonResponse<Integer> deleteUser(@PathVariable(value = "userId") Long userId){
        Integer count = userService.deleteAuthUser(userId);
        return CommonResponse.success(count);
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
    public CommonResponse<Integer> editUser(@NotNull @PathVariable(value = "userId") Long userId, @RequestBody AuthUserDto authUserDto){
        Integer count = userService.updateAuthUser(userId,authUserDto);
        return CommonResponse.success(count);
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
    public CommonResponse<PageInfo<List<AuthUserDto>>> queryUsers(
            AuthUserQueryCondition authUserQueryCondition,
            @RequestParam(value = "pageNum",required = false) Integer pageNum,
            @RequestParam(value = "pageSize",required = false) Integer pageSize){
        return CommonResponse.success(userService.queryAuthUsers(authUserQueryCondition, pageNum, pageSize));
    }

    @ApiOperation("分配用户角色")
    @PostMapping("/role/update")
    public CommonResponse<Integer> updateUserRole(AuthUserRoleDto authUserRoleDto){
        int count = userService.updateUserRole(authUserRoleDto.getUserId(), authUserRoleDto.getRoleIds());
        if (count >= 0) {
            return CommonResponse.success(count);
        }
        return CommonResponse.error(ResultCode.UNKNOWN_ERROR);
    }
}

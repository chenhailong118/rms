package com.still.rms.auth.controller;

import com.github.pagehelper.PageInfo;
import com.still.rms.auth.service.RoleService;
import com.still.rms.common.api.ResultCode;
import com.still.rms.common.api.CommonResponse;
import com.still.rms.common.dto.*;
import com.still.rms.common.querycondition.AuthRoleQueryCondition;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 角色
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */


@Slf4j
@RestController
@RequestMapping("/auth/role")
@Api(value = "角色", description = "角色API")
@Validated
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 添加角色信息
     * @param authRoleDto 角色参数
     * @return
     */
    @ApiOperation(value = "添加角色信息",notes = "添加角色信息")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public CommonResponse<Integer> addRole(@Validated(AuthRoleDto.AddAuthRoleGroup.class) @RequestBody AuthRoleDto authRoleDto){
        Integer count = roleService.addAuthRole(authRoleDto);
        return CommonResponse.success(count);
    }

    /**
     * 删除角色信息
     * @param roleId 角色id
     * @return
     */
    @ApiOperation(value = "删除角色信息",notes = "删除角色信息")
    @ApiImplicitParam(name = "roleId", value = "角色id")
    @RequestMapping(value = "/{roleId}",method = RequestMethod.DELETE)
    public CommonResponse<Integer> deleteRole(@PathVariable(value = "roleId") Long roleId){
        Integer count = roleService.deleteAuthRole(roleId);
        return CommonResponse.success(count);
    }

    /**
     * 修改角色信息
     * @param roleId 角色ID
     * @param authRoleDto 角色信息
     * @return
     */
    @ApiOperation(value = "修改角色信息",notes = "修改角色信息")
    @ApiImplicitParam(name = "roleId", value = "角色id")
    @RequestMapping(value = "/{roleId}",method = RequestMethod.PUT)
    public CommonResponse<Integer> editRole(@NotNull @PathVariable(value = "roleId") Long roleId, AuthRoleDto authRoleDto){
        Integer count = roleService.updateAuthRole(roleId,authRoleDto);
        return CommonResponse.success(count);
    }

    /**
     * 根据条件查询角色信息(分页)
     * @param authRoleQueryCondition 角色查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @ApiOperation(value = "获取角色列表(分页)", notes = "根据条件查询角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "Integer")
    })
    @GetMapping(value = "")
    public CommonResponse<PageInfo<List<AuthRoleDto>>> queryActors(
            AuthRoleQueryCondition authRoleQueryCondition,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize){
        return CommonResponse.success(roleService.queryAuthRoles(authRoleQueryCondition, pageNum, pageSize));
    }

    /**
     * 根据条件查询角色信息（ALL）
     * @param authRoleQueryCondition 角色查询条件
     * @return
     */
    @ApiOperation(value = "获取角色列表（ALL）", notes = "根据条件查询角色分页列表")
    @GetMapping(value = "all")
    public CommonResponse<List<AuthRoleDto>> queryActors(AuthRoleQueryCondition authRoleQueryCondition){
        return CommonResponse.success(roleService.queryAuthRoles(authRoleQueryCondition));
    }

    /**
     * 根据用户ID查询角色列表
     * @return
     */
    @ApiOperation(value = "获取角色列表", notes = "根据用户ID查询角色列表")
    @ApiParam(name = "userId", value = "用户ID",type = "Long")
    @GetMapping(value = "{userId}")
    public CommonResponse<List<AuthRoleDto>> getAuthRoleByUserId(@PathVariable Long userId){
        return CommonResponse.success(roleService.getAuthRoleByUserId(userId));
    }

    @ApiOperation("给角色分配菜单")
    @PostMapping("/allocMenu")
    public CommonResponse allocMenu(AuthRoleMenuDto authRoleMenuDto){
        int count = roleService.updateRoleMenus(authRoleMenuDto.getRoleId(), authRoleMenuDto.getMenuIds());
        if (count >= 0) {
            return CommonResponse.success(count);
        }
        return CommonResponse.error(ResultCode.UNKNOWN_ERROR);
    }

    @ApiOperation("给角色分配资源")
    @PostMapping("/allocResource")
    public CommonResponse allocMenu(AuthRoleResourceDto authRoleResourceDto){
        int count = roleService.updateRoleResource(authRoleResourceDto.getRoleId(), authRoleResourceDto.getResourceIds());
        if (count >= 0) {
            return CommonResponse.success(count);
        }
        return CommonResponse.error(ResultCode.UNKNOWN_ERROR);
    }
}

package com.still.aikandy.auth.controller;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.auth.service.RoleService;
import com.still.aikandy.common.api.RestCode;
import com.still.aikandy.common.api.RestResponse;
import com.still.aikandy.common.dto.*;
import com.still.aikandy.common.querycondition.AuthRoleQueryCondition;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author Lee
 * @Description 角色
 * @Date 2020/6/22 17:48
 * @Version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/role")
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
    public RestResponse<Integer> addRole(@Validated(AuthRoleDto.AddAuthRoleGroup.class) @RequestBody AuthRoleDto authRoleDto){
        Integer count = roleService.addAuthRole(authRoleDto);
        return RestResponse.success(count);
    }

    /**
     * 删除角色信息
     * @param roleId 角色id
     * @return
     */
    @ApiOperation(value = "删除角色信息",notes = "删除角色信息")
    @ApiImplicitParam(name = "roleId", value = "角色id")
    @RequestMapping(value = "/{roleId}",method = RequestMethod.DELETE)
    public RestResponse<Integer> deleteRole(@PathVariable(value = "roleId") Long roleId){
        Integer count = roleService.deleteAuthRole(roleId);
        return RestResponse.success(count);
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
    public RestResponse<Integer> editRole(@NotNull @PathVariable(value = "roleId") Long roleId, AuthRoleDto authRoleDto){
        Integer count = roleService.updateAuthRole(roleId,authRoleDto);
        return RestResponse.success(count);
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
    public RestResponse<PageInfo<List<AuthRoleDto>>> queryActors(
            AuthRoleQueryCondition authRoleQueryCondition,
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize){
        return RestResponse.success(roleService.queryAuthRoles(authRoleQueryCondition, pageNum, pageSize));
    }

    /**
     * 根据条件查询角色信息（ALL）
     * @param authRoleQueryCondition 角色查询条件
     * @return
     */
    @ApiOperation(value = "获取角色列表（ALL）", notes = "根据条件查询角色分页列表")
    @GetMapping(value = "all")
    public RestResponse<List<AuthRoleDto>> queryActors(AuthRoleQueryCondition authRoleQueryCondition){
        return RestResponse.success(roleService.queryAuthRoles(authRoleQueryCondition));
    }

    /**
     * 根据用户ID查询角色列表
     * @return
     */
    @ApiOperation(value = "获取角色列表", notes = "根据用户ID查询角色列表")
    @ApiParam(name = "userId", value = "用户ID",type = "Long")
    @GetMapping(value = "{userId}")
    public RestResponse<List<AuthRoleDto>> getAuthRoleByUserId(@PathVariable Long userId){
        return RestResponse.success(roleService.getAuthRoleByUserId(userId));
    }

    @ApiOperation("给角色分配菜单")
    @PostMapping("/allocMenu")
    public RestResponse allocMenu(AuthRoleMenuDto authRoleMenuDto){
        int count = roleService.updateRoleMenus(authRoleMenuDto.getRoleId(), authRoleMenuDto.getMenuIds());
        if (count >= 0) {
            return RestResponse.success(count);
        }
        return RestResponse.error(RestCode.UNKNOWN_ERROR);
    }

    @ApiOperation("给角色分配资源")
    @PostMapping("/allocResource")
    public RestResponse allocMenu(AuthRoleResourceDto authRoleResourceDto){
        int count = roleService.updateRoleResource(authRoleResourceDto.getRoleId(), authRoleResourceDto.getResourceIds());
        if (count >= 0) {
            return RestResponse.success(count);
        }
        return RestResponse.error(RestCode.UNKNOWN_ERROR);
    }
}

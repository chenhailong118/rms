package com.still.aikandy.auth.controller;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.auth.service.MenuService;
import com.still.aikandy.common.api.CommonResponse;
import com.still.aikandy.common.dto.AuthMenuDto;
import com.still.aikandy.common.dto.AuthMenuTree;
import com.still.aikandy.common.querycondition.AuthMenuQueryCondition;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 菜单
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/auth/menu")
@Api(value = "菜单", description = "菜单API")
@Validated
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 添加菜单信息
     * @param authMenuDto 菜单信息
     * @return
     */
    @ApiOperation(value = "添加菜单信息",notes = "添加菜单信息")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public CommonResponse<Integer> addMenu(@Validated(AuthMenuDto.AddAuthMenuGroup.class) @RequestBody AuthMenuDto authMenuDto){
        Integer count = menuService.addAuthUser(authMenuDto);
        return CommonResponse.success(count);
    }

    /**
     * 删除菜单信息
     * @param menuId 菜单id
     * @return
     */
    @ApiOperation(value = "删除菜单信息",notes = "删除菜单信息")
    @ApiImplicitParam(name = "menuId", value = "菜单id")
    @RequestMapping(value = "/{menuId}",method = RequestMethod.DELETE)
    public CommonResponse<Integer> deleteMenu(@PathVariable(value = "menuId") Long menuId){
        Integer count = menuService.deleteAuthMenu(menuId);
        return CommonResponse.success(count);
    }

    /**
     * 修改菜单信息
     * @param menuId 菜单ID
     * @param authMenuDto 菜单信息
     * @return
     */
    @ApiOperation(value = "修改菜单信息",notes = "修改菜单信息")
    @ApiImplicitParam(name = "menuId", value = "菜单id")
    @RequestMapping(value = "/{menuId}",method = RequestMethod.PUT)
    public CommonResponse<Integer> editRole(@NotNull @PathVariable(value = "menuId") Long menuId, AuthMenuDto authMenuDto){
        Integer count = menuService.updateAuthMenu(menuId,authMenuDto);
        return CommonResponse.success(count);
    }

    /**
     * 查询菜单信息
     * @param authMenuQueryCondition 菜单查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @ApiOperation(value = "获取菜单列表(分页)", notes = "根据条件查询菜单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "Integer")
    })
    @GetMapping(value = "")
    public CommonResponse<PageInfo<List<AuthMenuDto>>> queryMenus(
            AuthMenuQueryCondition authMenuQueryCondition,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        return CommonResponse.success(menuService.queryAuthMenus(authMenuQueryCondition, pageNum, pageSize));
    }

    /**
     * 查询菜单信息（ALL）
     * @param authMenuQueryCondition 菜单查询条件
     * @return
     */
    @ApiOperation(value = "获取菜单列表（ALL）", notes = "根据条件查询菜单信息")
    @GetMapping(value = "all")
    public CommonResponse<List<AuthMenuDto>> queryMenus(AuthMenuQueryCondition authMenuQueryCondition){
        return CommonResponse.success(menuService.queryAuthMenus(authMenuQueryCondition));
    }

    /**
     * 查询菜单树形结构
     * @param authMenuQueryCondition 菜单查询条件
     * @return
     */
    @ApiOperation(value = "查询菜单树结构", notes = "根据条件查询菜单数结构")
    @GetMapping(value = "tree")
    public CommonResponse<List<AuthMenuTree>> queryMenuTree(AuthMenuQueryCondition authMenuQueryCondition){
        return CommonResponse.success(menuService.queryAuthMenusTree(authMenuQueryCondition));
    }

    /**
     * 根据角色ID查询菜单列表
     * @return
     */
    @ApiOperation(value = "获取菜单列表", notes = "根据角色ID查询菜单列表")
    @ApiParam(name = "roleId", value = "角色ID",type = "Long")
    @GetMapping(value = "{roleId}")
    public CommonResponse<List<AuthMenuDto>> getAuthMenuByRoleId(@PathVariable Long roleId){
        return CommonResponse.success(menuService.getAuthMenuByRoleId(roleId));
    }
}

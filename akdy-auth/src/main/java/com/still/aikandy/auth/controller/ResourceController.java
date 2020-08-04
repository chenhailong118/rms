package com.still.aikandy.auth.controller;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.auth.service.ResourceService;
import com.still.aikandy.common.api.CommonResponse;
import com.still.aikandy.common.dto.AuthResourceCategoryDto;
import com.still.aikandy.common.dto.AuthResourceDto;
import com.still.aikandy.common.querycondition.AuthResourceQueryCondition;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 资源
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/auth/resource")
@Api(value = "资源", description = "资源API")
@Validated
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 添加资源信息
     * @param authResourceDto 资源信息
     * @return
     */
    @ApiOperation(value = "添加资源信息",notes = "添加资源信息")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public CommonResponse<Integer> addResource(@Validated(AuthResourceDto.AddAuthResourceGroup.class) @RequestBody AuthResourceDto authResourceDto){
        Integer count = resourceService.addAuthResource(authResourceDto);
        return CommonResponse.success(count);
    }

    /**
     * 修改资源信息
     * @param id 资源ID
     * @param authResourceDto 资源信息
     * @return
     */
    @ApiOperation(value = "修改资源信息",notes = "修改资源信息")
    @ApiImplicitParam(name = "id", value = "资源id")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public CommonResponse<Integer> editResource(@NotNull(message = "id不能为空") @PathVariable(value = "id") Long id, AuthResourceDto authResourceDto){
        Integer count = resourceService.updateAuthResource(id,authResourceDto);
        return CommonResponse.success(count);
    }

    /**
     * 删除资源信息
     * @param id 资源id
     * @return
     */
    @ApiOperation(value = "删除资源信息",notes = "删除资源信息")
    @ApiImplicitParam(name = "id", value = "资源id")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public CommonResponse<Integer> deleteResource(@PathVariable(value = "id") Long id){
        Integer count = resourceService.deleteAuthResource(id);
        return CommonResponse.success(count);
    }

    /**
     * 根据条件查询资源信息（分页）
     * @param authResourceQueryCondition 资源查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @ApiOperation(value = "获取资源列表（分页）", notes = "根据条件查询资源信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "Integer")
    })
    @GetMapping(value = "")
    public CommonResponse<PageInfo<List<AuthResourceDto>>> queryResources(
            AuthResourceQueryCondition authResourceQueryCondition,
            @RequestParam(value = "pageNum",required = false) Integer pageNum,
            @RequestParam(value = "pageSize",required = false) Integer pageSize){
        return CommonResponse.success(resourceService.queryAuthResources(authResourceQueryCondition, pageNum, pageSize));
    }

    /**
     * 根据条件查询资源信息（ALL）
     * @param authResourceQueryCondition 资源查询条件
     * @return
     */
    @ApiOperation(value = "获取资源列表（ALL）", notes = "根据条件查询资源信息")
    @GetMapping(value = "all")
    public CommonResponse<List<AuthResourceDto>> queryResources(
            AuthResourceQueryCondition authResourceQueryCondition){
        return CommonResponse.success(resourceService.queryAuthResources(authResourceQueryCondition));
    }

    /**
     * 根据角色ID查询资源列表
     * @return
     */
    @ApiOperation(value = "获取资源列表", notes = "根据角色ID查询资源列表")
    @ApiParam(name = "roleId", value = "角色ID",type = "Long")
    @GetMapping(value = "{roleId}")
    public CommonResponse<List<AuthResourceDto>> getAuthResourceaByRoleId(@PathVariable Long roleId){
        return CommonResponse.success(resourceService.getAuthResourceaByRoleId(roleId));
    }
}

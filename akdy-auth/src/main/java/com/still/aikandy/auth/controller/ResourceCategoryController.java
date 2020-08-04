package com.still.aikandy.auth.controller;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.auth.service.ResourceCategoryService;
import com.still.aikandy.common.api.CommonResponse;
import com.still.aikandy.common.dto.AuthResourceCategoryDto;
import com.still.aikandy.common.dto.AuthUserDto;
import com.still.aikandy.common.querycondition.AuthResourceCategoryQueryCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 资源分类
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */

@Slf4j
@RestController
@RequestMapping("/auth/resourceCategory")
@Api(value = "资源分类", description = "资源分类API")
@Validated
public class ResourceCategoryController {

    private final ResourceCategoryService resourceCategoryService;

    public ResourceCategoryController(ResourceCategoryService resourceCategoryService) {
        this.resourceCategoryService = resourceCategoryService;
    }

    /**
     * 添加资源分类信息
     * @param authResourceCategoryDto 资源分类信息
     * @return
     */
    @ApiOperation(value = "添加资源分类信息",notes = "添加资源分类信息")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public CommonResponse<Integer> addResourceCategory(@Validated(AuthResourceCategoryDto.AddAuthResourceCategoryGroup.class) @RequestBody AuthResourceCategoryDto authResourceCategoryDto){
        Integer count = resourceCategoryService.addAuthResourceCategory(authResourceCategoryDto);
        return CommonResponse.success(count);
    }

    /**
     * 修改资源分类信息
     * @param id 资源分类ID
     * @param authResourceCategoryDto 资源分类信息
     * @return
     */
    @ApiOperation(value = "修改资源分类信息",notes = "修改资源分类信息")
    @ApiImplicitParam(name = "id", value = "资源分类id")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public CommonResponse<Integer> editResourceCategory(@NotNull(message = "id不能为空") @PathVariable(value = "id") Long id, AuthResourceCategoryDto authResourceCategoryDto){
        Integer count = resourceCategoryService.updateAuthResourceCategory(id,authResourceCategoryDto);
        return CommonResponse.success(count);
    }

    /**
     * 删除资源分类信息
     * @param id 资源分类id
     * @return
     */
    @ApiOperation(value = "删除资源分类信息",notes = "删除资源分类信息")
    @ApiImplicitParam(name = "id", value = "资源分类id")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public CommonResponse<Integer> deleteResourceCategory(@PathVariable(value = "id") Long id){
        Integer count = resourceCategoryService.deleteAuthResourceCategory(id);
        return CommonResponse.success(count);
    }

    /**
     * 根据条件查询资源分类信息(分页)
     * @param authResourceCategoryQueryCondition 资源分类查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @ApiOperation(value = "获取资源分类列表(分页)", notes = "根据条件查询资源分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "Integer")
    })
    @GetMapping(value = "")
    public CommonResponse<PageInfo<List<AuthResourceCategoryDto>>> queryResourceCategorys(
            AuthResourceCategoryQueryCondition authResourceCategoryQueryCondition,
            @RequestParam(value = "pageNum",required = false) Integer pageNum,
            @RequestParam(value = "pageSize",required = false) Integer pageSize){
        return CommonResponse.success(resourceCategoryService.queryAuthResourceCategorys(authResourceCategoryQueryCondition, pageNum, pageSize));
    }

    /**
     * 根据条件查询资源分类信息（ALL）
     * @param authResourceCategoryQueryCondition 资源分类查询条件
     * @return
     */
    @ApiOperation(value = "获取资源分类列表（ALL）", notes = "根据条件查询资源分类信息")
    @GetMapping(value = "all")
    public CommonResponse<List<AuthResourceCategoryDto>> queryResourceCategorys(
            AuthResourceCategoryQueryCondition authResourceCategoryQueryCondition){
        return CommonResponse.success(resourceCategoryService.queryAuthResourceCategorys(authResourceCategoryQueryCondition));
    }


}

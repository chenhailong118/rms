package com.still.rms.superstar.controller;

import com.github.pagehelper.PageInfo;
import com.still.rms.common.api.CommonResponse;
import com.still.rms.common.api.ResultCode;
import com.still.rms.common.dto.AuthRoleMenuDto;
import com.still.rms.common.dto.ResourceTagDto;
import com.still.rms.common.dto.TagDto;
import com.still.rms.common.dto.TagTree;
import com.still.rms.common.querycondition.TagQueryCondition;
import com.still.rms.superstar.service.TagService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @or FishAndFlower
 * @Description 标签
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/superstar/tag")
@Api(value = "标签", description = "标签API")
@Validated
public class TagController {

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * 添加标签信息
     * @param tagDto 标签信息
     * @return
     */
    @ApiOperation(value = "添加标签信息",notes = "添加标签信息")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public CommonResponse<Integer> addTag(@Validated(TagDto.AddTagGroup.class) @RequestBody TagDto tagDto){
        Integer count = tagService.addTag(tagDto);
        return CommonResponse.success(count);
    }

    /**
     * 删除标签信息
     * @param tagId 标签id
     * @return
     */
    @ApiOperation(value = "删除标签信息",notes = "删除标签信息")
    @ApiImplicitParam(name = "tagId", value = "标签id")
    @RequestMapping(value = "/{tagId}",method = RequestMethod.DELETE)
    public CommonResponse<Integer> deleteTag(@PathVariable(value = "tagId") Long tagId){
        Integer count = tagService.deleteTag(tagId);
        return CommonResponse.success(count);
    }

    /**
     * 修改标签信息
     * @param tagId 标签ID
     * @param tagDto 标签信息
     * @return
     */
    @ApiOperation(value = "修改标签信息",notes = "修改标签信息")
    @ApiImplicitParam(name = "tagId", value = "标签id")
    @RequestMapping(value = "/{tagId}",method = RequestMethod.PUT)
    public CommonResponse<Integer> editRole(@NotNull @PathVariable(value = "tagId") Long tagId, TagDto tagDto){
        Integer count = tagService.updateTag(tagId,tagDto);
        return CommonResponse.success(count);
    }

    /**
     * 查询标签信息
     * @param tagQueryCondition 标签查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @ApiOperation(value = "获取标签列表(分页)", notes = "根据条件查询标签信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "Integer")
    })
    @GetMapping(value = "")
    public CommonResponse<PageInfo<List<TagDto>>> queryTags(
            TagQueryCondition tagQueryCondition,
            @RequestParam(value = "pageNum", required = false) Integer pageNum,
            @RequestParam(value = "pageSize", required = false) Integer pageSize){
        return CommonResponse.success(tagService.queryTags(tagQueryCondition, pageNum, pageSize));
    }


    /**
     * 查询标签树形结构
     * @param tagQueryCondition 标签查询条件
     * @return
     */
    @ApiOperation(value = "查询标签树结构", notes = "根据条件查询标签数结构")
    @GetMapping(value = "tree")
    public CommonResponse<List<TagTree>> queryTagTree(TagQueryCondition tagQueryCondition){
        return CommonResponse.success(tagService.queryTagsTree(tagQueryCondition));
    }

    /**
     * 根据资源ID查询标签列表
     * @return
     */
    @ApiOperation(value = "获取标签列表", notes = "根据资源ID查询标签列表")
    @ApiParam(name = "resourceId", value = "资源ID",type = "Long")
    @GetMapping(value = "{resourceId}")
    public CommonResponse<List<TagDto>> getTagByRoleId(@PathVariable Long resourceId){
        return CommonResponse.success(tagService.getTagsByResourceId(resourceId));
    }
}

package com.still.rms.superstar.service;

import com.github.pagehelper.PageInfo;
import com.still.rms.common.dto.TagDto;
import com.still.rms.common.dto.TagTree;
import com.still.rms.common.querycondition.TagQueryCondition;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 标签Service
 * @Date 2020/8/19 11:24
 * @Version 1.0
 */
public interface TagService {
    /**
     * 添加标签信息
     * @param tagDto 标签信息
     * @return
     */
    Integer addTag(TagDto tagDto);

    /**
     * 根据标签ID删除标签信息
     * @param tagId 标签信息
     * @return
     */
    Integer deleteTag(Long tagId);

    /**
     * 修改标签信息
     * @param tagId 标签ID
     * @param tagDto 标签信息
     * @return
     */
    Integer updateTag(Long tagId, TagDto tagDto);

    /**
     * 根据条件查询标签
     * @param tagQueryCondition 标签查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    PageInfo<List<TagDto>> queryTags(TagQueryCondition tagQueryCondition, Integer pageNum, Integer pageSize);


    /**
     * 查询标签数结构
     * @param tagQueryCondition
     * @return
     */
    List<TagTree> queryTagsTree(TagQueryCondition tagQueryCondition);

    /**
     * 根据资源ID查询标签列表
     * @param resourceId
     * @return
     */
    List<TagDto> getTagsByResourceId(Long resourceId);
}

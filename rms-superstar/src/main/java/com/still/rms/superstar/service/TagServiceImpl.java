package com.still.rms.superstar.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.still.rms.common.api.ApiException;
import com.still.rms.common.api.ResultCode;
import com.still.rms.common.dto.TagDto;
import com.still.rms.common.dto.TagTree;
import com.still.rms.common.querycondition.TagQueryCondition;
import com.still.rms.mbg.mapper.TagMapper;
import com.still.rms.mbg.model.Tag;
import com.still.rms.superstar.dao.RelationResourceTagCustomMapper;
import com.still.rms.superstar.dao.TagCustomMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author FishAndFlower
 * @Description 标签Service
 * @Date 2020/8/19 11:24
 * @Version 1.0
 */
@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private TagCustomMapper tagCustomMapper;

    @Autowired
    private RelationResourceTagCustomMapper relationResourceTagCustomMapper;

    @Override
    public Integer addTag(TagDto tagDto) {
        //判断是否顶级标签
        if(tagDto.getParentId() != 0){
            //非顶级标签，判断父级标签是否存在
            Tag parantTag = tagMapper.selectByPrimaryKey(tagDto.getParentId());
            if(parantTag == null){
                //父级标签不存在，报错
                throw new ApiException(ResultCode.PARENT_MENU_NOT_FOUND);
            }else{
                //父级标签存在，设置本级标签等级
                tagDto.setLevel(parantTag.getLevel() + 1);
            }
        }else {
            //顶级标签，设置本级标签等级
            tagDto.setLevel(0);
        }
        tagDto.setCreateTime(new Date());
        return tagMapper.insert(tagDto);
    }

    @Override
    public Integer deleteTag(Long tagId) {
        if(tagMapper.selectByPrimaryKey(tagId) == null) {
            throw new ApiException(ResultCode.MENU_NOT_FOUND);
        }

        //删除资源标签关系
        relationResourceTagCustomMapper.deleteByTagId(tagId);
        return tagMapper.deleteByPrimaryKey(tagId);
    }

    @Override
    public Integer updateTag(Long tagId, TagDto tagDto) {
        if(tagMapper.selectByPrimaryKey(tagId) == null){
            throw new ApiException(ResultCode.MENU_NOT_FOUND);
        }
        tagDto.setId(tagId);
        tagDto.setCreateTime(null);
        tagDto.setUpdateTime(null);
        tagMapper.updateByPrimaryKeySelective(tagDto);
        return null;
    }

    @Override
    public PageInfo<List<TagDto>> queryTags(TagQueryCondition tagQueryCondition, Integer pageNum, Integer pageSize) {
        if(pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize,"sort desc");
        }else {
            PageHelper.orderBy("sort desc");
        }
        List<TagDto> users = tagCustomMapper.getTagsByCondition(tagQueryCondition);
        return new PageInfo(users);
    }


    @Override
    public List<TagTree> queryTagsTree(TagQueryCondition tagQueryCondition) {
        List<TagDto> tagList = tagCustomMapper.getTagsByCondition(tagQueryCondition);
        List<TagTree> result = tagList.stream()
                .filter(tag -> tag.getParentId().equals(0L))
                .map(tag -> covertTagNode(tag, tagList)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<TagDto> getTagsByResourceId(Long resourceId) {
        return tagCustomMapper.getTagsByResourceId(resourceId);
    }

    /**
     * 将标签列表转换成树形结构
     */
    private TagTree covertTagNode(TagDto tag, List<TagDto> tagList) {
        TagTree node = new TagTree();
        BeanUtils.copyProperties(tag, node);
        List<TagTree> children = tagList.stream()
                .filter(subTag -> subTag.getParentId().equals(tag.getId()))
                .map(subTag -> covertTagNode(subTag, tagList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}

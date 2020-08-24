package com.still.rms.superstar.service;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.still.rms.common.api.ApiException;
import com.still.rms.common.api.ResultCode;
import com.still.rms.common.dto.ActorIdAndName;
import com.still.rms.common.dto.ResourceDto;
import com.still.rms.common.querycondition.ResourceQueryCondition;
import com.still.rms.mbg.mapper.RealtionActorResourceMapper;
import com.still.rms.mbg.mapper.ResourceMapper;
import com.still.rms.mbg.model.AuthRoleMenu;
import com.still.rms.mbg.model.RealtionActorResource;
import com.still.rms.mbg.model.RelationResourceTag;
import com.still.rms.mbg.model.Resource;
import com.still.rms.superstar.dao.RealtionActorResourceCustomMapper;
import com.still.rms.superstar.dao.ResourceCustomMapper;
import com.still.rms.superstar.dao.RelationResourceTagCustomMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 资源
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceCustomMapper resourceCustomMapper;
    private final ResourceMapper resourceMapper;
    private final RealtionActorResourceMapper realtionActorResourceMapper;
    private final RealtionActorResourceCustomMapper realtionActorResourceCustomMapper;
    private final RelationResourceTagCustomMapper relationResourceTagCustomMapper;

    public ResourceServiceImpl(ResourceCustomMapper resourceCustomMapper, ResourceMapper resourceMapper, RealtionActorResourceMapper realtionActorResourceMapper, RealtionActorResourceCustomMapper realtionActorResourceCustomMapper, RelationResourceTagCustomMapper relationResourceTagCustomMapper) {
        this.resourceCustomMapper = resourceCustomMapper;
        this.resourceMapper = resourceMapper;
        this.realtionActorResourceMapper = realtionActorResourceMapper;
        this.realtionActorResourceCustomMapper = realtionActorResourceCustomMapper;
        this.relationResourceTagCustomMapper = relationResourceTagCustomMapper;
    }

    /**
     * 资源信息查询
     * @param resourceQueryCondition 查询条件
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public PageInfo<List<Resource>> queryResources(ResourceQueryCondition resourceQueryCondition, Integer pageNum, Integer pageSize) {
        if(pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        if(!StringUtils.isEmpty(resourceQueryCondition.getOrderStr())){
            PageHelper.orderBy(resourceQueryCondition.getOrderStr());
        }else{
            PageHelper.orderBy(" score desc ");
        }
        List<ResourceDto> actosList = resourceCustomMapper.getResourcesBySelective(resourceQueryCondition);
        actosList.stream().forEach(resourceDto -> {
            resourceDto.setActors(realtionActorResourceCustomMapper.queryActorIdsByResourceId(resourceDto.getId()));
        });
        PageInfo pageInfo = new PageInfo(actosList);
        return pageInfo;
    }


    /**
     * 添加资源信息
     * @param resource 资源信息
     * @return
     */
    @Override
    public Integer addResource(ResourceDto resource) {
        resource.setId(null);
        int count = resourceMapper.insertSelective(resource);
        if(count == 1 && resource.getActors() != null && resource.getActors().size() > 0){
            resource.getActors().stream().forEach( actorId -> {
                RealtionActorResource rav = new RealtionActorResource();
                rav.setActorId(actorId);
                rav.setResourceId(resource.getId());
                realtionActorResourceMapper.insertSelective(rav);
            });
        }
        return count;
    }

    /**
     * 修改资源信息
     * @param id 资源id
     * @param resourceDto 资源信息
     * @return
     */
    @Override
    public Integer updateResource(Long id, ResourceDto resourceDto) {
        if(id == null){
            throw new ApiException(ResultCode.ILLEGAL_PARAMS);
        }
        if(resourceMapper.selectByPrimaryKey(id) == null){
            throw new ApiException("ID不存在");
        }
        resourceDto.setId(id);
        int count = resourceMapper.updateByPrimaryKeyWithBLOBs(resourceDto);
        if(resourceDto.getActors() != null && resourceDto.getActors().size() > 0){
            realtionActorResourceCustomMapper.deleteByResourceId(id);
            resourceDto.getActors().stream().forEach(actorId -> {
                RealtionActorResource rav = new RealtionActorResource();
                rav.setActorId(actorId);
                rav.setResourceId(id);
                realtionActorResourceMapper.insertSelective(rav);
            });
        }
        return count;
    }

    /**
     * 删除资源信息
     * @param id 资源id
     * @return
     */
    @Override
    @Transactional
    public Integer deleteResource(Long id) {
        if(resourceMapper.selectByPrimaryKey(id) == null){
            throw new ApiException(ResultCode.USER_NOT_FOUND);
        }
        Resource resource = new Resource();
        resource.setId(id);
        resource.setStatus(0);
        Integer count = resourceMapper.updateByPrimaryKeySelective(resource);
        realtionActorResourceCustomMapper.deleteByResourceId(id);
        relationResourceTagCustomMapper.deleteByResourceId(id);
        return count;
    }

    /**
     * 根据资源ID查询演员ID列表
     * @param id 资源ID
     * @return
     */
    @Override
    public List<Long> queryActorIds(Long id) {
        return realtionActorResourceCustomMapper.queryActorIdsByResourceId(id);
    }

    @Override
    public int updateResourceTags(Long resourceId, List<Long> tagIds) {
        //删除资源旧标签关系
        relationResourceTagCustomMapper.deleteByResourceId(resourceId);

        //建立用户角色关系
        if(!CollectionUtil.isEmpty(tagIds)){
            List<RelationResourceTag> relationList = new ArrayList<>();
            tagIds.forEach(tagId -> {
                RelationResourceTag relation = new RelationResourceTag();
                relation.setResourceId(resourceId);
                relation.setTagId(tagId);
                relationList.add(relation);
            });
            return relationResourceTagCustomMapper.insertList(relationList);
        }
        return 0;
    }

    /**
     * 根据资源IDc查询演员列表
     * @param id
     * @return
     */
    @Override
    public List<ActorIdAndName> queryActors(Long id) {
        return resourceCustomMapper.queryActors(id);
    }
}

package com.still.rms.superstar.service;

import com.github.pagehelper.PageInfo;
import com.still.rms.common.dto.ActorIdAndName;
import com.still.rms.common.dto.ResourceDto;
import com.still.rms.common.querycondition.ResourceQueryCondition;
import com.still.rms.mbg.model.Resource;

import java.util.List;


/**
 * @Author FishAndFlower
 * @Description 资源
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface ResourceService {
    /**
     * 资源信息查询
     * @param resourceQueryCondition 查询条件
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return
     */
    PageInfo<List<Resource>> queryResources(ResourceQueryCondition resourceQueryCondition, Integer pageNum, Integer pageSize);

    /**
     * 添加资源信息
     * @param resource 资源信息
     * @return
     */
    Integer addResource(ResourceDto resource);

    /**
     * 修改资源信息
     * @param id 资源id
     * @param resourceDto 资源信息
     * @return
     */
    Integer updateResource(Long id, ResourceDto resourceDto);

    /**
     * 删除资源信息
     * @param id 资源id
     * @return
     */
    Integer deleteResource(Long id);

    /**
     * 根据资源ID查询演员ID列表
     * @param id 资源ID
     * @return
     */
    List<Long> queryActorIds(Long id);

    /**
     * 分配资源标签关系
     * @param resourceId 资源ID
     * @param tagIds 标签ID数组
     * @return
     */
    int updateResourceTags(Long resourceId, List<Long> tagIds);

    /**
     * 根据资源IDc查询演员列表
     * @param id
     * @return
     */
    List<ActorIdAndName> queryActors(Long id);
}

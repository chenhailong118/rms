package com.still.rms.superstar.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 演员资源关系Mapper
 * @Date 2020/8/15 23:57
 * @Version 1.0
 */
@Repository
public interface RealtionActorResourceCustomMapper {
    /**
     * 根据资源ID删除关系信息
     * @param resourceId
     * @return
     */
    Integer deleteByResourceId(@Param("resourceId") Long resourceId);

    List<Long> queryActorIdsByResourceId(@Param("resourceId") Long resourceId);
}

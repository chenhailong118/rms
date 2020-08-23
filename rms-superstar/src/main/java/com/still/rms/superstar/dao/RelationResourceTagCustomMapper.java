package com.still.rms.superstar.dao;

import com.still.rms.mbg.model.AuthRoleMenu;
import com.still.rms.mbg.model.RelationResourceTag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 资源标签关系自定义mapper
 * @Date 2020/8/22 12:14
 * @Version 1.0
 */
@Repository
public interface RelationResourceTagCustomMapper {

    Integer deleteByResourceId(@Param("resourceId") Long resourceId);

    /**
     * 批量插入资源标签关系
     * @param relationList
     * @return
     */
    Integer insertList(@Param("list") List<RelationResourceTag> relationList);

}

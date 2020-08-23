package com.still.rms.superstar.dao;

import com.still.rms.common.dto.TagDto;
import com.still.rms.common.querycondition.TagQueryCondition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 标签自定义mapper
 * @Date 2020/8/19 11:46
 * @Version 1.0
 */
@Repository
public interface TagCustomMapper {
    
    List<TagDto> getTagsByCondition(TagQueryCondition tagQueryCondition);

    List<TagDto> getTagsByResourceId(@Param("resourceId") Long resourceId);
}

package com.still.rms.superstar.dao;

import com.still.rms.common.dto.ResourceDto;
import com.still.rms.common.querycondition.ResourceQueryCondition;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 资源自定义Mapper接口
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Repository
public interface ResourceCustomMapper {
    List<ResourceDto> getResourcesBySelective(ResourceQueryCondition resourceQueryCondition);
}

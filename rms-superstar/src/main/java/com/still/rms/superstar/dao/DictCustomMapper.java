package com.still.rms.superstar.dao;

import com.still.rms.common.dto.DictinfoDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 数据字典自定义Mapper接口
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Repository
public interface DictCustomMapper {
    List<DictinfoDto> getDictsByName(@Param("name") String name);
}

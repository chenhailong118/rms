package com.still.rms.superstar.service;

import com.still.rms.common.dto.DictinfoDto;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 数据字典
 * @Date 2020/8/14 14:10
 * @Version 1.0
 */
public interface DictService {
    /**
     * 根据名称查询数据字段
     * @param name 字典名称
     * @return
     */
    List<DictinfoDto> getDictsByName(String name);
}

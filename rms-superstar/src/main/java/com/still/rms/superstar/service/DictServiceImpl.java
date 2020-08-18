package com.still.rms.superstar.service;

import com.still.rms.common.dto.DictinfoDto;
import com.still.rms.mbg.model.Dictinfo;
import com.still.rms.superstar.dao.DictCustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description TODO
 * @Date 2020/8/14 14:13
 * @Version 1.0
 */
@Service
public class DictServiceImpl implements DictService{

    @Autowired
    private DictCustomMapper dictCustomMapper;

    /**
     * 根据名称查询数据字段
     * @param name 字典名称
     * @return
     */
    @Override
    public List<DictinfoDto> getDictsByName(String name) {
        return dictCustomMapper.getDictsByName(name);
    }
}

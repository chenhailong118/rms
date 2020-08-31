package com.still.rms.superstar.dao;

import com.still.rms.common.dto.LogDto;
import com.still.rms.common.querycondition.LogQueryCondition;
import com.still.rms.mbg.model.Log;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 日志自定义Mapper
 * @Date 2020/8/30 11:35
 * @Version 1.0
 */
public interface LogCustomMapper {
    List<Log> queryLogs(LogQueryCondition logQueryCondition);
}

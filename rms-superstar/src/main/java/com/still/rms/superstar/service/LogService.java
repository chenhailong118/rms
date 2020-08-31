package com.still.rms.superstar.service;

import com.github.pagehelper.PageInfo;
import com.still.rms.common.querycondition.LogQueryCondition;
import com.still.rms.mbg.model.Log;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 日志Service
 * @Date 2020/8/30 11:55
 * @Version 1.0
 */
public interface LogService {
    /**
     * 查询日志列表
     * @param logQueryCondition
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<List<Log>> queryLogs(LogQueryCondition logQueryCondition, Integer pageNum, Integer pageSize);

    /**
     * 根据日志ID查询日志详情
     * @param id
     * @return
     */
    Log queryLogById(Long id);
}

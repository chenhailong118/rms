package com.still.rms.superstar.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.still.rms.common.dto.TagDto;
import com.still.rms.common.querycondition.LogQueryCondition;
import com.still.rms.mbg.mapper.LogMapper;
import com.still.rms.mbg.model.Log;
import com.still.rms.superstar.dao.LogCustomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 日志Service
 * @Date 2020/8/30 11:56
 * @Version 1.0
 */
@Service
public class LogServiceImpl implements LogService{
    @Autowired
    private LogCustomMapper logCustomMapper;
    @Autowired
    private LogMapper logMapper;

    /**
     * 根据条件查询日志信息
     * @param logQueryCondition
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<List<Log>> queryLogs(LogQueryCondition logQueryCondition, Integer pageNum, Integer pageSize) {
        if(pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize,"id desc");
        }else {
            PageHelper.orderBy("id desc");
        }
        List<Log> logs = logCustomMapper.queryLogs(logQueryCondition);
        return new PageInfo(logs);
    }

    /**
     * 根据日志ID查询日志详情
     * @param id
     * @return
     */
    @Override
    public Log queryLogById(Long id) {
        return logMapper.selectByPrimaryKey(id);
    }
}

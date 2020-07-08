package com.still.aikandy.apigateway.service;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.common.api.RestResponse;
import com.still.aikandy.common.querycondition.ActorQueryCondition;
import com.still.aikandy.mbg.model.Actor;

import java.util.List;


/**
 * @Author Lee
 * @Description 演员Service
 * @Date 2020/5/22 21:41
 * @Version 1.0
 */
public interface ActorService {

    /**
     *
     * @param actorQueryCondition 演员查询条件
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return
     */
    RestResponse queryActors(ActorQueryCondition actorQueryCondition, Integer pageNum, Integer pageSize);
}

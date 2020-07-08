package com.still.aikandy.apigateway.service;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.apigateway.feignclient.ActorFc;
import com.still.aikandy.common.api.RestResponse;
import com.still.aikandy.common.querycondition.ActorQueryCondition;
import com.still.aikandy.mbg.model.Actor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author Lee
 * @Description 演员Service实现类
 * @Date 2020/5/22 21:41
 * @Version 1.0
 */
@Service
public class ActorServiceImpl implements ActorService {

    private final ActorFc actorFc;

    public ActorServiceImpl(ActorFc actorFc) {
        this.actorFc = actorFc;
    }


    /**
     *
     * @param actorQueryCondition 演员查询条件
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public RestResponse queryActors(ActorQueryCondition actorQueryCondition, Integer pageNum, Integer pageSize) {
        return actorFc.queryActors(actorQueryCondition, pageNum, pageSize);
    }
}

package com.still.aikandy.apigateway.feignclient;

import com.still.aikandy.common.api.RestCode;
import com.still.aikandy.common.api.RestResponse;
import com.still.aikandy.common.querycondition.ActorQueryCondition;
import org.springframework.stereotype.Component;


/**
 * @Author Lee
 * @Description 演员查询回调函数
 * @Date 2020/5/25 21:32
 * @Version 1.0
 */
@Component
public class ActorFcFallback implements ActorFc {
    @Override
    public RestResponse queryActors(ActorQueryCondition actorQueryCondition, Integer pageNum, Integer pageSize) {
        return RestResponse.error(RestCode.HYSTRIX_DONOTHING_ERROR);
    }
}

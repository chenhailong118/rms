package com.still.aikandy.apigateway.feignclient;

import com.still.aikandy.common.api.RestResponse;
import com.still.aikandy.common.querycondition.ActorQueryCondition;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * @Author Lee
 * @Description 演员远程调用
 * @Date 2020/5/24 23:56
 * @Version 1.0
 */
@FeignClient(value = "actor", fallback = ActorFcFallback.class)
public interface ActorFc {

    /**
     * @param actorQueryCondition 查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @RequestMapping(value = "actor", method = RequestMethod.GET)
    RestResponse queryActors(@RequestBody ActorQueryCondition actorQueryCondition, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize);
}

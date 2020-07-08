package com.still.aikandy.apigateway.controller;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.apigateway.service.ActorService;
import com.still.aikandy.common.api.RestResponse;
import com.still.aikandy.common.querycondition.ActorQueryCondition;
import com.still.aikandy.mbg.model.Actor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Lee
 * @Description 演员Controller
 * @Date 2020/5/25 0:17
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/actor")
@Api(value = "演员", description = "演员查询接口")
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    /**
     * 根据条件查询演员信息
     * @param actorQueryAddtion 演员查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @ApiOperation(value = "获取演员列表", notes = "根据条件查询演员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public RestResponse queryActors(
            ActorQueryCondition actorQueryAddtion,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        RestResponse reslult = actorService.queryActors(actorQueryAddtion,pageNum,pageSize);
        return reslult;
    }
}

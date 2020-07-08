package com.still.aikandy.actor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.still.aikandy.common.dto.ActorDto;
import com.still.aikandy.common.querycondition.ActorQueryCondition;
import com.still.aikandy.actor.service.ActorService;
import com.still.aikandy.common.api.RestResponse;
import com.still.aikandy.mbg.model.Actor;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author Lee
 * @Description 演员
 * @Date 2020/5/22 21:37
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/actor")
@Api(value = "演员", description = "演员API")
@Validated
public class ActorController {
    private final ActorService actorService;

    private final ObjectMapper objectMapper;

    public ActorController(ActorService actorService, ObjectMapper objectMapper) {
        this.actorService = actorService;
        this.objectMapper = objectMapper;
    }

    /**
     * 根据条件查询演员信息
     * @param actorQueryCondition 演员查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @ApiOperation(value = "获取演员列表", notes = "根据条件查询演员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "查询页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数", dataType = "Integer")
    })
    @RequestMapping(value = "", method = RequestMethod.GET)
    public RestResponse<PageInfo<List<Actor>>> queryActors(
            ActorQueryCondition actorQueryCondition,
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
        return RestResponse.success(actorService.queryActors(actorQueryCondition, pageNum, pageSize));
    }

    /**
     * 添加演员信息
     * @param actor 演员信息
     * @return
     */
    @ApiOperation(value = "添加演员信息",notes = "添加演员信息")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public RestResponse<Integer> addActor(@Validated(ActorDto.AddActorGroup.class) @RequestBody ActorDto actor){
        Integer count = actorService.addActor(actor);
        return RestResponse.success(count);
    }

    /**
     * 修改演员信息
     * @param id 演员id
     * @param actorDto 演员信息
     * @return
     */
    @ApiOperation(value = "修改演员信息",notes = "修改演员信息")
    @ApiImplicitParam(name = "id", value = "演员id")
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public RestResponse<Integer> editActor(@NotNull @PathVariable(value = "id") Integer id, @Validated @RequestBody ActorDto actorDto){
        Integer count = actorService.updateActor(id,actorDto);
        return RestResponse.success(count);
    }

    /**
     * 删除演员信息
     * @param id 演员id
     * @return
     */
    @ApiOperation(value = "删除演员信息",notes = "删除演员信息")
    @ApiImplicitParam(name = "id", value = "演员id")
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public RestResponse<Integer> deleteActor(@PathVariable(value = "id") Integer id){
        Integer count = actorService.deleteActor(id);
        return RestResponse.success(count);
    }
}

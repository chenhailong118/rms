package com.still.rms.superstar.service;

import com.github.pagehelper.PageInfo;
import com.still.rms.common.dto.ActorDto;
import com.still.rms.common.querycondition.ActorQueryCondition;
import com.still.rms.mbg.model.Actor;

import java.util.List;


/**
 * @Author FishAndFlower
 * @Description 演员
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface ActorService {
    /**
     * 演员信息查询
     * @param actorQueryCondition 查询条件
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return
     */
    PageInfo<List<Actor>> queryActors(ActorQueryCondition actorQueryCondition, Integer pageNum, Integer pageSize);

    /**
     * 添加演员信息
     * @param actor 演员信息
     * @return
     */
    Integer addActor(Actor actor);

    /**
     * 修改演员信息
     * @param id 演员id
     * @param actorDto 演员信息
     * @return
     */
    Integer updateActor(Integer id, ActorDto actorDto);

    /**
     * 删除演员信息
     * @param id 演员id
     * @return
     */
    Integer deleteActor(Integer id);
}

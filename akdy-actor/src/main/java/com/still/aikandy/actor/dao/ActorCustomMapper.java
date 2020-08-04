package com.still.aikandy.actor.dao;

import com.still.aikandy.common.querycondition.ActorQueryCondition;
import com.still.aikandy.mbg.model.Actor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 演员自定义Mapper接口
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Repository
public interface ActorCustomMapper {
    List<Actor> getActorsBySelective(ActorQueryCondition actorQueryCondition);
}

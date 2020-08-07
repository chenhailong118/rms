package com.still.rms.actor.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.still.rms.actor.dao.ActorCustomMapper;
import com.still.rms.common.api.ApiException;
import com.still.rms.common.dto.ActorDto;
import com.still.rms.common.querycondition.ActorQueryCondition;
import com.still.rms.common.api.ResultCode;
import com.still.rms.mbg.mapper.ActorMapper;
import com.still.rms.mbg.model.Actor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 演员
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Service
public class ActorServiceImpl implements ActorService {

    private final ActorCustomMapper actorCustomMapper;
    private final ActorMapper actorMapper;

    public ActorServiceImpl(ActorCustomMapper actorCustomMapper, ActorMapper actorMapper) {
        this.actorCustomMapper = actorCustomMapper;
        this.actorMapper = actorMapper;
    }

    /**
     * 演员信息查询
     * @param actorQueryCondition 查询条件
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public PageInfo<List<Actor>> queryActors(ActorQueryCondition actorQueryCondition, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        if(!StringUtils.isEmpty(actorQueryCondition.getOrderStr())){
            PageHelper.orderBy(actorQueryCondition.getOrderStr());
        }
        if(actorQueryCondition.getStatus() == null){
            actorQueryCondition.setStatus(1);
        }
        List<Actor> actosList = actorCustomMapper.getActorsBySelective(actorQueryCondition);
        PageInfo pageInfo = new PageInfo(actosList);
        return pageInfo;
    }


    /**
     * 添加演员信息
     * @param actor 演员信息
     * @return
     */
    @Override
    public Integer addActor(Actor actor) {
        ActorQueryCondition condition = new ActorQueryCondition();
        condition.setName(actor.getName());
        if(actorCustomMapper.getActorsBySelective(condition).size() > 0){
            throw new ApiException("该用户名已存在");
        }
        actor.setId(null);
        return actorMapper.insertSelective(actor);
    }

    /**
     * 修改演员信息
     * @param id 演员id
     * @param actorDto 演员信息
     * @return
     */
    @Override
    public Integer updateActor(Integer id, ActorDto actorDto) {
        if(id == null){
            throw new ApiException(ResultCode.ILLEGAL_PARAMS);
        }
        if(actorMapper.selectByPrimaryKey(id) == null){
            throw new ApiException("ID不存在");
        }
        actorDto.setId(id);
        return actorMapper.updateByPrimaryKey(actorDto);
    }

    /**
     * 删除演员信息
     * @param id 演员id
     * @return
     */
    @Override
    public Integer deleteActor(Integer id) {
        if(actorMapper.selectByPrimaryKey(id) == null){
            throw new ApiException(ResultCode.USER_NOT_FOUND);
        }
        Actor actor = new Actor();
        actor.setId(id);
        actor.setStatus(0);
        return actorMapper.updateByPrimaryKeySelective(actor);
    }
}

package com.still.aikandy.mbg.mapper;

import com.still.aikandy.mbg.model.RealtionActorDictinfo;

public interface RealtionActorDictinfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table realtion_actor_dictinfo
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table realtion_actor_dictinfo
     *
     * @mbggenerated
     */
    int insert(RealtionActorDictinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table realtion_actor_dictinfo
     *
     * @mbggenerated
     */
    int insertSelective(RealtionActorDictinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table realtion_actor_dictinfo
     *
     * @mbggenerated
     */
    RealtionActorDictinfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table realtion_actor_dictinfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RealtionActorDictinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table realtion_actor_dictinfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RealtionActorDictinfo record);
}
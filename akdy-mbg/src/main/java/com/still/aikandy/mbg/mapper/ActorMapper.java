package com.still.aikandy.mbg.mapper;

import com.still.aikandy.mbg.model.Actor;

public interface ActorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbggenerated
     */
    int insert(Actor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbggenerated
     */
    int insertSelective(Actor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbggenerated
     */
    Actor selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Actor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(Actor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table actor
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Actor record);
}
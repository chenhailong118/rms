package com.still.rms.mbg.mapper;

import com.still.rms.mbg.model.Dictinfo;

public interface DictinfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictinfo
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictinfo
     *
     * @mbggenerated
     */
    int insert(Dictinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictinfo
     *
     * @mbggenerated
     */
    int insertSelective(Dictinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictinfo
     *
     * @mbggenerated
     */
    Dictinfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictinfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Dictinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dictinfo
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Dictinfo record);
}
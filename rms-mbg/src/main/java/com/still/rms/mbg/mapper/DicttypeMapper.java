package com.still.rms.mbg.mapper;

import com.still.rms.mbg.model.Dicttype;

public interface DicttypeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dicttype
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dicttype
     *
     * @mbggenerated
     */
    int insert(Dicttype record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dicttype
     *
     * @mbggenerated
     */
    int insertSelective(Dicttype record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dicttype
     *
     * @mbggenerated
     */
    Dicttype selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dicttype
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Dicttype record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dicttype
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Dicttype record);
}
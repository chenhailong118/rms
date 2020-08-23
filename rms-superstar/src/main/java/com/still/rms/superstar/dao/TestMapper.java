package com.still.rms.superstar.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author FishAndFlower
 * @Description TODO
 * @Date 2020/8/22 18:15
 * @Version 1.0
 */
@Repository
public interface TestMapper {

    @Select("select id,tags from resource")
    List<Map<String,Object>> getIdAndTagIds();

    @Select("select id from tag where `name` =  (select `name` from dictinfo where dicttype_id = 2 and id = #{id}) and parent_id != 0")
    Long getId(@Param("id") Integer id);

}

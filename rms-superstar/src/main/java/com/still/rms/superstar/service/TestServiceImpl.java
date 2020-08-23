package com.still.rms.superstar.service;

import com.still.rms.mbg.mapper.RelationResourceTagMapper;
import com.still.rms.mbg.model.RelationResourceTag;
import com.still.rms.superstar.dao.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @Author FishAndFlower
 * @Description TODO
 * @Date 2020/8/22 19:29
 * @Version 1.0
 */
@Service
public class TestServiceImpl {
    @Autowired
    private TestMapper testMapper;

    @Autowired
    private RelationResourceTagMapper relationResourceTagMapper;

    public void test(){
        List<Map<String,Object>> list = testMapper.getIdAndTagIds();
        list.forEach(map -> {
            System.out.println(map.get("id"));
            String[] tagArr = ((String)map.get("tags")).split(",");
            for (String id : tagArr) {
                if(!StringUtils.isEmpty(id)){
                    Long tagId = testMapper.getId(Integer.parseInt(id));
                    RelationResourceTag relationResourceTag = new RelationResourceTag();
                    relationResourceTag.setTagId(tagId);
                    relationResourceTag.setResourceId(new Long((Integer)map.get("id")));
                    relationResourceTagMapper.insert(relationResourceTag);
                }
            }
        });
    }

}

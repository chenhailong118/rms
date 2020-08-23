package com.still.rms.common.dto;

import com.still.rms.mbg.model.Tag;
import lombok.Data;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 标签树形结构对象
 * @Date 2020/8/19 11:17
 * @Version 1.0
 */
@Data
public class TagTree extends Tag {
    private List<TagTree> children;
}

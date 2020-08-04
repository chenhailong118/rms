package com.still.aikandy.common.dto;

import com.still.aikandy.mbg.model.AuthMenu;
import lombok.Data;

import java.util.List;

/**
 * @Author FishAndFlower
 * @Description 菜单树形结构对象
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
@Data
public class AuthMenuTree extends AuthMenu {
    private List<AuthMenuTree> children;
}

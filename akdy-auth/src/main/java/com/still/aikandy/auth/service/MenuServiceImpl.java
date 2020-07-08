package com.still.aikandy.auth.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.still.aikandy.auth.dao.AuthMenuCustomMapper;
import com.still.aikandy.auth.dao.AuthRoleMenuCustomMapper;
import com.still.aikandy.common.api.RestCode;
import com.still.aikandy.common.api.RestException;
import com.still.aikandy.common.dto.AuthMenuDto;
import com.still.aikandy.common.dto.AuthMenuTree;
import com.still.aikandy.common.querycondition.AuthMenuQueryCondition;
import com.still.aikandy.mbg.mapper.AuthMenuMapper;
import com.still.aikandy.mbg.model.AuthMenu;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Lee
 * @Description 角色service实现类
 * @Date 2020/6/24 16:19
 * @Version 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    private final AuthMenuCustomMapper authMenuCustomMapper;
    private final AuthMenuMapper authMenuMapper;
    private final AuthRoleMenuCustomMapper authRoleMenuCustomMapper;

    public MenuServiceImpl(AuthMenuCustomMapper authMenuCustomMapper, AuthMenuMapper authMenuMapper, AuthRoleMenuCustomMapper authRoleMenuCustomMapper) {
        this.authMenuCustomMapper = authMenuCustomMapper;
        this.authMenuMapper = authMenuMapper;
        this.authRoleMenuCustomMapper = authRoleMenuCustomMapper;
    }


    /**
     * 根据用户ID查询用户菜单
     * @param userId 用户ID
     * @return
     */
    @Override
    public List<AuthMenuDto> getMenuBYUserId(Long userId) {
        return authMenuCustomMapper.getMenuBYUserId(userId);
    }

    /**
     * 根据条件查询菜单
     * @param authMenuQueryCondition 菜单查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public PageInfo<List<AuthMenuDto>> queryAuthMenus(AuthMenuQueryCondition authMenuQueryCondition, Integer pageNum, Integer pageSize) {
        if(pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize,"sort desc");
        }else {
            PageHelper.orderBy("sort desc");
        }
        List<AuthMenuDto> users = authMenuCustomMapper.getAuthMenusByCondition(authMenuQueryCondition);
        return new PageInfo(users);
    }

    /**
     * 根据条件查询菜单
     * @param authMenuQueryCondition 菜单查询条件
     * @return
     */
    @Override
    public List<AuthMenuDto> queryAuthMenus(AuthMenuQueryCondition authMenuQueryCondition) {
        PageHelper.orderBy("sort desc");
        return authMenuCustomMapper.getAuthMenusByCondition(authMenuQueryCondition);
    }

    /**
     * 根据角色ID查询菜单列表
     * @param roleId 角色ID
     * @return
     */
    @Override
    public List<AuthMenuDto> getAuthMenuByRoleId(Long roleId) {
        return authMenuCustomMapper.getAuthMenuByRoleId(roleId);
    }

    /**
     * 查询菜单数结构
     * @param authMenuQueryCondition 菜单查询条件
     * @return
     */
    @Override
    public List<AuthMenuTree> queryAuthMenusTree(AuthMenuQueryCondition authMenuQueryCondition) {
        List<AuthMenuDto> menuList = authMenuCustomMapper.getAuthMenusByCondition(authMenuQueryCondition);
        List<AuthMenuTree> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
        return result;
    }

    /**
     * 修改菜单信息
     * @param menuId 菜单ID
     * @param authMenuDto 菜单信息
     * @return
     */
    @Override
    public Integer updateAuthMenu(Long menuId, AuthMenuDto authMenuDto) {
        if(authMenuMapper.selectByPrimaryKey(menuId) == null){
            throw new RestException(RestCode.MENU_NOT_FOUND);
        }
        authMenuDto.setId(menuId);
        authMenuMapper.updateByPrimaryKeySelective(authMenuDto);
        return null;
    }

    /**
     * 添加菜单信息
     * @param authMenuDto 菜单信息
     * @return
     */
    @Override
    public Integer addAuthUser(AuthMenuDto authMenuDto) {
        //判断是否顶级菜单
        if(authMenuDto.getParentId() != 0){
            //非顶级菜单，判断父级菜单是否存在
            AuthMenu parantMenu = authMenuMapper.selectByPrimaryKey(authMenuDto.getParentId());
            if(parantMenu == null){
                //父级菜单不存在，报错
                throw new RestException(RestCode.PARENT_MENU_NOT_FOUND);
            }else{
                //父级菜单存在，设置本级菜单等级
                authMenuDto.setLevel(parantMenu.getLevel() + 1);
            }
        }else {
            //顶级菜单，设置本级菜单等级
            authMenuDto.setLevel(0);
        }
        authMenuDto.setCreateTime(new Date());
        return authMenuMapper.insert(authMenuDto);
    }

    /**
     * 根据菜单ID删除菜单信息
     * @param menuId 菜单信息
     * @return
     */
    @Override
    @Transactional
    public Integer deleteAuthMenu(Long menuId) {
        if(authMenuMapper.selectByPrimaryKey(menuId) == null) {
            throw new RestException(RestCode.MENU_NOT_FOUND);
        }

        //删除角色菜单关系
        authRoleMenuCustomMapper.deleteByMenuId(menuId);
        return authMenuMapper.deleteByPrimaryKey(menuId);
    }

    /**
     * 将菜单列表转换成树形结构
     */
    private AuthMenuTree covertMenuNode(AuthMenuDto menu, List<AuthMenuDto> menuList) {
        AuthMenuTree node = new AuthMenuTree();
        BeanUtils.copyProperties(menu, node);
        List<AuthMenuTree> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
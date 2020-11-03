package com.still.rms.auth.service;

import com.github.pagehelper.PageInfo;
import com.still.rms.common.dto.AuthUserDto;
import com.still.rms.common.dto.AuthUserLoginParam;
import com.still.rms.common.querycondition.AuthUserQueryCondition;
import com.still.rms.mbg.model.AuthResource;
import com.still.rms.mbg.model.AuthUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @Author FishAndFlower
 * @Description 用户Service接口
 * @Date 2020/8/4 10:51
 * @Version 1.0
 */
public interface UserService {

    /**
     * 添加用户信息
     * @param authUserDto 用户信息
     * @return
     */
    Integer addAuthUser(AuthUserDto authUserDto) throws IOException;

    /**
     * 删除用户信息
     * @param userId 用户ID
     * @return
     */
    Integer deleteAuthUser(Long userId);

    /**
     * 修改用户信息
     * @param userId 用户ID
     * @param authUserDto 用户信息
     * @return
     */
    Integer updateAuthUser(Long userId, AuthUserDto authUserDto);

    /**
     * 查询用户信息
     * @param authUserQueryCondition 用户查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    PageInfo<List<AuthUserDto>> queryAuthUsers(AuthUserQueryCondition authUserQueryCondition, Integer pageNum, Integer pageSize);


    /**
     * 分配用户角色
     * @param userId
     * @param roleIds
     * @return
     */
    Integer updateUserRole(Long userId, List<Long> roleIds);

    /**
     * 根据用户名查询用户信息（支持昵称、手机号码、邮箱查询）
     * @param username
     * @return
     */
    AuthUser getUserByUsername(String username);

    /**
     * 用户登录
     * @param authUserLoginParam 登录参数
     * @return
     */
    String login(AuthUserLoginParam authUserLoginParam);

    /**
     * 用户登出
     * @param token
     */
    void logout(String token);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    Map<String,Object> getUserInfo(String token);

    /**
     * 根据用户ID获取用户资源列表
     * @param userId
     * @return
     */
    List<AuthResource> getResourcesByUserId(Long userId);
}

package com.still.aikandy.apigateway.service.auth;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.common.api.RestResponse;
import com.still.aikandy.common.dto.AuthUserDto;
import com.still.aikandy.common.dto.AuthUserLoginParam;
import com.still.aikandy.common.dto.AuthUserRoleDto;
import com.still.aikandy.common.dto.UrlsDto;
import com.still.aikandy.common.querycondition.AuthUserQueryCondition;
import com.still.aikandy.mbg.model.AuthUser;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author Lee
 * @Description 用户Service接口
 * @Date 2020/6/22 17:52
 * @Version 1.0
 */
public interface AuthUserService {

    /**
     * 添加用户信息
     * @param authUserDto 用户信息
     * @return
     */
    RestResponse<Integer> addAuthUser(AuthUserDto authUserDto) throws IOException;

    /**
     * 删除用户信息
     * @param userId 用户ID
     * @return
     */
    RestResponse<Integer> deleteAuthUser(Long userId);

    /**
     * 修改用户信息
     * @param userId 用户ID
     * @param authUserDto 用户信息
     * @return
     */
    RestResponse<Integer> updateAuthUser(Long userId, AuthUserDto authUserDto);

    /**
     * 查询用户信息
     * @param authUserQueryCondition 用户查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    RestResponse<PageInfo> queryAuthUsers(AuthUserQueryCondition authUserQueryCondition, Integer pageNum, Integer pageSize);

    /**
     * 分配用户角色
     * @param authUserRoleDto 用户角色信息
     * @return
     */
    RestResponse<Integer> updateUserRole(AuthUserRoleDto authUserRoleDto);

//    /**
//     * 用户头像上传
//     * @param icon 用户头像
//     * @return
//     */
//    RestResponse<String> iconUpload(MultipartFile icon);
//
//    /**
//     * 获取URL信息
//     * @return
//     */
//    RestResponse<UrlsDto> getUrls();

}

package com.still.aikandy.apigateway.service.auth;

import com.github.pagehelper.PageInfo;
import com.still.aikandy.apigateway.feignclient.AuthUserFc;
import com.still.aikandy.common.api.RestResponse;
import com.still.aikandy.common.dto.AuthUserDto;
import com.still.aikandy.common.dto.AuthUserRoleDto;
import com.still.aikandy.common.querycondition.AuthUserQueryCondition;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @Author Lee
 * @Description 用户Service实现类
 * @Date 2020/6/22 17:53
 * @Version 1.0
 */
@Service
public class AuthUserServiceImpl implements AuthUserService {

    private final AuthUserFc authUserFc;

    public AuthUserServiceImpl(AuthUserFc authUserFc) {
        this.authUserFc = authUserFc;
    }


    /**
     * 添加用户信息
     * @param authUserDto 用户信息
     * @return
     */
    @Override
    public RestResponse<Integer> addAuthUser(AuthUserDto authUserDto) throws IOException {
        return authUserFc.addUser(authUserDto);
    }

    /**
     * 删除用户信息
     * @param userId 用户ID
     * @return
     */
    @Override
    @Transactional
    public RestResponse<Integer> deleteAuthUser(Long userId) {
        return authUserFc.deleteUser(userId);
    }

    /**
     * 修改用户信息
     * @param userId 用户ID
     * @param authUserDto 用户信息
     * @return
     */
    @Override
    public RestResponse<Integer> updateAuthUser(Long userId, AuthUserDto authUserDto) {
            return authUserFc.editUser(userId,authUserDto);
    }

    /**
     * 查询用户信息
     * @param authUserQueryCondition 用户查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public RestResponse<PageInfo> queryAuthUsers(AuthUserQueryCondition authUserQueryCondition, Integer pageNum, Integer pageSize) {
        return authUserFc.queryUsers(authUserQueryCondition,pageNum,pageSize);
    }

    /**
     * 分配用户角色
     * @param authUserRoleDto 用户角色信息
     * @return
     */
    @Override
    @Transactional
    public RestResponse<Integer> updateUserRole(AuthUserRoleDto authUserRoleDto) {
        return authUserFc.updateUserRole(authUserRoleDto);
    }

//    /**
//     * 用户头像上传
//     * @param icon 用户头像
//     * @return
//     */
//    @Override
//    public RestResponse<String> iconUpload(MultipartFile icon) {
//        try {
//            return authProperties.getIconPath() + FileUtil.saveToLocal(icon,authProperties.getBasePath() + authProperties.getIconPath());
//        } catch (IOException e) {
//            throw new RestException(RestCode.ICON_PARSE_WRONG);
//        }
//    }
//
//    /**
//     * 获取URL信息
//     * @return
//     */
//    @Override
//    public RestResponse<UrlsDto> getUrls() {
//        UrlsDto urlsDto = new UrlsDto();
//        urlsDto.setPostIconUrl(authProperties.getPostIconUrl());
//        urlsDto.setImageServer(authProperties.getImageServer());
//        return urlsDto;
//    }

}

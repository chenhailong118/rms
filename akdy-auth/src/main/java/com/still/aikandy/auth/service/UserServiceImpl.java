package com.still.aikandy.auth.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.system.UserInfo;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.still.aikandy.auth.config.AuthProperties;
import com.still.aikandy.auth.dao.AuthUserCustomMapper;
import com.still.aikandy.auth.dao.AuthUserRoleCustomMapper;
import com.still.aikandy.common.api.RestCode;
import com.still.aikandy.common.api.RestException;
import com.still.aikandy.common.dto.AuthUserDto;
import com.still.aikandy.common.dto.AuthUserLoginParam;
import com.still.aikandy.common.dto.UrlsDto;
import com.still.aikandy.common.querycondition.AuthUserQueryCondition;
import com.still.aikandy.common.utils.FileUtil;
import com.still.aikandy.common.utils.JwtHelper;
import com.still.aikandy.mbg.mapper.AuthUserMapper;
import com.still.aikandy.mbg.model.AuthUser;
import com.still.aikandy.mbg.model.AuthUserRole;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author Lee
 * @Description 用户Service实现类
 * @Date 2020/6/22 17:53
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    private final AuthUserMapper authUserMapper;
    private final AuthUserCustomMapper authUserCustomMapper;
    private final AuthUserRoleCustomMapper authUserRoleCustomMapper;

    private final RoleService roleService;
    private final RedisService redisService;
    private final MenuService  menuService;

    private final PasswordEncoder passwordEncoder;
    private final AuthProperties authProperties;

    private static final String TOKEN_HEAD = "token:";


    public UserServiceImpl(AuthUserMapper authUserMapper, AuthUserCustomMapper authUserCustomMapper, AuthUserRoleCustomMapper authUserRoleCustomMapper, RoleService roleService, RedisService redisService, MenuService menuService, PasswordEncoder passwordEncoder, AuthProperties authProperties) {
        this.authUserMapper = authUserMapper;
        this.authUserCustomMapper = authUserCustomMapper;
        this.authUserRoleCustomMapper = authUserRoleCustomMapper;
        this.roleService = roleService;
        this.redisService = redisService;
        this.menuService = menuService;
        this.passwordEncoder = passwordEncoder;
        this.authProperties = authProperties;
    }


    /**
     * 添加用户信息
     * @param authUserDto 用户信息
     * @return
     */
    @Override
    public Integer addAuthUser(AuthUserDto authUserDto) throws IOException {
        //密码校验
        if(!StringUtils.equalsIgnoreCase(authUserDto.getPassword(),authUserDto.getComfirmPassword())){
            throw new RestException(RestCode.PWD_NOT_EQUALS_COMPWD);
        }
        check(authUserDto);
        //判断有没有头像字段
        if(authUserDto.getIcon() == null){
            //设置默认头像地址
            authUserDto.setIcon(authProperties.getDefaultIcon());
        }
        authUserDto.setPassword(passwordEncoder.encode(authUserDto.getPassword()));//设置密码加密
        authUserDto.setId(null);
        authUserDto.setStatus(1);
        authUserDto.setCreateTime(new Date());
        return authUserMapper.insert(authUserDto);
    }

    /**
     * 删除用户信息
     * @param userId 用户ID
     * @return
     */
    @Override
    @Transactional
    public Integer deleteAuthUser(Long userId) {
        if(authUserMapper.selectByPrimaryKey(userId) == null){
            throw new RestException(RestCode.USER_NOT_FOUND);
        }
        //删除用户角色关系表
        authUserRoleCustomMapper.deleteByUserId(userId);

        //删除用户信息
        return authUserMapper.deleteByPrimaryKey(userId);
    }

    /**
     * 修改用户信息
     * @param userId 用户ID
     * @param authUserDto 用户信息
     * @return
     */
    @Override
    public Integer updateAuthUser(Long userId, AuthUserDto authUserDto) {
        //用户密码和确认密码是否都存在
        if(!StringUtils.isEmpty(authUserDto.getPassword()) && !StringUtils.isEmpty(authUserDto.getComfirmPassword())){
            //用户密码和确认密码是否一致
            if(!StringUtils.equals(authUserDto.getPassword(),authUserDto.getComfirmPassword())){
                throw new RestException(RestCode.PWD_NOT_EQUALS_COMPWD);
            }else{
                //密码加密
                authUserDto.setPassword(passwordEncoder.encode(authUserDto.getPassword()));
            }
        }else{
            authUserDto.setPassword(null);
        }
        //信息重复检查
        check(authUserDto);
        //设置用户ID
        authUserDto.setId(userId);
        return authUserMapper.updateByPrimaryKeySelective(authUserDto);
    }

    /**
     * 查询用户信息
     * @param authUserQueryCondition 用户查询条件
     * @param pageNum 查询页码
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public PageInfo<List<AuthUserDto>> queryAuthUsers(AuthUserQueryCondition authUserQueryCondition, Integer pageNum, Integer pageSize) {
        if(pageNum != null && pageSize != null){
            PageHelper.startPage(pageNum,pageSize);
        }
        List<AuthUserDto> users = authUserCustomMapper.getAuthUsersByCondition(authUserQueryCondition);
        return new PageInfo(users);
    }

    /**
     * 用户头像上传
     * @param icon 用户头像
     * @return
     */
    @Override
    public String iconUpload(MultipartFile icon) {
        try {
            return authProperties.getIconPath() + FileUtil.saveToLocal(icon,authProperties.getBasePath() + authProperties.getIconPath());
        } catch (IOException e) {
            throw new RestException(RestCode.ICON_PARSE_WRONG);
        }
    }

    /**
     * 获取URL信息
     * @return
     */
    @Override
    public UrlsDto getUrls() {
        UrlsDto urlsDto = new UrlsDto();
        urlsDto.setPostIconUrl(authProperties.getPostIconUrl());
        urlsDto.setImageServer(authProperties.getImageServer());
        return urlsDto;
    }

    /**
     * 分配用户角色
     * @param userId 用户ID
     * @param roleIds 角色ID数组
     * @return
     */
    @Override
    @Transactional
    public Integer updateUserRole(Long userId, List<Long> roleIds) {
        //删除旧用户角色关系
        authUserRoleCustomMapper.deleteByUserId(userId);

        Integer updateCount = 0;
        //建立新用户角色关系
        if(!CollectionUtil.isEmpty(roleIds)){
            List<AuthUserRole> list = new ArrayList<>();
            roleIds.forEach(roleId -> {
                AuthUserRole relation = new AuthUserRole();
                relation.setUserId(userId);
                relation.setRoleId(roleId);
                list.add(relation);
            });
            updateCount = authUserRoleCustomMapper.insertList(list);
        }
        //更新角色用户数量
        roleService.updateRoleUserCount();
        return updateCount;
    }

    /**
     * 根据用户名查询用户信息（支持用户名、手机号码、邮箱查询）
     * @param username
     * @return
     */
    @Override
    public AuthUser getUserByUsername(String username){
        List<AuthUserDto> userList = null;
        userList = getAuthUserByUsername(username);
        if(CollectionUtil.isEmpty(userList)){
            userList = getAuthUserByEmail(username);
        }
        if(CollectionUtil.isEmpty(userList)){
            userList = getAuthUserByPhone(username);
        }
        if(CollectionUtil.isEmpty(userList)){
            return null;
        }else{
            return userList.get(0);
        }
    }


    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return
     */
    private List<AuthUserDto> getAuthUserByUsername(String username){
        AuthUserQueryCondition condition = new AuthUserQueryCondition();
        condition.setUsername(username);
        return authUserCustomMapper.getAuthUsersByCondition(condition);
    }

    /**
     * 根据手机号码查询用户信息
     * @param phone 手机号码
     * @return
     */
    private List<AuthUserDto> getAuthUserByPhone(String phone){
        AuthUserQueryCondition condition = new AuthUserQueryCondition();
        condition.setPhone(phone);
        return authUserCustomMapper.getAuthUsersByCondition(condition);
    }

    /**
     * 根据邮箱查询用户信息
     * @param email 邮箱吗
     * @return
     */
    private List<AuthUserDto> getAuthUserByEmail(String email){
        AuthUserQueryCondition condition = new AuthUserQueryCondition();
        condition.setEmail(email);
        return authUserCustomMapper.getAuthUsersByCondition(condition);
    }

    //用户参数校验
    private void check(AuthUserDto authUserDto) {
        //用户名校验
        if(authUserDto.getUsername() != null && !CollectionUtil.isEmpty(getAuthUserByUsername(authUserDto.getUsername()))){
            throw new RestException(RestCode.USER_EXIST);
        }
        //校验手机号码
        if(authUserDto.getPhone() != null && !CollectionUtil.isEmpty(getAuthUserByPhone(authUserDto.getPhone()))){
            throw new RestException(RestCode.PHONE_EXIST);
        }
        //校验邮箱
        if(authUserDto.getEmail() != null && !CollectionUtil.isEmpty(getAuthUserByEmail(authUserDto.getEmail()))){
            throw new RestException(RestCode.EMAIL_EXIST);
        }
    }

    /**
     * 用户登录
     * @param authUserLoginParam 登录参数
     * @return
     */
    @Override
    public String login(AuthUserLoginParam authUserLoginParam) {
        AuthUser user = getUserByUsername(authUserLoginParam.getUsername());
        if(user == null){
            throw new RestException(RestCode.USERNAME_OR_PASSWORD_WRONG);//
        }
        if(!passwordEncoder.matches(authUserLoginParam.getPassword(),user.getPassword())){
            throw new RestException(RestCode.USERNAME_OR_PASSWORD_WRONG);
        }
        //更新最近登录时间
        AuthUser updateUser = new AuthUser();
        updateUser.setId(user.getId());
        Date date = new Date();
        updateUser.setLoginTime(date);
        authUserMapper.updateByPrimaryKeySelective(updateUser);

        Map map = new HashMap<>();
        map.put("username",user.getUsername());
        map.put("nickname",user.getNickname());
//        map.put("roles", JSONObject.toJSONString(roleService.getAuthRoleByUserId(user.getId())));
//        map.put("menus",JSONObject.toJSONString(menuService.getMenuBYUserId(user.getId())));
        map.put("icon", user.getIcon());
        map.put("datetime", LocalDateTime.now().toString());
        String token = JwtHelper.genToken(map);
        redisService.set(TOKEN_HEAD + user.getUsername(),token);
        redisService.expire(TOKEN_HEAD + user.getUsername(),30*60);//超时时间30分钟
        return token;
    }

    /**
     * 用户登出
     * @param token
     */
    @Override
    public void logout(String token) {
        Map map = JwtHelper.verifyToken(token.replace("akdy",""));
        redisService.remove(TOKEN_HEAD + map.get("username"));
    }


    /**
     * 获取登录用户信息
     * @param token
     * @return
     */
    @Override
    public Map<String,Object> getUserInfo(String token) {
        if(StringUtils.isEmpty(token)){
            throw new RestException(RestCode.TOKEN_ERROR);
        }
        Map map = JwtHelper.verifyToken(token.replace("akdy",""));
        AuthUser user = getAuthUserByUsername((String) map.get("username")).get(0);
        map.put("roles", roleService.getAuthRoleByUserId(user.getId()));
        map.put("menus",menuService.getMenuBYUserId(user.getId()));
        map.put("imageServer",authProperties.getImageServer());
        return map;
    }
}

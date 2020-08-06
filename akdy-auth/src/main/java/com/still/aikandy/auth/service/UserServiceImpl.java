package com.still.aikandy.auth.service;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.still.aikandy.auth.component.AuthProperties;
import com.still.aikandy.auth.dao.AuthUserCustomMapper;
import com.still.aikandy.auth.dao.AuthUserRoleCustomMapper;
import com.still.aikandy.common.api.ResultCode;
import com.still.aikandy.common.api.ApiException;
import com.still.aikandy.common.dto.AuthUserDto;
import com.still.aikandy.common.dto.AuthUserLoginParam;
import com.still.aikandy.common.querycondition.AuthUserQueryCondition;
import com.still.aikandy.common.utils.FileUtil;
import com.still.aikandy.common.utils.JwtHelper;
import com.still.aikandy.mbg.mapper.AuthUserMapper;
import com.still.aikandy.mbg.model.AuthResource;
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
 * @Author FishAndFlower
 * @Description 用户Service实现类
 * @Date 2020/8/4 10:51
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

    private String TOKEN_HEAD = "akdy";


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
            throw new ApiException(ResultCode.PWD_NOT_EQUALS_COMPWD);
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
            throw new ApiException(ResultCode.USER_NOT_FOUND);
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
        AuthUser authUser = authUserMapper.selectByPrimaryKey(userId);
        //用户名校验
        if(authUserDto.getUsername() == null){
            throw new ApiException(ResultCode.USER_NAME_IS_NULL);
        }
        if(!authUser.getUsername().equals(authUserDto.getUsername())){
            if(authUserDto.getUsername() != null && !CollectionUtil.isEmpty(getAuthUserByUsername(authUserDto.getUsername()))){
                throw new ApiException(ResultCode.USER_EXIST);
            }
        }

        //手机号码校验
        if(StringUtils.isEmpty(authUser.getPhone()) || !authUser.getPhone().equals(authUserDto.getPhone())){
            if(authUserDto.getPhone() != null && !CollectionUtil.isEmpty(getAuthUserByPhone(authUserDto.getPhone()))){
                throw new ApiException(ResultCode.PHONE_EXIST);
            }
        }

        //邮箱校验
        if(StringUtils.isEmpty(authUser.getEmail()) || !authUser.getEmail().equals(authUserDto.getEmail())){
            if(authUserDto.getEmail() != null && !CollectionUtil.isEmpty(getAuthUserByEmail(authUserDto.getEmail()))){
                throw new ApiException(ResultCode.EMAIL_EXIST);
            }
        }

        //设置用户ID
        authUserDto.setId(userId);
        return authUserCustomMapper.updateByPrimaryKey(authUserDto);
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
            throw new ApiException(ResultCode.ICON_PARSE_WRONG);
        }
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
            throw new ApiException(ResultCode.USER_EXIST);
        }
        //校验手机号码
        if(authUserDto.getPhone() != null && !CollectionUtil.isEmpty(getAuthUserByPhone(authUserDto.getPhone()))){
            throw new ApiException(ResultCode.PHONE_EXIST);
        }
        //校验邮箱
        if(authUserDto.getEmail() != null && !CollectionUtil.isEmpty(getAuthUserByEmail(authUserDto.getEmail()))){
            throw new ApiException(ResultCode.EMAIL_EXIST);
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
            throw new ApiException(ResultCode.USERNAME_OR_PASSWORD_WRONG);//
        }
        if(!passwordEncoder.matches(authUserLoginParam.getPassword(),user.getPassword())){
            throw new ApiException(ResultCode.USERNAME_OR_PASSWORD_WRONG);
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
            throw new ApiException(ResultCode.TOKEN_ERROR);
        }
        Map map = JwtHelper.verifyToken(token.replace(TOKEN_HEAD,""));
        AuthUser user = getAuthUserByUsername((String) map.get("username")).get(0);
        map.put("roles", roleService.getAuthRoleByUserId(user.getId()));
        map.put("menus",menuService.getMenuBYUserId(user.getId()));
        return map;
    }


    /**
     * 根据用户ID获取用户资源列表
     * @param userId
     * @return
     */
    public List<AuthResource> getResourcesByUserId(Long userId){
        return authUserCustomMapper.getResourcesByUserId(userId);
    }
}

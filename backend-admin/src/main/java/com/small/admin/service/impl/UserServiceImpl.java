package com.small.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.admin.common.utils.PageUtil;
import com.small.admin.dao.PermissionMapper;
import com.small.admin.dao.RoleMapper;
import com.small.admin.dao.UserMapper;
import com.small.admin.entity.dto.UserDto;
import com.small.admin.entity.po.Permission;
import com.small.admin.entity.po.Role;
import com.small.admin.entity.po.User;
import com.small.admin.entity.vo.UserPageVo;
import com.small.admin.service.UserService;
import com.small.common.utils.BlankUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户接口实现
 */
@Slf4j
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto findByUsername(String username) {
        UserDto userDto = new UserDto();

        User user = lambdaQuery().eq(User::getUserName, username).one();

        if(user==null){
            return null;
        }

        BeanUtil.copyProperties(user, userDto);
        // 关联角色
        List<Role> roleList = roleMapper.findByUserId(user.getId());
        userDto.setRoles(roleList);
        // 关联权限菜单
        List<Permission> permissionList = permissionMapper.findByUserId(user.getId());
        userDto.setPermissions(permissionList);
        return userDto;
    }

    @Override
    public UserDto findByUserId(String userId) {
        UserDto userDto = new UserDto();
        User user = lambdaQuery().eq(User::getId, userId).one();
        if(user==null){
            return null;
        }
        BeanUtil.copyProperties(user, userDto);
        // 关联角色
        List<Role> roleList = roleMapper.findByUserId(user.getId());
        userDto.setRoles(roleList);
        // 关联权限菜单
        List<Permission> permissionList = permissionMapper.findByUserId(user.getId());
        userDto.setPermissions(permissionList);
        return userDto;
    }

    @Override
    public List<UserDto> findAllUser() {

        List<User> list = lambdaQuery().list();
        List<UserDto> result = new ArrayList<>();
        for (User user : list) {
            UserDto userDto = new UserDto();
            BeanUtil.copyProperties(user, userDto);
            // 关联角色
            List<Role> roleList = roleMapper.findByUserId(user.getId());
            userDto.setRoles(roleList);
            // 关联权限菜单
            List<Permission> permissionList = permissionMapper.findByUserId(user.getId());
            userDto.setPermissions(permissionList);
            result.add(userDto);
        }


        return result;
    }

    @Override
    public List<Role> findRolesByUserId(String userId) {
        User user = lambdaQuery().eq(User::getId, userId).one();
        List<Role> roleList = roleMapper.findByUserId(user.getId());
        return roleList;
    }

    @Override
    public User findByMobile(String mobile) {
        User user = lambdaQuery().eq(User::getMobile, mobile).one();
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = lambdaQuery().eq(User::getEmail, email).one();
        return user;
    }

    @Override
    public IPage<User> getPageUsersByCondition(UserPageVo page) {
        // 1. 初始化分页条件
        Page initIPage = PageUtil.initMpPage(page);

        // 2. 封装查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if (BlankUtil.isNotBlank(page.getUserName())) {
            wrapper.like("user_name", page.getUserName());
        }
        if (BlankUtil.isNotBlank(page.getNickName())) {
            wrapper.like("nick_name", page.getNickName());
        }
        if (BlankUtil.isNotBlank(page.getMobile())) {
            wrapper.like("mobile", page.getMobile());
        }
        if (BlankUtil.isNotBlank(page.getEmail())) {
            wrapper.like("email", page.getEmail());
        }
        if (BlankUtil.isNotBlank(page.getSex())) {
            wrapper.like("sex", page.getSex());
        }
        if (BlankUtil.isNotBlank(page.getDescription())) {
            wrapper.like("description", page.getDescription());
        }
        if (BlankUtil.isNotBlank(page.getUserType())) {
            wrapper.eq("user_type", page.getUserType());
        }
        if (BlankUtil.isNotBlank(page.getUserStatus())) {
            wrapper.eq("user_status", page.getUserStatus());
        }
        IPage iPage = userMapper.selectPage(initIPage, wrapper);
        List<User> records = iPage.getRecords();
        records.forEach(user -> user.setPassword(null));
        iPage.setRecords(records);
        return iPage;
    }

    @Override
    public List<User> getUsersByCondition(UserPageVo page) {
        // 2. 封装查询条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();

        if (BlankUtil.isNotBlank(page.getUserName())) {
            wrapper.likeRight("user_name", page.getUserName());
        }
        if (BlankUtil.isNotBlank(page.getNickName())) {
            wrapper.like("nick_name", page.getNickName());
        }
        if (BlankUtil.isNotBlank(page.getMobile())) {
            wrapper.eq("mobile", page.getMobile());
        }
        if (BlankUtil.isNotBlank(page.getEmail())) {
            wrapper.likeRight("email", page.getEmail());
        }
        if (BlankUtil.isNotBlank(page.getSex())) {
            wrapper.like("sex", page.getSex());
        }
        if (BlankUtil.isNotBlank(page.getDescription())) {
            wrapper.eq("description", page.getDescription());
        }
        if (BlankUtil.isNotBlank(page.getUserType())) {
            wrapper.eq("user_type", page.getUserType());
        }
        if (BlankUtil.isNotBlank(page.getUserStatus())) {
            wrapper.eq("user_status", page.getUserStatus());
        }
        List<User> list = list(wrapper);
        list.forEach(user -> user.setPassword(null));
        return list;
    }
}

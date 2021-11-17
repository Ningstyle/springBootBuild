package com.small.admin.common.utils;

import com.small.admin.entity.po.Permission;
import com.small.admin.entity.po.User;
import com.small.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luhanlin
 */
@Slf4j
@Component
public class SecurityUtil {

    @Autowired
    private UserService userService;

    /**
     * 获取当前登录用户
     * @return
     */
    public User getCurrUser(){

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.findByUsername(user.getUsername());
    }

    /**
     * 获取当前用户数据权限 null代表具有所有权限
     */
//    public List<String> getDeparmentIds(){
//
//        List<String> deparmentIds = new ArrayList<>();
//        User u = getCurrUser();
//        // 用户角色
//        List<Role> userRoleList = iUserRoleService.findByUserId(u.getId());
//        // 判断有无全部数据的角色
//        Boolean flagAll = false;
//        for(Role r : userRoleList){
//            if(r.getDataType()==null||r.getDataType().equals(CommonConstant.DATA_TYPE_ALL)){
//                flagAll = true;
//                break;
//            }
//        }
//        if(flagAll){
//            return null;
//        }
//        // 查找自定义
//        return iUserRoleService.findDepIdsByUserId(u.getId());
//    }

    /**
     * 通过用户名获取用户拥有权限
     * @param username
     */
    public List<GrantedAuthority> getCurrUserPerms(String username){

        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Permission p : userService.findByUsername(username).getPermissions()){
            authorities.add(new SimpleGrantedAuthority(p.getId()));
        }
        return authorities;
    }
}


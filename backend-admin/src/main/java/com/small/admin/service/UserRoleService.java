package com.small.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.small.admin.entity.po.UserRole;

import java.util.List;

/**
 * 用户角色关系接口
 * @author luhanlin
 */
public interface UserRoleService extends IService<UserRole> {
    
    /**
     * 功能描述:通过角色id查询用户角色表
     * @Param: [id]
     * @Return: 
     * @Author: liuwei
     * @Date: 2019/10/11 11:29
     */
    List<UserRole> findByRoleId(String id);


    /**
     * 根据userId 删除用户角色
     * @param userId
     */
    boolean deleteByUserId(String userId);

}
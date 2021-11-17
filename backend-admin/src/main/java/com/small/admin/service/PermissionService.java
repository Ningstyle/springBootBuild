package com.small.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.small.admin.entity.po.Permission;
import com.small.admin.entity.vo.PermissionPageVo;

import java.util.List;

/**
 * 权限相关接口
 * @author luhanlin
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 根据 id 获取权限信息
     * @param id
     * @return
     */
    Permission getPermissionInfo(String id);

    /**
     * 根据用户 id 获取权限列表
     * @param userId
     * @return
     */
    List<Permission> findByUserId(String userId);

    /**
     * 根据名称查询权限
     * @param name
     * @return
     */
    Permission findByName(String name);

    /**
     * 根据层级查询权限列表并排序
     * @param levelZero
     * @return
     */
    List<Permission> findByLevelOrderBySortOrder(Integer levelZero);

    /**
     * 根据父资源 ID 查询权限列表并排序
     * @param parentId
     * @return
     */
    List<Permission> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 查询所有权限信息
     * @return
     */
    List<Permission> getAllPermissions();

    /**
     * 条件查询权限信息并进行分页
     * @param page
     * @return
     */
    IPage<Permission> getPagePermissionsByCondition(PermissionPageVo page);
}
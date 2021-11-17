package com.small.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.small.admin.entity.po.RolePermission;

import java.util.List;

/**
 * 角色权限关系接口
 *
 * @author luhanlin
 */
public interface RolePermissionService extends IService<RolePermission> {
    /**
     * 功能描述:保存角色权限
     *
     * @Param: [id, permissionIds]
     * @Return: void
     * @Author: liuwei
     * @Date: 2019/10/10 8:39
     */
    void saveRolePermissions(String id, List<String> permissionIds);

    /**
     * 根据权限 id 获取列表
     *
     * @param id
     * @return
     */
    List<RolePermission> getByPermissionId(String id);

    /**
     * 功能描述:通过角色id删除角色权限关系表
     *
     * @Param: [id]
     * @Return: void
     * @Author: liuwei
     * @Date: 2019/10/11 11:20
     */
    void deleteRolePermissionsByRoleId(String id);

    /**
     * 功能描述:修改角色权限
     *
     * @Param: [id]
     * @Return: void
     * @Author: liuwei
     * @Date: 2019/10/11 16:40
     */
    void updateRolePermissions(String id, List<String> permissionIds);
}
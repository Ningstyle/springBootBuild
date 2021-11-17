package com.small.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.small.admin.entity.dto.RoleDto;
import com.small.admin.entity.po.Permission;
import com.small.admin.entity.po.Role;
import com.small.admin.entity.vo.RolePageVo;

import java.util.List;

/**
 * 角色相关接口
 * @author luhanlin
 */
public interface RoleService extends IService<Role> {

    /**
     * 查找默认角色
     * @param isDefault
     * @return
     */
    List<Role> findByDefaultRole(boolean isDefault);


    /**
     * 功能描述:通过角色id查找权限列表
     * @Param: [id]
     * @Return: java.util.List<com.small.admin.entity.po.Permission>
     * @Author: liuwei
     * @Date: 2019/10/8 14:34
     */
    List<Permission> findPermissionsById(String id);
    /**
     * 功能描述:通过角色名查找角色
     * @Param: [roleName]
     * @Return: com.small.admin.entity.po.Role
     * @Author: liuwei
     * @Date: 2019/10/10 8:34
     */
    Role findByRoleName(String roleName);
    /**
     * 功能描述:添加角色
     * @Param: [role]
     * @Return: void
     * @Author: liuwei
     * @Date: 2019/10/10 8:37
     */
    void addRole(RoleDto role);
    /**
     * 功能描述:删除角色
     * @Param: [id]
     * @Return: void
     * @Author: liuwei
     * @Date: 2019/10/11 16:32
     */
    void deleteRoleById(String id);

    /**
     * 条件查询角色信息并进行分页
     * @param page
     * @return
     */
    IPage<Role> getPageRolesByCondition(RolePageVo page);

}

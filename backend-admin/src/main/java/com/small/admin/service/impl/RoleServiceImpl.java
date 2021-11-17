package com.small.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.admin.common.utils.PageUtil;
import com.small.admin.dao.PermissionMapper;
import com.small.admin.dao.RoleMapper;
import com.small.admin.entity.dto.RoleDto;
import com.small.admin.entity.po.Permission;
import com.small.admin.entity.po.Role;
import com.small.admin.entity.vo.RolePageVo;
import com.small.admin.service.RoleService;
import com.small.common.utils.BlankUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 角色相关接口实现
 *
 * @author luhanlin
 */
@Slf4j
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RolePermissionServiceImpl rolePermissionService;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> findByDefaultRole(boolean isDefault) {

        List<Role> list = lambdaQuery().eq(Role::getDefaultRole, isDefault).list();
        return list;
    }

    @Override
    public List<Permission> findPermissionsById(String id) {
        return permissionMapper.findByRoleId(id);
    }


    @Override
    public Role findByRoleName(String roleName) {
        Role role = lambdaQuery().eq(Role::getRoleName, roleName).one();
        return role;
    }


    @Override
    public void addRole(RoleDto role) {
        if (BlankUtil.isNotBlank(role.getPermissions())) {
            rolePermissionService.saveRolePermissions(role.getId(), role.getPermissions());
        }
        save(role);
    }

    @Override
    public void deleteRoleById(String id) {
        rolePermissionService.deleteRolePermissionsByRoleId(id);
        removeById(id);
    }


    @Override
    public IPage<Role> getPageRolesByCondition(RolePageVo page) {
        // 1. 初始化分页条件
        Page initIPage = PageUtil.initMpPage(page);

        // 2. 封装查询条件
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if (BlankUtil.isNotBlank(page.getRoleNo())) {
            wrapper.likeRight(Role::getId, page.getRoleNo());
        }

        if (BlankUtil.isNotBlank(page.getRoleName())) {
            wrapper.likeRight(Role::getRoleName, page.getRoleName());
        }

        if (BlankUtil.isNotBlank(page.getRoleDescription())) {
            wrapper.like(Role::getRoleDescription, page.getRoleDescription());
        }
        return roleMapper.selectPage(initIPage, wrapper);
    }


}
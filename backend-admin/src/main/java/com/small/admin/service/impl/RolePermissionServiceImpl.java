package com.small.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.admin.dao.RolePermissionMapper;
import com.small.admin.entity.po.RolePermission;
import com.small.admin.service.RolePermissionService;
import com.small.common.utils.BlankUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色权限关系接口实现
 *
 * @author luhanlin
 */
@Slf4j
@Service
@Transactional
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Override
    public void saveRolePermissions(String id, List<String> permissionIds) {
        saveBatch(permissionIds.stream().map(permissionId->new RolePermission(id,permissionId)).collect(Collectors.toList()));
    }

    @Override
    public List<RolePermission> getByPermissionId(String id) {
        return lambdaQuery().eq(RolePermission::getPermissionId, id).list();
    }

    @Override
    public void deleteRolePermissionsByRoleId(String id) {
        List<RolePermission> list = lambdaQuery().eq(RolePermission::getRoleId, id).list();
        if (BlankUtil.isNotBlank(list)) {
            remove(lambdaQuery().eq(RolePermission::getRoleId, id).getWrapper());
        }
    }

    @Override
    public void updateRolePermissions(String id, List<String> permissionIds) {
        deleteRolePermissionsByRoleId(id);
        saveRolePermissions(id, permissionIds);
    }
}
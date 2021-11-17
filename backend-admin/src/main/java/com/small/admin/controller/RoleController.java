package com.small.admin.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.small.admin.common.enums.AdminResultCodeEnum;
import com.small.admin.entity.dto.RoleDto;
import com.small.admin.entity.po.Permission;
import com.small.admin.entity.po.Role;
import com.small.admin.entity.po.UserRole;
import com.small.admin.entity.vo.RolePageVo;
import com.small.admin.service.RolePermissionService;
import com.small.admin.service.RoleService;
import com.small.admin.service.UserRoleService;
import com.small.common.enums.GenericResultCodeEnum;
import com.small.common.exception.ResultException;
import com.small.common.result.ResultInfo;
import com.small.common.utils.BlankUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liuwei
 * @date 2019/10/8 14:25
 */
@Slf4j
@RestController
@Api(description = "角色接口")
@RequestMapping("/roles")
@CacheConfig(cacheNames = "role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private UserRoleService userRoleService;

    @GetMapping("/{id}")
    @ApiOperation("获取指定角色全部信息")
    public ResultInfo findById(@PathVariable String id) {
        if (BlankUtil.isBlank(id)) {
            throw new ResultException(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        Role role = roleService.getById(id);
        if (BlankUtil.isBlank(role)) {
            throw new ResultException(GenericResultCodeEnum.DBRecordNotFound);
        }
        return ResultInfo.ok(role);
    }

    @GetMapping("/{id}/uiPermissions")
    @ApiOperation("获取指定角色的权限")
    public ResultInfo findPermissionsById(@PathVariable String id) {
        if (BlankUtil.isBlank(id)) {
            throw new ResultException(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        List<Permission> permissionList = roleService.findPermissionsById(id);
        if (BlankUtil.isBlank(permissionList)) {
           throw new ResultException(GenericResultCodeEnum.DBRecordNotFound);
        }
        return ResultInfo.ok(permissionList);
    }

    @PostMapping
    @ApiOperation("添加自定义角色")
    public ResultInfo addRole(@RequestBody RoleDto role) {
        if (BlankUtil.isBlank(role) || StrUtil.isBlank(role.getRoleName())) {
           throw new ResultException(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        Role existRole = roleService.findByRoleName(role.getRoleName());
        if (!BlankUtil.isBlank(existRole)) {
           throw new ResultException(AdminResultCodeEnum.ROLE_EXIST);
        }
        roleService.addRole(role);
        return ResultInfo.ok(role);
    }

    @PutMapping("/{id}")
    @ApiOperation("修改自定义角色基本信息")
    public ResultInfo updateRole(@PathVariable String id, @RequestBody Role role) {
        if (BlankUtil.isBlank(role) || StrUtil.isBlank(role.getRoleName())) {
           throw new ResultException(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        Role existRole = roleService.findByRoleName(role.getRoleName());
        if (!BlankUtil.isBlank(existRole)) {
           throw new ResultException(AdminResultCodeEnum.ROLE_EXIST);
        }
        role.setId(id);
        roleService.updateById(role);
        return ResultInfo.ok();
    }

    @PutMapping("/{id}/uiPermissions")
    @ApiOperation("修改自定义角色权限")
    public ResultInfo updateRolePermissions(@PathVariable String id, @RequestBody List<String> permissionIds) {
        rolePermissionService.updateRolePermissions(id, permissionIds);
        return ResultInfo.ok();
    }


    @DeleteMapping("/{id}")
    @ApiOperation("删除自定义角色")
    public ResultInfo deleteRoleById(@PathVariable String id) {
        if (BlankUtil.isBlank(id)) {
            throw new ResultException(GenericResultCodeEnum.PARAMETER_ERROR);
        }
        Role role = roleService.getById(id);
        if (BlankUtil.isBlank(role)) {
           throw new ResultException(GenericResultCodeEnum.DBRecordNotFound);
        }
        List<UserRole> list = userRoleService.findByRoleId(id);

        if (list != null && list.size() > 0) {
           throw new ResultException(AdminResultCodeEnum.ROLE_IS_USED);
        }
        roleService.deleteRoleById(id);
        return ResultInfo.ok();
    }

    @GetMapping
    @ApiOperation("根据条件获取角色列表")
    public ResultInfo getAllRoles(@ModelAttribute RolePageVo page){
        if (!page.isPage()) {
            List<Role> roles = roleService.list();

            if (BlankUtil.isBlank(roles)) {
                throw new ResultException(GenericResultCodeEnum.DBRecordNotFound);
            }

            return ResultInfo.ok(roles);
        }

        IPage<Role> data = roleService.getPageRolesByCondition(page);

        return ResultInfo.ok(data);
    }
}

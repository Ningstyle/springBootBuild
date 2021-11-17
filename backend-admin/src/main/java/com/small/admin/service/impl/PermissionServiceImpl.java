package com.small.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.admin.common.utils.PageUtil;
import com.small.admin.dao.PermissionMapper;
import com.small.admin.entity.po.Permission;
import com.small.admin.entity.vo.PermissionPageVo;
import com.small.admin.enums.StatusEnum;
import com.small.admin.service.PermissionService;
import com.small.common.utils.BlankUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 权限相关接口实现
 * @author luhanlin
 */
@Slf4j
@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Permission getPermissionInfo(String id) {
        return lambdaQuery().eq(Permission::getId, id).one();
    }

    @Override
    public List<Permission> findByUserId(String userId) {
        return permissionMapper.findByUserId(userId);
    }

    @Override
    public Permission findByName(String name) {
        return lambdaQuery().eq(Permission::getName, name).one();
    }

    @Override
    public List<Permission> findByLevelOrderBySortOrder(Integer levelZero) {
        return lambdaQuery().eq(Permission::getLevel, levelZero)
                .orderByAsc(Permission::getSortOrder).list();
    }

    @Override
    public List<Permission> findByParentIdOrderBySortOrder(String parentId) {
        return lambdaQuery().eq(Permission::getParentId, parentId)
                .orderByAsc(Permission::getSortOrder).list();
    }

    @Override
    public List<Permission> getAllPermissions() {
        return lambdaQuery()
                .eq(Permission::getStatus, StatusEnum.NORMAL.getValue())
                .orderByAsc(Permission::getSortOrder).list();
    }

    @Override
    public IPage<Permission> getPagePermissionsByCondition(PermissionPageVo page) {
        // 1. 初始化分页条件
        Page initIPage = PageUtil.initMpPage(page);

        // 2. 封装查询条件
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();

        if (BlankUtil.isNotBlank(page.getName())) {
            wrapper.likeRight("name", page.getName());
        }

        if (BlankUtil.isNotBlank(page.getDescription())) {
            wrapper.like("description", page.getDescription());
        }

        if (BlankUtil.isNotBlank(page.getType())) {
            wrapper.eq("type", page.getType());
        }

        return permissionMapper.selectPage(initIPage, wrapper);
    }
}
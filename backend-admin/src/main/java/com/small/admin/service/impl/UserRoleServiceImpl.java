package com.small.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.admin.dao.UserRoleMapper;
import com.small.admin.entity.po.UserRole;
import com.small.admin.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户角色关系接口实现
 * @author luhanlin
 */
@Slf4j
@Service
@Transactional
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public boolean deleteByUserId(String userId) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("user_id", userId);
        return removeByMap(columnMap);
    }

    @Override
    public List<UserRole> findByRoleId(String id) {
        return lambdaQuery().eq(UserRole::getRoleId, id).list();
    }

}
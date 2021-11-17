package com.small.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.admin.entity.po.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findByUserId(@Param("userId") String userId);
    List<Permission> findByRoleId(String roleId);
}

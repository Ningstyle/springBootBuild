package com.small.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.admin.entity.po.RolePermission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    int deleteByPrimaryKey(String id);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
}
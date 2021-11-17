package com.small.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.admin.entity.po.UserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    int deleteByPrimaryKey(String id);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

}
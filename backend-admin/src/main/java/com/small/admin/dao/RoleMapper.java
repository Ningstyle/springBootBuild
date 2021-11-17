package com.small.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.admin.entity.po.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    int deleteByPrimaryKey(String id);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 通过用户 id 查询角色信息
     * @param userId
     * @return
     */
    List<Role> findByUserId(@Param("userId") String userId);
}
package com.small.admin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.admin.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户数据处理层
 * @author luhanlin
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

//    User findByUsername(String username);

    int deleteByPrimaryKey(String id);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}

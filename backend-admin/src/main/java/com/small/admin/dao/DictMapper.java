package com.small.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.admin.entity.po.Dict;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictMapper extends BaseMapper<Dict> {

    int deleteByPrimaryKey(String id);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
}
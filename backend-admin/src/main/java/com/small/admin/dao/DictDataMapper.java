package com.small.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.admin.entity.po.DictData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictDataMapper extends BaseMapper<DictData> {

    int deleteByPrimaryKey(String id);

    int insertSelective(DictData record);

    DictData selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DictData record);

    int updateByPrimaryKey(DictData record);
}
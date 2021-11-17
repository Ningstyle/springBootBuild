package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrBoltData;

import java.util.List;

public interface VFemsCsrBoltDataMapper extends BaseMapper<VFemsCsrBoltData> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrBoltData record);

    int insertSelective(VFemsCsrBoltData record);

    VFemsCsrBoltData selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrBoltData record);

    int updateByPrimaryKey(VFemsCsrBoltData record);

}
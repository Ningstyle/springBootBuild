package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrBolt;
import com.small.business.entity.po.VFemsCsrBoltLog;

public interface VFemsCsrBoltLogMapper extends BaseMapper<VFemsCsrBolt> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrBoltLog record);

    int insertSelective(VFemsCsrBoltLog record);

    VFemsCsrBoltLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrBoltLog record);

    int updateByPrimaryKey(VFemsCsrBoltLog record);
}
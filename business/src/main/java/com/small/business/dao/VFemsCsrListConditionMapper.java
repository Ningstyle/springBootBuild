package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrListCondition;

public interface VFemsCsrListConditionMapper extends BaseMapper<VFemsCsrListCondition> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrListCondition record);

    int insertSelective(VFemsCsrListCondition record);

    VFemsCsrListCondition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrListCondition record);

    int updateByPrimaryKey(VFemsCsrListCondition record);
}
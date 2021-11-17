package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrListCondition;
import com.small.business.entity.po.VFemsCsrListConditionBak;

public interface VFemsCsrListConditionBakMapper extends BaseMapper<VFemsCsrListConditionBak> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrListCondition record);

    int insertSelective(VFemsCsrListCondition record);

    VFemsCsrListConditionBak selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrListCondition record);

    int updateByPrimaryKey(VFemsCsrListCondition record);
}
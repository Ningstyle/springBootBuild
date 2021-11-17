package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrExamine;

public interface VFemsCsrExamineMapper  extends BaseMapper<VFemsCsrExamine> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrExamine record);

    int insertSelective(VFemsCsrExamine record);

    VFemsCsrExamine selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrExamine record);

    int updateByPrimaryKey(VFemsCsrExamine record);
}
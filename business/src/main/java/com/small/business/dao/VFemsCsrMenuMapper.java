package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrMenu;

public interface VFemsCsrMenuMapper extends BaseMapper<VFemsCsrMenu> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrMenu record);

    int insertSelective(VFemsCsrMenu record);

    VFemsCsrMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrMenu record);

    int updateByPrimaryKey(VFemsCsrMenu record);
}
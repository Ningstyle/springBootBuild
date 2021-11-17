package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.Test;
import com.small.business.entity.po.VFemsCsrSystem;

import java.util.List;

public interface VFemsCsrSystemMapper  extends BaseMapper<VFemsCsrSystem> {
    int deleteByPrimaryKey(Integer sysId);

    int insert(VFemsCsrSystem record);

    int insertSelective(VFemsCsrSystem record);

    VFemsCsrSystem selectByPrimaryKey(Integer sysId);

    int updateByPrimaryKeySelective(VFemsCsrSystem record);

    int updateByPrimaryKey(VFemsCsrSystem record);

    List<VFemsCsrSystem> selectAll();
}
package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrNode;

public interface VFemsCsrNodeMapper extends BaseMapper<VFemsCsrNode> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrNode record);

    int insertSelective(VFemsCsrNode record);

    VFemsCsrNode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrNode record);

    int updateByPrimaryKey(VFemsCsrNode record);
}
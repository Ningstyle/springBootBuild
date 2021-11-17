package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.small.business.entity.po.VFemsCsrProjectext;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface VFemsCsrProjectextMapper extends BaseMapper<VFemsCsrProjectext> {
    int insert(VFemsCsrProjectext record);

    int insertSelective(VFemsCsrProjectext record);

    List<VFemsCsrProjectext> selectAllByPjId( @Param("vFemsCsrProjectext")VFemsCsrProjectext vFemsCsrProjectext, @Param("pjId") List<String> pjId, IPage page);
}
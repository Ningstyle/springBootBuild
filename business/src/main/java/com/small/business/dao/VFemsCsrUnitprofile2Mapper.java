package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrUnitprofile2;
import org.apache.ibatis.annotations.Param;

public interface VFemsCsrUnitprofile2Mapper extends BaseMapper<VFemsCsrUnitprofile2> {
    int deleteByPrimaryKey(String upId);

    int insert(VFemsCsrUnitprofile2 record);

    int insertSelective(VFemsCsrUnitprofile2 record);

    VFemsCsrUnitprofile2 selectByPrimaryKey(String upId);

    int updateByPrimaryKeySelective(VFemsCsrUnitprofile2 record);

    int updateByPrimaryKey(VFemsCsrUnitprofile2 record);

    String selectExaminUser(@Param("upId") String upId);
}
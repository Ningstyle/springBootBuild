package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VFemsCsrTypeMapper extends BaseMapper<VFemsCsrType> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrType record);

    int insertSelective(VFemsCsrType record);

    VFemsCsrType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrType record);

    int updateByPrimaryKey(VFemsCsrType record);

    List<VFemsCsrType> selectByConId(@Param("conId") String conId);
}
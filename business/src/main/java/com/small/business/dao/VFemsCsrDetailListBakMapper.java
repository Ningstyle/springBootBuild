package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.small.business.entity.po.VFemsCsrDetailList;
import com.small.business.entity.po.VFemsCsrDetailListBak;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VFemsCsrDetailListBakMapper extends BaseMapper<VFemsCsrDetailListBak> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrDetailList record);

    int insertSelective(VFemsCsrDetailList record);

    VFemsCsrDetailListBak selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrDetailList record);

    int updateByPrimaryKey(VFemsCsrDetailList record);

    List<VFemsCsrDetailList> selectByConId(@Param("conId")String conId , IPage page);

}
package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrBoltConfiguration;
import com.small.business.entity.vo.VFemsCsrUpVo;

import java.util.List;

public interface VFemsCsrBoltConfigurationMapper extends BaseMapper<VFemsCsrBoltConfiguration> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrBoltConfiguration record);

    int insertSelective(VFemsCsrBoltConfiguration record);

    VFemsCsrBoltConfiguration selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrBoltConfiguration record);

    int updateByPrimaryKey(VFemsCsrBoltConfiguration record);


    List<VFemsCsrUpVo> selectUpIds();
}
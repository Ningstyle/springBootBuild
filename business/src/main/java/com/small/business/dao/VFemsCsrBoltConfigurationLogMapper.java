package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.VFemsCsrBoltConfiguration;
import com.small.business.entity.po.VFemsCsrBoltConfigurationLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VFemsCsrBoltConfigurationLogMapper extends BaseMapper<VFemsCsrBoltConfiguration> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrBoltConfiguration record);

    int insertSelective(VFemsCsrBoltConfiguration record);

    VFemsCsrBoltConfigurationLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrBoltConfiguration record);

    int updateByPrimaryKey(VFemsCsrBoltConfiguration record);

    String selectBefore(VFemsCsrBoltConfiguration record);

    List<VFemsCsrBoltConfigurationLog> selectBeforeList(@Param("connId") String record);

}
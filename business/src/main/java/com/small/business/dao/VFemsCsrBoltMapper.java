package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.small.business.entity.po.VFemsCsrBolt;
import com.small.business.entity.vo.Employee;
import com.small.business.entity.vo.VFemsCsrApplyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VFemsCsrBoltMapper extends BaseMapper<VFemsCsrBolt> {
    int deleteByPrimaryKey(Long id);

    int insert(VFemsCsrBolt record);

    int insertSelective(VFemsCsrBolt record);

    VFemsCsrBolt selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VFemsCsrBolt record);

    int updateByPrimaryKey(VFemsCsrBolt record);

    List<VFemsCsrBolt> selectBoltExaminList(IPage<VFemsCsrBolt> page, @Param("vFemsCsrApplyVo") VFemsCsrApplyVo vFemsCsrApplyVo);

}
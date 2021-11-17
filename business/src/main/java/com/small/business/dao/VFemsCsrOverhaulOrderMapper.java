package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.small.business.entity.po.VFemsCsrConstructionOrder;
import com.small.business.entity.po.VFemsCsrOverhaulOrder;
import com.small.business.entity.vo.VFemsCsrOverhaulOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VFemsCsrOverhaulOrderMapper extends BaseMapper<VFemsCsrOverhaulOrder> {
    int insert(VFemsCsrOverhaulOrder record);

    int insertSelective(VFemsCsrOverhaulOrder record);

    List<VFemsCsrOverhaulOrderVo> selectByUpId(@Param("upId") String upId, IPage page);
}
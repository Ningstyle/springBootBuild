package com.small.business.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.small.business.dao.VFemsCsrApplyMapper;
import com.small.business.dao.VFemsCsrBoltConfigurationLogMapper;
import com.small.business.dao.VFemsCsrBoltConfigurationMapper;
import com.small.business.dao.VFemsCsrExamineMapper;
import com.small.business.entity.po.VFemsCsrBoltConfiguration;
import com.small.business.entity.po.VFemsCsrUnitprofile2;
import com.small.business.entity.vo.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BaseService {
    @Autowired
    private VFemsCsrApplyMapper vFemsCsrApplyMapper;
    @Autowired
    private VFemsCsrExamineMapper vFemsCsrExamineMapper;
    @Autowired
    private VFemsCsrBoltConfigurationMapper vFemsCsrBoltConfigurationMapper;
    @Autowired
    private VFemsCsrBoltConfigurationLogMapper vFemsCsrBoltConfigurationLogMapper;

    public IPage<VFemsCsrBoltConfiguration> getConfigurationByUpId(VFemsCsrUnitprofile2 vFemsCsrUnitprofile2, Query query){
        IPage<VFemsCsrBoltConfiguration> page = new Page<VFemsCsrBoltConfiguration>(query.getCurrent(),query.getPageSize());
        QueryWrapper<VFemsCsrBoltConfiguration> queryWrapper = new QueryWrapper<VFemsCsrBoltConfiguration>();
        queryWrapper.in("up_id",vFemsCsrUnitprofile2.getUpId());
        IPage<VFemsCsrBoltConfiguration> list = vFemsCsrBoltConfigurationMapper.selectPage(page,queryWrapper);
        return list;

    }
}

package com.small.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.business.dao.VFemsCsrPjmemberMapper;
import com.small.business.dao.VFemsCsrUnitprofile2Mapper;
import com.small.business.entity.po.VFemsCsrPjmember;
import com.small.business.entity.po.VFemsCsrUnitprofile2;
import com.small.business.service.VFemsCsrPjmemberService;
import com.small.business.service.VFemsCsrUnitprofile2Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 类详细描述：
 *
 * @author Eddy Zhang
 * @version 1.0
 * 创建时间：2020/11/4 15:00
 */
@Service
@Transactional
public class VFemsCsrUnitprofile2ServiceImpl extends ServiceImpl<VFemsCsrUnitprofile2Mapper, VFemsCsrUnitprofile2> implements VFemsCsrUnitprofile2Service {

    private static Logger logger = LogManager.getLogger(VFemsCsrUnitprofile2ServiceImpl.class);


    @Autowired
    private VFemsCsrUnitprofile2Mapper vFemsCsrUnitprofile2Mapper;
    @Value("${upload}")
    private String uploadresource;

    @Override
    public IPage<Map<String, Object>> getUpByPjId(IPage<Map<String, Object>> page, QueryWrapper<VFemsCsrUnitprofile2> queryWrapper) {
        return vFemsCsrUnitprofile2Mapper.selectMapsPage(page,queryWrapper);
    }


}

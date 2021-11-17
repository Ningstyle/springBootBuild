package com.small.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.business.dao.VFemsCsrDetailListMapper;
import com.small.business.dao.VFemsCsrListConditionMapper;
import com.small.business.dao.VFemsCsrProjectextMapper;
import com.small.business.dao.VFemsCsrTypeMapper;
import com.small.business.entity.po.VFemsCsrDetailList;
import com.small.business.entity.po.VFemsCsrListCondition;
import com.small.business.entity.po.VFemsCsrProjectext;
import com.small.business.entity.po.VFemsCsrType;
import com.small.business.entity.vo.VFemsCsrListVo;
import com.small.business.service.VFemsCsrListConditionService;
import com.small.business.service.VFemsCsrProjectextService;
import com.small.business.utils.BlankUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class VFemsCsrListConditionImpl extends ServiceImpl<VFemsCsrListConditionMapper, VFemsCsrListCondition> implements VFemsCsrListConditionService {

    private static Logger logger = LogManager.getLogger(VFemsCsrListConditionImpl.class);


    @Autowired
    private VFemsCsrListConditionMapper vFemsCsrListConditionMapper;
    @Autowired
    private VFemsCsrDetailListMapper vFemsCsrDetailListMapper;
    @Autowired
    private VFemsCsrTypeMapper vFemsCsrTypeMapper;
    @Value("${upload}")
    private String uploadresource;




    @Override
    public IPage<VFemsCsrListCondition> getTorqueListCondition(IPage<VFemsCsrListCondition> page, QueryWrapper<VFemsCsrListCondition> queryWrapper) {
        return vFemsCsrListConditionMapper.selectPage(page,queryWrapper);
    }

    @Override
    public VFemsCsrListVo getAllByListCondition(IPage<VFemsCsrDetailList> page,  VFemsCsrListCondition vFemsCsrListCondition) {
        VFemsCsrListVo vFemsCsrListVo = new VFemsCsrListVo();
        vFemsCsrListVo.setId(vFemsCsrListCondition.getId().toString());
        List<VFemsCsrType> vFemsCsrTypeList = new ArrayList<>();
        vFemsCsrTypeList = vFemsCsrTypeMapper.selectByConId(vFemsCsrListCondition.getId().toString());
        vFemsCsrListVo.setVFemsCsrTypeList(vFemsCsrTypeList);
        IPage<VFemsCsrDetailList> vFemsCsrDetailList = new Page<>() ;
        if (vFemsCsrDetailListMapper.selectByConId(vFemsCsrListCondition.getId().toString(),page).size()>0)
            vFemsCsrDetailList = page.setRecords(vFemsCsrDetailListMapper.selectByConId(vFemsCsrListCondition.getId().toString(),page));
        if (!BlankUtil.isBlank(vFemsCsrDetailList)){
            vFemsCsrListVo.setVFemsCsrDetailList(vFemsCsrDetailList.getRecords());
        }else {
            vFemsCsrListVo.setVFemsCsrDetailList(new ArrayList<>());
        }
        return vFemsCsrListVo;
    }
}

package com.small.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.business.dao.VFemsCsrPjmemberMapper;
import com.small.business.dao.VFemsCsrProjectextMapper;
import com.small.business.entity.po.VFemsCsrPjmember;
import com.small.business.entity.po.VFemsCsrProjectext;
import com.small.business.service.VFemsCsrPjmemberService;
import com.small.business.service.VFemsCsrProjectextService;
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
public class VFemsCsrPjmemberServiceImpl extends ServiceImpl<VFemsCsrPjmemberMapper, VFemsCsrPjmember> implements VFemsCsrPjmemberService {

    private static Logger logger = LogManager.getLogger(VFemsCsrPjmemberServiceImpl.class);


    @Autowired
    private VFemsCsrPjmemberMapper vFemsCsrPjmemberMapper;
    @Value("${upload}")
    private String uploadresource;


    @Override
    public List<VFemsCsrPjmember> selectUserByid(String userId) {
        return vFemsCsrPjmemberMapper.selectByUserId(userId);
    }
}

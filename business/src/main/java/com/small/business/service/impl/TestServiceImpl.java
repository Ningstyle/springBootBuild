package com.small.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.business.dao.TestMapper;
import com.small.business.entity.po.Test;
import com.small.business.service.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2020/3/2 8:11 PM
 */
@Service
@Transactional
public class TestServiceImpl extends ServiceImpl<TestMapper,Test> implements TestService {
}

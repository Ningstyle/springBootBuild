package com.small.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.admin.dao.DictMapper;
import com.small.admin.entity.po.Dict;
import com.small.admin.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典相关接口实现
 * @author luhanlin
 */
@Slf4j
@Service
@Transactional
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

}
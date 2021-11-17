package com.small.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.small.admin.dao.DictDataMapper;
import com.small.admin.entity.po.DictData;
import com.small.admin.service.DictDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 字典数据相关接口实现
 * @author luhanlin
 */
@Slf4j
@Service
@Transactional
public class DictDataServiceImpl extends ServiceImpl<DictDataMapper, DictData> implements DictDataService {

}
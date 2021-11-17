package com.small.business.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.small.business.entity.po.Test;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper extends BaseMapper<Test> {

}
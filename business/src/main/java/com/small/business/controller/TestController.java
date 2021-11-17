package com.small.business.controller;

import com.small.business.common.annotation.Login;
import com.small.business.entity.po.Test;
import com.small.business.entity.po.User;
import com.small.business.service.TestService;
import com.small.common.result.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luhanlin 2019-08-28
 */
@Slf4j
@RestController
@Api(description = "权限测试接口")
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/noNeedLogin")
    @ApiOperation(value = "测试不需要登录")
    public ResultInfo noNeedLogin(){
        /**
         * 此处为测试
         */
//        Test test = new Test();
//        Test result = test.selectById("123");

        // 正常开发模式
        Test result = testService.getById("123");
        return ResultInfo.ok(result);
    }

    @Login
    @GetMapping("/needLogin")
    @ApiOperation(value = "测试需要登录")
    public ResultInfo needLogin(User user){
        log.info("登录后的用户信息 user -> {}",user);
        Test result = testService.getById("123");
        return ResultInfo.ok(result);
    }

    @GetMapping("/test")
    @ApiOperation(value = "测试")
    public ResultInfo test(User user){
        return ResultInfo.ok("success!!!");
    }

}

package com.small.admin.controller;

import cn.hutool.http.HttpUtil;
import com.small.admin.common.enums.AdminResultCodeEnum;
import com.small.common.exception.ResultException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luhanlin 2019-08-28
 */
@Slf4j
@RestController
@Api(description = "Security相关接口")
@RequestMapping("/admin/common")
public class SecurityController {

    @RequestMapping(value = "/needLogin",method = RequestMethod.GET)
    @ApiOperation(value = "没有登录")
    public ResponseEntity needLogin(){
        throw new ResultException(AdminResultCodeEnum.WDLERROR);
    }

    @RequestMapping(value = "/swagger/login", method = RequestMethod.GET)
    @ApiOperation(value = "Swagger接口文档专用登录接口 方便测试")
    public String swaggerLogin(@RequestParam String username, @RequestParam String password,
                @ApiParam("验证码") @RequestParam(required = false) String code,
                @ApiParam("图片验证码ID") @RequestParam(required = false) String captchaId,
                @ApiParam("可自定义登录接口地址")
                @RequestParam(required = false, defaultValue = "http://127.0.0.1:9090/admin/login")
                        String loginUrl){

        Map<String, Object> params = new HashMap<>(16);
        params.put("username", username);
        params.put("password", password);
        params.put("code", code);
        params.put("captchaId", captchaId);
        String result = HttpUtil.createPost(loginUrl)
                .form(params)
                .execute()
                .body();
        return result;
    }

}

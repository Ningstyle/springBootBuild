package com.small.business.common.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.small.business.common.annotation.Login;
import com.small.business.common.enums.BusinessCodeEnum;
import com.small.business.entity.vo.Employee;
import com.small.common.constant.SecurityConstant;
import com.small.common.utils.BlankUtil;
import com.small.common.utils.ResponseUtil;
import com.small.redis.utils.RedisUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2020/2/18 4:49 PM
 */
@Log4j2
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        // 如果处理对象是一个处理方法，则获取到方法上的注解
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod)handler).getMethodAnnotation(Login.class);
            log.info("annotation -> {}", annotation);
            // 说明此方法没有Login注解
            if (annotation == null) {
                return true;
            }
            // 否则直接放过拦截的请求
        } else {
            return true;
        }



        // 1. 从请求头获取token
        String token = request.getHeader(SecurityConstant.HEADER);
        if(BlankUtil.isBlank(token)){
            token = request.getParameter(SecurityConstant.HEADER);
        }
        log.info("用户登录的 token -> {}",token);
        Boolean notValid = BlankUtil.isBlank(token) || !token.startsWith(SecurityConstant.TOKEN_SPLIT);
        if (notValid) {
            log.error("token 有误，可能非法输入，或者没有登录");
            ResponseUtil.out(response, BusinessCodeEnum.TokenInvalid,"登录已失效，请重新登录");
            return false;
        }

        // 2. 查询token信息是否过期, JWT与redis 双重验证

//        boolean expired = JWTUtils.isExpired(token);
//        if (expired) {
//            log.error("token 已失效，需要重新登录");
//            ResponseUtil.out(response, BusinessCodeEnum.TokenExpired,"登录已失效，请重新登录");
//            return false;
//        }

        String userInfo = RedisUtil.get(token);
        if (BlankUtil.isBlank(userInfo)) {
            log.error("token 已失效，需要重新登录");
            ResponseUtil.out(response, BusinessCodeEnum.TokenExpired,"登录已失效，请重新登录");
            return false;
        }
        request.setAttribute("employee", JSONObject.parseObject(userInfo, Employee.class));

        return true;
    }
}

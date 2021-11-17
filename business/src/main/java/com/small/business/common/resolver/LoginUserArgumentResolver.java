package com.small.business.common.resolver;

import cn.hutool.json.JSONUtil;
import com.small.business.common.enums.BusinessCodeEnum;
import com.small.business.entity.po.User;
import com.small.business.entity.vo.Employee;
import com.small.common.constant.SecurityConstant;
import com.small.common.utils.BlankUtil;
import com.small.common.utils.ResponseUtil;
import com.small.redis.utils.RedisUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 类详细描述：
 *
 * @author Mr_lu
 * @version 1.0
 * @mail allen_lu_hh@163.com
 * 创建时间：2020/2/18 8:01 PM
 */
@Log4j2
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
//        return clazz==User.class;
        return parameter.getParameterType().equals(Employee.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Employee user = new Employee();

        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
//        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        String token = request.getHeader(SecurityConstant.HEADER);
        if(BlankUtil.isBlank(token)){
            token = request.getParameter(SecurityConstant.HEADER);
        }

        if (BlankUtil.isBlank(token)) {
            return null;
        }
        String userInfo = RedisUtil.get(token);
        log.info("userInfo = {}",userInfo);
        if (BlankUtil.isNotBlank(userInfo)) {
            user = JSONUtil.toBean(userInfo,Employee.class);
        }

        return user;
    }
}

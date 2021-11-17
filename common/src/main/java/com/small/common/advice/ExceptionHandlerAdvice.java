package com.small.common.advice;

import cn.hutool.json.JSONUtil;
import com.small.common.enums.GenericResultCodeEnum;
import com.small.common.exception.ResultException;
import com.small.common.result.ResultInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice implements ResponseBodyAdvice {

    private ThreadLocal<Object> modelHolder = new ThreadLocal<Object>();

    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultInfo handleIllegalParamException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String tips = "参数不合法";
        if (errors.size() > 0) {
            tips = errors.get(0).getDefaultMessage();
        }
        return new ResultInfo(GenericResultCodeEnum.PARAMETER_ERROR, tips);
    }
    @ExceptionHandler(ResultException.class)
    public ResultInfo handleResultException(ResultException e, HttpServletRequest request,HttpServletResponse response) {
        logger.debug("uri={} | requestBody={}", request.getRequestURI(),
                JSONUtil.toJsonStr(modelHolder.get()));
        //response.setStatus(e.getResultCode().getHttpStatus());
        return new ResultInfo(e.getResultCode(),e.getData());
    }
    @ExceptionHandler(Exception.class)
    public ResultInfo handleException(Exception e, HttpServletRequest request) {
        logger.error("uri={} | requestBody={}", request.getRequestURI(),
                JSONUtil.toJsonStr(modelHolder.get()), e);
        return new ResultInfo(GenericResultCodeEnum.NETWORK_FAILURE);
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        // ModelHolder 初始化
        modelHolder.set(webDataBinder.getTarget());
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // ModelHolder 清理
        modelHolder.remove();
        return body;
    }
}

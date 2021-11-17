package com.small.business.common.enums;

import com.small.common.result.ResponseTypeDetails;


/**
 * 响应码相关定义
 * <类详细描述>
 * 响应码编码规则为“xxx    x   nn nn”
 *       xxx 表示系统类型，三位大写字母
 *       x   表示信息种类，一位大写字母
 *       nn  表示信息小分类，两位数字
 *       nn  表示信息编号，两位数字
 *
 * @author luhanlin
 * @version [V_1.0.0, 2018/5/11 18:00]
 */
public enum BusinessCodeEnum implements ResponseTypeDetails {


    ParamError("B1001","填报参数错误"),
    TokenInvalid("B1002","不是合法token值"),
    TokenExpired("B1003","token 已过期，请重新登录"),
    PASSWORD_ERROR("U3300", "用户名或密码错误"),


    APPLYING("AP1000","审核中不可修改"),
    EXAMINED("AP1000","已审核完成，无需重新审核"),

    ;


    private String code;
    private String msg;


    BusinessCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

	@Override
	public String getResultCode() {
		return code;
	}

	@Override
	public String getResultMsg() {
		return msg;
	}


}

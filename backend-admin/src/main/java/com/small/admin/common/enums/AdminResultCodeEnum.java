package com.small.admin.common.enums;


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
public enum AdminResultCodeEnum implements ResponseTypeDetails {

    // *****************用户权限管理系统定义的相关错误 U **************

    // ********用户模块相关错误 U3300-U3330 *****
    PASSWORD_ERROR("U3300", "用户名或密码错误"),
    YZM_ERROR("U3301", "验证码错误"),
    YZMGQ_ERROR("U3302", "验证码过期或者失效"),
    TPYZM_ERROR("U3303", "图片验证码错误"),
    LOGIN_EXPIRED("U3304","登录已失效，请重新登录"),
    WDLERROR("U3306","您还未登录，请登陆后查看"),
    REGISTERED("U3307","您已经注册过了，请直接登录"),
    NOREGISTERED("U3308","该手机号还未注册"),
    PHONE_WRONG("U3309","请输入正确的手机号"),
    REPASS_WRONG("U3310","两次密码不一致"),
    OLDPASS_WRONG("U3311","新密码与旧密码相同"),
    USER_NAME_EXIST("U3312", "该用户名已被存在"),
    MOBILE_EXIST("U3313", "该手机号已绑定其他账户"),
    EMAIL_EXIST("U3314", "该邮箱已绑定其他账户"),
    USER_UPDATE_ERROR("U3315", "修改失败"),
    OLDPASSWORD_ERROR("U3316", "旧密码错误"),
    ACCOUNT_ERROR("U3317", "账户被锁或失效"),
    NO_ACCESS("U3318", "您没有访问权限"),

    // ********角色相关错误 U3331-U3350 *****
    ROLE_EXIST("U3331","角色已存在"),
    ROLE_IS_USED("U3332","角色被用户使用，无法删除"),

    // ********权限相关错误 U3351-U3370 *****
    PERMISSION_NAME_EXIST("U3351", "权限名称已存在"),
    PERMISSION_IS_USED("U3352","权限已被使用，无法进行删除等操作"),

    // ******行政区域相关错误 U3371-U3390 ****
    AdmDivisionParameterError("U3371", "参数不正确"),
    AdmDivisionCodeBeEmpty("U3372", "编码为空"),
    AdmDivisionCodeBeExist("U3373", "编码重复"),
    AdmDivisionNotFound("U3374", "未找到记录"),
    AdmDivisionExcelFile("U3375", "导入文件格式不正确"),
    AdmDivisionUpdateError("U3376", "修改数据过程出错"),

    // ******自定义区域区域相关错误 U3371-U3390 ****
    CustomAreaParameterError("U3381", "参数不正确"),
    CustomAreaCodeBeEmpty("U3382", "编码为空"),
    CustomAreaCodeBeExist("U3383", "编码重复"),
    CustomAreaNotFound("U3384", "未找到记录"),
    CustomAreaExcelFile("U3385", "导入文件格式不正确"),
    CustomAreaUpdateError("U3386", "修改数据过程出错"),
    ;


    private String code;
    private String msg;


    AdminResultCodeEnum(String code, String msg) {
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

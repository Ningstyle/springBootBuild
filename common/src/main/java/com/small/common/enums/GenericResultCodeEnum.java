package com.small.common.enums;

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
public enum GenericResultCodeEnum implements ResponseTypeDetails {

    // **********通知信息定义****************
    SUCCESS("10000", "SUCCESS"),

    // **********常规错误定义 N ****************
    NETWORK_FAILURE("N10001", "系统内部错误"),
    PARAMETER_ERROR("N10101", "参数错误"),

    // **********消息错误定义 M ****************
    MsgDomainCannotBeEmpty("M1000", "值域不能为空"),

    // ***********安全相关错误 M **************
    MsgCheckFailure("M2001", "报文核验校验失败"),
    MsgDecryptionFailure("M2002", "报文解密失败"),
    MsgNoSuchBusinessPermission("M2003", "无此业务权限"),
    MsgUserOrPasswordError("M2004", "用户或密码错误"),
    MsgChannelWithoutPermission("M2005", "该渠道无权限"),
    MsgOthersPermissionError("M2999", "其他权限错误"),

    // ***********数据库相关错误 D **************
    DBKeywordRepetition("D1001", "关键字重复"),
    DBRollbackFailure("D1002", "Rollback失败"),
    DBSelectFailure("D1003", "SELECT失败"),
    DBFetchFailure("D1004", "FETCH失败"),
    DBInsertFailure("D1005", "INSERT失败"),
    DBUpdateFailure("D1006", "UPDATE失败"),
    DBDeleteFailure("D1007", "DELETE失败"),
    DBCountFailure("D1008","COUNT失败"),

    DBTableEmpty("D2001", "数据库表空"),
    DBRecordNotFound("D2002", "未找到记录"),
    DBNullOfConnection("D2003", "数据库连接对象为空"),
    DBNullOfCursor("D2004", "游标为空"),
    DBGetSequenceException("D2005", "获取序号值信息异常,结果集不为1条"),
    DBDistributedLocksCreateException("D2006", "分布式锁创建异常"),
    DBDistributedLocksUnknownException("D2007", "分布式锁创建未知异常"),
    DBstatus("D2999", "该用户被禁用"),
    OtherDBError("D9999", "数据库其他错误"),

    // ********redis 相关************
    DistributedLocksLockFail("R10001", "Redis加锁失败"),
    DistributedLocksUnlockFail("R10001", "Redis解锁失败"),

	// authentication related
    TOKENERROR("U3305","token校验失败或失效，请重新获取token"),

    ;
	
    private String code;
    private String msg;


    GenericResultCodeEnum(String code, String msg) {
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

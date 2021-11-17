package com.small.admin.exception;

import com.small.common.enums.GenericResultCodeEnum;
import com.small.common.exception.ResultException;
import lombok.Data;

/**
 * @author Luhanlin
 */
@Data
public class AdminException extends ResultException {

    private String msg;

    private String specificInfo;        // 异常具体信息

    public AdminException(GenericResultCodeEnum e) {
        super(e);
    }

    /**
     * 传入具体错误信息构造
     * @param e
     * @param specificInfo
     */
    public AdminException(GenericResultCodeEnum e, String specificInfo) {
        super(e);
        this.specificInfo = specificInfo;
    }
}

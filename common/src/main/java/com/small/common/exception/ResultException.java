package com.small.common.exception;


import com.small.common.result.ResponseTypeDetails;

/**
 * 结果异常，会被 ExceptionHandler 捕捉并返回给前端
 *
 */
public class ResultException extends RuntimeException {

    private ResponseTypeDetails resultCode;
    private Object data;

    public ResultException() {
        super();
        this.resultCode = null;
    }

    public ResultException(ResponseTypeDetails resultCode) {
        super(resultCode.getResultMsg());
        
        this.resultCode = resultCode;
    }

    public ResultException(ResponseTypeDetails resultCode, Object data) {
        super(resultCode.getResultMsg());

        this.resultCode = resultCode;
        this.data=data;
    }

    public ResponseTypeDetails getResultCode() {
        return resultCode;
    }
    public Object getData() {
        return data;
    }

}

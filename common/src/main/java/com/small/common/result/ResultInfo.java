package com.small.common.result;

import com.small.common.enums.GenericResultCodeEnum;
import lombok.Data;


@Data
public class ResultInfo<T> {

    private String code;
    private String msg;
    private String token;
    private T data;

    public ResultInfo(T data,String token) {
        this.data = data;
        this.token = token;
    }

    public ResultInfo(T data) {
        this.data = data;
    }

    public ResultInfo(ResponseTypeDetails resultCode, T data,String token) {
        this(resultCode);
        this.data = data;
        this.token = token;
    }
    public ResultInfo(ResponseTypeDetails resultCode,String token) {
        this(resultCode);
        this.token = token;
    }
    public ResultInfo(ResponseTypeDetails resultCode, T data) {
        this(resultCode);
        this.data = data;
    }
    public ResultInfo(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public ResultInfo(ResponseTypeDetails resultCode) {
        //this.code = ResultCode.SysType + resultCode.getCode();
        this.code =resultCode.getResultCode();
        this.msg = resultCode.getResultMsg();
    }
    public static<T> ResultInfo ok(T data) {
      return new ResultInfo(GenericResultCodeEnum.SUCCESS,data);
    }
    public static<T> ResultInfo ok(T data,String token) {
        return new ResultInfo(GenericResultCodeEnum.SUCCESS,data,token);
    }

    public static ResultInfo ok() {
        return new ResultInfo(GenericResultCodeEnum.SUCCESS);
    }

}

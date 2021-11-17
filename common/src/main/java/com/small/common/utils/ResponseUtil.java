package com.small.common.utils;

import cn.hutool.json.JSONUtil;
import com.small.common.result.ResponseTypeDetails;
import com.small.common.result.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author luhanlin
 */
@Slf4j
public class ResponseUtil {

    /**
     *  使用response输出JSON
     * @param response
     * @param resultCodeEnum
     */
    public static void out(HttpServletResponse response, ResponseTypeDetails resultCodeEnum){
        out(response, resultCodeEnum, null);
    }

    /**
     *  使用response输出JSON
     * @param response
     * @param msg
     */
    public static void out(HttpServletResponse response, String msg){
        out(response, null, msg);
    }

    /**
     *  使用response输出JSON
     * @param response
     * @param resultCodeEnum
     */
    public static void out(HttpServletResponse response, ResponseTypeDetails resultCodeEnum, String msg){

        ServletOutputStream out = null;
        int http_status = HttpStatus.OK.value();
        ResultInfo resultinfo = new ResultInfo<>(msg);

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            out = response.getOutputStream();
            if (BlankUtil.isNotBlank(resultCodeEnum)){
               // http_status = resultCodeEnum.getHttpStatus();
                resultinfo = new ResultInfo<>(resultCodeEnum, msg);
            }
            response.setStatus(http_status);
            out.write(JSONUtil.toJsonStr(resultinfo).getBytes());
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        } finally{
            if(out!=null){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

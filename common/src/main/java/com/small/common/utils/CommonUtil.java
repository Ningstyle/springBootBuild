package com.small.common.utils;


import java.util.Random;
import java.util.UUID;

/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-08-28 08:48]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CommonUtil {

    /**
     * 以UUID重命名
     *
     * @param fileName
     * @return
     */
    public static String renamePic(String fileName) {
        String extName = fileName.substring(fileName.lastIndexOf("."));
        return UUID.randomUUID().toString().replace("-", "") + extName;
    }

    /**
     * 随机6位数生成
     */
    public static String getRandomNum() {

        Random random = new Random();
        int num = random.nextInt(999999);
        //不足六位前面补0
        String str = String.format("%06d", num);
        return str;
    }

    /**
     * 批量递归删除时 判断target是否在ids中 避免重复删除
     * @param target
     * @param ids
     * @return
     */
    public static Boolean judgeIds(String target, String[] ids){

        Boolean flag = false;
        for(String id : ids){
            if(id.equals(target)){
                flag = true;
                break;
            }
        }
        return flag;
    }
}

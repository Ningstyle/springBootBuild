package com.small.admin.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.small.common.utils.BlankUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 通用填充类 适用于mybatis plus
 *
 * @author luhanlin
 */
@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {

    /**
     * 创建时间
     */
    private final String createTime = "createTime";
    /**
     * 修改时间
     */
    private final String updateTime = "updateTime";
    /**
     * 创建者ID
     */
    private final String createBy = "createBy";

    /**
     * 修改者ID
     */
    private final String updateBy = "updateBy";

    @Override
    public void insertFill(MetaObject metaObject) {
        setInsertFieldValByName(createTime, LocalDateTime.now(), metaObject);
        setInsertFieldValByName(createBy, currentUserName(), metaObject);
        setInsertFieldValByName(updateTime, LocalDateTime.now(), metaObject);
        setInsertFieldValByName(updateBy, currentUserName(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setUpdateFieldValByName(updateTime, LocalDateTime.now(), metaObject);
        setUpdateFieldValByName(updateBy, currentUserName(), metaObject);
    }

    /**
     * 获取当前用户名称
     */
    private String currentUserName() {
        UserDetails user = null;
        try {
            user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            // ignore
        }

        if (BlankUtil.isBlank(user)) {
            // 查询不到返回匿名用户
            return "anonymous";
        }
        return user.getUsername();
    }

}

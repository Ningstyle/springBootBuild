package com.small.admin.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <类详细描述>
 *
 * @author luhanlin
 * @version [V_1.0.0, 2019-08-28 08:48]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Data
@AllArgsConstructor
public class TokenUser implements Serializable{

    private String username;

    private List<String> permissions;

    private Boolean saveLogin;
}

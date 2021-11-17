package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.util.List;

@Data
public class VFemsCsrMenu extends Model<VFemsCsrMenu> {
    private Long id;

    private Long roleId;

    private String roleName;

    private String name;

    private String path;

    private Long fatherId;

    private Integer orders;

    private Integer grade;

    private String icon;

}
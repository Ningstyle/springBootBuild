package com.small.business.entity.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.small.business.entity.po.VFemsCsrMenu;
import lombok.Data;

import java.util.List;

@Data
public class VFemsCsrMenuVo extends Model<VFemsCsrMenuVo> {

    private String name;

    private String path;


    private String icon;


    private List<VFemsCsrMenuVo>  children;
}
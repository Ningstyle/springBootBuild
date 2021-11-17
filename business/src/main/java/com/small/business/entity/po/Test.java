package com.small.business.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class Test extends Model<Test> {

    private Integer id;

    private String name;

    private String phone;

}
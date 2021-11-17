package com.small.business.entity.vo;

import lombok.Data;

@Data
public class Employee {
    private String userId;
    private String userName;
   /* private String deptId;
    private String deptName;
    private String email;
    private String phoneNumber;
    private String unitName;
    private String centerId;
    private String centerTxt;*/

   String message;
   int applyType =0 ;
}

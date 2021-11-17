package com.small.business.entity.po;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserBaseBean {
    private String userName;
    private String password;

    private String userId;
    private String oldpassword;

    private String orgCode;

    private String pid;

    private String param;

    private String pageNum;
    private String pageSize;
    private String phoneNumber;
   /* private String userId;
    private String userName;*/
    private String email;


    private String subject;
    private String bodyText;


    private String title;
    private String type;
    private String msg_content;
    private String url;
    private String imgurl;
    private List<Map<String,String>> peoples;


    private List<String> sendTo;
    private List<String> sendCC;
    /*private String subject;
    private String bodyText;*/


    private String from;
    private String to;


}

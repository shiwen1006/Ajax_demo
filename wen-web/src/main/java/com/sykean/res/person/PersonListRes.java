package com.sykean.res.person;

import lombok.Data;

@Data
public class PersonListRes {
    private Integer id;

    //姓名
    private String name;

    //工号
    private String workerSn;

    //职务
    private String duty;

    //工种
    private Integer workKind;

    private String workKindLabel;

    //性别
    private Integer sex;

    private String sexLabel;
    ;

    //是否注册
    private Boolean regisiter;
}

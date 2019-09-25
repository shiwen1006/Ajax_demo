package com.sykean.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("person")
public class Person {

    @TableId
    private Integer id;

    //姓名
    private String name;

    //工号
    private String workerSn;

    //职务
    private String duty;

    //工种
    private Integer workKind;

    //性别
    private Integer sex;

    //是否注册
    private Boolean regisiter;
}

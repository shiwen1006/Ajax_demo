package com.sykean.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

@Data
@TableName("person")
public class City {

    @TableId(type = IdType.INPUT)
    private String code;

    private String name;

    private String parentId;

    public City() {
    }

    public City(String code, String name, String parentId) {
        this.code = code;
        this.name = name;
        this.parentId = parentId;
    }
}

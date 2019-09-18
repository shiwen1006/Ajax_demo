package com.sykean.entity;

import lombok.Data;

@Data
public class City {

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

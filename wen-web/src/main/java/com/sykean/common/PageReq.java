package com.sykean.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("分页请求req")
public class PageReq implements Serializable {
    private static final long serialVersionUID = 666487620133112654L;

    @ApiModelProperty("每页个数")
    private Integer limit;

    @ApiModelProperty("偏移量")
    private Integer offset;

    public PageReq() {
        limit = 10;
        offset = 0;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "PageReq{" + "limit=" + limit + ", offset=" + offset + '}';
    }
}

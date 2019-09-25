package com.sykean.common;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

@Builder
@ApiModel("分页res")
public class PageRes<T> {

    private Integer count;
    private T data;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageRes{" + "count=" + count + ", data=" + data + '}';
    }
}

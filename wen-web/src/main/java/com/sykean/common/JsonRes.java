package com.sykean.common;

import java.io.Serializable;

/**
 * 返回体
 *
 * @param <T>
 */
public class JsonRes<T> implements Serializable {
    //响应码
    private int code = 0;
    //返回体
    private T data;
    //返回信息
    private String msg;

    private JsonRes(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }

    public JsonRes(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonRes(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonRes<?> success() {
        return new JsonRes(ResultCode.SUCCESS);
    }

    public static JsonRes success(Object data) {
        JsonRes jsonObj = new JsonRes(ResultCode.SUCCESS);
        jsonObj.setData(data);
        return jsonObj;
    }

    public static JsonRes error(ResultCode resultCode) {
        return new JsonRes(resultCode.getCode(), resultCode.getMsg());
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

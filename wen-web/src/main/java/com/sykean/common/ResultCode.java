package com.sykean.common;

/**
 * Created by lee on 02/09/2017.
 */
public enum ResultCode {
    SUCCESS(0, "请求成功"), UN_KNOW(-1, "未知错误"), USER_NOT_FOUND(1, "用户不存在"), LOGIN_FAILED(2, "用户名或密码错误"), UN_AUTH(3,
            "未授权"), DEPT_NOT_FOUND(4, "部门不存在"), CHILDREN_EXIST(5, "存在子菜单，请先删除子菜单"), USER_BINDED(6,
            "部门绑定了用户，不能删除"), ROLE_BINDED(7, "角色绑定了用户，不能删除"), OLD_PWD_ERROR(8, "旧密码错误"), REGION_CHILD_EXIST(9,
            "存在子区域，无法删除"), TYPE_BINDED(10, "类型绑定了设备或版本，不能删除"), PERSON_NOT_FIND(11,
            "未查询到该过检人员"), DEVICE_BIND(12, "该组织下有设备，请先移除。"), USER_BIND(13, "该组织有人员，请先移除。"),
    IMGE_COUNT_LIMIT(14, "上传图片数不能超过三张，请重新选择");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getValue() {
        return this.code;
    }
}
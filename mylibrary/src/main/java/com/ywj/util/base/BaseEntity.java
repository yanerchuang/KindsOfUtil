package com.ywj.util.base;


public class BaseEntity<T extends Object> {

    private String code;
    private T data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        msg = msg;
    }
}

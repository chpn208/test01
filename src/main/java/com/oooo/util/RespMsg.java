package com.oooo.util;

/**
 * Created by chenpan on 17-1-7.
 */
public class RespMsg<T>{
    private int code;
    private T msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }
}

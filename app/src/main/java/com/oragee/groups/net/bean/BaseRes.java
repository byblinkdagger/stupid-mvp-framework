package com.oragee.groups.net.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lucky on 2017/9/11.
 * Here be dragons
 * 前方高能
 */

public class BaseRes<T> {
    /**
     * 成功状态码
     */
    private static final String CODE_SUCCESS = "0";
    /**
     * token过期
     */
    private static final String CODE_TOKEN_TIMEOUT = "401";
    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return code.equals(CODE_SUCCESS);
    }

    public boolean isTokenTimeout() {
        return code.equals(CODE_TOKEN_TIMEOUT);
    }
}

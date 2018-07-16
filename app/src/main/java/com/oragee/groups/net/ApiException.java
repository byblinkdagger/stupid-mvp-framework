package com.oragee.groups.net;

/**
 * Created by karel on 2017/5/15.
 */

public class ApiException extends RuntimeException {
    public static final String CODE_UNKNOWN_ERROR = "9999";
    public static final String CODE_NO_NET_ERROR = "-9999";
    public static final String CODE_SERVER_ERROR = "0101";
    public static final String CODE_TOKEN_ERROR = "9903";
    public static final String CODE_TOKEN_ILLEAGAL = "9901";
    private String errorCode;
    private String errorMessage;

    public ApiException(String errorCode) {
        this.errorCode = errorCode;
    }

    public ApiException(String errorCode, String errorMessage) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

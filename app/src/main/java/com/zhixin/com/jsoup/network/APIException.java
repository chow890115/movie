package com.zhixin.com.jsoup.network;

/**
 * Created by admin on 2016/8/26.
 */
public class APIException extends Exception {
    public String errorMsg;
    public String[] errors;
    public APIException(String errorMsg,String[] errors) {
        this.errorMsg = errorMsg;
        this.errors=errors;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrors() {
        return errors[0];
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }
}

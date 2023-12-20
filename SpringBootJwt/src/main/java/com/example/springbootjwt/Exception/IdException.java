package com.example.springbootjwt.Exception;

public class IdException extends Exception{
    private final String errorMsg;

    public IdException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}

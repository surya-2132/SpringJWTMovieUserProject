package com.example.springbootjwt.Exception;

public class MovieException extends Exception{

    private static final long serialVersionUID = 1L;
    private final String errorMsg;

    public MovieException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }
}

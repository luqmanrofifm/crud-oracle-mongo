package com.example.crud_oracle_mongo.exceptions;

import lombok.Getter;

@Getter
public class BaseException extends Exception{
    private final Integer errorCode;
    private final String message;
    private final String[] values;

    public BaseException(String message) {
        super(message);
        this.errorCode = 500;
        this.message = message;
        this.values = new String[0];
    }

    public BaseException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.values = new String[0];
    }

    public BaseException(Integer errorCode, String message, String... values) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.values = values;
    }

}

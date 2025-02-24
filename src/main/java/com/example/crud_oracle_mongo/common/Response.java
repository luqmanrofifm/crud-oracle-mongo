package com.example.crud_oracle_mongo.common;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
    private int status;
    private String message;
    private Object data;

    public Response(final int status, final String message, final Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Response(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}

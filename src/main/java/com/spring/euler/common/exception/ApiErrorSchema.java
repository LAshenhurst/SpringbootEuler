package com.spring.euler.common.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ApiErrorSchema {
    private HttpStatus status;
    private String timestamp;
    private Object message;

    private ApiErrorSchema() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        timestamp = format.format(new Date());
    }

    public ApiErrorSchema(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiErrorSchema(HttpStatus status, Object message) {
        this();
        this.status = status;
        this.message = message;
    }

    public ApiErrorSchema(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = ex.getMessage();
    }
}

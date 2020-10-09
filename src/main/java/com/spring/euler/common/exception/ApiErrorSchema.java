package com.spring.euler.common.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ApiErrorSchema {
    private final static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    private HttpStatus status;
    private String timestamp;
    private Object message;

    private ApiErrorSchema() {
        timestamp = DATETIME_FORMAT.format(new Date());
    }

    public ApiErrorSchema(HttpStatus status, Object message) {
        this();
        this.status = status;
        this.message = message;
    }
}

package com.spring.Euler.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiError.class)
    protected ResponseEntity<Object> handleAnyException(ApiError ex) {
        ApiErrorSchema response = new ApiErrorSchema(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
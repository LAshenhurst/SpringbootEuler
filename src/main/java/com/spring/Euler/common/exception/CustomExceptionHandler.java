package com.spring.Euler.common.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        String errorMessage = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        ApiErrorSchema response = new ApiErrorSchema(HttpStatus.NOT_FOUND, errorMessage);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatus status,
                                                                         WebRequest request) {
        String errorMessage = ex.getMethod() + " method is not supported for this request. ";
        if (ex.getSupportedHttpMethods() == null) { errorMessage += "No methods are supported."; }
        else {
            List<String> validMethods = ex.getSupportedHttpMethods().stream().map(Enum::name).collect(Collectors.toList());
            errorMessage += "Supported methods are: " + String.join(", ", validMethods) + ".";
        }

        ApiErrorSchema response = new ApiErrorSchema(HttpStatus.METHOD_NOT_ALLOWED, errorMessage);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        List<String> errorMessages = new ArrayList<>();
        for (FieldError error: ex.getBindingResult().getFieldErrors()) {
            errorMessages.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error: ex.getBindingResult().getGlobalErrors()) {
            errorMessages.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiErrorSchema response = new ApiErrorSchema(HttpStatus.BAD_REQUEST, errorMessages);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatus status,
                                                                          WebRequest request) {
        String errorMessage = ex.getParameterName() + " parameter is missing";
        ApiErrorSchema response = new ApiErrorSchema(HttpStatus.BAD_REQUEST, errorMessage);

        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String errorMessage = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        ApiErrorSchema response = new ApiErrorSchema(HttpStatus.BAD_REQUEST, errorMessage);

        return new ResponseEntity<>(response, response.getStatus());
    }


    @ExceptionHandler({ ApiError.class })
    protected ResponseEntity<Object> handleAnyException(ApiError ex) {
        ApiErrorSchema response = new ApiErrorSchema(ex.getStatus(), ex.getMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex) {
        ApiErrorSchema response = new ApiErrorSchema(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
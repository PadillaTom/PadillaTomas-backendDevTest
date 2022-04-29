package com.padillatomas.devTest.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HttpClientErrorException.class})
    protected ResponseEntity<Object> handleConflict(HttpClientErrorException ex, WebRequest request) {
        if(ex.getStatusCode() == HttpStatus.NOT_FOUND) {
            return handleExceptionInternal(ex, "Product Not Found",
                    new HttpHeaders(), ex.getStatusCode(), request);
        }
        return handleExceptionInternal(ex, ex.getResponseBodyAsString(),
                new HttpHeaders(), ex.getStatusCode(), request);
    }

}

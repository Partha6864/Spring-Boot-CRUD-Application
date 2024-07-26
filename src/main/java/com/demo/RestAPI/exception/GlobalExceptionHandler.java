package com.demo.RestAPI.exception;

import com.demo.RestAPI.response.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CloudVendorNotFoundException.class)
    public ResponseEntity<Object> handleCloudVendorNotFoundException(CloudVendorNotFoundException ex, WebRequest request) {
        return ResponseHandler.simpleResponseBuilder(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CloudVendorAlreadyExistsException.class)
    public ResponseEntity<Object> handleCloudVendorAlreadyExistsException(CloudVendorAlreadyExistsException ex, WebRequest request) {
        return ResponseHandler.simpleResponseBuilder(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        return ResponseHandler.simpleResponseBuilder(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

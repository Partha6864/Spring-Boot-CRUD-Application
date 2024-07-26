package com.demo.RestAPI.exception;

public class CloudVendorAlreadyExistsException extends RuntimeException {
    public CloudVendorAlreadyExistsException(String message) {
        super(message);
    }
}

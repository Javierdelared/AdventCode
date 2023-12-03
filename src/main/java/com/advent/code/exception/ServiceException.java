package com.advent.code.exception;

public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
            super(message);
        }
    public ServiceException(String message, Throwable cause) {
        super(message, cause, true, true);
    }
}

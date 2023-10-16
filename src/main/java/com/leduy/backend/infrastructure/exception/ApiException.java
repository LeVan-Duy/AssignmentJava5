package com.leduy.backend.infrastructure.exception;

public class ApiException extends RuntimeException{
    public ApiException(String message) {
        super(message);
    }
}

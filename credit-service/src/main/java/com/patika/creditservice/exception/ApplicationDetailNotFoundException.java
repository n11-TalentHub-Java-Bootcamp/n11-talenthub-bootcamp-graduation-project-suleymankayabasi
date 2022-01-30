package com.patika.creditservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApplicationDetailNotFoundException extends RuntimeException {
    public ApplicationDetailNotFoundException(String message) {
        super(message);
    }
}

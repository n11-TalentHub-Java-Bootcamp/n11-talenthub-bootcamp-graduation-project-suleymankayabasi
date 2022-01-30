package com.patika.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerIsNotFound extends RuntimeException {
    public CustomerIsNotFound(String message) {
        super(message);
    }
}

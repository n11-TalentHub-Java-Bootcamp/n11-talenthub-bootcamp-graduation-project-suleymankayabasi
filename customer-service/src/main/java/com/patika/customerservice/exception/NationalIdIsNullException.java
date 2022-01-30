package com.patika.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NationalIdIsNullException extends RuntimeException {
    public NationalIdIsNullException(String message) {
        super(message);
    }
}

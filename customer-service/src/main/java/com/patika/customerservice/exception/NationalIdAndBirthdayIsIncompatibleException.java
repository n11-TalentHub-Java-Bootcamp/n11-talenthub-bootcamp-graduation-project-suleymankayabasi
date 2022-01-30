package com.patika.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NationalIdAndBirthdayIsIncompatibleException extends RuntimeException {
    public NationalIdAndBirthdayIsIncompatibleException(String message) {
        super(message);
    }
}

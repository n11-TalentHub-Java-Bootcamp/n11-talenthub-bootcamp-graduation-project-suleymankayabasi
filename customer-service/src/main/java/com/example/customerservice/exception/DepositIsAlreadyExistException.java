package com.example.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DepositIsAlreadyExistException extends RuntimeException{
    public DepositIsAlreadyExistException(String message) {
        super(message);
    }
}

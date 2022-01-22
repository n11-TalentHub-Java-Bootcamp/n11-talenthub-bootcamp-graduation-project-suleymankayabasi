package com.example.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CustomerIsAlreadyExistException extends RuntimeException{
    public CustomerIsAlreadyExistException(String message){
        super(message);
    }
}
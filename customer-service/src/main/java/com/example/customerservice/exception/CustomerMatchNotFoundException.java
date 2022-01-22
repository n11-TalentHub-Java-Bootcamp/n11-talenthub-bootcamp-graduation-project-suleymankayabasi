package com.example.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerMatchNotFoundException extends RuntimeException{

    public CustomerMatchNotFoundException(String message){
        super(message);
    }
}
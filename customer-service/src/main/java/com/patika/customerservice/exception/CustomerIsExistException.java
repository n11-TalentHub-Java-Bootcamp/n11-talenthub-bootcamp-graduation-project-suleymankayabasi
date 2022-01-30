package com.patika.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CustomerIsExistException extends RuntimeException{
    public CustomerIsExistException(String message){
        super(message);
    }
}
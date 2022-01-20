package com.example.customerservice.enums;

public enum ErrorMessage {

    CUSTOMER_IS_NOT_FOUND("Customer is not found"),
    CUSTOMER_IS_ALREADY_EXIST("Customer is already exist"),
    CUSTOMER_MATCH_NOT_FOUND("Customer National-ID and Birthday doesn't match"),
    DEPOSIT_IS_NOT_FOUND("Deposit is not found"),
    DEPOSIT_IS_ALREADY_EXIST("Deposit is already exist");

    private final String text;

    ErrorMessage(final String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}

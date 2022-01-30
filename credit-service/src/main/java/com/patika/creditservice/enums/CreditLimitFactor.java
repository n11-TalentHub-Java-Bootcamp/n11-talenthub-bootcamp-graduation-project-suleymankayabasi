package com.patika.creditservice.enums;

public enum CreditLimitFactor {

    CREDIT_LIMIT_FACTOR(4);

    private double factor;

    CreditLimitFactor(double factor){
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}
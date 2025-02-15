package org.jose.java.webapp.jpa.zenithpay_api.exception;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
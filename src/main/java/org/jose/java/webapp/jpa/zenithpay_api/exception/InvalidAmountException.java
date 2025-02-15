package org.jose.java.webapp.jpa.zenithpay_api.exception;

public class InvalidAmountException extends RuntimeException {

    public InvalidAmountException(String message) {
        super(message);
    }
}
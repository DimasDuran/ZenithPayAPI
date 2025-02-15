package org.jose.java.webapp.jpa.zenithpay_api.exception;

public class InvalidPinException extends RuntimeException {

    public InvalidPinException(String message) {
        super(message);
    }
}
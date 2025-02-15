package org.jose.java.webapp.jpa.zenithpay_api.exception;

public class InvalidOtpException extends RuntimeException {

    public InvalidOtpException(String msg) {
        super(msg);
    }
}
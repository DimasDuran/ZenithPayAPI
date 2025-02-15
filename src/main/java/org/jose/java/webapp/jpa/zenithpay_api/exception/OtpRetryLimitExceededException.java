package org.jose.java.webapp.jpa.zenithpay_api.exception;

public class OtpRetryLimitExceededException extends RuntimeException {

    public OtpRetryLimitExceededException(String message) {
        super(message);
    }
}
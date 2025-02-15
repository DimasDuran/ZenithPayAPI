package org.jose.java.webapp.jpa.zenithpay_api.dto;

public record PinRequest(String accountNumber, String pin, String password) {
}

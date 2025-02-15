package org.jose.java.webapp.jpa.zenithpay_api.dto;

public record PinUpdateRequest(String accountNumber, String oldPin, String newPin, String password) {
}
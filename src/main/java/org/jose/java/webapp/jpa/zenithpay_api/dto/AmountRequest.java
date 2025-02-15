package org.jose.java.webapp.jpa.zenithpay_api.dto;

public record AmountRequest(String accountNumber, String pin, double amount) {
}
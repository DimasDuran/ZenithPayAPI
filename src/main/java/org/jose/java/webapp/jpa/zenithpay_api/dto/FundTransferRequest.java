package org.jose.java.webapp.jpa.zenithpay_api.dto;

public record FundTransferRequest(String sourceAccountNumber, String targetAccountNumber, double amount, String pin) {}

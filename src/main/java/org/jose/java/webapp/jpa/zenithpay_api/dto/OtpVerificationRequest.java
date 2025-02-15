package org.jose.java.webapp.jpa.zenithpay_api.dto;

public record OtpVerificationRequest(String identifier, String otp) {
}
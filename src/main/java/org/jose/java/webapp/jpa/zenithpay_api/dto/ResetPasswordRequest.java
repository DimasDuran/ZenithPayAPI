package org.jose.java.webapp.jpa.zenithpay_api.dto;

public record ResetPasswordRequest(String identifier, String resetToken, String newPassword) {
}
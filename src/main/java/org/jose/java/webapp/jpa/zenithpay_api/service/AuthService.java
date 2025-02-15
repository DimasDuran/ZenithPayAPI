package org.jose.java.webapp.jpa.zenithpay_api.service;

import org.springframework.http.ResponseEntity;

import org.jose.java.webapp.jpa.zenithpay_api.dto.OtpRequest;
import org.jose.java.webapp.jpa.zenithpay_api.dto.OtpVerificationRequest;
import org.jose.java.webapp.jpa.zenithpay_api.dto.ResetPasswordRequest;
import org.jose.java.webapp.jpa.zenithpay_api.entity.User;

public interface AuthService {
    public String generatePasswordResetToken(User user);

    public boolean verifyPasswordResetToken(String token, User user);

    public void deletePasswordResetToken(String token);

    public ResponseEntity<String> sendOtpForPasswordReset(OtpRequest otpRequest);

    public ResponseEntity<String> verifyOtpAndIssueResetToken(OtpVerificationRequest otpVerificationRequest);

    public ResponseEntity<String> resetPassword(ResetPasswordRequest resetPasswordRequest);

}
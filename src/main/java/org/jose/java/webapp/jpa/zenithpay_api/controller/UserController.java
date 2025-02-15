package org.jose.java.webapp.jpa.zenithpay_api.controller;

import org.jose.java.webapp.jpa.zenithpay_api.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.jose.java.webapp.jpa.zenithpay_api.dto.LoginRequest;
import org.jose.java.webapp.jpa.zenithpay_api.dto.OtpRequest;
import org.jose.java.webapp.jpa.zenithpay_api.dto.OtpVerificationRequest;
import org.jose.java.webapp.jpa.zenithpay_api.entity.User;
import org.jose.java.webapp.jpa.zenithpay_api.exception.InvalidTokenException;
import org.jose.java.webapp.jpa.zenithpay_api.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        ResponseEntity<String> response = userService.registerUser(user);

        // Only send email if registration succeeded
        if (response.getStatusCode().is2xxSuccessful()) {
            String emailBody = emailService.getBankStatementEmailTemplate(user.getName(), "Welcome! Your account is created.");
            emailService.sendEmail(user.getEmail(), "Welcome to OneStopBank", emailBody);
        }

        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request)
            throws InvalidTokenException {
        return userService.login(loginRequest, request);
    }

    @PostMapping("/generate-otp")
    public ResponseEntity<String> generateOtp(@RequestBody OtpRequest otpRequest) {
        return userService.generateOtp(otpRequest);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtpAndLogin(@RequestBody OtpVerificationRequest otpVerificationRequest)
            throws InvalidTokenException {

        return userService.verifyOtpAndLogin(otpVerificationRequest);
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @GetMapping("/logout")
    public ModelAndView logout(@RequestHeader("Authorization") String token)
            throws InvalidTokenException {

        return userService.logout(token);
    }

}
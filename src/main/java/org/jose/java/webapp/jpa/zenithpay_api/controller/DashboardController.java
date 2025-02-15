package org.jose.java.webapp.jpa.zenithpay_api.controller;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.jose.java.webapp.jpa.zenithpay_api.service.DashboardService;
import org.jose.java.webapp.jpa.zenithpay_api.util.JsonUtil;
import org.jose.java.webapp.jpa.zenithpay_api.util.LoggedinUser;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/user")
    public ResponseEntity<String> getUserDetails() {
        val accountNumber = LoggedinUser.getAccountNumber();
        val userResponse = dashboardService.getUserDetails(accountNumber);
        return ResponseEntity.ok(JsonUtil.toJson(userResponse));
    }

    @GetMapping("/account")
    public ResponseEntity<String> getAccountDetails() {
        val accountNumber = LoggedinUser.getAccountNumber();
        val accountResponse = dashboardService.getAccountDetails(accountNumber);
        return ResponseEntity.ok(JsonUtil.toJson(accountResponse));
    }

}
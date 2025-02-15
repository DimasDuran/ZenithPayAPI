package org.jose.java.webapp.jpa.zenithpay_api.service;

import org.jose.java.webapp.jpa.zenithpay_api.dto.AccountResponse;
import org.jose.java.webapp.jpa.zenithpay_api.dto.UserResponse;

public interface DashboardService {
    UserResponse getUserDetails(String accountNumber);
    AccountResponse getAccountDetails(String accountNumber);
}
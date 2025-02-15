package org.jose.java.webapp.jpa.zenithpay_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.jose.java.webapp.jpa.zenithpay_api.dto.AccountResponse;
import org.jose.java.webapp.jpa.zenithpay_api.dto.UserResponse;
import org.jose.java.webapp.jpa.zenithpay_api.exception.NotFoundException;
import org.jose.java.webapp.jpa.zenithpay_api.repository.AccountRepository;
import org.jose.java.webapp.jpa.zenithpay_api.repository.UserRepository;
import org.jose.java.webapp.jpa.zenithpay_api.util.ApiMessages;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AccountRepository accountRepository;

    @Override
    public UserResponse getUserDetails(String accountNumber) {
        val user = userRepository.findByAccountAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException(
                        String.format(ApiMessages.USER_NOT_FOUND_BY_ACCOUNT.getMessage(), accountNumber)));

        return new UserResponse(user);
    }

    @Override
    public AccountResponse getAccountDetails(String accountNumber) {
        val account = accountRepository.findByAccountNumber(accountNumber);
        if (account == null) {
            throw new NotFoundException(String.format(ApiMessages.USER_NOT_FOUND_BY_ACCOUNT.getMessage(), accountNumber));
        }

        return new AccountResponse(account);
    }

}

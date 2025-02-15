package org.jose.java.webapp.jpa.zenithpay_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.cache.annotation.Cacheable;
import org.jose.java.webapp.jpa.zenithpay_api.dto.AmountRequest;
import org.jose.java.webapp.jpa.zenithpay_api.dto.FundTransferRequest;
import org.jose.java.webapp.jpa.zenithpay_api.dto.PinRequest;
import org.jose.java.webapp.jpa.zenithpay_api.dto.PinUpdateRequest;
import org.jose.java.webapp.jpa.zenithpay_api.service.AccountService;
import org.jose.java.webapp.jpa.zenithpay_api.service.TransactionService;
import org.jose.java.webapp.jpa.zenithpay_api.util.ApiMessages;
import org.jose.java.webapp.jpa.zenithpay_api.util.JsonUtil;
import org.jose.java.webapp.jpa.zenithpay_api.util.LoggedinUser;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    @GetMapping("/pin/check")
    public ResponseEntity<String> checkAccountPIN() {
        val isPINValid = accountService.isPinCreated(LoggedinUser.getAccountNumber());
        val response = isPINValid ? ApiMessages.PIN_CREATED.getMessage()
                : ApiMessages.PIN_NOT_CREATED.getMessage();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/pin/create")
    @Cacheable(value = "idempotency", key = "T(org.jose.java.webapp.jpa.zenithpay_api.util.LogdedinUser).getAccountNumber() + ':' + '/api/account/pin/create' + ':' + (#pinRequest).hashCode()")
    public ResponseEntity<String> createPIN(@RequestBody PinRequest pinRequest) {
        accountService.createPin(
                LoggedinUser.getAccountNumber(),
                pinRequest.password(),
                pinRequest.pin());

        return ResponseEntity.ok(ApiMessages.PIN_CREATION_SUCCESS.getMessage());
    }

    @PostMapping("/pin/update")
    @Cacheable(value = "idempotency", key = "T(org.jose.java.webapp.jpa.zenithpay_api.util.LogdedinUser).getAccountNumber() + ':' + '/api/account/pin/update' + ':' + (#pinUpdateRequest).hashCode()")
    public ResponseEntity<String> updatePIN(@RequestBody PinUpdateRequest pinUpdateRequest) {
        accountService.updatePin(
                LoggedinUser.getAccountNumber(),
                pinUpdateRequest.oldPin(),
                pinUpdateRequest.password(),
                pinUpdateRequest.newPin());

        return ResponseEntity.ok(ApiMessages.PIN_UPDATE_SUCCESS.getMessage());
    }

    @PostMapping("/deposit")
    @Cacheable(value = "idempotency", key = "T(org.jose.java.webapp.jpa.zenithpay_api.util.LogdedinUser).getAccountNumber() + ':' + '/api/account/deposit' + ':' + (#amountRequest).hashCode()")
    public ResponseEntity<String> cashDeposit(@RequestBody AmountRequest amountRequest) {
        accountService.cashDeposit(
                LoggedinUser.getAccountNumber(),
                amountRequest.pin(),
                amountRequest.amount());

        return ResponseEntity.ok(ApiMessages.CASH_DEPOSIT_SUCCESS.getMessage());
    }

    @PostMapping("/withdraw")
    @Cacheable(value = "idempotency", key = "T(org.jose.java.webapp.jpa.zenithpay_api.util.LogdedinUser).getAccountNumber() + ':' + '/api/account/withdraw' + ':' + (#amountRequest).hashCode()")
    public ResponseEntity<String> cashWithdrawal(@RequestBody AmountRequest amountRequest) {
        accountService.cashWithdrawal(
                LoggedinUser.getAccountNumber(),
                amountRequest.pin(),
                amountRequest.amount());

        return ResponseEntity.ok(ApiMessages.CASH_WITHDRAWAL_SUCCESS.getMessage());
    }

    @PostMapping("/fund-transfer")
    @Cacheable(value = "idempotency", key = "T(org.jose.java.webapp.jpa.zenithpay_api.util.LogdedinUser).getAccountNumber() + ':' + '/api/account/fund-transfer' + ':' + (#fundTransferRequest).hashCode()")
    public ResponseEntity<String> fundTransfer(@RequestBody FundTransferRequest fundTransferRequest) {
        accountService.fundTransfer(
                LoggedinUser.getAccountNumber(),
                fundTransferRequest.targetAccountNumber(),
                fundTransferRequest.pin(),
                fundTransferRequest.amount());

        return ResponseEntity.ok(ApiMessages.CASH_TRANSFER_SUCCESS.getMessage());
    }

    @GetMapping("/transactions")
    public ResponseEntity<String> getAllTransactionsByAccountNumber() {
        val transactions = transactionService
                .getAllTransactionsByAccountNumber(LoggedinUser.getAccountNumber());
        return ResponseEntity.ok(JsonUtil.toJson(transactions));
    }
    @GetMapping("/send-statement")
    public ResponseEntity<String> sendBankStatement() {
        String accountNumber = LoggedinUser.getAccountNumber(); // Get logged-in user account
        transactionService.sendBankStatementByEmail(accountNumber);
        return ResponseEntity.ok("{\"message\": \"Bank statement sent to your email.\"}");
    }

}
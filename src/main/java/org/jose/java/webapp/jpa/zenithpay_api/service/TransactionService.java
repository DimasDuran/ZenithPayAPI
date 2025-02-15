package org.jose.java.webapp.jpa.zenithpay_api.service;

import java.util.List;

import org.jose.java.webapp.jpa.zenithpay_api.dto.TransactionDTO;

public interface TransactionService {

    List<TransactionDTO> getAllTransactionsByAccountNumber(String accountNumber);
    void sendBankStatementByEmail(String accountNumber);

}
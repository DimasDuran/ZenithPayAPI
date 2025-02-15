package org.jose.java.webapp.jpa.zenithpay_api.mapper;

import org.springframework.stereotype.Component;

import org.jose.java.webapp.jpa.zenithpay_api.dto.TransactionDTO;
import org.jose.java.webapp.jpa.zenithpay_api.entity.Transaction;

@Component
public class TransactionMapper {

    public TransactionDTO toDto(Transaction transaction) {
        return new TransactionDTO(transaction);
    }

}
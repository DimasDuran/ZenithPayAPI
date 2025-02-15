package org.jose.java.webapp.jpa.zenithpay_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.jose.java.webapp.jpa.zenithpay_api.entity.Account;
import org.jose.java.webapp.jpa.zenithpay_api.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByToken(String token);

    Token[] findAllByAccount(Account account);

    void deleteByToken(String token);
}
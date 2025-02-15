package org.jose.java.webapp.jpa.zenithpay_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.jose.java.webapp.jpa.zenithpay_api.entity.PasswordResetToken;
import org.jose.java.webapp.jpa.zenithpay_api.entity.User;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    Optional<PasswordResetToken> findByToken(String token);

    PasswordResetToken findByUser(User user);

    void deleteByToken(String token);
}
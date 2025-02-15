package org.jose.java.webapp.jpa.zenithpay_api.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import org.jose.java.webapp.jpa.zenithpay_api.exception.NotFoundException;

import lombok.val;

public class LoggedinUser {

    /**
     * Returns the account number of the currently logged in user. If there is
     * no user logged in, an exception is thrown.
     *
     * @return The account number of the currently logged in user.
     * @throws NotFoundException If there is no user logged in.
     */
    public static String getAccountNumber() {
        val authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            throw new NotFoundException("No user is currently logged in.");
        }
        val principal = (User) authentication.getPrincipal();
        return principal.getUsername();
    }

}
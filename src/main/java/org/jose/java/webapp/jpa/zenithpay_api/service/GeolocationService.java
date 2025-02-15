package org.jose.java.webapp.jpa.zenithpay_api.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;

import org.jose.java.webapp.jpa.zenithpay_api.dto.GeolocationResponse;

public interface GeolocationService {

    @Async
    public CompletableFuture<GeolocationResponse> getGeolocation(String ip);
}
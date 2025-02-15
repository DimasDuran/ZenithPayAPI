package org.jose.java.webapp.jpa.zenithpay_api.service;

import org.jose.java.webapp.jpa.zenithpay_api.type.CacheKeyType;
import java.util.Optional;

public interface CacheService {

    boolean exists(CacheKeyType cacheKeyType, String... keyArguments);

    Optional<String> get(CacheKeyType cacheKeyType, String... keyArguments);

    <T> Optional<T> get(CacheKeyType cacheKeyType, Class<T> clazz, String... keyArguments);

    void put(CacheKeyType cacheKeyType, Object value, String... keyArguments);

    void put(CacheKeyType cacheKeyType, Object value, long ttlSeconds, String... keyArguments);

    void delete(CacheKeyType cacheKeyType, String... keyArguments);
}
package com.hitsto.infrastructure.config.cache;

import java.time.Duration;

import static com.hitsto.infrastructure.mysql.repository.redirect.UrlRedirectDbRepository.REDIRECT_CACHE_VALUE;

public enum CacheNamesTTL {

    REDIRECT_CACHE_TTL(REDIRECT_CACHE_VALUE, Duration.ofMinutes(5));

    private final String cacheName;
    private final Duration ttl;

    CacheNamesTTL(final String cacheName, final Duration ttl) {
        this.cacheName = cacheName;
        this.ttl = ttl;
    }

    public String getCacheName() {
        return cacheName;
    }

    public Duration getTtl() {
        return ttl;
    }
}

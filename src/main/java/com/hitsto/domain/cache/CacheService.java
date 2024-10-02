package com.hitsto.domain.cache;

public interface CacheService {

    void cachePut(final String key, final Object value, final long ttl);

    Object cachePut(final String key);
}

package com.hitsto.infrastructure.redis;

import com.hitsto.domain.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService implements CacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(final RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void cachePut(final String key, final Object value, final long ttl) {
        redisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
    }

    @Override
    public Object cachePut(final String key) {
        return redisTemplate.opsForValue().get(key);
    }
}

package org.example.rateLimiter.tokenBucketRateLimiter;

import org.example.rateLimiter.RateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBucketRateLimiter implements RateLimiter {

    Map<String, TokenBucket> userTokenBuckets;
    @Value("${rateLimiter.capacity:100}")
    long capacity;
    @Value("${rateLimiter.refillRate:5}")
    int refillRate;

    public TokenBucketRateLimiter() {
        userTokenBuckets = new ConcurrentHashMap<>();
    }

    @Override
    public boolean allowRequest(String userId) {
        userTokenBuckets.computeIfAbsent(userId, id -> new TokenBucket(capacity, refillRate, userId));
        return userTokenBuckets.get(userId).allowRequest();
    }
}

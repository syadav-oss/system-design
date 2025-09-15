package org.example.rateLimiter;

public interface RateLimiter {
    public boolean allowRequest(String userId);
}

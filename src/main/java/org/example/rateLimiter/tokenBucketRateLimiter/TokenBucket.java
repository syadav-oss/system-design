package org.example.rateLimiter.tokenBucketRateLimiter;

public class TokenBucket {
    long tokens;
    int refillRate; // per second
    long capacity;
    long lastRefillTs;
    String userId;

    public TokenBucket(long capacity, int refillRate, String userId) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = refillRate;
        this.lastRefillTs = System.currentTimeMillis();
        this.userId = userId;
    }


    public synchronized boolean allowRequest() {
        refill();
        if(tokens >= 1) {
            tokens--;
            System.out.println("[" + userId + "] Request allowed, available tokens = " + tokens);
            return true;
        }

        System.out.println("[" + userId + "] Request Denied, insufficient tokens");
        return false;
    }

    public void refill() {
        long now = System.currentTimeMillis();
        if((now-lastRefillTs) > 0) {
            long tokensToAdd = (now - lastRefillTs) / 1000 * refillRate;
            tokens = Math.min(capacity, tokensToAdd + tokens);
            lastRefillTs = now;
        }
    }
}

package org.example.rateLimiter.leakyRateLimiter;

import org.example.rateLimiter.RateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeakyRateLimiter implements RateLimiter {
    private final Map<String, LeakyBucket> userBucket;
    long capacity;
    int dequeueRate;
    private final ScheduledExecutorService scheduler;

    public LeakyRateLimiter(long capacity, int dequeueRate) {
        this.capacity = capacity;
        this.dequeueRate = dequeueRate;
        userBucket = new ConcurrentHashMap<>();

        scheduler = Executors.newScheduledThreadPool(1);
        processQueuedRequests();
    }

    @Override
    public boolean allowRequest(String userId) {
        LeakyBucket bucket = userBucket.computeIfAbsent(userId, id -> new LeakyBucket(capacity, dequeueRate, userId));
        String requestId = userId + "_" + System.currentTimeMillis();
        return bucket.allowRequest(requestId);
    }

    public void processQueuedRequests() {
        // Schedule periodic leak (once per second for simplicity)

        scheduler.scheduleAtFixedRate(() -> {
            for (LeakyBucket bucket : userBucket.values()) {
                bucket.processQueuedRequests();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }


    public void shutdown() {
        System.out.println("Shutting down the scheduler...");
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(3, TimeUnit.SECONDS)) {
                System.out.println("Scheduler didn't terminate in time, forcing shutdown.");
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted during shutdown, forcing shutdown now.");
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }


}

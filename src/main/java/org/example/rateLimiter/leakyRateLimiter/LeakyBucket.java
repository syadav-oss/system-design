package org.example.rateLimiter.leakyRateLimiter;

import java.util.LinkedList;
import java.util.Queue;

public class LeakyBucket {
    private final Queue<String> queue;
    long capacity;
    int dequeueRate;
    String userId;

    public LeakyBucket(long capacity, int dequeueRate, String userId) {
        this.capacity = capacity;
        this.dequeueRate = dequeueRate;
        this.userId = userId;
        this.queue = new LinkedList<>();
    }

    public synchronized boolean allowRequest(String requestId) {
        if( queue.size() == capacity ) {
            System.out.println("[" + userId + "] Request Denied (bucket overflowed) at " + System.currentTimeMillis());
            return false;
        }
        System.out.println("[" + userId + "] Request Allowed (queued) at " + System.currentTimeMillis());
        queue.add(requestId);
        return true;
    }

    public void processQueuedRequests() {
        if(queue.isEmpty()){
            System.out.println("No request to processes");
            return;
        }
        for(int i=0;i<Math.min(dequeueRate, queue.size());i++){
            System.out.println("[" + userId + "] Processed requestId = " + queue.poll() + " at " + System.currentTimeMillis());
        }
    }
}

package org.example.rateLimiter.tokenBucketRateLimiter;

public class TokenBucketRateLimiterTest {

    public static void main(String[] args) {
        TokenBucketRateLimiter rateLimiter = new TokenBucketRateLimiter(); // 5 tokens, 2/sec

        Runnable userTask = () -> {
            String user = Thread.currentThread().getName();
            for (int i = 0; i < 10; i++) {
                boolean allowed = rateLimiter.allowRequest(user);
                System.out.println("[" + user + "] Request " + i + ": " + (allowed ? "✔️" : "❌"));
                try {
                    Thread.sleep(500); // simulate time between requests
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Launch 3 user threads
        Thread t1 = new Thread(userTask, "sagar");
        Thread t2 = new Thread(userTask, "dev");
        Thread t3 = new Thread(userTask, "admin");

        t1.start();
        t2.start();
        t3.start();
    }


}

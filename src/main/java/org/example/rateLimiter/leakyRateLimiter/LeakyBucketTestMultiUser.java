package org.example.rateLimiter.leakyRateLimiter;

public class LeakyBucketTestMultiUser {
    public static void main(String[] args) throws InterruptedException {
        LeakyRateLimiter limiter = new LeakyRateLimiter(5, 2); // Capacity = 5, Leak = 2/sec

        Runnable userTask = () -> {
            String userId = Thread.currentThread().getName();
            for (int i = 0; i < 8; i++) {
                boolean allowed = limiter.allowRequest(userId);
                System.out.println("[" + userId + "] Request " + i + " -> " + (allowed ? "Allowed" : "Rejected"));
                try {
                    Thread.sleep(150); // Requests come in every 150ms
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };



        Thread user1 = new Thread(userTask, "user-sagar");
        Thread user2 = new Thread(userTask, "user-raj");
        Thread user3 = new Thread(userTask, "user-pooja");

        user1.start();
        user2.start();
        user3.start();

        user1.join();
        user2.join();
        user3.join();

        Thread.sleep(5000); // Wait to see draining in logs

        // Shutdown the scheduler
        limiter.shutdown();

    }
}

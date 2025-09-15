package org.example.producerConsumerProblem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {


        ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,2L, TimeUnit.DAYS, new ArrayBlockingQueue<>(3));

        ProducerConsumer pc = new ProducerConsumer();

        List<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(3);
        arr.add(5);

        for(int i : arr) {
            arr.add(i);
        }

        // Test Case 1: Single Producer, Single Consumer
        Thread producer1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                pc.produce(i);
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
        });

        Thread consumer1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                pc.consume();
                try { Thread.sleep(150); } catch (InterruptedException ignored) {}
            }
        });

        // Test Case 2: Multiple Producers and Consumers
        Thread producer2 = new Thread(() -> {
            for (int i = 6; i <= 10; i++) {
                pc.produce(i);
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            }
        });

        Thread consumer2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                pc.consume();
                try { Thread.sleep(120); } catch (InterruptedException ignored) {}
            }
        });

        producer1.start();
        consumer1.start();
        producer2.start();
        consumer2.start();

        try {
            producer1.join();
            consumer1.join();
            producer2.join();
            consumer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("✅ All test cases finished!");
    }

}

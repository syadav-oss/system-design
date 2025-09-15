package org.example.producerConsumerProblem;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    Queue<Integer> items = new LinkedList<>();
    int size = 10;

    public synchronized void produce(int i) {
        System.out.println("Inside produce method");
        while (items.size() == size) {
            try {
                System.out.println("Producer thread is waiting");
                wait();
            } catch (Exception _) {

            }

        }
        items.add(i);
        notifyAll();
        System.out.println("Producer thread added item");
    }


    public synchronized int consume() {
        System.out.println("Inside consume method");

        while (items.isEmpty()) {
            try {
                System.out.println("Consumer thread is waiting");
                wait();
            } catch (Exception _) {

            }
        }
        int item = items.poll();
        System.out.println("Consumer thread consumed " + item);
        notifyAll();
        return item;
    }

}

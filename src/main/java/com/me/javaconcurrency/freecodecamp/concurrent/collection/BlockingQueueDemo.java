package com.me.javaconcurrency.freecodecamp.concurrent.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    static final int QUEUE_CAPACITY = 10;
    private static final Logger log = LoggerFactory.getLogger(BlockingQueueDemo.class);
    static BlockingQueue<Integer> taskQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    public static void main(String[] args) {
        // Producer Thread
        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 20; i++) {
                    log.info("Task Produced: {}", i);
                    taskQueue.put(i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // consumer thread
        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    int task = taskQueue.take();
                    processTask(task, "ConsumerOne");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumerThreadTwo = new Thread(() -> {
            try {
                while (true) {
                    int task = taskQueue.take();
                    processTask(task, "ConsumerTwo");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producerThread.start();
        consumerThread.start();
        consumerThreadTwo.start();
    }

    private static void processTask(int task, String consumerName) throws InterruptedException {
        log.info("Task being processed by: {} task: {}", consumerName, task);
        Thread.sleep(1000);
        log.info("Task consumed by {}, task: {}", consumerName, task);
    }
}

package com.me.javaconcurrency.freecodecamp.concurrent.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    private static final Logger log = LoggerFactory.getLogger(ReentrantLockDemo.class);
    private int count = 0;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();  // Acquire the lock
        try {
            count++;
            ReentrantLockDemo.log.info(Thread.currentThread().getName() + " incremented count to: " + count);
        } finally {
            lock.unlock();  // Release the lock
        }
    }

    public void decrement() {
        lock.lock();  // Acquire the lock
        try {
            count--;
            System.out.println(Thread.currentThread().getName() + " decremented count to: " + count);
        } finally {
            lock.unlock();  // Release the lock
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo counter = new ReentrantLockDemo();

        Runnable incrementTask = () -> {
            for (int i = 0; i < 5; i++) {
                counter.increment();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable decrementTask = () -> {
            for (int i = 0; i < 5; i++) {
                counter.decrement();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(incrementTask, "IncrementThread");
        Thread t2 = new Thread(decrementTask, "DecrementThread");

        t1.start();
        t2.start();
    }
}

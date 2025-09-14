package com.me.javaconcurrency.freecodecamp.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockWithCustomObjects {

    private static final Logger log = LoggerFactory.getLogger(LockWithCustomObjects.class);
    private static int counter1 = 0;
    private static int counter2 = 0;
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment1();
            }
        });

        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                increment2();
            }
        });
        
        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        
        log.info("counter1: {}, counter2: {}", counter1, counter2);
    }

    private static void increment1() {
        synchronized (lock1) {
            counter1++;
        }
    }

    private static void increment2() {
        synchronized (lock2) {
            counter2++;
        }
    }
}

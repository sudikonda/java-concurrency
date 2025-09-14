package com.me.javaconcurrency.freecodecamp.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SynchronizationDemo {
    private static final Logger log = LoggerFactory.getLogger(SynchronizationDemo.class);
    private static int counter = 0;

    public static void main(String[] args) {
        log.info("Before - Counter value: {}", counter);
        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                counter++;
                increment();
            }
        });

        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
//                counter++; // counter = counter + 1
                increment();
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

        log.info("After - counter value: {}", counter);
    }

    // only allow one and only one thread to access counter
    private static synchronized void increment() {
        counter++;
    }
}

package com.me.javaconcurrency.freecodecamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoinThreadExample {
    private static final Logger log = LoggerFactory.getLogger(JoinThreadExample.class);

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                log.info("Thread 1: {}", i);
            }
        });

        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < 25; i++) {
                log.info("Thread 2: {}", i);
            }
        });

        log.info("Starting threads !!");

        threadOne.start();
        threadTwo.start();

        // wait for both the threads to be completed
        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            log.error("Interrupted Exception");
        }

        log.info("Stopping threads !!");
    }
}

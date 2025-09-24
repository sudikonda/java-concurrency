package com.me.javaconcurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariableDemo {

    private static final Logger log = LoggerFactory.getLogger(AtomicVariableDemo.class);
    private static int count = 0;
    private static final AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                count++;
                counter.incrementAndGet();
            }
        });

        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                count++;
                counter.incrementAndGet();
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

        log.info("int count value: {}", count);
        log.info("Atomic Int value: {}", counter.get());


    }
}

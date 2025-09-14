package com.me.javaconcurrency.freecodecamp.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitAndNotifyDemo {

    private static final Object LOCK = new Object();
    private static final Logger log = LoggerFactory.getLogger(WaitAndNotifyDemo.class);

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            try {
                one();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread threadTwo = new Thread(() -> {
            try {
                two();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        threadOne.start();
        threadTwo.start();
    }

    private static void one() throws InterruptedException {
        synchronized (LOCK) {
            log.info("Hello from method one...");
            LOCK.wait();
            log.info("Back again in the method one");
        }
    }

    private static void two() throws InterruptedException {
        synchronized (LOCK) {
            log.info("Hello from method two");
            LOCK.notify();
            log.info("Back again in the method two after notifying...");
        }
    }
}

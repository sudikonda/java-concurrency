package com.me.javaconcurrency.freecodecamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtendsThreadExample {
    private static final Logger log = LoggerFactory.getLogger(ExtendsThreadExample.class);

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.start();
        thread2.start();
    }
}

class Thread1 extends Thread {
    private static final Logger log = LoggerFactory.getLogger(Thread1.class);

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            log.info("Thread 1: {}", i);
        }
    }
}

class Thread2 extends Thread {
    private static final Logger log = LoggerFactory.getLogger(Thread2.class);

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            log.info("Thread 2: {}", i);
        }
    }
}

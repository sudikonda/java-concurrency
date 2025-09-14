package com.me.javaconcurrency.freecodecamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ThreadPriorityExample {

    private static final Logger log = LoggerFactory.getLogger(ThreadPriorityExample.class);

    public static void main(String[] args) {
//        demoThreadPriority();

        // Main Thread always have first priority in execution even though it is NORM_PRIORITY
        log.info("{} says Hi !!", Thread.currentThread().getName());

        Thread threadOne = new Thread(() -> {
            log.info("{} says Hi as well !!", Thread.currentThread().getName());
        });

        threadOne.setPriority(Thread.MAX_PRIORITY);
        threadOne.start();



    }

    private static void demoThreadPriority() {
        log.info("Current Thread Name: {}, Priority: {}", Thread.currentThread().getName(), Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        log.info("Current Thread Name: {}, Priority: {}", Thread.currentThread().getName(), Thread.currentThread().getPriority());
    }
}

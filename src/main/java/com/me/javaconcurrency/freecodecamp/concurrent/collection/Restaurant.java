package com.me.javaconcurrency.freecodecamp.concurrent.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class Restaurant {

    private static final Logger log = LoggerFactory.getLogger(Restaurant.class);

    public static void main(String[] args) {
        int numberOfChefs = 3;
        CountDownLatch latch = new CountDownLatch(numberOfChefs);

        log.info("Starting to prepare dishes....");

        new Thread(new Chef("Chef A", "Pizza", latch)).start();
        new Thread(new Chef("Chef B", "Pasta", latch)).start();
        new Thread(new Chef("Chef C", "Salad", latch)).start();

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("All the dishes are ready !! Let's start serving....");

    }
}

class Chef implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Chef.class);
    private final String name;
    private final String dish;
    private final CountDownLatch latch;

    public Chef(String name, String dish, CountDownLatch latch) {
        this.name = name;
        this.dish = dish;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            log.info("{} is preparing {}", name, dish);
            log.info(Thread.currentThread().getName());
            Thread.sleep(2000); // cooking dish
            log.info("{} has finished preparing {}", name, dish);
            latch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}



package com.me.javaconcurrency.freecodecamp.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerDemo {

    public static void main(String[] args) {
        Worker worker = new Worker(5, 0);

        Thread producerThread = new Thread(() -> {
            try {
                worker.produce();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                worker.consume();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}

class Worker {
    private static final Logger log = LoggerFactory.getLogger(Worker.class);
    private int sequence = 0;
    private final Integer top;
    private final Integer bottom;
    private final List<Integer> container;
    private final Object LOCK = new Object();

    public Worker(Integer top, Integer bottom) {
        this.top = top;
        this.bottom = bottom;
        this.container = new ArrayList<>();
    }

    public void produce() throws InterruptedException{
        synchronized (LOCK) {
            while (true) {
                if (container.size() == top) {
                    log.info("Container is full, waiting for items to be removed");
                    LOCK.wait();
                } else {
                    log.info("{} added to the container", sequence);
                    container.add(sequence++);
                    LOCK.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (LOCK) {
            while (true) {
                if (container.size() == bottom) {
                    log.info("Container is empty, waiting for items to be added");
                    LOCK.wait();
                } else {
                    log.info("{} is removed from the container", container.removeFirst());
                    LOCK.notify();
                }
                Thread.sleep(500);
            }

        }

    }
}


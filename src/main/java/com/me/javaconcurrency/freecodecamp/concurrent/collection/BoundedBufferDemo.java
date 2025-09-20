package com.me.javaconcurrency.freecodecamp.concurrent.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedBufferDemo {
    public static void main(String[] args) {
        ConditionDemo conditionDemo = new ConditionDemo();

        Thread producerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    conditionDemo.put(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    conditionDemo.take();
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producerThread.start();
        consumerThread.start();

    }
}


class ConditionDemo {


    private static final Logger log = LoggerFactory.getLogger(ConditionDemo.class);
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    final Object[] items = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await(); // Wait for room
            items[putptr] = x;
            log.info("Produced >> {}", x);
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal(); // Notify consumer
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await(); // Wait for data
            Object x = items[takeptr];
            log.info("Consumed << {}", x);
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal(); // Notify producer
            return x;
        } finally {
            lock.unlock();
        }
    }
}


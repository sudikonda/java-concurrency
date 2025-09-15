package com.me.javaconcurrency.freecodecamp.executor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            for (int i = 0; i < 7; i++) {
                executorService.execute(new UserWorkTask(i));
            }
        }

    }
}

class UserWorkTask implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(UserWorkTask.class);
    private final int workId;

    public UserWorkTask(int workId) {
        this.workId = workId;
    }

    @Override
    public void run() {
        log.info("Task ID: {} is being executed by thread: {}", workId, Thread.currentThread().getName());

        // mimic some work done here
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

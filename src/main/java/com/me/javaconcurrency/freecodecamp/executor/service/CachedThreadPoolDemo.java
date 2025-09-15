package com.me.javaconcurrency.freecodecamp.executor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 1000; i++) {
                executorService.execute(new TaskOne(i));
            }
        }
    }
}

class TaskOne implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(TaskOne.class);
    private final int taskId;

    public TaskOne(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        log.info("Task: {} is being executed by Thread: {}", taskId, Thread.currentThread().getName());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
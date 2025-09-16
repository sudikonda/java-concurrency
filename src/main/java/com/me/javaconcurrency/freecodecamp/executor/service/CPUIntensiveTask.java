package com.me.javaconcurrency.freecodecamp.executor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CPUIntensiveTask {
    private static final Logger log = LoggerFactory.getLogger(CPUIntensiveTask.class);

    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        log.info("Created Thread Pool with cores: {}", cores);

        for (int i = 0; i < 20; i++) {
            executorService.execute(new CpuTask());
        }

    }
}

class CpuTask implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(CpuTask.class);

    @Override
    public void run() {
        log.info("Some CPU intensive task being done by: {}", Thread.currentThread().getName());
    }
}
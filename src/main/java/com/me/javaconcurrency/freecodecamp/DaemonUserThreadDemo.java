package com.me.javaconcurrency.freecodecamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaemonUserThreadDemo {
    public static void main(String[] args) {
        Thread backgroundThread = new Thread(new DaemonHelper());
        Thread userThread = new Thread(new UserThreadHelper());

        backgroundThread.setDaemon(true);

        backgroundThread.start();
        userThread.start();
    }
}

class DaemonHelper implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(DaemonHelper.class);

    @Override
    public void run() {
        int count = 0;
        while (count < 500) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            log.info("Daemon Thread running. count: {}", count);
        }
    }
}

class UserThreadHelper implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(UserThreadHelper.class);

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("User thread done with execution");
    }
}

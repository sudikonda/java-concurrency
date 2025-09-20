package com.me.javaconcurrency.freecodecamp.concurrent.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();

        Thread threadOne = new Thread(new FirstThread(exchanger));
        Thread threadTwo = new Thread(new SecondThread(exchanger));

        threadOne.start();
        threadTwo.start();
    }
}

class FirstThread implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(FirstThread.class);
    private final Exchanger<Integer> exchanger;

    public FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int dataToSend = 10;
        log.info("First thread is sending data: {}", dataToSend);

        try {
            Integer receivedData = exchanger.exchange(dataToSend);
            log.info("First thread received data: {}", receivedData);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class SecondThread implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(SecondThread.class);
    private final Exchanger<Integer> exchanger;

    public SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            int dataToSend = 20;
            log.info("Second Thread is sending data.. {}", dataToSend);
            Integer receivedData = exchanger.exchange(dataToSend);
            log.info("Second Thread received data: {}", receivedData);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

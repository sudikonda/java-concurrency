package com.me.javaconcurrency.freecodecamp.concurrent.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// Multistate tour.. Tourists visiting multiple attractions reach a checkpoint.
public class CyclicBarrierDemo {

    private static final Logger log = LoggerFactory.getLogger(CyclicBarrierDemo.class);

    private static final int NUM_TOURISTS = 5;
    private static final int NUM_STAGES = 3;
    private static final CyclicBarrier BARRIER = new CyclicBarrier(NUM_TOURISTS, () -> log.info("Tour guide starts speaking.."));

    public static void main(String[] args) {
        for (int i = 0; i < NUM_TOURISTS; i++) {
            Thread touristThread = new Thread(new Tourist(i));
            touristThread.start();
        }
    }

    static class Tourist implements Runnable {
        private final int touristId;

        public Tourist(int touristId) {
            this.touristId = touristId;
        }

        @Override
        public void run() {
            for (int i = 0; i < NUM_STAGES; i++) {
                try {
                    Thread.sleep(1000); // tourist visiting attraction - simulation
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                log.info("Tourist {} arrives at stage {}", touristId, i + 1);
                try {
                    BARRIER.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}

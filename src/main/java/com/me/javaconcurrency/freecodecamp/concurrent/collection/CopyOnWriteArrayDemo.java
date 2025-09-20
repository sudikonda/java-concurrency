package com.me.javaconcurrency.freecodecamp.concurrent.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayDemo {
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.simulate();
    }
}

class Simulation {
    private final List<Integer> integerList;

    public Simulation() {
        this.integerList = new CopyOnWriteArrayList<>();
        this.integerList.addAll(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
    }

    public void simulate() {
        Thread one = new Thread(new UserWriteTask(integerList));
        Thread two = new Thread(new UserWriteTask(integerList));
        Thread three = new Thread(new UserWriteTask(integerList));
        Thread four = new Thread(new UserReadTask(integerList));

        one.start();
        two.start();
        three.start();
        four.start();
    }
}

class UserReadTask implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(UserReadTask.class);
    private final List<Integer> integerList;

    public UserReadTask(List<Integer> integerList) {
        this.integerList = integerList;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("integerList: {}", integerList);
        }
    }
}

class UserWriteTask implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(UserWriteTask.class);
    private final List<Integer> integerList;
    private final Random random;

    public UserWriteTask(List<Integer> integerList) {
        this.integerList = integerList;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int randomInt = random.nextInt(integerList.size());
            int randomIndex = random.nextInt(10);
            log.info("inserting {} into index: {}", randomInt, randomIndex);
            integerList.set(randomInt, randomIndex);
        }
    }
}

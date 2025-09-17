package com.me.javaconcurrency.freecodecamp.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedCollectionsDemo {
    private static final Logger log = LoggerFactory.getLogger(SynchronizedCollectionsDemo.class);

    public static void main(String[] args) {
//        List<Integer> integerList = new ArrayList<>();

        // synchronized list
        List<Integer> integerList = Collections.synchronizedList(new ArrayList<>());

        Thread threadOne = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                integerList.add(i);
            }
        });

        Thread threadTwo = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                integerList.add(i);
            }
        });

        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        log.info("Integer Array List: {}", integerList);
        log.info("Integer Array List size: {}", integerList.size());
    }
}

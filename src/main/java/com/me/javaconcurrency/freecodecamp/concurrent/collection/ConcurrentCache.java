package com.me.javaconcurrency.freecodecamp.concurrent.collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCache {

    private static final Map<String, String> cache = new ConcurrentHashMap<>();
    private static final Logger log = LoggerFactory.getLogger(ConcurrentCache.class);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;

            new Thread(() -> {
                String key = "Key@" + threadNum;
                for (int j = 0; j < 3; j++) {
                    String value = getCacheValue(key);
                    log.info("Thread: {}, Key: {}, Value: {}", Thread.currentThread().getName(), key, value);
                }
            }).start();
        }
    }

    private static String getCacheValue(String key) {
        String value = cache.get(key);

        if (value == null) {
            value = computeValue(key);
            cache.put(key, value);
        }

        return value;
    }

    private static String computeValue(String key) {
       log.info("Key: {} not present in the cache, so going to compute", key);

        try {
            Thread.sleep(500); // simulate compute
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "Value@" + key;

    }


}

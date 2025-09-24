package com.me.javaconcurrency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// sample simulation of web scrapper
public class SemaphoreDemo {

    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            for (int i = 0; i < 15; i++) {
                executorService.execute(ScrapeService.INSTANCE::scrape);
            }
        }
    }

}

enum ScrapeService {
    INSTANCE;

    private static final Logger log = LoggerFactory.getLogger(ScrapeService.class);
    private final Semaphore semaphore = new Semaphore(3);

    public void scrape() {
        log.info("before: available permits: {}", semaphore.availablePermits());
        try {
            semaphore.acquire();
            invokeScrapeBot();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
            log.info("after: available permits: {}", semaphore.availablePermits());
        }
    }

    private void invokeScrapeBot() {
        try {
            log.info("scraping data......");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

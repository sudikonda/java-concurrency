package com.me.javaconcurrency.freecodecamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableDemo {
    private static final Logger log = LoggerFactory.getLogger(CallableDemo.class);

    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            Future<String> returnVal = executorService.submit(new ReturnValueTask());
            try {
                String returnedString = returnVal.get();
                log.info("return value: {}", returnedString);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

            log.info("Main thread execution completed !!");
        }
    }
}

class ReturnValueTask implements Callable<String > {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        return "hello there !!";
    }
}

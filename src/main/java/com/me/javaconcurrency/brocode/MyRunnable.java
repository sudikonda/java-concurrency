package com.me.javaconcurrency.brocode;

public class MyRunnable implements Runnable {

    private String text;

    public MyRunnable(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " " + text);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
            }
        }
    }
}

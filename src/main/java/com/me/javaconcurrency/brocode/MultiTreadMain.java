package com.me.javaconcurrency.brocode;

public class MultiTreadMain {

    public static void main(String[] args) {

        // Multi-Threading = Enables a program to run multiple threads concurrently
        // Thread = A set of instructions that runs independently
        // Useful for background tasks for time-consuming operations

        MyRunnable myRunnable = new MyRunnable();
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(new MyRunnable());

        thread1.start();
        thread2.start();

    }
}

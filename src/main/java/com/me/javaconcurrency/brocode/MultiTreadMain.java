package com.me.javaconcurrency.brocode;

public class MultiTreadMain {

    public static void main(String[] args) {

        // Multi-Threading = Enables a program to run multiple threads concurrently
        // Thread = A set of instructions that runs independently
        // Useful for background tasks for time-consuming operations

        MyRunnable myRunnable = new MyRunnable("PING");
        Thread thread1 = new Thread(myRunnable);
        Thread thread2 = new Thread(new MyRunnable("PONG"));

        System.out.println("Game Starts !!");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.out.println("Main Thread was interrupted");
        }

        System.out.println("Game Over !!");

    }
}

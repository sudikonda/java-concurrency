package com.me.javaconcurrency;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

class SumTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 10; // threshold for splitting
    private int[] array;
    private int start, end;

    public SumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    protected Integer compute() {
        if ((end - start) <= THRESHOLD) {
            // Compute sum directly
            int sum = 0;
            for (int i = start; i < end; i++)
                sum += array[i];
            return sum;
        } else {
            // Split task
            int mid = (start + end) / 2;
            SumTask leftTask = new SumTask(array, start, mid);
            SumTask rightTask = new SumTask(array, mid, end);

            leftTask.fork();  // fork left half
            int rightResult = rightTask.compute(); // compute right half directly
            int leftResult = leftTask.join();  // join left result

            return leftResult + rightResult;
        }
    }
}

public class ForkJoinDemo {
    public static void main(String[] args) {
        int[] numbers = new int[100];
        for(int i=0; i<100; i++) numbers[i] = i+1;

        ForkJoinPool pool = new ForkJoinPool();
        SumTask task = new SumTask(numbers, 0, numbers.length);

        int sum = pool.invoke(task);
        System.out.println("Sum: " + sum);
    }
}


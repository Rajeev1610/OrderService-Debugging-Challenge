package com.example.orderservice.service;

import java.util.*;
import java.util.concurrent.*;

public class PrimeSumCalculator {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int rangeStart = 1;
        int rangeEnd = 1000000;
        int numThreads = 4;

        long totalSum = calculateSumOfPrimes(rangeStart, rangeEnd, numThreads);
        System.out.println("Total sum of primes: " + totalSum);
    }

    private static long calculateSumOfPrimes(int rangeStart, int rangeEnd, int numThreads) throws InterruptedException, ExecutionException {
        // Divide the range and assign tasks to threads
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
        List<Future<Long>> results = new ArrayList<>();

        int chunkSize = (rangeEnd - rangeStart + 1) / numThreads;
        for (int i = 0; i < numThreads; i++) {
            int start = rangeStart + i * chunkSize;
            int end = (i == numThreads - 1) ? rangeEnd : start + chunkSize - 1;

            results.add(executor.submit(() -> calculatePrimeSumInRange(start, end)));
        }

        // Aggregate the results
        long totalSum = 0;
        for (Future<Long> result : results) {
            totalSum += result.get();
        }

        executor.shutdown();
        return totalSum;
    }

    private static long calculatePrimeSumInRange(int start, int end) {
        long sum = 0;
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                sum += i;
            }
        }
        return sum;
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}

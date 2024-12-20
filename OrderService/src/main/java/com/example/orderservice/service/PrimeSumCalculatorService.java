package com.example.orderservice.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PrimeSumCalculatorService {

    public String primeCalculation() throws InterruptedException {
        int rangeStart = 1;
        int rangeEnd = 1000000;
        int numThreads = 4;

        List<Thread> threads = new ArrayList<>();
        List<Long> threadsSums = new ArrayList<>();

        for (int i =0; i<numThreads;i++){
            threadsSums.add(0L);
        }
        int chunkSize = (rangeEnd-rangeStart+ 1)/numThreads;
        for(int i = 0; i<numThreads;i++){
            final int threadIndex = i;
            final int start = rangeStart + i * chunkSize;
            final int end = (i == numThreads-1) ? rangeEnd : start + chunkSize - 1;
            Thread thread = new Thread(()->{
               long sum = calculatePrimeSumInRange(start,end);
               synchronized (threadsSums){
                   threadsSums.set(threadIndex,sum);
               }
               System.out.println("Thread "+ threadIndex +" finished with range ["+start+"," + end + "] and sum: " + sum);
            });
            threads.add(thread);
            thread.start();
        }
        for(Thread thread : threads){
            thread.join();
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0;i<threadsSums.size();i++){
            result.append("Total sum of primes for thread").append(i+1).append(":").append(threadsSums.get(i)).append("\n");
        }
       return  result.toString();
    }

    /*private static long calculateSumOfPrimes(int rangeStart, int rangeEnd, int numThreads) throws InterruptedException, ExecutionException {
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
    }*/

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

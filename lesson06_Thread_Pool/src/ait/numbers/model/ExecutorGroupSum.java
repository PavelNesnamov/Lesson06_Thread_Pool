package ait.numbers.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ExecutorGroupSum extends GroupSum{
    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        ExecutorService executor = Executors.newFixedThreadPool(numberGroups.length);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int[] group : numberGroups) {
            // Add the task as a lambda implementing Callable<Integer>
            futures.add(executor.submit(() -> {
                int sum = 0;
                for (int num : group) {
                    sum += num;
                }
                return sum;
            }));
        }

        int totalSum = 0;
        for (Future<Integer> future : futures) {
            try {
                totalSum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        return totalSum;
    }
}
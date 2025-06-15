package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

public class ThreadGroupSum extends GroupSum {
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        int groupCount = numberGroups.length;
        OneGroupSum[] tasks = new OneGroupSum[groupCount];
        Thread[] threads = new Thread[groupCount];

        // Create and start threads
        for (int i = 0; i < groupCount; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        // Wait for all threads to complete
        for (int i = 0; i < groupCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Summarize all the results
        int totalSum = 0;
        for (OneGroupSum task : tasks) {
            totalSum += task.getSum();
        }

        return totalSum;
    }

}
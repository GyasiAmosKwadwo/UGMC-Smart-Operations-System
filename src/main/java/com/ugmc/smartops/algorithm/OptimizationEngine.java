package com.ugmc.smartops.algorithm;

import com.ugmc.smartops.datastructure.DynamicArray;
import com.ugmc.smartops.model.ServiceRequest;

/**
 * Custom Engine for Optimization Algorithms:
 * 1. Greedy Choice Algorithm (Priority dispatch based on urgency ratio)
 * 2. Dynamic Programming (0/1 Knapsack capacity planning for service execution)
 *
 * Implemented from scratch.
 *
 * @author UGMC Smart Operations Team
 */
public class OptimizationEngine {

    /** Result container for Dynamic Programming Knapsack optimization. */
    public static class OptimizationResult {
        private final DynamicArray<ServiceRequest> selectedRequests;
        private final int totalValueScore;
        private final int totalWeightCapacityUsed;

        public OptimizationResult(DynamicArray<ServiceRequest> selectedRequests,
                                  int totalValueScore, int totalWeightCapacityUsed) {
            this.selectedRequests = selectedRequests;
            this.totalValueScore = totalValueScore;
            this.totalWeightCapacityUsed = totalWeightCapacityUsed;
        }

        public DynamicArray<ServiceRequest> getSelectedRequests() { return selectedRequests; }
        public int getTotalValueScore() { return totalValueScore; }
        public int getTotalWeightCapacityUsed() { return totalWeightCapacityUsed; }

        @Override
        public String toString() {
            return "OptimizationResult{" +
                    "selectedRequests=" + selectedRequests.size() +
                    ", totalValueScore=" + totalValueScore +
                    ", capacityUsed=" + totalWeightCapacityUsed +
                    '}';
        }
    }

    /**
     * Greedy Optimization Algorithm:
     * Sorts service requests by urgency rank using QuickSort and returns greedy dispatch order.
     */
    public static DynamicArray<ServiceRequest> greedyDispatch(DynamicArray<ServiceRequest> requests) {
        ServiceRequestWrapper[] wrappers = new ServiceRequestWrapper[requests.size()];
        for (int i = 0; i < requests.size(); i++) {
            wrappers[i] = new ServiceRequestWrapper(requests.get(i));
        }

        // Sort using QuickSort in descending order of urgency
        SortEngine.quickSort(wrappers);

        DynamicArray<ServiceRequest> result = new DynamicArray<>();
        for (int i = wrappers.length - 1; i >= 0; i--) {
            result.add(wrappers[i].request);
        }
        return result;
    }

    /** Helper class for Greedy Sorting. */
    private static class ServiceRequestWrapper implements Comparable<ServiceRequestWrapper> {
        final ServiceRequest request;
        final int urgency;

        ServiceRequestWrapper(ServiceRequest request) {
            this.request = request;
            this.urgency = request.getUrgency();
        }

        @Override
        public int compareTo(ServiceRequestWrapper o) {
            return Integer.compare(this.urgency, o.urgency);
        }
    }

    /**
     * Dynamic Programming (0/1 Knapsack Algorithm):
     * Selects an optimal subset of service requests to maximize total urgency priority score
     * without exceeding maximum available hospital capacity budget.
     *
     * @param requests list of pending requests
     * @param capacityMaxBudget total available capacity budget
     */
    public static OptimizationResult dynamicProgrammingCapacityPlan(DynamicArray<ServiceRequest> requests, int capacityMaxBudget) {
        if (requests == null) {
            throw new IllegalArgumentException("Requests cannot be null");
        }
        if (capacityMaxBudget < 0) {
            throw new IllegalArgumentException("Capacity budget cannot be negative");
        }
        int n = requests.size();
        int[] weights = new int[n];
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            ServiceRequest req = requests.get(i);
            weights[i] = Math.max(1, 6 - req.getUrgency()); // Cost weight based on complexity
            values[i] = req.getUrgency() * 20;              // Value derived from urgency score
        }

        // DP Table: dp[i][w] = max value considering first i items with weight limit w
        int[][] dp = new int[n + 1][capacityMaxBudget + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacityMaxBudget; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Backtrack to find selected requests
        DynamicArray<ServiceRequest> selected = new DynamicArray<>();
        int w = capacityMaxBudget;
        int totalWeightUsed = 0;

        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                ServiceRequest req = requests.get(i - 1);
                selected.add(req);
                w -= weights[i - 1];
                totalWeightUsed += weights[i - 1];
            }
        }

        return new OptimizationResult(selected, dp[n][capacityMaxBudget], totalWeightUsed);
    }
}

package com.ugmc.smartops.algorithm;

import com.ugmc.smartops.datastructure.DynamicArray;
import com.ugmc.smartops.datastructure.Graph;
import com.ugmc.smartops.model.AlgorithmRun;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Empirical performance evaluation and reporting module.
 * Measures runtime and memory consumption of core searching, sorting,
 * and graph algorithms across varying input sizes N.
 *
 * @author UGMC Smart Operations Team
 */
public class PerformanceBenchmark {

    private static final Random random = new Random(42);
    private static int runCounter = 1;

    /**
     * Executes all benchmark suites and returns recorded AlgorithmRun instances.
     * Also prints human-readable comparison reports to stdout.
     */
    public static DynamicArray<AlgorithmRun> runAllBenchmarks() {
        DynamicArray<AlgorithmRun> runs = new DynamicArray<>();

        System.out.println("\n=========================================================================");
        System.out.println("            UGMC SMART OPERATIONS - EMPIRICAL BENCHMARK LAB              ");
        System.out.println("=========================================================================");

        benchmarkSorting(runs);
        benchmarkSearching(runs);
        benchmarkGraphMST(runs);

        System.out.println("\n[SUMMARY] Completed " + runs.size() + " benchmark test runs.");
        System.out.println("=========================================================================\n");

        return runs;
    }

    /**
     * Benchmarks Selection, Insertion, Merge, and Quick Sort across scaling input sizes.
     */
    public static void benchmarkSorting(DynamicArray<AlgorithmRun> runs) {
        System.out.println("\n--- 1. SORTING ALGORITHMS BENCHMARK ---");
        System.out.println(String.format("%-16s | %-10s | %-14s | %-12s | %-15s",
                "Algorithm", "Input (N)", "Time (ms)", "Memory (KB)", "Big-O (Worst)"));
        System.out.println("-------------------------------------------------------------------------");

        int[] sizes = {10, 100, 1000, 5000};
        String dateRun = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        for (int n : sizes) {
            Integer[] baseData = generateRandomArray(n);

            // Selection Sort
            Integer[] data1 = baseData.clone();
            BenchmarkResult r1 = measureMemoryAndTime(() -> SortEngine.selectionSort(data1));
            AlgorithmRun run1 = createRun("Selection Sort", n, r1, dateRun);
            runs.add(run1);
            printRow("Selection Sort", n, r1.timeMs, r1.memoryKb, "O(N^2)");

            // Insertion Sort
            Integer[] data2 = baseData.clone();
            BenchmarkResult r2 = measureMemoryAndTime(() -> SortEngine.insertionSort(data2));
            AlgorithmRun run2 = createRun("Insertion Sort", n, r2, dateRun);
            runs.add(run2);
            printRow("Insertion Sort", n, r2.timeMs, r2.memoryKb, "O(N^2)");

            // Merge Sort
            Integer[] data3 = baseData.clone();
            BenchmarkResult r3 = measureMemoryAndTime(() -> SortEngine.mergeSort(data3));
            AlgorithmRun run3 = createRun("Merge Sort", n, r3, dateRun);
            runs.add(run3);
            printRow("Merge Sort", n, r3.timeMs, r3.memoryKb, "O(N log N)");

            // Quick Sort
            Integer[] data4 = baseData.clone();
            BenchmarkResult r4 = measureMemoryAndTime(() -> SortEngine.quickSort(data4));
            AlgorithmRun run4 = createRun("Quick Sort", n, r4, dateRun);
            runs.add(run4);
            printRow("Quick Sort", n, r4.timeMs, r4.memoryKb, "O(N^2)");

            System.out.println("-------------------------------------------------------------------------");
        }
    }

    /**
     * Benchmarks Linear Search vs Binary Search across scaling input sizes.
     */
    public static void benchmarkSearching(DynamicArray<AlgorithmRun> runs) {
        System.out.println("\n--- 2. SEARCHING ALGORITHMS BENCHMARK ---");
        System.out.println(String.format("%-16s | %-10s | %-14s | %-12s | %-15s",
                "Algorithm", "Input (N)", "Time (ms)", "Memory (KB)", "Big-O (Worst)"));
        System.out.println("-------------------------------------------------------------------------");

        int[] sizes = {100, 1000, 10000, 100000};
        String dateRun = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        for (int n : sizes) {
            Integer[] sortedData = generateSortedArray(n);
            Integer target = sortedData[n - 1]; // worst case: target at end

            // Linear Search
            BenchmarkResult r1 = measureMemoryAndTime(() -> SearchEngine.linearSearch(sortedData, target));
            AlgorithmRun run1 = createRun("Linear Search", n, r1, dateRun);
            runs.add(run1);
            printRow("Linear Search", n, r1.timeMs, r1.memoryKb, "O(N)");

            // Binary Search
            BenchmarkResult r2 = measureMemoryAndTime(() -> SearchEngine.binarySearch(sortedData, target));
            AlgorithmRun run2 = createRun("Binary Search", n, r2, dateRun);
            runs.add(run2);
            printRow("Binary Search", n, r2.timeMs, r2.memoryKb, "O(log N)");

            System.out.println("-------------------------------------------------------------------------");
        }
    }

    /**
     * Benchmarks Prim's vs Kruskal's MST algorithms on scaling graph topologies.
     */
    public static void benchmarkGraphMST(DynamicArray<AlgorithmRun> runs) {
        System.out.println("\n--- 3. GRAPH MST ALGORITHMS BENCHMARK (PRIM VS KRUSKAL) ---");
        System.out.println(String.format("%-16s | %-10s | %-14s | %-12s | %-15s",
                "Algorithm", "Vertices (V)", "Time (ms)", "Memory (KB)", "Big-O (Worst)"));
        System.out.println("-------------------------------------------------------------------------");

        int[] vertexCounts = {10, 30, 60, 100};
        String dateRun = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        for (int v : vertexCounts) {
            Graph g = generateSyntheticGraph(v);

            // Prim's MST
            BenchmarkResult r1 = measureMemoryAndTime(() -> GraphEngine.primMST(g));
            AlgorithmRun run1 = createRun("Prim's MST", v, r1, dateRun);
            runs.add(run1);
            printRow("Prim's MST", v, r1.timeMs, r1.memoryKb, "O(E log V)");

            // Kruskal's MST
            BenchmarkResult r2 = measureMemoryAndTime(() -> GraphEngine.kruskalMST(g));
            AlgorithmRun run2 = createRun("Kruskal's MST", v, r2, dateRun);
            runs.add(run2);
            printRow("Kruskal's MST", v, r2.timeMs, r2.memoryKb, "O(E log E)");

            System.out.println("-------------------------------------------------------------------------");
        }
    }

    // --- Helper Methods ---

    private static BenchmarkResult measureMemoryAndTime(Runnable task) {
        System.gc();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ignored) {}

        long freeMemBefore = Runtime.getRuntime().freeMemory();
        long totalMemBefore = Runtime.getRuntime().totalMemory();
        long usedMemBefore = totalMemBefore - freeMemBefore;

        long startTime = System.nanoTime();
        task.run();
        long endTime = System.nanoTime();

        long freeMemAfter = Runtime.getRuntime().freeMemory();
        long totalMemAfter = Runtime.getRuntime().totalMemory();
        long usedMemAfter = totalMemAfter - freeMemAfter;

        long elapsedNs = Math.max(1, endTime - startTime);
        long memUsedKb = Math.max(0, (usedMemAfter - usedMemBefore) / 1024);

        return new BenchmarkResult(elapsedNs, memUsedKb);
    }

    private static AlgorithmRun createRun(String name, int inputSize, BenchmarkResult result, String dateRun) {
        String id = String.format("RUN%04d", runCounter++);
        return new AlgorithmRun(id, name, inputSize, result.elapsedNs, result.memoryKb, dateRun);
    }

    private static void printRow(String algo, int n, double timeMs, long memKb, String bigO) {
        System.out.println(String.format("%-16s | %-10d | %-14.4f | %-12d | %-15s",
                algo, n, timeMs, memKb, bigO));
    }

    private static Integer[] generateRandomArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(n * 10);
        }
        return arr;
    }

    private static Integer[] generateSortedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i * 2;
        }
        return arr;
    }

    private static Graph generateSyntheticGraph(int numVertices) {
        Graph g = new Graph(false);
        for (int i = 0; i < numVertices; i++) {
            g.addVertex("LOC_" + i);
        }
        // Connect each vertex to 3-5 random other vertices
        for (int i = 0; i < numVertices; i++) {
            int edges = 2 + random.nextInt(3);
            for (int e = 0; e < edges; e++) {
                int target = random.nextInt(numVertices);
                if (target != i) {
                    double weight = 0.5 + (random.nextDouble() * 9.5);
                    g.addEdge("LOC_" + i, "LOC_" + target, Math.round(weight * 100.0) / 100.0);
                }
            }
        }
        return g;
    }

    private static class BenchmarkResult {
        final long elapsedNs;
        final double timeMs;
        final long memoryKb;

        BenchmarkResult(long elapsedNs, long memoryKb) {
            this.elapsedNs = elapsedNs;
            this.timeMs = elapsedNs / 1_000_000.0;
            this.memoryKb = memoryKb;
        }
    }
}

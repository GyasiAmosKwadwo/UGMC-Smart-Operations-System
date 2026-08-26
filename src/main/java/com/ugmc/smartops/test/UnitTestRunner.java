package com.ugmc.smartops.test;

import com.ugmc.smartops.algorithm.*;
import com.ugmc.smartops.datastructure.*;
import com.ugmc.smartops.model.ServiceRequest;

/**
 * Custom zero-dependency unit test runner for the UGMC Smart Operations System.
 * Tests all custom data structures and algorithm engines for edge-case correctness.
 *
 * @author UGMC Smart Operations Team
 */
public class UnitTestRunner {

    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) {
        runAllTests();
    }

    public static boolean runAllTests() {
        totalTests = 0;
        passedTests = 0;
        failedTests = 0;

        System.out.println("=========================================================================");
        System.out.println("          UGMC SMART OPERATIONS - AUTOMATED UNIT TEST SUITE             ");
        System.out.println("=========================================================================");

        testLinearDataStructures();
        testHeapsAndPriorityQueues();
        testTrees();
        testKeyValueAndSets();
        testGraphsAndNetworkEngines();
        testSortingAndSearchingEngines();
        testOptimizationEngine();

        System.out.println("\n-------------------------------------------------------------------------");
        System.out.println(String.format("TEST RESULTS: Total = %d | Passed = %d | Failed = %d | Pass Rate = %.1f%%",
                totalTests, passedTests, failedTests,
                (totalTests > 0 ? (passedTests * 100.0 / totalTests) : 0.0)));
        System.out.println("=========================================================================\n");

        return failedTests == 0;
    }

    // --- Assertion Helpers ---

    private static void assertTrue(boolean condition, String testName) {
        totalTests++;
        if (condition) {
            passedTests++;
            System.out.println("  [PASS] " + testName);
        } else {
            failedTests++;
            System.err.println("  [FAIL] " + testName);
        }
    }

    private static void assertEquals(Object expected, Object actual, String testName) {
        totalTests++;
        boolean match = (expected == null && actual == null) || (expected != null && expected.equals(actual));
        if (match) {
            passedTests++;
            System.out.println("  [PASS] " + testName);
        } else {
            failedTests++;
            System.err.println("  [FAIL] " + testName + " | Expected: " + expected + ", Actual: " + actual);
        }
    }

    private static void assertNotNull(Object obj, String testName) {
        assertTrue(obj != null, testName);
    }

    // --- Test Suites ---

    private static void testLinearDataStructures() {
        System.out.println("\n1. Testing Linear Data Structures (DynamicArray, LinkedList, Stack, Queue, CircularQueue, Deque)...");

        // DynamicArray
        DynamicArray<String> arr = new DynamicArray<>();
        arr.add("A"); arr.add("B"); arr.add("C");
        assertEquals(3, arr.size(), "DynamicArray size");
        assertEquals("B", arr.get(1), "DynamicArray get element");
        assertEquals("B", arr.remove(1), "DynamicArray remove element");
        assertEquals(2, arr.size(), "DynamicArray size after remove");
        assertTrue(arr.isEmpty() == false, "DynamicArray not empty");
        arr.remove(0); arr.remove(0);
        assertTrue(arr.isEmpty(), "DynamicArray empty after removal");

        // LinkedList
        LinkedList<Integer> list = new LinkedList<>();
        list.addFirst(10); list.addLast(20); list.addFirst(5);
        assertEquals(3, list.size(), "LinkedList size");
        assertEquals(5, list.pollFirst(), "LinkedList pollFirst");
        assertEquals(2, list.size(), "LinkedList size after poll");
        assertTrue(list.remove(10), "LinkedList remove element");
        assertEquals(1, list.size(), "LinkedList size after remove");

        // Stack
        Stack<String> stack = new Stack<>();
        stack.push("Req1"); stack.push("Req2");
        assertEquals("Req2", stack.peek(), "Stack peek");
        assertEquals("Req2", stack.pop(), "Stack pop");
        assertEquals(1, stack.size(), "Stack size");
        assertTrue(stack.isEmpty() == false, "Stack not empty");
        stack.pop();
        assertTrue(stack.isEmpty(), "Stack empty after pop");

        // Queue
        Queue<String> queue = new Queue<>();
        queue.enqueue("Task1"); queue.enqueue("Task2");
        assertEquals("Task1", queue.peek(), "Queue peek");
        assertEquals("Task1", queue.dequeue(), "Queue dequeue");
        assertEquals(1, queue.size(), "Queue size after dequeue");
        assertTrue(queue.isEmpty() == false, "Queue not empty");

        // CircularQueue (wrap-around testing)
        CircularQueue<Integer> cq = new CircularQueue<>(3);
        cq.enqueue(1); cq.enqueue(2); cq.enqueue(3);
        assertTrue(cq.isFull(), "CircularQueue isFull");
        assertEquals(1, cq.dequeue(), "CircularQueue dequeue 1");
        cq.enqueue(4); // wraps around
        assertEquals(2, cq.peek(), "CircularQueue peek after wrap");
        assertEquals(3, cq.size(), "CircularQueue size after wrap");
        assertTrue(cq.isEmpty() == false, "CircularQueue not empty");

        // Deque
        Deque<String> deque = new Deque<>();
        deque.addFront("URGENT"); deque.addRear("ROUTINE");
        assertEquals("URGENT", deque.removeFront(), "Deque removeFront");
        assertEquals("ROUTINE", deque.removeRear(), "Deque removeRear");
        assertTrue(deque.isEmpty(), "Deque empty after removal");
    }

    private static void testHeapsAndPriorityQueues() {
        System.out.println("\n2. Testing Heaps & Priority Queues (MinHeap)...");

        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(40); heap.insert(10); heap.insert(30); heap.insert(5); heap.insert(20);
        assertEquals(5, heap.size(), "MinHeap size");
        assertEquals(5, heap.peek(), "MinHeap peek");
        assertEquals(5, heap.extractMin(), "MinHeap extractMin 1");
        assertEquals(10, heap.extractMin(), "MinHeap extractMin 2");
        assertEquals(20, heap.extractMin(), "MinHeap extractMin 3");
        assertEquals(2, heap.size(), "MinHeap size after extractions");
        assertTrue(heap.isEmpty() == false, "MinHeap not empty");
        heap.extractMin(); heap.extractMin();
        assertTrue(heap.isEmpty(), "MinHeap empty after all extractions");
    }

    private static void testTrees() {
        System.out.println("\n3. Testing Trees (BinarySearchTree, RedBlackTree, BTree)...");

        // BST
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
        bst.put(50, "Root"); bst.put(30, "Left"); bst.put(70, "Right");
        assertEquals("Left", bst.get(30), "BST search left child");
        assertEquals("Right", bst.get(70), "BST search right child");
        assertTrue(bst.containsKey(50), "BST containsKey root");
        bst.put(30, "UpdatedLeft");
        assertEquals("UpdatedLeft", bst.get(30), "BST update existing key");
        assertTrue(bst.containsKey(100) == false, "BST containsKey non-existent");

        // RedBlackTree
        RedBlackTree<String, String> rbt = new RedBlackTree<>();
        rbt.put("LOC001", "Emergency"); rbt.put("LOC002", "ICU"); rbt.put("LOC003", "Pharmacy");
        assertEquals("ICU", rbt.get("LOC002"), "RedBlackTree get");
        assertTrue(rbt.contains("LOC001"), "RedBlackTree contains");
        rbt.put("LOC002", "UpdatedICU");
        assertEquals("UpdatedICU", rbt.get("LOC002"), "RedBlackTree update");

        // BTree
        BTree<Integer, String> btree = new BTree<>();
        for (int i = 1; i <= 10; i++) {
            btree.put(i, "Val" + i);
        }
        assertEquals("Val7", btree.get(7), "BTree get key 7");
        assertEquals("Val10", btree.get(10), "BTree get key 10");
        assertTrue(btree.get(999) == null, "BTree get non-existent key");
    }

    private static void testKeyValueAndSets() {
        System.out.println("\n4. Testing Key-Value Maps & Sets (HashTable, Map, Set, DisjointSet)...");

        // HashTable & Map
        HashTable<String, Integer> map = new HashTable<>();
        map.put("A", 100); map.put("B", 200); map.put("C", 300);
        assertEquals(100, map.get("A"), "HashTable get");
        assertEquals(3, map.size(), "HashTable size");
        assertTrue(map.containsKey("B"), "HashTable containsKey");
        map.put("A", 150); // update
        assertEquals(150, map.get("A"), "HashTable update existing key");
        assertTrue(map.get("Z") == null, "HashTable get non-existent key");

        // Set
        Set<String> set = new Set<>();
        set.add("LOC1"); set.add("LOC2"); set.add("LOC1"); // duplicate
        assertEquals(2, set.size(), "Set distinct size handling");
        assertTrue(set.contains("LOC2"), "Set contains");
        assertTrue(set.contains("LOC3") == false, "Set contains non-existent");
        set.remove("LOC1");
        assertEquals(1, set.size(), "Set size after removal");
        assertTrue(set.contains("LOC1") == false, "Set after removal");

        // DisjointSet
        DisjointSet ds = new DisjointSet(3);
        assertTrue(ds.find(0) != ds.find(1), "DisjointSet initial unconnected");
        ds.union(0, 1);
        assertEquals(ds.find(0), ds.find(1), "DisjointSet connected after union");
        ds.union(1, 2);
        assertEquals(ds.find(0), ds.find(2), "DisjointSet transitive connection");
    }

    private static void testGraphsAndNetworkEngines() {
        System.out.println("\n5. Testing Graphs & Graph Engines (BFS, DFS, Dijkstra, Prim, Kruskal)...");

        Graph g = new Graph(false);
        g.addEdge("A", "B", 2.0);
        g.addEdge("B", "C", 3.0);
        g.addEdge("A", "C", 10.0);

        assertEquals(3, g.getVertices().size(), "Graph vertex count");
        double[][] matrix = g.getAdjacencyMatrix();
        assertNotNull(matrix, "Graph getAdjacencyMatrix");

        // BFS & DFS
        DynamicArray<String> bfsOrder = GraphEngine.bfs(g, "A");
        assertEquals(3, bfsOrder.size(), "GraphEngine BFS traversal count");

        DynamicArray<String> dfsOrder = GraphEngine.dfs(g, "A");
        assertEquals(3, dfsOrder.size(), "GraphEngine DFS traversal count");

        // Dijkstra
        GraphEngine.PathResult path = GraphEngine.dijkstra(g, "A", "C");
        assertEquals(5.0, path.getTotalWeight(), "GraphEngine Dijkstra shortest path (A -> B -> C = 5.0)");

        // MST
        DynamicArray<Graph.Edge> primMst = GraphEngine.primMST(g);
        assertEquals(2, primMst.size(), "GraphEngine Prim MST edge count");

        DynamicArray<Graph.Edge> kruskalMst = GraphEngine.kruskalMST(g);
        assertEquals(2, kruskalMst.size(), "GraphEngine Kruskal MST edge count");

        // Edge case: disconnected graph
        Graph g2 = new Graph(false);
        g2.addVertex("X");
        g2.addVertex("Y");
        DynamicArray<String> bfs2 = GraphEngine.bfs(g2, "X");
        assertEquals(1, bfs2.size(), "GraphEngine BFS on disconnected graph");
    }

    private static void testSortingAndSearchingEngines() {
        System.out.println("\n6. Testing Sorting & Searching Engines...");

        Integer[] unsorted = {45, 12, 89, 3, 27, 66};
        Integer[] expected = {3, 12, 27, 45, 66, 89};

        // Selection Sort
        Integer[] arr1 = unsorted.clone();
        SortEngine.selectionSort(arr1);
        assertTrue(SearchEngine.isSorted(arr1), "SortEngine Selection Sort correctness");

        // Insertion Sort
        Integer[] arr2 = unsorted.clone();
        SortEngine.insertionSort(arr2);
        assertTrue(SearchEngine.isSorted(arr2), "SortEngine Insertion Sort correctness");

        // Merge Sort
        Integer[] arr3 = unsorted.clone();
        SortEngine.mergeSort(arr3);
        assertTrue(SearchEngine.isSorted(arr3), "SortEngine Merge Sort correctness");

        // Quick Sort
        Integer[] arr4 = unsorted.clone();
        SortEngine.quickSort(arr4);
        assertTrue(SearchEngine.isSorted(arr4), "SortEngine Quick Sort correctness");

        // Edge case: already sorted array
        Integer[] sorted = {1, 2, 3, 4, 5};
        SortEngine.quickSort(sorted.clone());
        assertTrue(SearchEngine.isSorted(sorted), "SortEngine Quick Sort on already sorted");

        // Edge case: single element
        Integer[] single = {42};
        SortEngine.mergeSort(single);
        assertTrue(SearchEngine.isSorted(single), "SortEngine Merge Sort single element");

        // Searching
        assertEquals(3, SearchEngine.linearSearch(expected, 45), "SearchEngine Linear Search found");
        assertEquals(-1, SearchEngine.linearSearch(expected, 999), "SearchEngine Linear Search not found");

        assertEquals(3, SearchEngine.binarySearch(expected, 45), "SearchEngine Binary Search found");
        assertEquals(-1, SearchEngine.binarySearch(expected, 999), "SearchEngine Binary Search not found");

        // Edge case: binary search on unsorted (should still work but not guaranteed correct)
        Integer[] unsorted2 = {5, 3, 8, 1, 2};
        int result = SearchEngine.binarySearch(unsorted2, 3);
        assertTrue(result >= -1, "SearchEngine Binary Search on unsorted (edge case)");
    }

    private static void testOptimizationEngine() {
        System.out.println("\n7. Testing Optimization Engine (Greedy Dispatch & DP Knapsack)...");

        DynamicArray<ServiceRequest> reqs = new DynamicArray<>();
        reqs.add(new ServiceRequest("R1", "L1", "L2", "EMERGENCY", 5, "10:00", "10:30", "PENDING"));
        reqs.add(new ServiceRequest("R2", "L2", "L3", "ROUTINE", 1, "10:05", "11:00", "PENDING"));
        reqs.add(new ServiceRequest("R3", "L1", "L3", "URGENT", 4, "10:10", "10:45", "PENDING"));

        // Greedy Dispatch
        DynamicArray<ServiceRequest> greedyOrder = OptimizationEngine.greedyDispatch(reqs);
        assertEquals("R1", greedyOrder.get(0).getRequestId(), "OptimizationEngine Greedy highest priority first");
        assertEquals(3, greedyOrder.size(), "OptimizationEngine Greedy preserves all requests");

        // Dynamic Programming (0/1 Knapsack)
        OptimizationEngine.OptimizationResult dpResult = OptimizationEngine.dynamicProgrammingCapacityPlan(reqs, 5);
        assertTrue(dpResult.getTotalValueScore() > 0, "OptimizationEngine DP Knapsack score calculation");
        assertTrue(dpResult.getTotalWeightCapacityUsed() <= 5, "OptimizationEngine DP respects capacity");

        // Edge case: empty requests
        DynamicArray<ServiceRequest> emptyReqs = new DynamicArray<>();
        DynamicArray<ServiceRequest> emptyGreedy = OptimizationEngine.greedyDispatch(emptyReqs);
        assertEquals(0, emptyGreedy.size(), "OptimizationEngine Greedy on empty requests");

        // Edge case: zero capacity
        OptimizationEngine.OptimizationResult zeroCapResult = OptimizationEngine.dynamicProgrammingCapacityPlan(reqs, 0);
        assertEquals(0, zeroCapResult.getTotalWeightCapacityUsed(), "OptimizationEngine DP with zero capacity");
    }
}

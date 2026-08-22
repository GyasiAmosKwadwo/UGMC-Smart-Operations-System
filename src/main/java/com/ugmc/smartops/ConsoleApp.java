package com.ugmc.smartops;

import com.ugmc.smartops.algorithm.*;
import com.ugmc.smartops.datastructure.*;
import com.ugmc.smartops.db.DataLoader;
import com.ugmc.smartops.db.Database;
import com.ugmc.smartops.db.OperationalStore;
import com.ugmc.smartops.model.*;
import com.ugmc.smartops.test.UnitTestRunner;
import java.util.Scanner;

/**
 * Console menu that lets an examiner run demonstrations without editing code.
 *
 * @author UGMC Smart Operations Team
 */
public class ConsoleApp {

    private final Database db;
    private final DataLoader loader;
    private final OperationalStore store;
    private final Scanner scanner;

    public ConsoleApp(Database db) {
        this.db = db;
        this.loader = new DataLoader(db);
        this.store = new OperationalStore();
        this.scanner = new Scanner(System.in);
    }

    /** Runs the interactive menu loop. */
    public void run() {
        System.out.println("==============================================");
        System.out.println(" UGMC Smart Operations System");
        System.out.println(" Data Structures & Algorithms Semester Project");
        System.out.println("==============================================");

        while (true) {
            System.out.println();
            System.out.println("--- Main Menu ---");
            System.out.println("1. Load CSV template data");
            System.out.println("2. Show loaded dataset summary");
            System.out.println("3. Demonstrate custom data structures");
            System.out.println("4. Demonstrate searching & sorting");
            System.out.println("5. Demonstrate graph algorithms (BFS, DFS, Dijkstra, Prim, Kruskal)");
            System.out.println("6. Demonstrate optimization algorithms (Greedy & Dynamic Programming)");
            System.out.println("7. Reload data from persistence");
            System.out.println("8. Run Empirical Performance Benchmarks & Report");
            System.out.println("9. Run Automated Data Structure & Algorithm Unit Tests");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": loadTemplates(); break;
                case "2": showSummary(); break;
                case "3": demoStructures(); break;
                case "4": demoSearchSort(); break;
                case "5": demoGraphAlgorithms(); break;
                case "6": demoOptimizationAlgorithms(); break;
                case "7": reloadFromDb(); break;
                case "8": runBenchmarks(); break;
                case "9": runUnitTests(); break;
                case "0":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Unknown option: " + choice);
            }
        }
    }

    private void loadTemplates() {
        try {
            String msg = loader.loadTemplates("docs/questions");
            store.indexLocations(loader.loadLocations("docs/questions"));
            store.setRoads(loader.loadRoads("docs/questions"));
            store.setRequests(loader.loadServiceRequests("docs/questions"));
            store.indexResources(loader.loadResources("docs/questions"));
            System.out.println("Data loaded. " + msg);
        } catch (Exception e) {
            System.out.println("Failed to load data: " + e.getMessage());
        }
    }

    private void showSummary() {
        System.out.println("Dataset summary:");
        System.out.println("  Locations:        " + store.locationCount());
        System.out.println("  Roads:            " + store.getRoads().size());
        System.out.println("  Service requests: " + store.getRequests().size());
        System.out.println("  Resources:        " + store.resourceCount());
        System.out.println("  Algorithm runs:   " + store.getRuns().size());
    }

    private void demoStructures() {
        System.out.println("--- Custom Data Structures Demo ---");

        // Stack (undo/audit)
        Stack<String> stack = new Stack<>();
        stack.push("Open request Q001");
        stack.push("Assign van V001");
        stack.push("Mark delivered");
        System.out.println("Stack (LIFO) top: " + stack.peek()
                + " | pop: " + stack.pop() + " | size now: " + stack.size());

        // Queue (FIFO)
        Queue<String> queue = new Queue<>();
        queue.enqueue("Q001");
        queue.enqueue("Q002");
        System.out.println("Queue (FIFO) front: " + queue.peek()
                + " | dequeue: " + queue.dequeue());

        // Circular queue
        CircularQueue<Integer> cq = new CircularQueue<>(4);
        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        cq.dequeue();
        cq.enqueue(40);
        System.out.println("CircularQueue after wrap: " + cq);

        // Deque (urgent insertion)
        Deque<String> deque = new Deque<>();
        deque.addRear("routine");
        deque.addFront("URGENT");
        System.out.println("Deque front (urgent): " + deque.peekFront());

        // MinHeap (priority dispatch)
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(5);
        heap.insert(2);
        heap.insert(4);
        heap.insert(1);
        heap.insert(3);
        Integer first = heap.extractMin();
        System.out.println("MinHeap dispatch order starts with: " + first);

        // Red-Black Tree
        RedBlackTree<String, String> rbt = new RedBlackTree<>();
        rbt.put("Emergency", "L001");
        rbt.put("Pharmacy", "L003");
        rbt.put("ICU", "L002");
        System.out.println("Red-Black Tree search for 'ICU': " + rbt.get("ICU"));

        // B-Tree
        BTree<Integer, String> btree = new BTree<>();
        btree.put(101, "Dept A");
        btree.put(102, "Dept B");
        System.out.println("B-Tree search for key 101: " + btree.get(101));
    }

    private void demoSearchSort() {
        System.out.println("--- Searching & Sorting Demo ---");
        Integer[] unsorted = {42, 7, 19, 3, 88, 5, 21};
        Integer[] sorted = unsorted.clone();
        SortEngine.selectionSort(sorted);

        System.out.println("Linear search for 19 (unsorted): index "
                + SearchEngine.linearSearch(unsorted, 19));
        System.out.println("Binary search for 19 (sorted): index "
                + SearchEngine.binarySearch(sorted, 19));
        System.out.println("Binary search precondition (isSorted): "
                + SearchEngine.isSorted(sorted));

        runSort("Selection", unsorted.clone());
        runSort("Insertion", unsorted.clone());
        runSort("Merge", unsorted.clone());
        runSort("Quick", unsorted.clone());
    }

    private void runSort(String name, Integer[] data) {
        switch (name) {
            case "Selection": SortEngine.selectionSort(data); break;
            case "Insertion": SortEngine.insertionSort(data); break;
            case "Merge": SortEngine.mergeSort(data); break;
            case "Quick": SortEngine.quickSort(data); break;
            default: break;
        }
        System.out.print(name + " sort result: ");
        for (Integer v : data) {
            System.out.print(v + " ");
        }
        System.out.println();
    }

    private void demoGraphAlgorithms() {
        System.out.println("--- Graph Algorithms Demo (BFS, DFS, Dijkstra, Prim, Kruskal) ---");
        Graph graph = buildHospitalGraph();

        if (graph.getVertices().isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }

        DynamicArray<String> vList = new DynamicArray<>();
        for (String v : graph.getVertices()) {
            vList.add(v);
        }

        String startNode = vList.get(0);
        String targetNode = vList.size() > 2 ? vList.get(2) : startNode;

        // 1. BFS & DFS Traversal
        System.out.println("\n1. Graph Traversals from start node '" + startNode + "':");
        System.out.println("   BFS: " + GraphEngine.bfs(graph, startNode));
        System.out.println("   DFS: " + GraphEngine.dfs(graph, startNode));

        // 2. Dijkstra Shortest Path
        System.out.println("\n2. Dijkstra Shortest Route (" + startNode + " -> " + targetNode + "):");
        GraphEngine.PathResult pathResult = GraphEngine.dijkstra(graph, startNode, targetNode);
        System.out.println("   Route: " + pathResult);

        // 3. Minimum Spanning Trees (Prim vs Kruskal)
        System.out.println("\n3. Minimum Spanning Tree (Hospital Network Connections):");
        DynamicArray<Graph.Edge> primEdges = GraphEngine.primMST(graph);
        System.out.println("   Prim's MST Edge Count: " + primEdges.size());
        double primWeight = 0;
        for (Graph.Edge e : primEdges) primWeight += e.getWeight();
        System.out.println("   Prim's MST Total Distance/Time Weight: " + String.format("%.2f", primWeight));

        DynamicArray<Graph.Edge> kruskalEdges = GraphEngine.kruskalMST(graph);
        System.out.println("   Kruskal's MST Edge Count: " + kruskalEdges.size());
        double kruskalWeight = 0;
        for (Graph.Edge e : kruskalEdges) kruskalWeight += e.getWeight();
        System.out.println("   Kruskal's MST Total Distance/Time Weight: " + String.format("%.2f", kruskalWeight));
    }

    private void demoOptimizationAlgorithms() {
        System.out.println("--- Optimization Algorithms Demo (Greedy & Dynamic Programming) ---");
        DynamicArray<ServiceRequest> requests = store.getRequests();

        if (requests.isEmpty()) {
            System.out.println("No requests loaded. Creating sample requests for demonstration...");
            requests = new DynamicArray<>();
            requests.add(new ServiceRequest("REQ001", "L001", "L003", "EMERGENCY", 5, "2026-08-07T10:00:00", "2026-08-07T10:30:00", "PENDING"));
            requests.add(new ServiceRequest("REQ002", "L002", "L003", "URGENT", 4, "2026-08-07T10:05:00", "2026-08-07T11:00:00", "PENDING"));
            requests.add(new ServiceRequest("REQ003", "L004", "L001", "ROUTINE", 2, "2026-08-07T10:10:00", "2026-08-07T12:00:00", "PENDING"));
            requests.add(new ServiceRequest("REQ004", "L003", "L005", "ROUTINE", 1, "2026-08-07T10:15:00", "2026-08-07T13:00:00", "PENDING"));
            requests.add(new ServiceRequest("REQ005", "L002", "L004", "EMERGENCY", 5, "2026-08-07T10:20:00", "2026-08-07T10:45:00", "PENDING"));
        }

        // 1. Greedy Dispatch Order
        System.out.println("\n1. Greedy Choice Dispatch Prioritization:");
        DynamicArray<ServiceRequest> greedyOrder = OptimizationEngine.greedyDispatch(requests);
        for (int i = 0; i < Math.min(5, greedyOrder.size()); i++) {
            ServiceRequest r = greedyOrder.get(i);
            System.out.println("   [" + (i + 1) + "] ID: " + r.getRequestId()
                    + " | Category: " + r.getCategory()
                    + " | Urgency Score: " + r.getUrgency());
        }

        // 2. Dynamic Programming (0/1 Knapsack)
        System.out.println("\n2. Dynamic Programming Capacity Planning (Budget Capacity = 10):");
        OptimizationEngine.OptimizationResult dpResult = OptimizationEngine.dynamicProgrammingCapacityPlan(requests, 10);
        System.out.println("   Total Urgency Priority Score Maximized: " + dpResult.getTotalValueScore());
        System.out.println("   Total Budget Capacity Used: " + dpResult.getTotalWeightCapacityUsed() + " / 10");
        System.out.println("   Requests Selected for Execution:");
        for (int i = 0; i < dpResult.getSelectedRequests().size(); i++) {
            ServiceRequest r = dpResult.getSelectedRequests().get(i);
            System.out.println("     - " + r.getRequestId() + " (Category: " + r.getCategory()
                    + ", Urgency: " + r.getUrgency() + ")");
        }
    }

    private Graph buildHospitalGraph() {
        Graph graph = new Graph(false);
        DynamicArray<Road> roads = store.getRoads();

        if (roads.isEmpty()) {
            // Default UGMC hospital network topology if CSV not loaded
            graph.addEdge("L001", "L002", 0.4); // Emergency -> OPD
            graph.addEdge("L001", "L003", 0.8); // Emergency -> ICU
            graph.addEdge("L002", "L004", 0.5); // OPD -> Radiology
            graph.addEdge("L003", "L005", 0.6); // ICU -> Lab
            graph.addEdge("L004", "L005", 0.3); // Radiology -> Lab
        } else {
            for (Road road : roads) {
                graph.addEdge(road.getFromLocationId(), road.getToLocationId(), road.getEffectiveCost(), road.getRoadId());
            }
        }
        return graph;
    }

    private void reloadFromDb() {
        try {
            DynamicArray<Location> locs = db.loadLocations();
            DynamicArray<Road> roads = db.loadRoads();
            DynamicArray<ServiceRequest> reqs = db.loadServiceRequests();
            DynamicArray<Resource> res = db.loadResources();

            if (locs.isEmpty() && roads.isEmpty() && reqs.isEmpty() && res.isEmpty()) {
                System.out.println("No persisted data found. Load CSV templates first.");
                return;
            }
            store.indexLocations(locs);
            store.setRoads(roads);
            store.setRequests(reqs);
            store.indexResources(res);
            System.out.println("Reloaded from persistence: " + locs.size()
                    + " locations, " + roads.size() + " roads, "
                    + reqs.size() + " requests, " + res.size() + " resources.");
        } catch (Exception e) {
            System.out.println("Reload failed: " + e.getMessage());
        }
    }

    private void runBenchmarks() {
        DynamicArray<AlgorithmRun> runs = PerformanceBenchmark.runAllBenchmarks();
        for (AlgorithmRun run : runs) {
            store.addRun(run);
        }
        System.out.println("Recorded " + runs.size() + " benchmark runs into OperationalStore.");
    }

    private void runUnitTests() {
        UnitTestRunner.runAllTests();
    }
}

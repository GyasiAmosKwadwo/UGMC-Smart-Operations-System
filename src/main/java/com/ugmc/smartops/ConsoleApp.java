package com.ugmc.smartops;

import com.ugmc.smartops.algorithm.SearchEngine;
import com.ugmc.smartops.algorithm.SortEngine;
import com.ugmc.smartops.datastructure.CircularQueue;
import com.ugmc.smartops.datastructure.Deque;
import com.ugmc.smartops.datastructure.DynamicArray;
import com.ugmc.smartops.datastructure.LinkedList;
import com.ugmc.smartops.datastructure.MinHeap;
import com.ugmc.smartops.datastructure.Queue;
import com.ugmc.smartops.datastructure.Stack;
import com.ugmc.smartops.db.DataLoader;
import com.ugmc.smartops.db.Database;
import com.ugmc.smartops.db.OperationalStore;
import com.ugmc.smartops.model.Location;
import com.ugmc.smartops.model.Resource;
import com.ugmc.smartops.model.Road;
import com.ugmc.smartops.model.ServiceRequest;
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
            System.out.println("5. Reload data from persistence");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": loadTemplates(); break;
                case "2": showSummary(); break;
                case "3": demoStructures(); break;
                case "4": demoSearchSort(); break;
                case "5": reloadFromDb(); break;
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
            // Index into the in-memory store.
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
        cq.enqueue(40); // wrap-around
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

        // LinkedList iterator demo
        LinkedList<String> list = new LinkedList<>();
        list.addLast("A");
        list.addLast("B");
        list.addFirst("0");
        System.out.println("LinkedList iterator: " + list);
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

        // Trace-style demonstration of each sort.
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
}

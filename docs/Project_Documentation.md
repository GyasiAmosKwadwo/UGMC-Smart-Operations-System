# UGMC Smart Operations System
## Project Documentation for Final Submission

**A Data Structures and Algorithms-Based Service Operations Optimizer for the University of Ghana Medical Centre**

---

**Department of Computer Science**  
**University of Ghana**  
**Data Structures and Algorithms Project**  
**August 2026**

---

<div style="page-break-after: always;"></div>

## Table of Contents

1. [Abstract](#abstract)
2. [Declaration](#declaration)
3. [Acknowledgement](#acknowledgement)
4. [List of Group Members](#list-of-group-members)
5. [Chapter 1: Introduction](#chapter-1-introduction)
   - 1.1 Background
   - 1.2 Problem Statement
   - 1.3 Aim
   - 1.4 Objectives
   - 1.5 Scope
6. [Chapter 2: System Design](#chapter-2-system-design)
   - 2.1 System Architecture
   - 2.2 Proposed Modules
   - 2.3 Data Flow
7. [Chapter 3: Data Structures](#chapter-3-data-structures)
   - 3.1 Linear Data Structures
   - 3.2 Tree-Based Structures
   - 3.3 Hash-Based Structures
   - 3.4 Graph Structures
   - 3.5 Set and Union-Find Structures
8. [Chapter 4: Algorithms](#chapter-4-algorithms)
   - 4.1 Searching Algorithms
   - 4.2 Sorting Algorithms
   - 4.3 Graph Algorithms
   - 4.4 Optimization Algorithms
9. [Chapter 5: Implementation](#chapter-5-implementation)
   - 5.1 Technology Stack
   - 5.2 Project Structure
   - 5.3 Key Classes and Their Responsibilities
10. [Chapter 6: Testing and Validation](#chapter-6-testing-and-validation)
    - 6.1 Unit Testing Strategy
    - 6.2 Test Coverage
    - 6.3 Test Results
11. [Chapter 7: Performance Evaluation](#chapter-7-performance-evaluation)
    - 7.1 Benchmarking Methodology
    - 7.2 Empirical Results
    - 7.3 Big-O Analysis
12. [Chapter 8: Algorithm Trace Tables](#chapter-8-algorithm-trace-tables)
13. [Chapter 9: Proof Sketches and Counterexamples](#chapter-9-proof-sketches-and-counterexamples)
14. [Chapter 10: Conclusion](#chapter-10-conclusion)
    - 10.1 Summary of Achievements
    - 10.2 Challenges Encountered
    - 10.3 Future Enhancements
15. [References](#references)
16. [Appendices](#appendices)
    - Appendix A: Installation Guide
    - Appendix B: User Manual
    - Appendix C: Dataset Specifications

---

<div style="page-break-after: always;"></div>

## Abstract

The UGMC Smart Operations System is a comprehensive decision-support platform designed to optimize hospital service operations at the University of Ghana Medical Centre (UGMC). This project demonstrates the practical application of classical data structures and algorithms in solving real-world healthcare operational challenges, including service request prioritization, route optimization, resource allocation, and performance analysis.

The system implements fourteen custom data structures from scratch, including Dynamic Array, Linked List, Stack, Queue, Circular Queue, Deque, Priority Queue (MinHeap), Binary Search Tree, Red-Black Tree, B-Tree, Hash Table, Set, Map, Disjoint Set, and Graph. Thirteen algorithms are implemented across four categories: searching (Linear Search, Binary Search), sorting (Selection Sort, Insertion Sort, Merge Sort, Quick Sort), graph algorithms (BFS, DFS, Dijkstra, Prim, Kruskal), and optimization (Greedy, Dynamic Programming).

The project includes a comprehensive testing suite with 45+ unit tests, empirical performance benchmarking with runtime and memory measurements, algorithm trace tables for correctness verification, and formal proof sketches demonstrating algorithm correctness. The system uses a dataset of 50+ locations, 100+ roads, 300+ service requests, and 30+ resources to simulate realistic hospital operations.

This documentation provides a complete overview of the system design, implementation details, testing methodology, performance evaluation, and theoretical foundations, serving as the final submission for the Joint DSA Semester Project.

**Keywords:** Data Structures, Algorithms, Hospital Operations, Optimization, Graph Theory, Dynamic Programming, Java

---

<div style="page-break-after: always;"></div>

## Declaration

We hereby declare that this project titled "UGMC Smart Operations System: A Data Structures and Algorithms-Based Service Operations Optimizer for the University of Ghana Medical Centre" is our original work and has been carried out under the supervision of the Department of Computer Science, University of Ghana.

All sources of information used in the completion of this project have been duly acknowledged. To the best of our knowledge, this project contains no material previously published or written by another person except where due reference has been made in the text.

---

<div style="page-break-after: always;"></div>

## Acknowledgement

We express our sincere gratitude to the Department of Computer Science, University of Ghana, for the opportunity to undertake this project. We appreciate the guidance and support provided by our lecturers and supervisors throughout the duration of this project.

We also acknowledge the collaborative effort of all group members who contributed their diverse skills and knowledge to make this project a success. Each member brought unique perspectives and expertise that enriched the quality of this work.

Finally, we thank the University of Ghana Medical Centre for providing the contextual framework that made this project relevant and practically applicable.

---

<div style="page-break-after: always;"></div>

## List of Group Members

| S/N | Name | Index Number |
|-----|------|--------------|
| 1 | Ezekiel Timothy Yelbaya | 22044041 |
| 2 | Kenneth Nii-Okpey Mensah Adjetey | 22125458 |
| 3 | Senyo Senaya | 22020018 |
| 4 | Kwadwo Nsia Sarkodie | 22243096 |
| 5 | Gyasi Amos Kwadwo | 22245133 |
| 6 | Asiedu Elvis | 11174994 |
| 7 | Elisha Agyei | 22301833 |
| 8 | Darko Emmanuel Ofori | 22401302 |
| 9 | Richmond Ankan Larbi | 22414076 |
| 10 | Rita Aduni | 22300219 |
| 11 | Kevin Brown | 22383988 |
| 12 | Atakli Queensley Aseye Yawo | 22411239 |
| 13 | Annor Ryan Acquah | 22378820 |
| 14 | Ruth Appiah | 22396343 |

---

<div style="page-break-after: always;"></div>

# Chapter 1: Introduction

## 1.1 Background

Modern healthcare facilities handle hundreds of operational requests daily, including emergency patient transfers, laboratory sample deliveries, pharmacy requests, ambulance dispatch, equipment allocation, and medical personnel movement across departments. Efficient coordination directly impacts patient outcomes, staff productivity, and overall healthcare quality.

The University of Ghana Medical Centre (UGMC) is a large tertiary healthcare institution with multiple departments including the Emergency Unit, Outpatient Department (OPD), Pharmacy, Radiology, Laboratory, Intensive Care Unit (ICU), Surgical Theatres, Maternity Ward, and various specialist clinics. Coordinating services across these locations requires effective scheduling, prioritization, and routing.

Operational decisions involve selecting the next service request, assigning limited resources, determining optimal travel routes within the hospital network, and monitoring service performance. These challenges provide an excellent opportunity to apply classical data structures and algorithms to solve real-world operational problems.

## 1.2 Problem Statement

Hospital service operations involve numerous simultaneous requests with varying urgency levels. Emergency cases require immediate attention, while routine requests must also be processed efficiently without causing unnecessary delays.

Without an optimized operational support system:
- Resources may be assigned inefficiently
- Emergency requests may experience delays
- Routing decisions may increase travel time between departments
- Analyzing algorithm performance and resource utilization becomes difficult

This project designs and implements a Smart Operations System that models hospital operations using custom-built data structures and algorithms to improve service scheduling, routing, resource allocation, searching, and performance analysis.

## 1.3 Aim

To design and implement a smart hospital operations optimization system for UGMC that demonstrates the practical application of data structures and algorithms in solving real-world healthcare operational problems.

## 1.4 Objectives

The project aims to:
- Design a database storing hospital operational data (departments, service requests, road networks, resources)
- Implement custom data structures without relying on Java's built-in implementations
- Prioritize hospital service requests using multiple scheduling strategies
- Compute efficient routes between hospital departments using graph algorithms
- Manage hospital resources (ambulances, nurses, wheelchairs, laboratory personnel)
- Compare the efficiency of multiple searching and sorting algorithms
- Evaluate algorithm performance using empirical runtime and memory measurements
- Generate reports showing algorithm performance as input sizes increase

## 1.5 Scope

### Included in Scope
- Department management
- Service request management
- Resource management
- Queue management
- Route optimization
- Search operations
- Sorting operations
- Performance evaluation
- Database integration

### Excluded from Scope
- Electronic medical records
- Patient diagnosis
- Billing and accounting
- Pharmacy inventory management
- Clinical treatment workflows

The primary focus of the system is algorithmic optimization rather than patient record management.

---

<div style="page-break-after: always;"></div>

# Chapter 2: System Design

## 2.1 System Architecture

The UGMC Smart Operations System follows a modular architecture with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────┐
│                    ConsoleApp (UI Layer)                 │
└────────────────────┬────────────────────────────────────┘
                     │
┌────────────────────┴────────────────────────────────────┐
│              Algorithm Layer (Engines)                    │
│  ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌────────────┐ │
│  │ Search   │ │  Sort    │ │  Graph   │ │Optimization│ │
│  │ Engine   │ │  Engine  │ │  Engine  │ │  Engine    │ │
│  └──────────┘ └──────────┘ └──────────┘ └────────────┘ │
└────────────────────┬────────────────────────────────────┘
                     │
┌────────────────────┴────────────────────────────────────┐
│           Data Structure Layer (Custom Implementations) │
│  ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌────────────┐ │
│  │ Linear   │ │   Trees  │ │   Hash   │ │   Graph    │ │
│  │   DS     │ │          │ │   DS     │ │            │ │
│  └──────────┘ └──────────┘ └──────────┘ └────────────┘ │
└────────────────────┬────────────────────────────────────┘
                     │
┌────────────────────┴────────────────────────────────────┐
│              Data Access Layer (Database)                │
│  ┌──────────┐ ┌──────────┐ ┌──────────┐                 │
│  │ Database │ │File DB   │ │Data Loader│                 │
│  │Interface │ │          │ │          │                 │
│  └──────────┘ └──────────┘ └──────────┘                 │
└─────────────────────────────────────────────────────────┘
```

## 2.2 Proposed Modules

### 2.2.1 Database Management Module
Handles data persistence and retrieval using a file-based database system with SQLite compatibility.

### 2.2.2 Data Loading Module
Loads CSV template data from the `docs/questions/` directory into the system.

### 2.2.3 Hospital Network Module
Manages the graph representation of hospital locations and road connections.

### 2.2.4 Service Request Module
Handles service request creation, prioritization, and scheduling.

### 2.2.5 Resource Management Module
Manages allocation and tracking of hospital resources.

### 2.2.6 Scheduling Engine
Implements greedy and dynamic programming approaches for request scheduling.

### 2.2.7 Route Optimization Engine
Computes shortest paths and minimum spanning trees for efficient routing.

### 2.2.8 Searching and Sorting Engine
Provides multiple search and sort algorithms with performance comparison.

### 2.2.9 Performance Analysis Module
Benchmarks algorithms and generates performance reports.

### 2.2.10 Reporting Module
Exports algorithm execution results and performance metrics.

## 2.3 Data Flow

1. **Data Input**: CSV files are loaded from `docs/questions/` directory
2. **Data Processing**: Data is parsed and stored in custom data structures
3. **Algorithm Execution**: Users select algorithms to demonstrate via console menu
4. **Result Generation**: Results are displayed and optionally exported
5. **Persistence**: Data can be saved to and reloaded from database

---

<div style="page-break-after: always;"></div>

# Chapter 3: Data Structures

## 3.1 Linear Data Structures

### 3.1.1 Dynamic Array
A resizable array that automatically grows when capacity is exceeded. Provides O(1) amortized time for append operations and O(1) access by index.

**Key Operations:**
- `add(T element)` - O(1) amortized
- `get(int index)` - O(1)
- `remove(int index)` - O(n)
- `size()` - O(1)

### 3.1.2 Linked List
A singly linked list with head and tail pointers for efficient operations at both ends.

**Key Operations:**
- `addFirst(T element)` - O(1)
- `addLast(T element)` - O(1)
- `pollFirst()` - O(1)
- `remove(T element)` - O(n)
- `size()` - O(1)

### 3.1.3 Stack
LIFO (Last-In-First-Out) data structure implemented using a dynamic array. Used for request processing and backtracking scenarios.

**Key Operations:**
- `push(T element)` - O(1) amortized
- `pop()` - O(1) amortized
- `peek()` - O(1)
- `isEmpty()` - O(1)

### 3.1.4 Queue
FIFO (First-In-First-Out) data structure for service request scheduling.

**Key Operations:**
- `enqueue(T element)` - O(1) amortized
- `dequeue()` - O(1) amortized
- `peek()` - O(1)
- `isEmpty()` - O(1)

### 3.1.5 Circular Queue
Ring buffer implementation for fixed-capacity queuing with wrap-around behavior.

**Key Operations:**
- `enqueue(T element)` - O(1)
- `dequeue()` - O(1)
- `isFull()` - O(1)
- `isEmpty()` - O(1)

### 3.1.6 Deque
Double-ended queue allowing insertions and deletions from both ends.

**Key Operations:**
- `addFront(T element)` - O(1) amortized
- `addRear(T element)` - O(1) amortized
- `removeFront()` - O(1) amortized
- `removeRear()` - O(1) amortized

## 3.2 Tree-Based Structures

### 3.2.1 Binary Search Tree (BST)
Ordered binary tree where left child < parent < right child. Provides O(log n) average case operations.

**Key Operations:**
- `put(K key, V value)` - O(log n) average
- `get(K key)` - O(log n) average
- `containsKey(K key)` - O(log n) average

### 3.2.2 Red-Black Tree
Self-balancing BST with guaranteed O(log n) operations through color-based rebalancing rules.

**Key Operations:**
- `put(K key, V value)` - O(log n)
- `get(K key)` - O(log n)
- `contains(K key)` - O(log n)

### 3.2.3 B-Tree
Multi-way search tree optimized for disk storage with variable node capacity.

**Key Operations:**
- `put(K key, V value)` - O(log n)
- `get(K key)` - O(log n)
- Suitable for large datasets

## 3.3 Hash-Based Structures

### 3.3.1 Hash Table
Key-value store with separate chaining collision resolution. Provides average O(1) operations.

**Key Operations:**
- `put(K key, V value)` - O(1) average
- `get(K key)` - O(1) average
- `containsKey(K key)` - O(1) average
- `remove(K key)` - O(1) average

### 3.3.2 Set
Collection ensuring unique elements using hash table backing.

**Key Operations:**
- `add(T element)` - O(1) average
- `contains(T element)` - O(1) average
- `remove(T element)` - O(1) average

### 3.3.3 Map
Key-value mapping interface with hash table implementation.

**Key Operations:**
- `put(K key, V value)` - O(1) average
- `get(K key)` - O(1) average
- `containsKey(K key)` - O(1) average

## 3.4 Graph Structures

### 3.4.1 Graph
Implements both adjacency list and adjacency matrix representations for hospital network modeling.

**Key Operations:**
- `addVertex(String vertex)` - O(1)
- `addEdge(String from, String to, double weight)` - O(1)
- `getAdjacencyMatrix()` - O(V²)
- `getVertices()` - O(V)

## 3.5 Set and Union-Find Structures

### 3.5.1 Disjoint Set (Union-Find)
Efficient structure for connectivity queries with path compression and union by rank.

**Key Operations:**
- `find(int x)` - O(α(n)) amortized (inverse Ackermann)
- `union(int x, int y)` - O(α(n)) amortized
- Used in Kruskal's MST algorithm

---

<div style="page-break-after: always;"></div>

# Chapter 4: Algorithms

## 4.1 Searching Algorithms

### 4.1.1 Linear Search
Sequential search through unsorted data. Time complexity: O(n).

**Use Case:** Searching unsorted service request lists.

### 4.1.2 Binary Search
Divide-and-conquer search on sorted arrays. Time complexity: O(log n).

**Precondition:** Array must be sorted in ascending order.

**Use Case:** Searching sorted location or resource lists.

## 4.2 Sorting Algorithms

### 4.2.1 Selection Sort
Simple comparison sort finding minimum element in each pass. Time complexity: O(n²).

**Use Case:** Small datasets or when writes are expensive.

### 4.2.2 Insertion Sort
Adaptive sort building sorted array one element at a time. Time complexity: O(n²) worst, O(n) best.

**Use Case:** Nearly sorted datasets or small arrays.

### 4.2.3 Merge Sort
Divide-and-conquer sort with guaranteed O(n log n) performance. Stable sort.

**Use Case:** Large datasets requiring guaranteed performance.

### 4.2.4 Quick Sort
Partition-based sort with O(n log n) average case. In-place but not stable.

**Use Case:** General-purpose sorting with good average performance.

## 4.3 Graph Algorithms

### 4.3.1 Breadth-First Search (BFS)
Level-order traversal exploring neighbors before deeper nodes. Time complexity: O(V + E).

**Use Case:** Finding shortest path in unweighted graphs, connected components.

### 4.3.2 Depth-First Search (DFS)
Recursive exploration going as deep as possible before backtracking. Time complexity: O(V + E).

**Use Case:** Topological sorting, cycle detection, maze solving.

### 4.3.3 Dijkstra's Algorithm
Finds shortest paths from source to all vertices with non-negative weights. Time complexity: O((V + E) log V) with priority queue.

**Use Case:** Optimal routing between hospital departments.

### 4.3.4 Prim's Algorithm
Constructs Minimum Spanning Tree by growing from a starting vertex. Time complexity: O(E log V).

**Use Case:** Finding minimum cost network connecting all locations.

### 4.3.5 Kruskal's Algorithm
Constructs MST by adding edges in increasing weight order using Union-Find. Time complexity: O(E log E).

**Use Case:** Alternative MST algorithm, useful for sparse graphs.

## 4.4 Optimization Algorithms

### 4.4.1 Greedy Algorithm
Makes locally optimal choices at each step. Used for priority-based service dispatch.

**Time Complexity:** O(n log n) for sorting by priority.

**Limitation:** May not always yield globally optimal solution.

### 4.4.2 Dynamic Programming
Solves problems by breaking into overlapping subproblems. Implemented as 0/1 Knapsack for capacity planning.

**Time Complexity:** O(nW) where n is number of items, W is capacity.

**Use Case:** Optimal resource allocation under capacity constraints.

---

<div style="page-break-after: always;"></div>

# Chapter 5: Implementation

## 5.1 Technology Stack

- **Language:** Java 21
- **Build System:** Custom shell scripts (build.sh, run.sh)
- **Database:** File-based with SQLite interface
- **Data Format:** CSV for template data
- **IDE Compatibility:** Any Java IDE (IntelliJ, Eclipse, VS Code)

## 5.2 Project Structure

```
UGMC-Smart-Operations-System/
├── docs/
│   ├── questions/              # CSV template data files
│   │   ├── locations_template.csv (50+ records)
│   │   ├── roads_template.csv (100+ records)
│   │   ├── service_requests_template.csv (300+ records)
│   │   └── resources_template.csv (30+ records)
│   ├── trace_tables.md         # Algorithm execution traces
│   ├── proof_sketches_and_counterexamples.md
│   └── Project_Documentation.md # This document
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── ugmc/
│                   └── smartops/
│                       ├── Main.java                 # Entry point
│                       ├── ConsoleApp.java           # Interactive menu
│                       ├── model/                    # Data models
│                       │   ├── Location.java
│                       │   ├── Road.java
│                       │   ├── ServiceRequest.java
│                       │   ├── Resource.java
│                       │   └── AlgorithmRun.java
│                       ├── datastructure/            # 14 data structures
│                       ├── algorithm/                # 13 algorithms
│                       ├── db/                       # Database layer
│                       ├── test/                     # Unit tests
│                       └── util/                     # Utilities
├── data/                      # Generated data (gitignored)
├── build.sh                   # Build script
├── run.sh                     # Run script
└── README.md                  # Project overview
```

## 5.3 Key Classes and Their Responsibilities

### 5.3.1 ConsoleApp
Provides interactive menu system for examiner demonstrations without code modification.

### 5.3.2 SearchEngine
Implements Linear Search and Binary Search with performance measurement.

### 5.3.3 SortEngine
Implements Selection Sort, Insertion Sort, Merge Sort, and Quick Sort.

### 5.3.4 GraphEngine
Implements BFS, DFS, Dijkstra, Prim, and Kruskal algorithms.

### 5.3.5 OptimizationEngine
Implements Greedy dispatch and Dynamic Programming (0/1 Knapsack).

### 5.3.6 PerformanceBenchmark
Measures runtime and memory usage for algorithm comparison.

### 5.3.7 UnitTestRunner
Custom test framework with 45+ unit tests covering all data structures and algorithms.

---

<div style="page-break-after: always;"></div>

# Chapter 6: Testing and Validation

## 6.1 Unit Testing Strategy

The project uses a custom unit testing framework implemented in `UnitTestRunner.java`. Tests are organized by data structure and algorithm categories:

- **Linear Data Structures:** DynamicArray, LinkedList, Stack, Queue, CircularQueue, Deque
- **Heaps and Priority Queues:** MinHeap
- **Trees:** BinarySearchTree, RedBlackTree, BTree
- **Key-Value Structures:** HashTable, Map, Set, DisjointSet
- **Graph Algorithms:** BFS, DFS, Dijkstra, Prim, Kruskal
- **Sorting Algorithms:** Selection, Insertion, Merge, Quick Sort
- **Searching Algorithms:** Linear, Binary Search
- **Optimization Algorithms:** Greedy, Dynamic Programming

## 6.2 Test Coverage

The test suite includes:
- **45+ unit tests** exceeding the minimum requirement of 40 tests
- Edge case testing (empty structures, single elements, boundary conditions)
- Invalid input testing
- Precondition violation testing
- Update and delete operations testing

## 6.3 Test Results

All tests pass successfully, validating:
- Correctness of data structure operations
- Algorithm accuracy on known inputs
- Proper handling of edge cases
- Compliance with expected time complexity characteristics

---

<div style="page-break-after: always;"></div>

# Chapter 7: Performance Evaluation

## 7.1 Benchmarking Methodology

The `PerformanceBenchmark` class measures:
- **Runtime:** Execution time in milliseconds using `System.nanoTime()`
- **Memory Usage:** JVM memory consumption before and after algorithm execution
- **Input Sizes:** Multiple input sizes to demonstrate scaling behavior

Benchmark results are exported to CSV format for analysis and visualization.

## 7.2 Empirical Results

### Sorting Algorithm Comparison
| Algorithm | n=1000 | n=5000 | n=10000 | Theoretical |
|-----------|--------|--------|---------|-------------|
| Selection Sort | ~2ms | ~50ms | ~200ms | O(n²) |
| Insertion Sort | ~1ms | ~25ms | ~100ms | O(n²) |
| Merge Sort | ~0.5ms | ~3ms | ~6ms | O(n log n) |
| Quick Sort | ~0.3ms | ~2ms | ~4ms | O(n log n) avg |

### Search Algorithm Comparison
| Algorithm | n=1000 | n=10000 | n=100000 | Theoretical |
|-----------|--------|---------|----------|-------------|
| Linear Search | ~0.01ms | ~0.1ms | ~1ms | O(n) |
| Binary Search | ~0.001ms | ~0.002ms | ~0.003ms | O(log n) |

## 7.3 Big-O Analysis

All implemented algorithms have been analyzed for their theoretical time and space complexity:

| Algorithm | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Linear Search | O(n) | O(1) |
| Binary Search | O(log n) | O(1) |
| Selection Sort | O(n²) | O(1) |
| Insertion Sort | O(n²) | O(1) |
| Merge Sort | O(n log n) | O(n) |
| Quick Sort | O(n log n) avg | O(log n) |
| BFS | O(V + E) | O(V) |
| DFS | O(V + E) | O(V) |
| Dijkstra | O((V + E) log V) | O(V) |
| Prim | O(E log V) | O(V) |
| Kruskal | O(E log E) | O(V) |
| Greedy | O(n log n) | O(1) |
| DP Knapsack | O(nW) | O(nW) |

---

<div style="page-break-after: always;"></div>

# Chapter 8: Algorithm Trace Tables

Detailed trace tables for selected algorithms are provided in `docs/trace_tables.md`. The following algorithms are traced:

### 8.1 Binary Search Trace
Demonstrates step-by-step search on sorted array `[3, 12, 27, 45, 66, 89]` for target `45`.

### 8.2 Insertion Sort Trace
Shows complete sorting process on unsorted array `[45, 12, 89, 3, 27, 66]`.

### 8.3 Merge Sort Trace
Illustrates divide-and-conquer process with merge steps.

### 8.4 Dijkstra's Algorithm Trace
Traces shortest path computation on graph A-B (2.0), B-C (3.0), A-C (10.0).

### 8.5 Kruskal's Algorithm Trace
Shows MST construction using Union-Find data structure.

### 8.6 Dynamic Programming Trace
Demonstrates 0/1 Knapsack DP table construction and backtracking.

These trace tables provide step-by-step execution evidence for oral defense and demonstrate deep understanding of algorithm behavior.

---

<div style="page-break-after: always;"></div>

# Chapter 9: Proof Sketches and Counterexamples

Formal proofs and counterexamples are provided in `docs/proof_sketches_and_counterexamples.md`.

## 9.1 Proof Sketches

### 9.1.1 Binary Search Correctness
**Theorem:** Binary Search correctly finds the index of a target value in a sorted array, or returns -1 if not present.

**Proof Method:** Loop Invariant
- **Invariant:** At each iteration, if target exists, it must be in `arr[left..right]`
- **Initialization:** True for initial bounds
- **Maintenance:** Preserved by correct boundary adjustment
- **Termination:** When `left > right`, subarray is empty, target not found

### 9.1.2 Insertion Sort Correctness
**Theorem:** After i-th iteration, first i+1 elements are sorted.

**Proof Method:** Mathematical Induction
- **Base Case:** Single element is trivially sorted
- **Inductive Step:** Inserting i-th element into sorted position maintains sorted order
- **Conclusion:** By induction, entire array sorted after n-1 iterations

### 9.1.3 Dijkstra's Algorithm Correctness
**Theorem:** Dijkstra finds shortest paths from source to all vertices with non-negative weights.

**Proof Method:** Induction on Set S (vertices with confirmed shortest distance)
- **Base Case:** Source vertex distance = 0 is correct
- **Inductive Step:** Minimum tentative distance vertex has correct shortest distance
- **Conclusion:** All vertices have correct distances when S includes all vertices

### 9.1.4 Prim's Algorithm Correctness
**Theorem:** Prim's algorithm produces a Minimum Spanning Tree.

**Proof Method:** Cut Property
- **Cut Property:** Minimum-weight edge crossing any cut belongs to some MST
- **Algorithm:** Always selects minimum edge crossing cut (S, V-S)
- **Conclusion:** By Cut Property, selected edges belong to MST

## 9.2 Counterexamples

### 9.2.1 Greedy Algorithm Failure
**Problem:** Service request scheduling with deadlines
**Greedy Approach:** Always select highest urgency
**Counterexample:** High urgency item may have tight deadline preventing selection of other feasible items
**Conclusion:** Greedy fails; Dynamic Programming required for optimal solution

### 9.2.2 Binary Search Precondition Violation
**Problem:** Binary search on unsorted array
**Counterexample:** Array `[45, 12, 89, 3, 27, 66]`, target `27`
**Result:** Binary search returns -1 even though 27 exists
**Conclusion:** Precondition (sorted array) is necessary for correctness

---

<div style="page-break-after: always;"></div>

# Chapter 10: Conclusion

## 10.1 Summary of Achievements

The UGMC Smart Operations System successfully demonstrates:

✅ **All 14 required data structures** implemented from scratch without using Java's built-in collections
✅ **All 13 required algorithms** implemented across searching, sorting, graph, and optimization categories
✅ **Dataset requirements met:** 50+ locations, 100+ roads, 300+ service requests, 30+ resources
✅ **45+ unit tests** exceeding the minimum requirement of 40 tests
✅ **Algorithm trace tables** providing step-by-step execution evidence
✅ **Proof sketches and counterexamples** demonstrating theoretical understanding
✅ **Interactive console menu** for examiner demonstrations without code modification
✅ **Database persistence layer** with file-based and SQLite-compatible implementations
✅ **Performance benchmarking** with empirical runtime and memory measurements
✅ **Professional documentation** including this comprehensive project documentation

## 10.2 Challenges Encountered

1. **Custom Implementation Complexity:** Implementing data structures from scratch required careful attention to edge cases and memory management
2. **Algorithm Correctness:** Ensuring all algorithms handle boundary conditions correctly required extensive testing
3. **Performance Measurement:** Accurate benchmarking required isolation from JVM optimizations
4. **Dataset Generation:** Creating realistic hospital operation data that meets size requirements
5. **Documentation:** Providing comprehensive theoretical foundations alongside practical implementation

## 10.3 Future Enhancements

Potential improvements for future iterations:

1. **Graphical User Interface:** Add web-based or desktop GUI for better user experience
2. **Real-time Data Integration:** Connect to live hospital operational systems
3. **Advanced Algorithms:** Implement additional algorithms (A*, Floyd-Warshall, Bellman-Ford)
4. **Machine Learning:** Add predictive capabilities for demand forecasting
5. **Mobile Application:** Extend system to mobile devices for field staff
6. **Cloud Deployment:** Deploy to cloud infrastructure for scalability
7. **Advanced Visualization:** Add interactive charts and graphs for performance analysis

---

<div style="page-break-after: always;"></div>

# References

1. Cormen, T. H., Leiserson, C. E., Rivest, R. L., & Stein, C. (2022). *Introduction to Algorithms* (4th ed.). MIT Press.

2. Sedgewick, R., & Wayne, K. (2011). *Algorithms* (4th ed.). Addison-Wesley Professional.

3. Goodrich, M. T., Tamassia, R., & Goldwasser, M. H. (2014). *Data Structures and Algorithms in Java* (6th ed.). Wiley.

4. Knuth, D. E. (1997). *The Art of Computer Programming, Volume 1: Fundamental Algorithms* (3rd ed.). Addison-Wesley.

5. University of Ghana. (2026). *Joint DSA Project Brief*. Department of Computer Science.

6. University of Ghana Medical Centre. (2024). *Hospital Operations Manual*.

---

<div style="page-break-after: always;"></div>

# Appendices

## Appendix A: Installation Guide

### Prerequisites
- JDK 21 or higher
- Unix-like operating system (Linux, macOS) or Windows with WSL/Git Bash
- Git (for version control)

### Installation Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/GyasiAmosKwadwo/UGMC-Smart-Operations-System.git
   cd UGMC-Smart-Operations-System
   ```

2. **Make Scripts Executable**
   ```bash
   chmod +x build.sh run.sh
   ```

3. **Build the Project**
   ```bash
   ./build.sh
   ```

4. **Run the Application**
   ```bash
   ./run.sh
   ```

### Troubleshooting

**Issue:** "java: command not found"
**Solution:** Install JDK 21+ and ensure JAVA_HOME is set correctly

**Issue:** Permission denied on build.sh or run.sh
**Solution:** Run `chmod +x build.sh run.sh`

**Issue:** Compilation errors
**Solution:** Ensure all source files are in correct directory structure

---

## Appendix B: User Manual

### Console Menu Options

#### Option 1: Load CSV Template Data
Loads data from `docs/questions/` directory including:
- locations_template.csv
- roads_template.csv
- service_requests_template.csv
- resources_template.csv

#### Option 2: Show Loaded Dataset Summary
Displays statistics about loaded data:
- Number of locations
- Number of roads
- Number of service requests
- Number of resources

#### Option 3: Demonstrate Custom Data Structures
Interactive demonstrations of:
- Stack operations (push, pop, peek)
- Queue operations (enqueue, dequeue, peek)
- Circular Queue with wrap-around
- Deque operations (addFront, addRear, removeFront, removeRear)
- MinHeap (insert, extractMin, peek)
- LinkedList operations

#### Option 4: Demonstrate Searching & Sorting
Shows:
- Linear Search on unsorted array
- Binary Search on sorted array
- Selection Sort execution
- Insertion Sort execution
- Merge Sort execution
- Quick Sort execution

#### Option 5: Demonstrate Graph Algorithms
Demonstrates:
- BFS traversal on hospital network
- DFS traversal on hospital network
- Dijkstra's shortest path
- Prim's MST algorithm
- Kruskal's MST algorithm

#### Option 6: Demonstrate Optimization Algorithms
Shows:
- Greedy service request dispatch
- Dynamic Programming capacity planning (0/1 Knapsack)

#### Option 7: Reload Data from Persistence
Reloads previously saved data from database.

#### Option 8: Run Performance Benchmark Lab
Executes performance benchmarks and generates CSV reports:
- Sorting algorithm comparison
- Search algorithm comparison
- Graph algorithm performance
- Memory usage analysis

#### Option 9: Run Unit Tests
Executes all 45+ unit tests and displays results.

#### Option 0: Exit
Exits the application gracefully.

---

## Appendix C: Dataset Specifications

### Locations Dataset
- **Minimum Required:** 50 records
- **Actual:** 50 records
- **Format:** CSV with columns: ID, Name, Zone, Type, Latitude, Longitude
- **Example:** L001,Trauma & Emergency Centre,Main Block,Clinical,5.6500,-0.1850

### Roads Dataset
- **Minimum Required:** 100 records
- **Actual:** 100 records
- **Format:** CSV with columns: ID, FromLocation, ToLocation, Distance, TravelTime, CongestionFactor
- **Example:** R001,L001,L002,0.05,0.7,1.0

### Service Requests Dataset
- **Minimum Required:** 300 records
- **Actual:** 300 records
- **Format:** CSV with columns: ID, FromLocation, ToLocation, Description, Priority, RequestTime, DueTime, Status
- **Example:** REQ001,L001,L002,Trauma Patient Transfer,5,2026-08-05T08:00,2026-08-05T08:10,NEW

### Resources Dataset
- **Minimum Required:** 30 records
- **Actual:** 30 records
- **Format:** CSV with columns: ID, Name, Location, Capacity, StaffCount, Status
- **Example:** RES001,Ambulance Fleet,L001,10,3,AVAILABLE

---

<div style="page-break-after: always;"></div>

## End of Document

**Document Version:** 1.0  
**Date:** August 2026  
**Submitted by:** UGMC Smart Operations System Team  
**Institution:** University of Ghana Medical Centre / Department of Computer Science, University of Ghana

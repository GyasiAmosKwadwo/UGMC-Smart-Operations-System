<div align="center">

# UGMC Smart Operations System

**A Data Structures and Algorithms-Based Service Operations Optimizer for the University of Ghana Medical Centre**

[Java 21](https://img.shields.io/badge/Java-21-orange)
[DSA Project](https://img.shields.io/badge/Project-DSA-blue)
[UGMC](https://img.shields.io/badge/Context-UGMC-green)

</div>

---

## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Group Members](#group-members)
- [Background](#background)
- [Problem Statement](#problem-statement)
- [Aim & Objectives](#aim--objectives)
- [System Features](#system-features)
- [Data Structures Implemented](#data-structures-implemented)
- [Algorithms Implemented](#algorithms-implemented)
- [Installation & Setup](#installation--setup)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Documentation](#documentation)
- [Performance Evaluation](#performance-evaluation)
- [Conclusion](#conclusion)

---

## 🎯 Project Overview

The UGMC Smart Operations System is a decision-support platform designed to optimize hospital service operations at the University of Ghana Medical Centre (UGMC). The system demonstrates the practical application of classical data structures and algorithms in solving real-world healthcare operational challenges, including service request prioritization, route optimization, resource allocation, and performance analysis.

---

## 👥 Group Members

| Name | Index Number |
|------|--------------|
| Ezekiel Timothy Yelbaya | 22044041 |
| Kenneth Nii-Okpey Mensah Adjetey | 22125458 |
| Senyo Senaya | 22020018 |
| Kwadwo Nsia Sarkodie | 22243096 |
| Gyasi Amos Kwadwo | 22245133 |
| Asiedu Elvis | 11174994 |
| Elisha Agyei | 22301833 |
| Darko Emmanuel Ofori | 22401302 |
| Richmond Ankan Larbi | 22414076 |
| Rita Aduni | 22300219 |
| Kevin Brown | 22383988 |
| Atakli Queensley Aseye Yawo | 22411239 |
| Annor Ryan Acquah | 22378820 |
| Ruth Appiah | 22396343 |

---

## 🏥 Background

Modern healthcare facilities handle hundreds of operational requests daily, including emergency patient transfers, laboratory sample deliveries, pharmacy requests, ambulance dispatch, equipment allocation, and medical personnel movement across departments. Efficient coordination directly impacts patient outcomes, staff productivity, and overall healthcare quality.

The University of Ghana Medical Centre (UGMC) is a large tertiary healthcare institution with multiple departments including the Emergency Unit, Outpatient Department (OPD), Pharmacy, Radiology, Laboratory, Intensive Care Unit (ICU), Surgical Theatres, Maternity Ward, and various specialist clinics. Coordinating services across these locations requires effective scheduling, prioritization, and routing.

Operational decisions involve selecting the next service request, assigning limited resources, determining optimal travel routes within the hospital network, and monitoring service performance. These challenges provide an excellent opportunity to apply classical data structures and algorithms to solve real-world operational problems.

---

## ⚠️ Problem Statement

Hospital service operations involve numerous simultaneous requests with varying urgency levels. Emergency cases require immediate attention, while routine requests must be processed efficiently without causing unnecessary delays.

Without an optimized operational support system:
- Resources may be assigned inefficiently
- Emergency requests may experience delays
- Routing decisions may increase travel time between departments
- Analyzing algorithm performance and resource utilization becomes difficult

This project designs and implements a Smart Operations System that models hospital operations using custom-built data structures and algorithms to improve service scheduling, routing, resource allocation, searching, and performance analysis.

---

## 🎯 Aim & Objectives

### Aim
To design and implement a smart hospital operations optimization system for UGMC that demonstrates the practical application of data structures and algorithms in solving real-world healthcare operational problems.

### Objectives
- Design a database storing hospital operational data (departments, service requests, road networks, resources)
- Implement custom data structures without relying on Java's built-in implementations
- Prioritize hospital service requests using multiple scheduling strategies
- Compute efficient routes between hospital departments using graph algorithms
- Manage hospital resources (ambulances, nurses, wheelchairs, laboratory personnel)
- Compare the efficiency of multiple searching and sorting algorithms
- Evaluate algorithm performance using empirical runtime and memory measurements
- Generate reports showing algorithm performance as input sizes increase

---

## ✨ System Features

The UGMC Smart Operations System maintains information about:
- Hospital departments and service locations
- Internal road and pathway network
- Service requests
- Medical resources
- Operational assignments
- Algorithm execution records

Users can:
- Register service requests
- Prioritize emergency and routine requests
- Allocate available resources
- Determine optimal travel routes
- Search and retrieve operational records efficiently
- Analyze algorithm performance through generated reports

### Scope
**Included:**
- Department management
- Service request management
- Resource management
- Queue management
- Route optimization
- Search operations
- Sorting operations
- Performance evaluation
- Database integration

**Not Included:**
- Electronic medical records
- Patient diagnosis
- Billing and accounting
- Pharmacy inventory management
- Clinical treatment workflows

---

## 🏗️ Data Structures Implemented

All data structures are implemented from scratch without using Java's built-in collections:

| Data Structure | Description |
|----------------|-------------|
| **Dynamic Array** | Resizable array with automatic capacity management |
| **Linked List** | Singly linked list with head/tail operations |
| **Stack** | LIFO data structure for request processing |
| **Queue** | FIFO data structure for service scheduling |
| **Circular Queue** | Ring buffer for fixed-capacity queuing |
| **Deque** | Double-ended queue for flexible operations |
| **Priority Queue (MinHeap)** | Heap-based priority queue for urgent requests |
| **Binary Search Tree** | Ordered tree for efficient searching |
| **Red-Black Tree** | Self-balancing BST for guaranteed O(log n) operations |
| **B-Tree** | Multi-way tree for disk-efficient storage |
| **Hash Table** | Key-value store with collision resolution |
| **Set** | Collection with unique elements |
| **Map** | Key-value mapping interface |
| **Disjoint Set** | Union-Find for connectivity queries |
| **Graph** | Adjacency list and matrix representations |

---

## ⚙️ Algorithms Implemented

### Searching
- **Linear Search** - O(n) sequential search
- **Binary Search** - O(log n) search on sorted arrays

### Sorting
- **Selection Sort** - O(n²) simple comparison sort
- **Insertion Sort** - O(n²) adaptive sort
- **Merge Sort** - O(n log n) divide-and-conquer
- **Quick Sort** - O(n log n) average case partition sort

### Graph Algorithms
- **Breadth-First Search (BFS)** - Level-order traversal
- **Depth-First Search (DFS)** - Recursive exploration
- **Dijkstra's Algorithm** - Shortest path with non-negative weights
- **Prim's Algorithm** - Minimum Spanning Tree
- **Kruskal's Algorithm** - MST with Union-Find

### Optimization
- **Greedy Algorithm** - Priority-based service dispatch
- **Dynamic Programming** - 0/1 Knapsack for capacity planning

---

## 🚀 Installation & Setup

### Requirements
- **JDK 21+** - Java Development Kit
- No external dependencies required (plain Java project)

### Build & Run

```bash
# Make scripts executable (once)
chmod +x build.sh run.sh

# Compile all sources into out/
./build.sh

# Run the interactive console menu
./run.sh
```

---

## 📖 Usage

### Console Menu

The interactive menu allows examiners to run demonstrations without editing code:

| Option | Description |
|--------|-------------|
| **1** | Load CSV template data from `docs/questions/` |
| **2** | Show loaded dataset summary |
| **3** | Demonstrate custom data structures (stack, queue, circular queue, deque, heap, linked list) |
| **4** | Demonstrate searching & sorting (linear/binary search, selection/insertion/merge/quicksort) |
| **5** | Demonstrate graph algorithms (BFS, DFS, Dijkstra, Prim, Kruskal) |
| **6** | Demonstrate optimization algorithms (Greedy & Dynamic Programming) |
| **7** | Reload data from persistence |
| **8** | Run empirical performance benchmark lab & generate runtime reports |
| **9** | Run automated data structure & algorithm unit tests suite |
| **0** | Exit |

### Dataset Specifications

The system uses the following minimum dataset sizes as per requirements:
- **Locations**: 50+ records
- **Roads**: 100+ records
- **Service Requests**: 300+ records
- **Resources**: 30+ records

---

## 📁 Project Structure

```
UGMC-Smart-Operations-System/
├── docs/
│   ├── questions/              # CSV template data files
│   │   ├── locations_template.csv
│   │   ├── roads_template.csv
│   │   ├── service_requests_template.csv
│   │   └── resources_template.csv
│   ├── trace_tables.md         # Algorithm trace tables
│   └── proof_sketches_and_counterexamples.md
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── ugmc/
│                   └── smartops/
│                       ├── Main.java                 # Entry point
│                       ├── ConsoleApp.java           # Interactive console menu
│                       ├── model/                    # Data models
│                       │   ├── Location.java
│                       │   ├── Road.java
│                       │   ├── ServiceRequest.java
│                       │   ├── Resource.java
│                       │   └── AlgorithmRun.java
│                       ├── datastructure/            # Custom data structures
│                       │   ├── DynamicArray.java
│                       │   ├── LinkedList.java
│                       │   ├── Stack.java
│                       │   ├── Queue.java
│                       │   ├── CircularQueue.java
│                       │   ├── Deque.java
│                       │   ├── MinHeap.java
│                       │   ├── BinarySearchTree.java
│                       │   ├── RedBlackTree.java
│                       │   ├── BTree.java
│                       │   ├── HashTable.java
│                       │   ├── Set.java
│                       │   ├── Map.java
│                       │   ├── DisjointSet.java
│                       │   └── Graph.java
│                       ├── algorithm/                # Algorithm implementations
│                       │   ├── SearchEngine.java
│                       │   ├── SortEngine.java
│                       │   ├── GraphEngine.java
│                       │   ├── OptimizationEngine.java
│                       │   └── PerformanceBenchmark.java
│                       ├── db/                       # Database layer
│                       │   ├── Database.java
│                       │   ├── FileDatabase.java
│                       │   ├── DataLoader.java
│                       │   └── OperationalStore.java
│                       ├── test/                     # Unit tests
│                       │   └── UnitTestRunner.java
│                       └── util/                     # Utilities
│                           └── CsvReader.java
├── data/                      # Generated data files (gitignored)
├── build.sh                   # Build script
├── run.sh                     # Run script
└── README.md                  # This file
```

---

## 📚 Documentation

### Algorithm Documentation
- **[Trace Tables](docs/trace_tables.md)** - Step-by-step execution traces for Binary Search, Insertion Sort, Merge Sort, Dijkstra, Kruskal, and Dynamic Programming
- **[Proof Sketches & Counterexamples](docs/proof_sketches_and_counterexamples.md)** - Formal correctness proofs and algorithm limitations

### Testing
The project includes **45+ unit tests** covering:
- Linear data structures (DynamicArray, LinkedList, Stack, Queue, CircularQueue, Deque)
- Heaps and priority queues (MinHeap)
- Trees (BinarySearchTree, RedBlackTree, BTree)
- Key-value structures (HashTable, Map, Set, DisjointSet)
- Graph algorithms (BFS, DFS, Dijkstra, Prim, Kruskal)
- Sorting algorithms (Selection, Insertion, Merge, Quick Sort)
- Searching algorithms (Linear, Binary Search)
- Optimization algorithms (Greedy, Dynamic Programming)

---

## 📊 Performance Evaluation

The system includes empirical performance benchmarking that measures:
- **Runtime** - Execution time for each algorithm across different input sizes
- **Memory Usage** - Memory consumption during algorithm execution
- **Big-O Analysis** - Theoretical complexity comparison

Benchmark results are exported to CSV format for analysis and visualization.

---

## 🎓 Conclusion

The UGMC Smart Operations System provides a realistic Ghanaian healthcare scenario for demonstrating the practical application of Data Structures and Algorithms. By integrating custom-built data structures, graph algorithms, searching, sorting, optimization techniques, database persistence, and empirical performance evaluation, the project satisfies the objectives of the Joint DSA Semester Project while addressing operational challenges commonly encountered in modern healthcare facilities.

### Key Achievements
- ✅ All 14 required data structures implemented from scratch
- ✅ All 13 required algorithms implemented
- ✅ Dataset requirements met (50+ locations, 100+ roads, 300+ service requests, 30+ resources)
- ✅ 45+ unit tests exceeding minimum requirement
- ✅ Algorithm trace tables provided
- ✅ Proof sketches and counterexamples documented
- ✅ Interactive console menu for examiner demonstrations
- ✅ Database persistence layer
- ✅ Performance benchmarking with empirical evidence

---

## 📄 License

This project is submitted as part of the Joint DSA Semester Project for the University of Ghana.

---

## 🏛️ Institution

**University of Ghana Medical Centre (UGMC)**  
Department of Computer Science  
Data Structures and Algorithms Project

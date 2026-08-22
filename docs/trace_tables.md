# Algorithm Trace Tables

This document contains trace tables for selected algorithms as required by the project specifications.

## 1. Binary Search Trace Table

**Algorithm:** Binary Search on sorted array
**Input:** `[3, 12, 27, 45, 66, 89]`, target = `45`
**Precondition:** Array must be sorted in ascending order

| Iteration | Left | Right | Mid | arr[mid] | Comparison | Action |
|-----------|------|-------|-----|----------|------------|--------|
| 1         | 0    | 5     | 2   | 27       | 27 < 45    | left = mid + 1 = 3 |
| 2         | 3    | 5     | 4   | 66       | 66 > 45    | right = mid - 1 = 3 |
| 3         | 3    | 3     | 3   | 45       | 45 == 45   | return 3 |

**Result:** Found at index 3

---

## 2. Insertion Sort Trace Table

**Algorithm:** Insertion Sort
**Input:** `[45, 12, 89, 3, 27, 66]`

| Pass | i | key | j | Array State | Comparison | Action |
|------|---|-----|---|-------------|------------|--------|
| 1    | 1 | 12  | 0 | [45, 12, 89, 3, 27, 66] | 45 > 12 | Shift 45 right, insert 12 |
|      |   |     |   | [12, 45, 89, 3, 27, 66] | | |
| 2    | 2 | 89  | 1 | [12, 45, 89, 3, 27, 66] | 45 < 89 | No shift needed |
|      |   |     |   | [12, 45, 89, 3, 27, 66] | | |
| 3    | 3 | 3   | 2 | [12, 45, 89, 3, 27, 66] | 89 > 3 | Shift 89 right |
|      |   |     | 1 | [12, 45, _, 89, 27, 66] | 45 > 3 | Shift 45 right |
|      |   |     | 0 | [12, _, 45, 89, 27, 66] | 12 > 3 | Shift 12 right |
|      |   |     |   | [3, 12, 45, 89, 27, 66] | | Insert 3 |
| 4    | 4 | 27  | 3 | [3, 12, 45, 89, 27, 66] | 89 > 27 | Shift 89 right |
|      |   |     | 2 | [3, 12, 45, _, 89, 66] | 45 > 27 | Shift 45 right |
|      |   |     | 1 | [3, 12, _, 45, 89, 66] | 12 < 27 | Insert 27 |
|      |   |     |   | [3, 12, 27, 45, 89, 66] | | |
| 5    | 5 | 66  | 4 | [3, 12, 27, 45, 89, 66] | 89 > 66 | Shift 89 right |
|      |   |     |   | [3, 12, 27, 45, 66, 89] | | Insert 66 |

**Result:** `[3, 12, 27, 45, 66, 89]`

---

## 3. Merge Sort Trace Table

**Algorithm:** Merge Sort (recursive)
**Input:** `[45, 12, 89, 3, 27, 66]`

### Divide Phase:
```
[45, 12, 89, 3, 27, 66]
        ↓
[45, 12, 89]    [3, 27, 66]
     ↓              ↓
[45] [12, 89]    [3] [27, 66]
      ↓              ↓
     [12] [89]      [27] [66]
```

### Merge Phase:

| Merge Step | Left Array | Right Array | Result Array | Comparison | Action |
|------------|------------|-------------|--------------|------------|--------|
| 1          | [12]       | [89]        | []           | 12 < 89     | Take 12 |
|            | []         | [89]        | [12]         | -          | Take 89 |
|            | []         | []          | [12, 89]     | -          | Done |
| 2          | [45]       | [12, 89]    | []           | 45 > 12     | Take 12 |
|            | [45]       | [89]        | [12]         | 45 < 89     | Take 45 |
|            | []         | [89]        | [12, 45]     | -          | Take 89 |
|            | []         | []          | [12, 45, 89] | -          | Done |
| 3          | [27]       | [66]        | []           | 27 < 66     | Take 27 |
|            | []         | [66]        | [27]         | -          | Take 66 |
|            | []         | []          | [27, 66]     | -          | Done |
| 4          | [12, 45, 89] | [3, 27, 66] | []        | 12 > 3      | Take 3 |
|            | [12, 45, 89] | [27, 66]    | [3]        | 12 < 27     | Take 12 |
|            | [45, 89]    | [27, 66]    | [3, 12]    | 45 > 27     | Take 27 |
|            | [45, 89]    | [66]        | [3, 12, 27] | 45 < 66     | Take 45 |
|            | [89]        | [66]        | [3, 12, 27, 45] | 89 > 66 | Take 66 |
|            | [89]        | []          | [3, 12, 27, 45, 66] | - | Take 89 |
|            | []          | []          | [3, 12, 27, 45, 66, 89] | - | Done |

**Result:** `[3, 12, 27, 45, 66, 89]`

---

## 4. Dijkstra's Algorithm Trace Table

**Algorithm:** Dijkstra's Shortest Path
**Graph:** A-B (2.0), B-C (3.0), A-C (10.0)
**Start:** A, **Target:** C

| Step | Current Node | Distance to A | Path | Visited | Action |
|------|--------------|--------------|------|---------|--------|
| Init | -            | A:0, B:∞, C:∞ | A:-, B:-, C:- | {} | Initialize |
| 1    | A            | A:0, B:2, C:10 | A:-, B:A, C:A | {A} | Process A, update B and C |
| 2    | B            | A:0, B:2, C:5 | A:-, B:A, C:B | {A,B} | Process B, update C via B (2+3=5 < 10) |
| 3    | C            | A:0, B:2, C:5 | A:-, B:A, C:B | {A,B,C} | Process C (target reached) |

**Result:** Shortest path A → B → C with total weight 5.0

---

## 5. Kruskal's Algorithm Trace Table

**Algorithm:** Kruskal's Minimum Spanning Tree
**Graph:** A-B (2.0), B-C (3.0), A-C (10.0)

| Step | Edge | Weight | Action | Disjoint Set State | MST Edges | Total Weight |
|------|------|--------|--------|-------------------|-----------|--------------|
| Init | -    | -      | Initialize | {A}, {B}, {C} | [] | 0 |
| 1    | A-B  | 2.0    | Add (A,B) | {A,B}, {C} | [(A,B)] | 2.0 |
| 2    | B-C  | 3.0    | Add (B,C) | {A,B,C} | [(A,B), (B,C)] | 5.0 |
| 3    | A-C  | 10.0   | Skip (cycle) | {A,B,C} | [(A,B), (B,C)] | 5.0 |

**Result:** MST edges: (A,B), (B,C) with total weight 5.0

---

## 6. Dynamic Programming (0/1 Knapsack) Trace Table

**Algorithm:** 0/1 Knapsack for Service Request Selection
**Capacity:** 5
**Items:** R1(urgency=5, weight=1), R2(urgency=4, weight=2), R3(urgency=3, weight=3)

### DP Table Construction:

| i (item) | w (weight) | v (value) | Capacity 0 | Capacity 1 | Capacity 2 | Capacity 3 | Capacity 4 | Capacity 5 |
|----------|-----------|----------|------------|------------|------------|------------|------------|------------|
| 0 (none) | -         | -        | 0          | 0          | 0          | 0          | 0          | 0          |
| 1 (R1)   | 1         | 5        | 0          | 5          | 5          | 5          | 5          | 5          |
| 2 (R2)   | 2         | 4        | 0          | 5          | 5          | 9          | 9          | 9          |
| 3 (R3)   | 3         | 3        | 0          | 5          | 5          | 9          | 8          | 12         |

### Backtracking for Solution:
- At capacity 5: max value = 12
- Include R3 (weight 3, value 3): remaining capacity = 2, value = 12 - 3 = 9
- At capacity 2: max value = 9 (from R2)
- Include R2 (weight 2, value 4): remaining capacity = 0, value = 9 - 4 = 5
- At capacity 0: max value = 5 (from R1)
- Include R1 (weight 1, value 5): remaining capacity = -1 (stop)

**Result:** Selected items: R1, R2, R3 with total value 12, total weight 6 (exceeds capacity)
**Optimal Solution:** R1 (weight 1, value 5) + R2 (weight 2, value 4) = total value 9, weight 3 (within capacity)

---

## Notes on Trace Tables

- All trace tables demonstrate the step-by-step execution of algorithms
- Preconditions are clearly stated where applicable
- Edge cases and special conditions are highlighted
- Results are clearly indicated at the end of each trace
- These traces can be used during oral defense to demonstrate algorithm understanding

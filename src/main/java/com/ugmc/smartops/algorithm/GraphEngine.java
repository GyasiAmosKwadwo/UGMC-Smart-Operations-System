package com.ugmc.smartops.algorithm;

import com.ugmc.smartops.datastructure.*;

/**
 * Custom Engine for Graph Algorithms:
 * 1. Breadth-First Search (BFS)
 * 2. Depth-First Search (DFS)
 * 3. Dijkstra's Shortest Path Algorithm
 * 4. Prim's Minimum Spanning Tree (MST)
 * 5. Kruskal's Minimum Spanning Tree (MST)
 *
 * Implemented from scratch using custom data structures.
 *
 * @author UGMC Smart Operations Team
 */
public class GraphEngine {

    /** Result container for shortest path calculations. */
    public static class PathResult {
        private final DynamicArray<String> path;
        private final double totalWeight;

        public PathResult(DynamicArray<String> path, double totalWeight) {
            this.path = path;
            this.totalWeight = totalWeight;
        }

        public DynamicArray<String> getPath() { return path; }
        public double getTotalWeight() { return totalWeight; }

        @Override
        public String toString() {
            if (path == null || path.isEmpty()) {
                return "No path found.";
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) sb.append(" -> ");
                sb.append(path.get(i));
            }
            sb.append(" (Total Weight/Cost: ").append(String.format("%.2f", totalWeight)).append(")");
            return sb.toString();
        }
    }

    /** Helper tuple for PriorityQueue (MinHeap) in Dijkstra. */
    private static class PQItem implements Comparable<PQItem> {
        final String vertex;
        final double distance;

        PQItem(String vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(PQItem o) {
            return Double.compare(this.distance, o.distance);
        }
    }

    /** Helper tuple for Kruskal's edge sorting. */
    public static class ScoredEdge implements Comparable<ScoredEdge> {
        public final Graph.Edge edge;

        public ScoredEdge(Graph.Edge edge) {
            this.edge = edge;
        }

        @Override
        public int compareTo(ScoredEdge o) {
            return Double.compare(this.edge.getWeight(), o.edge.getWeight());
        }
    }

    // ==========================================
    // 1. BREADTH-FIRST SEARCH (BFS)
    // ==========================================
    public static DynamicArray<String> bfs(Graph graph, String startVertex) {
        DynamicArray<String> result = new DynamicArray<>();
        if (graph == null || startVertex == null || !graph.getVertices().contains(startVertex)) {
            return result;
        }

        Set<String> visited = new Set<>();
        Queue<String> queue = new Queue<>();

        visited.add(startVertex);
        queue.enqueue(startVertex);

        while (!queue.isEmpty()) {
            String current = queue.dequeue();
            result.add(current);

            DynamicArray<Graph.Edge> neighbors = graph.getNeighbors(current);
            for (Graph.Edge edge : neighbors) {
                String neighbor = edge.getDestination();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.enqueue(neighbor);
                }
            }
        }

        return result;
    }

    // ==========================================
    // 2. DEPTH-FIRST SEARCH (DFS)
    // ==========================================
    public static DynamicArray<String> dfs(Graph graph, String startVertex) {
        DynamicArray<String> result = new DynamicArray<>();
        if (graph == null || startVertex == null || !graph.getVertices().contains(startVertex)) {
            return result;
        }

        Set<String> visited = new Set<>();
        dfsRecursive(graph, startVertex, visited, result);
        return result;
    }

    private static void dfsRecursive(Graph graph, String current, Set<String> visited, DynamicArray<String> result) {
        visited.add(current);
        result.add(current);

        DynamicArray<Graph.Edge> neighbors = graph.getNeighbors(current);
        for (Graph.Edge edge : neighbors) {
            String neighbor = edge.getDestination();
            if (!visited.contains(neighbor)) {
                dfsRecursive(graph, neighbor, visited, result);
            }
        }
    }

    // ==========================================
    // 3. DIJKSTRA'S SHORTEST PATH ALGORITHM
    // ==========================================
    public static PathResult dijkstra(Graph graph, String source, String destination) {
        if (graph == null || source == null || destination == null ||
            !graph.getVertices().contains(source) || !graph.getVertices().contains(destination)) {
            return new PathResult(new DynamicArray<>(), Double.POSITIVE_INFINITY);
        }

        Map<String, Double> dist = new Map<>();
        Map<String, String> prev = new Map<>();
        MinHeap<PQItem> pq = new MinHeap<>();

        for (String v : graph.getVertices()) {
            dist.put(v, Double.POSITIVE_INFINITY);
        }

        dist.put(source, 0.0);
        pq.insert(new PQItem(source, 0.0));

        while (!pq.isEmpty()) {
            PQItem item = pq.extractMin();
            String u = item.vertex;

            if (u.equals(destination)) break;

            Double currentDist = dist.get(u);
            if (currentDist == null || item.distance > currentDist) continue;

            DynamicArray<Graph.Edge> neighbors = graph.getNeighbors(u);
            for (Graph.Edge edge : neighbors) {
                String v = edge.getDestination();
                Double vDist = dist.get(v);
                double alt = currentDist + edge.getWeight();
                if (vDist != null && alt < vDist) {
                    dist.put(v, alt);
                    prev.put(v, u);
                    pq.insert(new PQItem(v, alt));
                }
            }
        }

        Double destDist = dist.get(destination);
        if (destDist == null || destDist == Double.POSITIVE_INFINITY) {
            return new PathResult(new DynamicArray<>(), Double.POSITIVE_INFINITY);
        }

        // Reconstruct path
        Stack<String> stack = new Stack<>();
        String curr = destination;
        while (curr != null) {
            stack.push(curr);
            curr = prev.get(curr);
        }

        DynamicArray<String> path = new DynamicArray<>();
        while (!stack.isEmpty()) {
            path.add(stack.pop());
        }

        return new PathResult(path, destDist);
    }

    // ==========================================
    // 4. PRIM'S MINIMUM SPANNING TREE (MST)
    // ==========================================
    public static DynamicArray<Graph.Edge> primMST(Graph graph) {
        DynamicArray<Graph.Edge> mstEdges = new DynamicArray<>();
        if (graph == null || graph.getVertices().isEmpty()) return mstEdges;

        Set<String> visited = new Set<>();
        for (String componentStart : graph.getVertices()) {
            if (visited.contains(componentStart)) continue;
            visited.add(componentStart);
            MinHeap<ScoredEdge> pq = new MinHeap<>();
            for (Graph.Edge edge : graph.getNeighbors(componentStart)) {
                pq.insert(new ScoredEdge(edge));
            }

            while (!pq.isEmpty()) {
                ScoredEdge scoredEdge = pq.extractMin();
                Graph.Edge edge = scoredEdge.edge;
                if (visited.contains(edge.getDestination())) continue;

                visited.add(edge.getDestination());
                mstEdges.add(edge);

                for (Graph.Edge nextEdge : graph.getNeighbors(edge.getDestination())) {
                    if (!visited.contains(nextEdge.getDestination())) {
                        pq.insert(new ScoredEdge(nextEdge));
                    }
                }
            }
        }

        return mstEdges;
    }

    // ==========================================
    // 5. KRUSKAL'S MINIMUM SPANNING TREE (MST)
    // ==========================================
    public static DynamicArray<Graph.Edge> kruskalMST(Graph graph) {
        DynamicArray<Graph.Edge> mstEdges = new DynamicArray<>();
        if (graph == null || graph.getVertices().isEmpty()) return mstEdges;

        Set<String> vertices = graph.getVertices();

        // Assign integer IDs to vertices for DisjointSet
        Map<String, Integer> vIndexMap = new Map<>();
        int index = 0;
        for (String v : vertices) {
            vIndexMap.put(v, index++);
        }

        DisjointSet ds = new DisjointSet(vertices.size());

        // Sort all edges using QuickSort via SortEngine logic
        DynamicArray<Graph.Edge> allEdges = graph.getAllEdges();
        ScoredEdge[] edgesArray = new ScoredEdge[allEdges.size()];
        for (int i = 0; i < allEdges.size(); i++) {
            edgesArray[i] = new ScoredEdge(allEdges.get(i));
        }

        SortEngine.quickSort(edgesArray);

        for (ScoredEdge se : edgesArray) {
            Graph.Edge edge = se.edge;
            Integer u = vIndexMap.get(edge.getSource());
            Integer v = vIndexMap.get(edge.getDestination());

            if (u != null && v != null && ds.find(u) != ds.find(v)) {
                ds.union(u, v);
                mstEdges.add(edge);
            }
        }

        return mstEdges;
    }
}

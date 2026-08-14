package com.ugmc.smartops.datastructure;

/**
 * A custom Graph implementation supporting both Adjacency List and Adjacency Matrix.
 * Modeled for hospital departments (vertices) and road/pathway connections (weighted edges).
 * Built from scratch without using java.util collections.
 *
 * @author UGMC Smart Operations Team
 */
public class Graph implements CustomCollection<String> {

    public static class Edge {
        private final String source;
        private final String destination;
        private final double weight;
        private final String label;

        public Edge(String source, String destination, double weight) {
            this(source, destination, weight, "");
        }

        public Edge(String source, String destination, double weight, String label) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
            this.label = label;
        }

        public String getSource() { return source; }
        public String getDestination() { return destination; }
        public double getWeight() { return weight; }
        public String getLabel() { return label; }

        @Override
        public String toString() {
            return source + " -> " + destination + " (w=" + weight + ")";
        }
    }

    private final Map<String, DynamicArray<Edge>> adjacencyList;
    private final Set<String> vertices;
    private final DynamicArray<Edge> allEdges;
    private final boolean directed;

    public Graph(boolean directed) {
        this.adjacencyList = new Map<>();
        this.vertices = new Set<>();
        this.allEdges = new DynamicArray<>();
        this.directed = directed;
    }

    /** Adds a vertex to the graph. */
    public void addVertex(String vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            adjacencyList.put(vertex, new DynamicArray<>());
        }
    }

    /** Adds a weighted edge between source and destination. */
    public void addEdge(String source, String destination, double weight) {
        addEdge(source, destination, weight, "");
    }

    /** Adds a weighted edge with a custom label (e.g. road ID). */
    public void addEdge(String source, String destination, double weight, String label) {
        addVertex(source);
        addVertex(destination);

        Edge edge = new Edge(source, destination, weight, label);
        adjacencyList.get(source).add(edge);
        allEdges.add(edge);

        if (!directed) {
            Edge revEdge = new Edge(destination, source, weight, label);
            adjacencyList.get(destination).add(revEdge);
        }
    }

    /** Returns all neighbor edges of a given vertex. */
    public DynamicArray<Edge> getNeighbors(String vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return new DynamicArray<>();
        }
        return adjacencyList.get(vertex);
    }

    /** Returns all vertices in the graph. */
    public Set<String> getVertices() {
        return vertices;
    }

    /** Returns all edges in the graph. */
    public DynamicArray<Edge> getAllEdges() {
        return allEdges;
    }

    public boolean isDirected() {
        return directed;
    }

    /** Constructs and returns an Adjacency Matrix representation. */
    public double[][] getAdjacencyMatrix() {
        int n = vertices.size();
        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) matrix[i][j] = 0;
                else matrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        DynamicArray<String> vList = new DynamicArray<>();
        for (String v : vertices) {
            vList.add(v);
        }

        for (int i = 0; i < vList.size(); i++) {
            String u = vList.get(i);
            DynamicArray<Edge> neighbors = getNeighbors(u);
            for (Edge edge : neighbors) {
                int j = vList.indexOf(edge.getDestination());
                if (j != -1) {
                    matrix[i][j] = Math.min(matrix[i][j], edge.getWeight());
                }
            }
        }

        return matrix;
    }

    @Override
    public int size() {
        return vertices.size();
    }

    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public void clear() {
        vertices.clear();
        adjacencyList.clear();
        allEdges.clear();
    }

    @Override
    public java.util.Iterator<String> iterator() {
        return vertices.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Graph (Vertices=" + vertices.size() + ", Edges=" + allEdges.size() + "):\n");
        for (String v : vertices) {
            sb.append("  ").append(v).append(" -> ").append(getNeighbors(v)).append("\n");
        }
        return sb.toString();
    }
}

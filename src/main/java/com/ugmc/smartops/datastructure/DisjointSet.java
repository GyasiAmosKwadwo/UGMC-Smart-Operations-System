package com.ugmc.smartops.datastructure;

/**
 * A custom disjoint-set (union-find) structure with union by rank/size and
 * path compression. Used to trace Kruskal's MST connectivity.
 *
 * @author UGMC Smart Operations Team
 */
public class DisjointSet {

    private final int[] parent;
    private final int[] rank;
    private int numSets;

    public DisjointSet(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Size cannot be negative: " + n);
        }
        this.parent = new int[n];
        this.rank = new int[n];
        this.numSets = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /** Creates a new singleton set for element i (makeSet). */
    public void makeSet(int i) {
        parent[i] = i;
        rank[i] = 0;
    }

    /** Finds the representative of the set containing i, with path compression. */
    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]); // path compression
        }
        return parent[i];
    }

    /** Unions the sets containing a and b using union by rank. */
    public void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) {
            return;
        }
        // Union by rank: attach the smaller tree under the larger.
        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++;
        }
        numSets--;
    }

    /** Returns true if a and b belong to the same set. */
    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }

    /** Returns the number of disjoint sets remaining. */
    public int numSets() { return numSets; }

    public int size() { return parent.length; }
}

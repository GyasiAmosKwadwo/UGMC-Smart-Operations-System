package com.ugmc.smartops.datastructure;

/**
 * A custom B-Tree (balanced M-way search tree) for multi-way indexing.
 * Implemented from scratch without using java.util collections.
 *
 * @param <K> key type (Comparable)
 * @param <V> value type
 * @author UGMC Smart Operations Team
 */
public class BTree<K extends Comparable<K>, V> implements CustomCollection<K> {

    private static final int M = 4; // B-Tree order (max children = M)

    private static class Entry {
        private Comparable key;
        private Object val;
        private Node next;

        public Entry(Comparable key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private static class Node {
        private int m;
        private Entry[] children = new Entry[M];

        private Node(int k) {
            m = k;
        }
    }

    private Node root;
    private int height;
    private int n;

    public BTree() {
        root = new Node(0);
    }

    @Override
    public int size() { return n; }

    @Override
    public boolean isEmpty() { return n == 0; }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return search(root, key, height);
    }

    @SuppressWarnings("unchecked")
    private V search(Node x, K key, int ht) {
        Entry[] children = x.children;

        if (ht == 0) {
            for (int j = 0; j < x.m; j++) {
                if (eq(key, children[j].key)) return (V) children[j].val;
            }
        } else {
            for (int j = 0; j < x.m; j++) {
                if (j + 1 == x.m || less(key, children[j + 1].key)) {
                    return search(children[j].next, key, ht - 1);
                }
            }
        }
        return null;
    }

    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        if (get(key) != null) {
            update(root, key, val, height);
            return;
        }
        Node u = insert(root, key, val, height);
        n++;
        if (u == null) return;

        // Create new root if split occurred
        Node t = new Node(2);
        t.children[0] = new Entry(root.children[0].key, null, root);
        t.children[1] = new Entry(u.children[0].key, null, u);
        root = t;
        height++;
    }

    private boolean update(Node node, K key, V val, int ht) {
        if (ht == 0) {
            for (int i = 0; i < node.m; i++) {
                if (eq(key, node.children[i].key)) {
                    node.children[i].val = val;
                    return true;
                }
            }
            return false;
        }
        for (int i = 0; i < node.m; i++) {
            if (i + 1 == node.m || less(key, node.children[i + 1].key)) {
                return update(node.children[i].next, key, val, ht - 1);
            }
        }
        return false;
    }

    private Node insert(Node h, K key, V val, int ht) {
        int j;
        Entry t = new Entry(key, val, null);

        if (ht == 0) {
            for (j = 0; j < h.m; j++) {
                if (less(key, h.children[j].key)) break;
            }
        } else {
            for (j = 0; j < h.m; j++) {
                if ((j + 1 == h.m) || less(key, h.children[j + 1].key)) {
                    Node u = insert(h.children[j++].next, key, val, ht - 1);
                    if (u == null) return null;
                    t.key = u.children[0].key;
                    t.val = null;
                    t.next = u;
                    break;
                }
            }
        }

        for (int i = h.m; i > j; i--) {
            h.children[i] = h.children[i - 1];
        }
        h.children[j] = t;
        h.m++;
        if (h.m < M) return null;
        else return split(h);
    }

    private Node split(Node h) {
        Node t = new Node(M / 2);
        h.m = M / 2;
        for (int j = 0; j < M / 2; j++) {
            t.children[j] = h.children[M / 2 + j];
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    private boolean less(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) < 0;
    }

    @SuppressWarnings("unchecked")
    private boolean eq(Comparable k1, Comparable k2) {
        return k1.compareTo(k2) == 0;
    }

    @Override
    public void clear() {
        root = new Node(0);
        height = 0;
        n = 0;
    }

    @Override
    public java.util.Iterator<K> iterator() {
        DynamicArray<K> keys = new DynamicArray<>();
        collectKeys(root, height, keys);
        return keys.iterator();
    }

    @SuppressWarnings("unchecked")
    private void collectKeys(Node x, int ht, DynamicArray<K> keys) {
        Entry[] children = x.children;

        if (ht == 0) {
            for (int j = 0; j < x.m; j++) {
                keys.add((K) children[j].key);
            }
        } else {
            for (int j = 0; j < x.m; j++) {
                collectKeys(children[j].next, ht - 1, keys);
            }
        }
    }
}

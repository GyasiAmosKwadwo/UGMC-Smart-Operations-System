package com.ugmc.smartops.datastructure;

/**
 * A custom Red-Black Tree implementation for balanced binary search tree operations.
 * Implemented from scratch (no java.util.TreeMap).
 *
 * @param <K> key type (Comparable)
 * @param <V> value type
 * @author UGMC Smart Operations Team
 */
public class RedBlackTree<K extends Comparable<K>, V> implements CustomCollection<K> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        K key;
        V value;
        Node left, right;
        boolean color;
        int size;

        Node(K key, V value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    private Node root;

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        return get(root, key);
    }

    private V get(Node x, K key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Key cannot be null");
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node h, K key, V value) {
        if (h == null) return new Node(key, value, RED, 1);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, value);
        else if (cmp > 0) h.right = put(h.right, key, value);
        else h.value = value;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color;
        if (h.left != null) h.left.color = !h.left.color;
        if (h.right != null) h.right.color = !h.right.color;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public java.util.Iterator<K> iterator() {
        DynamicArray<K> keys = new DynamicArray<>();
        inorder(root, keys);
        return keys.iterator();
    }

    private void inorder(Node x, DynamicArray<K> keys) {
        if (x == null) return;
        inorder(x.left, keys);
        keys.add(x.key);
        inorder(x.right, keys);
    }
}

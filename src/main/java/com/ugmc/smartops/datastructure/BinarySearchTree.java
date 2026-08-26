package com.ugmc.smartops.datastructure;

import java.util.NoSuchElementException;

/**
 * A custom binary search tree (BST) implemented from scratch.
 * Supports insert, search, delete, and in-order traversal.
 *
 * @param <K> key type (must be Comparable)
 * @param <V> value type
 * @author UGMC Smart Operations Team
 */
public class BinarySearchTree<K extends Comparable<K>, V> {

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V> root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /** Inserts or updates the value for the given key. */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value; // update existing
        }
        return node;
    }

    /** Returns the value for the given key, or null if absent. */
    public V get(K key) {
        Node<K, V> node = search(key);
        return node == null ? null : node.value;
    }

    /** Returns true if the key is present. */
    public boolean containsKey(K key) {
        return search(key) != null;
    }

    /** Searches the tree for the given key, returning the node or null. */
    public Node<K, V> search(K key) {
        Node<K, V> current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    /** Removes the given key; returns true if it was present. */
    public boolean remove(K key) {
        int before = size;
        root = remove(root, key);
        return size < before;
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            size--;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // Node with two children: replace with in-order successor.
            Node<K, V> successor = min(node.right);
            node.key = successor.key;
            node.value = successor.value;
            node.right = remove(node.right, successor.key);
        }
        return node;
    }

    private Node<K, V> min(Node<K, V> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /** Returns the smallest key in the tree. */
    public K minKey() {
        if (root == null) {
            throw new NoSuchElementException("Tree is empty");
        }
        return min(root).key;
    }

    /** Returns the largest key in the tree. */
    public K maxKey() {
        if (root == null) {
            throw new NoSuchElementException("Tree is empty");
        }
        Node<K, V> node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.key;
    }

    /** Returns the height of the tree (number of edges on longest path). */
    public int height() {
        return height(root);
    }

    private int height(Node<K, V> node) {
        if (node == null) return -1;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /** Performs an in-order traversal, invoking the visitor for each node. */
    public void inorder(Visitor<K, V> visitor) {
        inorder(root, visitor);
    }

    private void inorder(Node<K, V> node, Visitor<K, V> visitor) {
        if (node == null) return;
        inorder(node.left, visitor);
        visitor.visit(node.key, node.value);
        inorder(node.right, visitor);
    }

    /** Performs a pre-order traversal, invoking the visitor for each node. */
    public void preorder(Visitor<K, V> visitor) {
        preorder(root, visitor);
    }

    private void preorder(Node<K, V> node, Visitor<K, V> visitor) {
        if (node == null) return;
        visitor.visit(node.key, node.value);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    /** Performs a post-order traversal, invoking the visitor for each node. */
    public void postorder(Visitor<K, V> visitor) {
        postorder(root, visitor);
    }

    private void postorder(Node<K, V> node, Visitor<K, V> visitor) {
        if (node == null) return;
        postorder(node.left, visitor);
        postorder(node.right, visitor);
        visitor.visit(node.key, node.value);
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public void clear() {
        root = null;
        size = 0;
    }

    /** Visitor callback for tree traversals. */
    public interface Visitor<K, V> {
        void visit(K key, V value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        final boolean[] first = {true};
        inorder((k, v) -> {
            if (!first[0]) sb.append(", ");
            sb.append(k);
            first[0] = false;
        });
        return sb.append("]").toString();
    }
}

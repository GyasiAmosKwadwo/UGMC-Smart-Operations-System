package com.ugmc.smartops.datastructure;

import java.util.NoSuchElementException;

/**
 * A custom map keyed by a Comparable key, backed by a BinarySearchTree.
 * Used for indexes and lookups where ordered iteration is useful.
 *
 * @param <K> key type (must be Comparable)
 * @param <V> value type
 * @author UGMC Smart Operations Team
 */
public class Map<K extends Comparable<K>, V> {

    private final BinarySearchTree<K, V> tree;

    public Map() {
        this.tree = new BinarySearchTree<>();
    }

    /** Inserts or updates the value for the given key. */
    public void put(K key, V value) {
        tree.put(key, value);
    }

    /** Returns the value for the given key, or null if absent. */
    public V get(K key) {
        return tree.get(key);
    }

    /** Returns true if the key is present. */
    public boolean containsKey(K key) {
        return tree.containsKey(key);
    }

    /** Removes the given key; returns true if present. */
    public boolean remove(K key) {
        return tree.remove(key);
    }

    /** Returns the smallest key. */
    public K firstKey() {
        if (tree.isEmpty()) throw new NoSuchElementException("Map is empty");
        return tree.minKey();
    }

    /** Returns the largest key. */
    public K lastKey() {
        if (tree.isEmpty()) throw new NoSuchElementException("Map is empty");
        return tree.maxKey();
    }

    /** Performs an in-order traversal of the map. */
    public void forEach(BinarySearchTree.Visitor<K, V> visitor) {
        tree.inorder(visitor);
    }

    public int size() { return tree.size(); }
    public boolean isEmpty() { return tree.isEmpty(); }
    public void clear() { tree.clear(); }

    @Override
    public String toString() { return tree.toString(); }
}

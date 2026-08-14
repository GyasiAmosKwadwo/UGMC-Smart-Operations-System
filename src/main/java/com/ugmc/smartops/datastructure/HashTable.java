package com.ugmc.smartops.datastructure;

import java.util.Objects;

/**
 * A custom hash table with separate chaining for collision handling.
 * Implemented from scratch (no java.util.HashMap).
 *
 * Supports: put, get, remove, containsKey, and collision statistics.
 *
 * @param <K> key type
 * @param <V> value type
 * @author UGMC Smart Operations Team
 */
public class HashTable<K, V> {

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K, V>[] buckets;
    private int size;
    private int tableSize;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.tableSize = initialCapacity;
        this.buckets = (Entry<K, V>[]) new Entry[initialCapacity];
        this.size = 0;
    }

    public HashTable() {
        this(16);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % tableSize;
    }

    /** Inserts or updates the value for the given key. */
    public void put(K key, V value) {
        Objects.requireNonNull(key, "Key cannot be null");
        int index = hash(key);
        Entry<K, V> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                current.value = value;
                return;
            }
            current = current.next;
        }
        Entry<K, V> entry = new Entry<>(key, value);
        entry.next = buckets[index];
        buckets[index] = entry;
        size++;
        if ((double) size / tableSize > DEFAULT_LOAD_FACTOR) {
            resize(tableSize * 2);
        }
    }

    /** Returns the value for the given key, or null if absent. */
    public V get(K key) {
        Objects.requireNonNull(key, "Key cannot be null");
        int index = hash(key);
        Entry<K, V> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /** Returns true if the key is present. */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /** Removes the key; returns the removed value or null if absent. */
    public V remove(K key) {
        Objects.requireNonNull(key, "Key cannot be null");
        int index = hash(key);
        Entry<K, V> prev = null;
        Entry<K, V> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Entry<K, V>[] old = buckets;
        buckets = (Entry<K, V>[]) new Entry[newCapacity];
        tableSize = newCapacity;
        size = 0;
        for (Entry<K, V> head : old) {
            Entry<K, V> current = head;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    /** Returns the number of non-empty buckets (used for collision statistics). */
    public int occupiedBuckets() {
        int count = 0;
        for (Entry<K, V> head : buckets) {
            if (head != null) count++;
        }
        return count;
    }

    /** Returns the number of collisions (entries beyond the first in each bucket). */
    public int collisionCount() {
        int collisions = 0;
        for (Entry<K, V> head : buckets) {
            if (head != null) {
                Entry<K, V> c = head.next;
                while (c != null) {
                    collisions++;
                    c = c.next;
                }
            }
        }
        return collisions;
    }

/** Returns all keys as a DynamicArray (snapshot). */
    public DynamicArray<K> keys() {
        DynamicArray<K> result = new DynamicArray<>(size);
        for (Entry<K, V> head : buckets) {
            Entry<K, V> c = head;
            while (c != null) {
                result.add(c.key);
                c = c.next;
            }
        }
        return result;
    }

    public double loadFactor() {
        return (double) size / tableSize;
    }

    public int tableSize() { return tableSize; }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void clear() {
        for (int i = 0; i < tableSize; i++) {
            buckets[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (Entry<K, V> head : buckets) {
            Entry<K, V> c = head;
            while (c != null) {
                if (!first) sb.append(", ");
                sb.append(c.key).append("=").append(c.value);
                first = false;
                c = c.next;
            }
        }
        return sb.append("}").toString();
    }
}

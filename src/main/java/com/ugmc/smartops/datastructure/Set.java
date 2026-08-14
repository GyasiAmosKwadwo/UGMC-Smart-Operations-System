package com.ugmc.smartops.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A custom set implemented on top of a hash table (backed by HashTable<K,V>).
 * Duplicate elements are prevented automatically. Membership is O(1) average.
 *
 * @param <T> element type
 * @author UGMC Smart Operations Team
 */
public class Set<T> implements CustomCollection<T> {

    // Backed by our custom hash table (no java.util.HashSet).
    private final HashTable<T, T> table;

    public Set() {
        this.table = new HashTable<>();
    }

    public Set(int initialCapacity) {
        this.table = new HashTable<>(initialCapacity);
    }

    /** Adds an element; returns true if it was newly added. */
    public boolean add(T element) {
        if (table.containsKey(element)) {
            return false;
        }
        table.put(element, element);
        return true;
    }

    /** Returns true if the element is present (membership lookup). */
    public boolean contains(T element) {
        return table.containsKey(element);
    }

    /** Removes an element; returns true if it was present. */
    public boolean remove(T element) {
        return table.remove(element) != null;
    }

    @Override
    public int size() { return table.size(); }

    @Override
    public boolean isEmpty() { return table.isEmpty(); }

    @Override
    public void clear() { table.clear(); }

    @Override
    public Iterator<T> iterator() {
        // Snapshot of keys so iteration is safe even if the set is mutated.
        DynamicArray<T> keys = table.keys();
        return new Iterator<T>() {
            private int cursor = 0;
            @Override public boolean hasNext() { return cursor < keys.size(); }
            @Override public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return keys.get(cursor++);
            }
        };
    }

    @Override
    public String toString() {
        return table.keys().toString();
    }
}

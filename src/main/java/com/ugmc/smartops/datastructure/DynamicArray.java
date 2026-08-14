package com.ugmc.smartops.datastructure;

/**
 * A custom dynamic array (array-backed list) with automatic resizing.
 * Implemented from scratch (no java.util.ArrayList).
 *
 * Supported operations: insert/get/set/remove/resize.
 *
 * @param <T> element type
 * @author UGMC Smart Operations Team
 */
public class DynamicArray<T> implements CustomCollection<T> {

    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private static final int RESIZE_FACTOR = 2;

    public DynamicArray() {
        this(DEFAULT_CAPACITY);
    }

    public DynamicArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative: " + initialCapacity);
        }
        this.data = new Object[initialCapacity];
        this.size = 0;
    }

    /** Appends an element to the end. */
    public void add(T element) {
        ensureCapacity(size + 1);
        data[size++] = element;
    }

    /** Inserts an element at the given index, shifting subsequent elements right. */
    public void insert(int index, T element) {
        checkIndexForInsert(index);
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }

    /** Returns the element at the given index. */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    /** Replaces the element at the given index and returns the old value. */
    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        checkIndex(index);
        T old = (T) data[index];
        data[index] = element;
        return old;
    }

    /** Removes and returns the element at the given index. */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T removed = (T) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null; // allow GC
        return removed;
    }

    /** Returns the index of the first occurrence of an element, or -1. */
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (java.util.Objects.equals(data[i], element)) {
                return i;
            }
        }
        return -1;
    }

    /** Returns true if the given element is present. */
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    /** Grows the backing array when needed. */
    private void ensureCapacity(int required) {
        if (required > data.length) {
            int newCapacity = Math.max(required, data.length * RESIZE_FACTOR);
            if (newCapacity == 0) {
                newCapacity = DEFAULT_CAPACITY;
            }
            resize(newCapacity);
        }
    }

    /** Allocates a new backing array of the given capacity and copies contents. */
    private void resize(int newCapacity) {
        Object[] newData = new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        this.data = newData;
    }

    /** Returns the length of the backing array (capacity), not the element count. */
    public int capacity() {
        return data.length;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
    }

    private void checkIndexForInsert(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Insert index " + index + " out of bounds for size " + size);
        }
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private int cursor = 0;
            @Override public boolean hasNext() { return cursor < size; }
            @Override public T next() { return get(cursor++); }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) sb.append(", ");
            sb.append(data[i]);
        }
        return sb.append("]").toString();
    }
}

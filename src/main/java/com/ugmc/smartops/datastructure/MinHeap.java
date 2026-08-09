package com.ugmc.smartops.datastructure;

import java.util.NoSuchElementException;

/**
 * A custom binary min-heap used as a priority queue for service dispatch.
 * Implemented from scratch on a dynamic array (no java.util.PriorityQueue).
 *
 * Supports: insert, extractMin, peek, heapify.
 *
 * @param <T> element type (must be Comparable)
 * @author UGMC Smart Operations Team
 */
public class MinHeap<T extends Comparable<T>> implements CustomCollection<T> {

    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;

    public MinHeap() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /** Inserts an element and restores the heap property. */
    public void insert(T element) {
        ensureCapacity(size + 1);
        data[size] = element;
        size++;
        siftUp(size - 1);
    }

    /** Removes and returns the minimum element. */
    @SuppressWarnings("unchecked")
    public T extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        T min = (T) data[0];
        data[0] = data[size - 1];
        data[size - 1] = null;
        size--;
        if (size > 0) {
            siftDown(0);
        }
        return min;
    }

    /** Returns the minimum element without removing it. */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }
        return (T) data[0];
    }

    /** Builds a heap from an existing array of elements (heapify). */
    public void heapify(T[] elements) {
        data = new Object[Math.max(DEFAULT_CAPACITY, elements.length)];
        size = elements.length;
        for (int i = 0; i < elements.length; i++) {
            data[i] = elements[i];
        }
        for (int i = (size / 2) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    @SuppressWarnings("unchecked")
    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (((Comparable<T>) data[parent]).compareTo((T) data[index]) <= 0) {
                break;
            }
            swap(parent, index);
            index = parent;
        }
    }

    @SuppressWarnings("unchecked")
    private void siftDown(int index) {
        int half = size / 2;
        while (index < half) {
            int left = 2 * index + 1;
            int right = left + 1;
            int smallest = left;
            if (right < size
                    && ((Comparable<T>) data[right]).compareTo((T) data[left]) < 0) {
                smallest = right;
            }
            if (((Comparable<T>) data[index]).compareTo((T) data[smallest]) <= 0) {
                break;
            }
            swap(index, smallest);
            index = smallest;
        }
    }

    private void swap(int i, int j) {
        Object tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    private void ensureCapacity(int required) {
        if (required > data.length) {
            Object[] newData = new Object[data.length * 2];
            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }
            data = newData;
        }
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

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private int cursor = 0;
            @Override public boolean hasNext() { return cursor < size; }
            @Override public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                @SuppressWarnings("unchecked")
                T val = (T) data[cursor++];
                return val;
            }
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

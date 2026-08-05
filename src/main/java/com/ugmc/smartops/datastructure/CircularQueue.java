package com.ugmc.smartops.datastructure;

import java.util.NoSuchElementException;

/**
 * A custom circular queue (ring buffer) implemented on an array, with
 * wrap-around handling for full/empty states.
 *
 * @param <T> element type
 * @author UGMC Smart Operations Team
 */
public class CircularQueue<T> implements CustomCollection<T> {

    private Object[] data;
    private int front;
    private int rear;   // index of next insertion slot
    private int size;
    private final int capacity;

    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive: " + capacity);
        }
        this.capacity = capacity;
        this.data = new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    /** Adds an element at the rear, wrapping around when needed. */
    public void enqueue(T element) {
        if (isFull()) {
            throw new IllegalStateException("Circular queue is full");
        }
        data[rear] = element;
        rear = (rear + 1) % capacity;
        size++;
    }

    /** Removes and returns the front element, advancing the front pointer. */
    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Circular queue is empty");
        }
        T result = (T) data[front];
        data[front] = null;
        front = (front + 1) % capacity;
        size--;
        return result;
    }

    /** Returns the front element without removing it. */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Circular queue is empty");
        }
        return (T) data[front];
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int capacity() {
        return capacity;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            data[i] = null;
        }
        front = rear = size = 0;
    }

    @Override
    public java.util.Iterator<T> iterator() {
        return new java.util.Iterator<T>() {
            private int count = 0;
            @Override public boolean hasNext() { return count < size; }
            @Override public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                @SuppressWarnings("unchecked")
                T val = (T) data[(front + count) % capacity];
                count++;
                return val;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int count = 0;
        while (count < size) {
            if (count > 0) sb.append(", ");
            sb.append(data[(front + count) % capacity]);
            count++;
        }
        return sb.append("]").toString();
    }
}

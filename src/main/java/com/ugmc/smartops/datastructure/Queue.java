package com.ugmc.smartops.datastructure;

import java.util.NoSuchElementException;

/**
 * A custom FIFO queue backed by our linked list.
 * FIFO dispatch rule for service requests.
 *
 * @param <T> element type
 * @author UGMC Smart Operations Team
 */
public class Queue<T> implements CustomCollection<T> {

    private final LinkedList<T> list;

    public Queue() {
        this.list = new LinkedList<>();
    }

    /** Adds an element to the rear of the queue. */
    public void enqueue(T element) {
        list.addLast(element);
    }

    /** Removes and returns the element at the front of the queue. */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.pollFirst();
    }

    /** Returns the front element without removing it. */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.peekFirst();
    }

    @Override
    public int size() { return list.size(); }

    @Override
    public boolean isEmpty() { return list.isEmpty(); }

    @Override
    public void clear() { list.clear(); }

    @Override
    public java.util.Iterator<T> iterator() { return list.iterator(); }

    @Override
    public String toString() { return list.toString(); }
}

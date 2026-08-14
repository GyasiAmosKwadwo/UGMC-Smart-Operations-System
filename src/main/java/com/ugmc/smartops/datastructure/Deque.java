package com.ugmc.smartops.datastructure;

import java.util.NoSuchElementException;

/**
 * A custom double-ended queue (deque) supporting insertion and removal at
 * both the front and the rear. Used for urgent-request insertion.
 *
 * @param <T> element type
 * @author UGMC Smart Operations Team
 */
public class Deque<T> implements CustomCollection<T> {

    private final LinkedList<T> list;

    public Deque() {
        this.list = new LinkedList<>();
    }

    /** Adds an element at the front. */
    public void addFront(T element) {
        list.addFirst(element);
    }

    /** Adds an element at the rear. */
    public void addRear(T element) {
        list.addLast(element);
    }

    /** Removes and returns the front element. */
    public T removeFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        return list.pollFirst();
    }

    /** Removes and returns the rear element. */
    public T removeRear() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
        }
        // Remove last element by iterating to the node before tail.
        T result = null;
        java.util.Iterator<T> it = list.iterator();
        T prev = null;
        T current = null;
        while (it.hasNext()) {
            prev = current;
            current = it.next();
        }
        result = current;
        if (prev == null) {
            list.pollFirst();
        } else {
            list.remove(current);
        }
        return result;
    }

    /** Returns the front element without removing it. */
    public T peekFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("Deque is empty");
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

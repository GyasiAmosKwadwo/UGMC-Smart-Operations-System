package com.ugmc.smartops.datastructure;

import java.util.NoSuchElementException;

/**
 * A custom LIFO stack implemented from scratch.
 * Used for undo/audit logs and recursion simulation.
 *
 * @param <T> element type
 * @author UGMC Smart Operations Team
 */
public class Stack<T> implements CustomCollection<T> {

    // Backed by our custom linked list.
    private final LinkedList<T> list;

    public Stack() {
        this.list = new LinkedList<>();
    }

    /** Pushes an element onto the top of the stack. */
    public void push(T element) {
        list.addFirst(element);
    }

    /** Removes and returns the top element. */
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return list.pollFirst();
    }

    /** Returns the top element without removing it. */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
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

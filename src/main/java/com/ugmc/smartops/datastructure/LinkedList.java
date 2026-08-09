package com.ugmc.smartops.datastructure;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A custom singly linked list implemented from scratch.
 *
 * Supported operations: addFirst, addLast, insertAfter, remove, size, iterator.
 *
 * @param <T> element type
 * @author UGMC Smart Operations Team
 */
public class LinkedList<T> implements CustomCollection<T> {

    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    /** Adds an element at the front of the list. */
    public void addFirst(T element) {
        Node<T> node = new Node<>(element);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    /** Adds an element at the end of the list. */
    public void addLast(T element) {
        Node<T> node = new Node<>(element);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /** Inserts a new element immediately after the node containing {@code after}. */
    public void insertAfter(T after, T element) {
        Node<T> current = head;
        while (current != null) {
            if (java.util.Objects.equals(current.data, after)) {
                Node<T> node = new Node<>(element);
                node.next = current.next;
                current.next = node;
                if (current == tail) {
                    tail = node;
                }
                size++;
                return;
            }
            current = current.next;
        }
        throw new NoSuchElementException("Element not found in list: " + after);
    }

    /** Removes the first occurrence of the given element; returns true if removed. */
    public boolean remove(T element) {
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            if (java.util.Objects.equals(current.data, element)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                if (current == tail) {
                    tail = prev;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false;
    }

    /** Returns the first element without removing it. */
    public T peekFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }

    /** Removes and returns the first element. */
    public T pollFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        T result = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return result;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;
            @Override public boolean hasNext() { return current != null; }
            @Override public T next() {
                if (current == null) {
                    throw new NoSuchElementException();
                }
                T val = current.data;
                current = current.next;
                return val;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        boolean first = true;
        while (current != null) {
            if (!first) sb.append(", ");
            sb.append(current.data);
            first = false;
            current = current.next;
        }
        return sb.append("]").toString();
    }
}

package com.ugmc.smartops.datastructure;

import java.util.Iterator;

/**
 * Core custom-collection interface implemented by all assessed custom
 * data structures. Built-in Java collections (HashMap, TreeMap, PriorityQueue,
 * Stack, ArrayDeque) are NOT allowed for this assessed logic.
 *
 * @param <T> element type
 * @author UGMC Smart Operations Team
 */
public interface CustomCollection<T> extends Iterable<T> {

    /** Returns the number of elements currently held. */
    int size();

    /** Returns true if the collection holds no elements. */
    boolean isEmpty();

    /** Removes all elements. */
    void clear();

    /** Returns an iterator over the elements. */
    @Override
    Iterator<T> iterator();
}

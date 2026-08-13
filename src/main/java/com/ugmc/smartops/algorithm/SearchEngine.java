package com.ugmc.smartops.algorithm;

/**
 * Custom searching algorithms implemented from scratch.
 * Provides linear search and binary search (with sorted precondition).
 *
 * @author UGMC Smart Operations Team
 */
public final class SearchEngine {

    private SearchEngine() {
    }

    /**
     * Linear search over a generic array. Returns the index of the target
     * or -1 if not found. Works on unsorted data. O(n).
     */
    public static <T extends Comparable<T>> int linearSearch(T[] data, T target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].compareTo(target) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Binary search over a SORTED array. Precondition: {@code data} must be
     * sorted in ascending order. Returns index or -1. O(log n).
     */
    public static <T extends Comparable<T>> int binarySearch(T[] data, T target) {
        int lo = 0;
        int hi = data.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = data[mid].compareTo(target);
            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Verifies that a generic array is sorted ascending. Used to test the
     * binary-search precondition (and to demonstrate the unsorted-input failure).
     */
    public static <T extends Comparable<T>> boolean isSorted(T[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i - 1].compareTo(data[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}

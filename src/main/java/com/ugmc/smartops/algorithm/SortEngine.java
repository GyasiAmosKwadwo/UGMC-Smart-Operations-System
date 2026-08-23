package com.ugmc.smartops.algorithm;

/**
 * Custom sorting algorithms implemented from scratch.
 * Provides selection sort, insertion sort, merge sort and quicksort.
 *
 * @author UGMC Smart Operations Team
 */
public final class SortEngine {

    private SortEngine() {
    }

    // --- Selection sort: O(n^2), in-place, NOT stable ---
    // Grows a sorted region on the left, one element at a time, by
    // repeatedly finding the smallest value still in the unsorted
    // remainder and swapping it into the next open slot.
    public static <T extends Comparable<T>> void selectionSort(T[] arr) {
        int boundary = 0;
        while (boundary < arr.length - 1) {
            int minIndex = boundary;
            int k = boundary + 1;
            while (k < arr.length) {
                if (arr[k].compareTo(arr[minIndex]) < 0) {
                    minIndex = k;
                }
                k++;
            }
            if (minIndex != boundary) {
                swap(arr, boundary, minIndex);
            }
            boundary++;
        }
    }

    // --- Insertion sort: O(n^2), in-place, stable ---
    public static <T extends Comparable<T>> void insertionSort(T[] a) {
        for (int i = 1; i < a.length; i++) {
            T key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j].compareTo(key) > 0) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    // --- Merge sort: O(n log n), stable ---
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void mergeSort(T[] a) {
        if (a.length <= 1) return;
        T[] aux = (T[]) new Comparable[a.length];
        mergeSort(a, aux, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(T[] a, T[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static <T extends Comparable<T>> void merge(T[] a, T[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (aux[j].compareTo(aux[i]) < 0) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    // --- Quicksort: O(n log n) average, in-place, NOT stable ---
    public static <T extends Comparable<T>> void quickSort(T[] a) {
        quickSort(a, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] a, int lo, int hi) {
        if (lo >= hi) return;
        int pivot = partition(a, lo, hi);
        quickSort(a, lo, pivot - 1);
        quickSort(a, pivot + 1, hi);
    }

    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        T pivot = a[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (a[j].compareTo(pivot) <= 0) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, hi);
        return i + 1;
    }

    private static <T extends Comparable<T>> void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

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
    public static <T extends Comparable<T>> void selectionSort(T[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            swap(a, i, minIdx);
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
        if (a.length <= 1) {
            return;
        }

        T[] temp = (T[]) new Comparable[a.length];
        mergeSort(a, temp, 0, a.length - 1);
    }

    private static <T extends Comparable<T>> void mergeSort(
            T[] a, T[] temp, int left, int right) {

        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;

        mergeSort(a, temp, left, middle);
        mergeSort(a, temp, middle + 1, right);

        merge(a, temp, left, middle, right);
    }

    private static <T extends Comparable<T>> void merge(
            T[] a, T[] temp, int left, int middle, int right) {

        int i = left;
        int j = middle + 1;

        for (int k = left; k <= right; k++) {
            temp[k] = a[k];
        }

        for (int k = left; k <= right; k++) {

            if (i > middle) {
                a[k] = temp[j++];
            }
            else if (j > right) {
                a[k] = temp[i++];
            }
            else if (temp[i].compareTo(temp[j]) <= 0) {
                a[k] = temp[i++];
            }
            else {
                a[k] = temp[j++];
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

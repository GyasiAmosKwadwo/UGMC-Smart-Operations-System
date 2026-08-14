package com.ugmc.smartops.algorithm;

public final class SearchEngine {

    private SearchEngine() {
    }

    public static <T extends Comparable<T>> int linearSearch(T[] data, T target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].compareTo(target) == 0) {
                return i;
            }
        }
        return -1;
    }

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

    public static <T extends Comparable<T>> boolean isSorted(T[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i - 1].compareTo(data[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}

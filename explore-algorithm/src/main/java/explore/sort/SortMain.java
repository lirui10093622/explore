package explore.sort;

import explore.sort.algorithm.FastSort;

import java.util.Arrays;

public class SortMain {

    private static final int[] array = new int[]{9, 10, 11, 12, 13, 14, 15, 16, 1, 2, 3, 4, 5, 6, 7, 8};

    public static void main(String[] args) {
        FastSort.sort(array);
        System.out.printf(Arrays.toString(array));
    }
}
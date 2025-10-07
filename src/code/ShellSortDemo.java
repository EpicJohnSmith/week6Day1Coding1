package code;

import java.util.*;  // Needed for Random and Arrays

public class ShellSortDemo {

    public static void main(String[] args) {
        int size = 10000;  // Array size for testing

        // Create a random array with values 0–9999
        int[] arr1 = new Random().ints(size, 0, 10000).toArray();
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);  // Copy for fair timing

        // --- Benchmark Insertion Sort ---
        long start = System.nanoTime();
        insertionSort(arr1);
        long end = System.nanoTime();
        System.out.println("Insertion Sort time: " + (end - start) / 1e6 + " ms");

        // --- Benchmark Shell Sort ---
        start = System.nanoTime();
        ShellSort(arr2);  // Uses automatically calculated intervals
        end = System.nanoTime();
        System.out.println("Shell Sort time: " + (end - start) / 1e6 + " ms");
    }

    // ------------------------------------------------------------
    //  Standard Insertion Sort
    // ------------------------------------------------------------
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            // Move elements greater than key one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;  // Insert key into correct position
        }
    }

    // ------------------------------------------------------------
    //  Shell Sort: Manual intervals version
    // ------------------------------------------------------------
    public static int[] ShellSort(int[] arr, int[] intervals) {
        for (int gap : intervals) {
            insertionSortByGap(arr, gap);
        }
        return arr;
    }

    // ------------------------------------------------------------
    //  Shell Sort: Automatic intervals version
    // ------------------------------------------------------------
    public static int[] ShellSort(int[] arr) {
        int n = arr.length;
        int power = 1;

        // Find n such that 2^(power+1) >= n > 2^power
        while (Math.pow(2, power + 1) < n) {
            power++;
        }

        // Build the intervals array: [2^power, 2^(power-1), ..., 1]
        int[] intervals = new int[power + 1];
        for (int i = 0; i <= power; i++) {
            intervals[i] = (int) Math.pow(2, power - i);
        }

        // Run Shell Sort using the computed intervals
        return ShellSort(arr, intervals);
    }

    // ------------------------------------------------------------
    //  Helper: Insertion Sort for elements spaced by 'gap'
    // ------------------------------------------------------------
    public static void insertionSortByGap(int[] arr, int gap) {
        // Start from the element at index = gap and iterate through array
        for (int i = gap; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;

            // Compare elements gap apart and shift until correct place found
            while (j >= gap && arr[j - gap] > temp) {
                arr[j] = arr[j - gap];
                j -= gap;
            }

            // Place element in its correct position within this subarray
            arr[j] = temp;
        }
    }
}

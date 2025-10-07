package code;

public class ShellSort {
	
	
	
	public static void insertionSortByGap(int[] arr, int gap) {
	    for (int i = gap; i < arr.length; i++) {
	        int temp = arr[i];
	        int j = i;
	        while (j >= gap && arr[j - gap] > temp) {
	            arr[j] = arr[j - gap];
	            j -= gap;
	        }
	        arr[j] = temp;
	    }
	}

	
	public static int[] ShellSort(int[] arr) {
	    int n = arr.length;
	    
	    // Find n such that 2^(n+1) >= arr.length > 2^n
	    int power = 1;
	    while (Math.pow(2, power + 1) < n) {
	        power++;
	    }

	    // Build the intervals array [2^power, 2^(power-1), ..., 1]
	    int[] intervals = new int[power + 1];
	    for (int i = 0; i <= power; i++) {
	        intervals[i] = (int) Math.pow(2, power - i);
	    }

	    // Run shell sort with generated intervals
	    return ShellSort(arr, intervals);
	}

	
	import java.util.*;

	public class ShellSortDemo {

	    public static void main(String[] args) {
	        int size = 10000;
	        int[] arr1 = new Random().ints(size, 0, 10000).toArray();
	        int[] arr2 = Arrays.copyOf(arr1, arr1.length);

	        // Benchmark insertion sort
	        long start = System.nanoTime();
	        insertionSort(arr1);
	        long end = System.nanoTime();
	        System.out.println("Insertion Sort time: " + (end - start) / 1e6 + " ms");

	        // Benchmark shell sort
	        start = System.nanoTime();
	        ShellSort(arr2);
	        end = System.nanoTime();
	        System.out.println("Shell Sort time: " + (end - start) / 1e6 + " ms");
	    }

	    // Standard insertion sort
	    public static void insertionSort(int[] arr) {
	        for (int i = 1; i < arr.length; i++) {
	            int key = arr[i];
	            int j = i - 1;
	            while (j >= 0 && arr[j] > key) {
	                arr[j + 1] = arr[j];
	                j--;
	            }
	            arr[j + 1] = key;
	        }
	    }

	    // Helper for shell sort
	    public static void insertionSortByGap(int[] arr, int gap) {
	        for (int i = gap; i < arr.length; i++) {
	            int temp = arr[i];
	            int j = i;
	            while (j >= gap && arr[j - gap] > temp) {
	                arr[j] = arr[j - gap];
	                j -= gap;
	            }
	            arr[j] = temp;
	        }
	    }

	    public static int[] ShellSort(int[] arr, int[] intervals) {
	        for (int gap : intervals) {
	            insertionSortByGap(arr, gap);
	        }
	        return arr;
	    }

	    public static int[] ShellSort(int[] arr) {
	        int n = arr.length;
	        int power = 1;
	        while (Math.pow(2, power + 1) < n) {
	            power++;
	        }

	        int[] intervals = new int[power + 1];
	        for (int i = 0; i <= power; i++) {
	            intervals[i] = (int) Math.pow(2, power - i);
	        }

	        return ShellSort(arr, intervals);
	    }
	}


}

package code;

import java.util.*;

public class ShellSortDemo
{

    public static void main(String[] args)
    {
        int size = 10000;  // Array size for testing like we always do for the benchmarks.

        int[] arr1 = new Random().ints(size, 0, 10000).toArray();
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);

        long start = System.nanoTime();
        insertionSort(arr1);
        long end = System.nanoTime();
        System.out.println("At array size " + size + ", Insertion Sort took " + (end - start) / 1e6 + " ms");

        start = System.nanoTime();
        ShellSort(arr2);
        end = System.nanoTime();
        System.out.println("At array size " + size + ", Shell Sort took " + (end - start) / 1e6 + " ms");
    }

    //  Standard Insertion Sort done by AI. I think this makes sense with the key and such.
    public static void insertionSort(int[] arr) 
    {
        for (int i = 1; i < arr.length; i++) 
        {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) 
            {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    //  Shell Sort: Manual intervals version as seen by AI documentation with the instruction help
    public static int[] ShellSort(int[] arr, int[] intervals)
    {
        for (int gap : intervals)
        {
            insertionSortByGap(arr, gap); // why is the gap here? Is this what the shell sort has for itself?
        }
        return arr;
    }

    //  Shell Sort: Automatic intervals version with instructions
    public static int[] ShellSort(int[] arr)
    {
        int n = arr.length;
        int power = 1;

        // Find n such that 2^(power+1) >= n > 2^power as seen by the instructions
        while (Math.pow(2, power + 1) < n) // WHAT IS THE Math.pow? Is this some kind of sorcery?
        {
            power++;
        }

        // Build the intervals array: [2^power, 2^(power-1), ..., 1] blah blah blah, I get it, I think.
        int[] intervals = new int[power + 1];
        for (int i = 0; i <= power; i++)
        {
            intervals[i] = (int) Math.pow(2, power - i);
        }

        // Run Shell Sort using the computed intervals
        return ShellSort(arr, intervals);
    }

    //  Helper: Insertion Sort for elements spaced by 'gap'
    public static void insertionSortByGap(int[] arr, int gap) // Not exactly sure why AI wants the insertion by gap...but okay.
    {
        // Start from the element at index = gap and iterate through array
        for (int i = gap; i < arr.length; i++)
        {
            int temp = arr[i];
            int j = i;

            // Compare elements gap apart and shift until correct place found
            while (j >= gap && arr[j - gap] > temp)
            {
                arr[j] = arr[j - gap];
                j -= gap;
            }

            arr[j] = temp;
        }
    }
}

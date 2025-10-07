package code;

public class ShellSort
{

    // Performs insertion sort on elements separated by 'gap'
    public static void insertionSortByGap(int[] arr, int gap) {
        // Start from the first element in each subarray (defined by gap)
        for (int i = gap; i < arr.length; i++) {
            int temp = arr[i];  // element to be inserted in correct position
            int j = i;

            // Shift earlier gap-sorted elements up until correct location found
            while (j >= gap && arr[j - gap] > temp) {
                arr[j] = arr[j - gap];
                j -= gap;
            }
            arr[j] = temp;  // place element in correct location
        }
    }

    // Shell sort using a given set of intervals (gaps)
    public static int[] ShellSort(int[] arr, int[] intervals) {
        for (int gap : intervals) {
            insertionSortByGap(arr, gap); // apply insertion sort with each gap
        }
        return arr;
    }

    // Shell sort that automatically calculates the intervals based on array length
    public static int[] ShellSort(int[] arr) {
        int n = arr.length;
        int power = 1;

        // Find n such that 2^(power+1) >= n > 2^power
        while (Math.pow(2, power + 1) < n) {
            power++;
        }

        // Build intervals array: [2^power, 2^(power-1), ..., 1]
        int[] intervals = new int[power + 1];
        for (int i = 0; i <= power; i++) {
            intervals[i] = (int) Math.pow(2, power - i);
        }

        // Perform shell sort with computed gaps
        return ShellSort(arr, intervals);
    }
}

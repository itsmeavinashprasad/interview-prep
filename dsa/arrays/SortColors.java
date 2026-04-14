package dsa.arrays;

import java.util.Arrays;

/**
 * <h1>75. Sort Colors</h1>
 *
 * <p>
 * Given an array {@code nums} with {@code n} objects colored red, white, or
 * blue,
 * sort them <b>in-place</b> so that objects of the same color are adjacent,
 * with the colors in the order red, white, and blue.
 * </p>
 * <p>
 * We will use the integers {@code 0}, {@code 1}, and {@code 2} to represent
 * the color red, white, and blue, respectively.
 * </p>
 * <p>
 * You must solve this problem without using the library's sort function.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> nums = [2,0,2,1,1,0]
 * <b>Output:</b> [0,0,1,1,2,2]
 *
 * <b>Input:</b> nums = [2,0,1]
 * <b>Output:</b> [0,1,2]
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code n == nums.length}</li>
 * <li>{@code 1 <= n <= 300}</li>
 * <li>{@code nums[i]} is either {@code 0}, {@code 1}, or {@code 2}.</li>
 * </ul>
 *
 * <h2>Follow up:</h2>
 * <ul>
 * <li>Could you come up with a one-pass algorithm using only constant extra
 * space?</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/sort-colors/">LeetCode - Sort
 *      Colors</a>
 */
public class SortColors {

    public void sortColors2Paas(int[] nums) {
        // TODO: implement
        int zeros = 0;
        int ones = 0;
        int twos = 0;
        for (int x : nums) {
            switch (x) {
                case 0 -> zeros++;
                case 1 -> ones++;
                case 2 -> twos++;
            }
        }
        int i = 0;
        for (; i < zeros; i++) {
            nums[i] = 0;
        }
        for (; i < zeros + ones; i++) {
            nums[i] = 1;
        }
        for (; i < nums.length; i++) {
            nums[i] = 2;
        }

    }

    public void sortColors1Paas(int[] nums) {
        // TODO: implement
        int n = nums.length;
        // index before which 0s should be there
        int l = 0;
        // index after which 2s should be there
        int r = n - 1;

        // iterate till r, because once arranged, after r everything will be sorted
        for (int i = 0; i <= r;) {
            int x = nums[i];
            if (x == 0) {
                // swap with l, increment l and i
                swap(nums, i, l);
                l++;
                i++;
            } else if (x == 2) {
                // swap with r, decrement r
                swap(nums, i, r);
                r--;
            } else {
                // increment i
                i++;
            }
        }

    }

    private void swap(int[] arr, int i, int j) {
        if (arr[i] == arr[j])
            return;

        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        SortColors solver = new SortColors();

        // Test Case 1: [2,0,2,1,1,0] → Expected: [0,0,1,1,2,2]
        int[] nums1 = { 2, 0, 2, 1, 1, 0 };
        System.out.println("Test 1 - Input: " + Arrays.toString(nums1));
        solver.sortColors1Paas(nums1);
        System.out.println("Output:   " + Arrays.toString(nums1));
        System.out.println("Expected: [0, 0, 1, 1, 2, 2]");

        // Test Case 2: [2,0,1] → Expected: [0,1,2]
        int[] nums2 = { 2, 0, 1 };
        System.out.println("\nTest 2 - Input: " + Arrays.toString(nums2));
        solver.sortColors1Paas(nums2);
        System.out.println("Output:   " + Arrays.toString(nums2));
        System.out.println("Expected: [0, 1, 2]");
    }
}

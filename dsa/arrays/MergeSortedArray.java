package dsa.arrays;

import java.util.Arrays;

/**
 * <h1>88. Merge Sorted Array</h1>
 *
 * <p>
 * You are given two integer arrays {@code nums1} and {@code nums2}, sorted in
 * <b>non-decreasing order</b>, and two integers {@code m} and {@code n},
 * representing
 * the number of elements in {@code nums1} and {@code nums2} respectively.
 * </p>
 * <p>
 * <b>Merge</b> {@code nums1} and {@code nums2} into a single array sorted in
 * <b>non-decreasing order</b>.
 * </p>
 * <p>
 * The final sorted array should not be returned by the function, but instead be
 * stored inside the array {@code nums1}. To accommodate this, {@code nums1} has
 * a
 * length of {@code m + n}, where the first {@code m} elements denote the
 * elements
 * that should be merged, and the last {@code n} elements are set to {@code 0}
 * and
 * should be ignored. {@code nums2} has a length of {@code n}.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * <b>Output:</b> [1,2,2,3,5,6]
 * <b>Explanation:</b> The arrays we are merging are [1,2,3] and [2,5,6].
 * The result of the merge is [1,2,2,3,5,6] with the underlined elements coming from nums1.
 *
 * <b>Input:</b> nums1 = [1], m = 1, nums2 = [], n = 0
 * <b>Output:</b> [1]
 * <b>Explanation:</b> The arrays we are merging are [1] and [].
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code nums1.length == m + n}</li>
 * <li>{@code nums2.length == n}</li>
 * <li>{@code 0 <= m, n <= 200}</li>
 * <li>{@code 1 <= m + n <= 200}</li>
 * <li>{@code -10^9 <= nums1[i], nums2[j] <= 10^9}</li>
 * </ul>
 *
 * <h2>Follow up:</h2>
 * <ul>
 * <li>Can you come up with an algorithm that runs in O(m + n) time?</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/merge-sorted-array/">LeetCode -
 *      Merge Sorted Array</a>
 */
public class MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // TODO: implement
        // starts from end - also last valid element
        int i = m - 1;
        // starts from end - also last valid element
        int j = n - 1;
        // index to overwrite
        int k = nums1.length - 1;

        // start comparing i and j th elements
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                // shift i-th element to k-th position
                nums1[k] = nums1[i];
                k--;
                i--;
            } else {
                nums1[k] = nums2[j];
                k--;
                j--;
            }
        }

        if (i > 0) {
            // if nums1 is not yet done, then start copying
            while (i >= 0) {
                nums1[k] = nums1[i];
                i--;
                k--;
            }
        } else {
            // if nums2 is not yet done, then start copying
            while (j >= 0) {
                nums1[k] = nums2[j];
                k--;
                j--;
            }
        }

    }

    public static void main(String[] args) {
        MergeSortedArray solver = new MergeSortedArray();

        // Test Case 1
        int[] nums1_1 = { 1, 2, 3, 0, 0, 0 };
        int[] nums2_1 = { 2, 5, 6 };
        System.out.println("Test 1 - Input: nums1 = " + Arrays.toString(nums1_1) + ", m = 3, nums2 = "
                + Arrays.toString(nums2_1) + ", n = 3");
        solver.merge(nums1_1, 3, nums2_1, 3);
        System.out.println("Output:   " + Arrays.toString(nums1_1));
        System.out.println("Expected: [1, 2, 2, 3, 5, 6]");

        // Test Case 2
        int[] nums1_2 = { 1 };
        int[] nums2_2 = {};
        System.out.println("\nTest 2 - Input: nums1 = " + Arrays.toString(nums1_2) + ", m = 1, nums2 = "
                + Arrays.toString(nums2_2) + ", n = 0");
        solver.merge(nums1_2, 1, nums2_2, 0);
        System.out.println("Output:   " + Arrays.toString(nums1_2));
        System.out.println("Expected: [1]");

        // Test Case 3
        int[] nums1_3 = { 0 };
        int[] nums2_3 = { 1 };
        System.out.println("\nTest 3 - Input: nums1 = " + Arrays.toString(nums1_3) + ", m = 0, nums2 = "
                + Arrays.toString(nums2_3) + ", n = 1");
        solver.merge(nums1_3, 0, nums2_3, 1);
        System.out.println("Output:   " + Arrays.toString(nums1_3));
        System.out.println("Expected: [1]");

        // Test Case 4
        int[] nums1_4 = { 4, 5, 6, 0, 0, 0 };
        int[] nums2_4 = { 1, 2, 3 };
        System.out.println("\nTest 4 - Input: nums1 = " + Arrays.toString(nums1_4) + ", m = 3, nums2 = "
                + Arrays.toString(nums2_4) + ", n = 3");
        solver.merge(nums1_4, 3, nums2_4, 3);
        System.out.println("Output:   " + Arrays.toString(nums1_4));
        System.out.println("Expected: [1, 2, 3, 4, 5, 6]");
    }
}

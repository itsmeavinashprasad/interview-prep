package dsa.arrays;

import java.util.Arrays;

/**
 * <h1>Find the Duplicate Number</h1>
 * 
 * <p>
 * Given an array of integers {@code nums} containing {@code n + 1} integers
 * where each integer is in the range {@code [1, n]} inclusive.
 * </p>
 * 
 * <p>
 * There is only <b>one repeated number</b> in {@code nums}, return this
 * repeated number.
 * </p>
 * 
 * <p>
 * You must solve the problem <b>without modifying</b> the array {@code nums}
 * and using only <b>constant extra space</b>.
 * </p>
 * 
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> nums = [1,3,4,2,2]
 * <b>Output:</b> 2
 * 
 * <b>Input:</b> nums = [3,1,3,4,2]
 * <b>Output:</b> 3
 * 
 * <b>Input:</b> nums = [3,3,3,3,3]
 * <b>Output:</b> 3
 * </pre>
 * 
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 1 <= n <= 10^5}</li>
 * <li>{@code nums.length == n + 1}</li>
 * <li>{@code 1 <= nums[i] <= n}</li>
 * <li>All the integers in {@code nums} appear only <b>once</b> except for
 * <b>precisely one integer</b> which appears <b>two or more times</b>.</li>
 * </ul>
 * 
 * <h2>Follow up:</h2>
 * <ul>
 * <li>How can we prove that at least one duplicate number must exist in
 * {@code nums}?</li>
 * <li>Can you solve the problem in linear runtime complexity?</li>
 * </ul>
 * 
 * @see <a href=
 *      "https://leetcode.com/problems/find-the-duplicate-number/">LeetCode -
 *      Find the Duplicate Number</a>
 */
public class FindDuplicateNumber {

    public int findDuplicate(int[] nums) {
        // TODO: Implement using Floyd's Tortoise and Hare (Cycle Detection)
        for (int i = 0; i < nums.length;) {

            if (nums[i] == i + 1) {
                // element is in correct position
                i++;
                continue;
            }

            int index = nums[i] - 1;
            if (nums[index] == nums[i]) {
                // if index already has nums[i], then nums[i] is the duplicate
                return nums[i];
            }
            swap(nums, i, index);
        }

        return -1;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        FindDuplicateNumber solver = new FindDuplicateNumber();

        // Test Case 1
        int[] nums1 = { 1, 3, 4, 2, 2 };
        System.out.println("Test Case 1: " + Arrays.toString(nums1));
        System.out.println("Output: " + solver.findDuplicate(nums1));
        System.out.println("Expected: 2\n");

        // Test Case 2
        int[] nums2 = { 3, 1, 3, 4, 2 };
        System.out.println("Test Case 2: " + Arrays.toString(nums2));
        System.out.println("Output: " + solver.findDuplicate(nums2));
        System.out.println("Expected: 3\n");

        // Test Case 3
        int[] nums3 = { 3, 3, 3, 3, 3 };
        System.out.println("Test Case 3: " + Arrays.toString(nums3));
        System.out.println("Output: " + solver.findDuplicate(nums3));
        System.out.println("Expected: 3\n");
    }
}

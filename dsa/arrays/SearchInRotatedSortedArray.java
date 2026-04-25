package dsa.arrays;

import java.util.Arrays;

/**
 * <h1>33. Search in Rotated Sorted Array</h1>
 *
 * <p>
 * There is an integer array {@code nums} sorted in ascending order (with
 * distinct values). Prior to being passed to your function, {@code nums} is
 * possibly left rotated at an unknown index {@code k} ({@code 1 <= k <
 * nums.length}) such that the resulting array is
 * {@code [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]}
 * (0-indexed). For example, {@code [0,1,2,4,5,6,7]} might be left rotated by
 * {@code 3} indices and become {@code [4,5,6,7,0,1,2]}.
 * </p>
 * <p>
 * Given the array {@code nums} after the possible rotation and an integer
 * {@code target}, return the index of {@code target} if it is in
 * {@code nums}, or {@code -1} if it is not in {@code nums}.
 * </p>
 * <p>
 * You must write an algorithm with {@code O(log n)} runtime complexity.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> nums = [4,5,6,7,0,1,2], target = 0
 * <b>Output:</b> 4
 *
 * <b>Input:</b> nums = [4,5,6,7,0,1,2], target = 3
 * <b>Output:</b> -1
 *
 * <b>Input:</b> nums = [1], target = 0
 * <b>Output:</b> -1
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 1 <= nums.length <= 5000}</li>
 * <li>{@code -10^4 <= nums[i] <= 10^4}</li>
 * <li>All values of {@code nums} are unique.</li>
 * <li>{@code nums} is an ascending array that is possibly rotated.</li>
 * <li>{@code -10^4 <= target <= 10^4}</li>
 * </ul>
 *
 * <h2>Hints:</h2>
 * <ul>
 * <li>Arrays are rotated, so one half will always be sorted.</li>
 * <li>In a rotated sorted array, you can identify which half is sorted and use
 * binary search on it.</li>
 * <li>Determine if target is in the sorted half by checking range.</li>
 * </ul>
 *
 * @see <a href=
 *      "https://leetcode.com/problems/search-in-rotated-sorted-array/">LeetCode
 *      - Search in Rotated Sorted Array</a>
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        return helper(nums, 0, nums.length - 1, target);
    }

    private int helper(int[] nums, int l, int r, int t) {
        if (l > r)
            return -1;

        // otherise partition until subarray is sorted one
        int mid = (l + r) / 2;
        if (nums[mid] == t)
            return mid;

        // at one parition level either left or right subarray will be sorted
        if (nums[l] <= nums[mid]) {
            // left is sorted
            if (nums[l] <= t && t <= nums[mid]) {
                // target is also in the left subarray
                return helper(nums, l, mid, t);
            } else {
                // target is might be in right subarray => look there
                return helper(nums, mid + 1, r, t);
            }
        } else {
            // right subarray is sorted
            if (nums[mid + 1] <= t && t <= nums[r]) {
                // target is in the right subarray
                return helper(nums, mid + 1, r, t);
            } else {
                // target might be in the left subarray
                return helper(nums, l, mid, t);
            }

        }
    }

    private void printResult(String testName, int actual, int expected) {
        boolean passed = actual == expected;
        System.out.println(testName + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Output:   " + actual);
        System.out.println("  Expected: " + expected);
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray solver = new SearchInRotatedSortedArray();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Target in rotated part
        System.out.println("==================== Test Case 1 ====================");
        int[] nums1 = { 4, 5, 6, 7, 0, 1, 2 };
        int target1 = 0;
        int expected1 = 4;
        System.out.println("Scenario: Target in rotated part");
        System.out.println("Input: " + Arrays.toString(nums1) + ", Target: " + target1);
        int result1 = solver.search(nums1, target1);
        solver.printResult("Test 1 (Target in rotated)", result1, expected1);
        totalTests++;
        if (result1 == expected1)
            passedTests++;

        // Test Case 2: Target not found
        System.out.println("\n==================== Test Case 2 ====================");
        int[] nums2 = { 4, 5, 6, 7, 0, 1, 2 };
        int target2 = 3;
        int expected2 = -1;
        System.out.println("Scenario: Target not found");
        System.out.println("Input: " + Arrays.toString(nums2) + ", Target: " + target2);
        int result2 = solver.search(nums2, target2);
        solver.printResult("Test 2 (Not found)", result2, expected2);
        totalTests++;
        if (result2 == expected2)
            passedTests++;

        // Test Case 3: Single element not found
        System.out.println("\n==================== Test Case 3 ====================");
        int[] nums3 = { 1 };
        int target3 = 0;
        int expected3 = -1;
        System.out.println("Scenario: Single element not found");
        System.out.println("Input: " + Arrays.toString(nums3) + ", Target: " + target3);
        int result3 = solver.search(nums3, target3);
        solver.printResult("Test 3 (Single element)", result3, expected3);
        totalTests++;
        if (result3 == expected3)
            passedTests++;

        // Test Case 4: Target at beginning
        System.out.println("\n==================== Test Case 4 ====================");
        int[] nums4 = { 4, 5, 6, 7, 0, 1, 2 };
        int target4 = 4;
        int expected4 = 0;
        System.out.println("Scenario: Target at beginning");
        System.out.println("Input: " + Arrays.toString(nums4) + ", Target: " + target4);
        int result4 = solver.search(nums4, target4);
        solver.printResult("Test 4 (At beginning)", result4, expected4);
        totalTests++;
        if (result4 == expected4)
            passedTests++;

        // Test Case 5: Target at end
        System.out.println("\n==================== Test Case 5 ====================");
        int[] nums5 = { 4, 5, 6, 7, 0, 1, 2 };
        int target5 = 2;
        int expected5 = 6;
        System.out.println("Scenario: Target at end");
        System.out.println("Input: " + Arrays.toString(nums5) + ", Target: " + target5);
        int result5 = solver.search(nums5, target5);
        solver.printResult("Test 5 (At end)", result5, expected5);
        totalTests++;
        if (result5 == expected5)
            passedTests++;

        // Test Case 6: No rotation (already sorted)
        System.out.println("\n==================== Test Case 6 ====================");
        int[] nums6 = { 1, 3, 5, 7, 9 };
        int target6 = 3;
        int expected6 = 1;
        System.out.println("Scenario: No rotation (already sorted)");
        System.out.println("Input: " + Arrays.toString(nums6) + ", Target: " + target6);
        int result6 = solver.search(nums6, target6);
        solver.printResult("Test 6 (No rotation)", result6, expected6);
        totalTests++;
        if (result6 == expected6)
            passedTests++;

        // Test Case 7: Target in first sorted half
        System.out.println("\n==================== Test Case 7 ====================");
        int[] nums7 = { 5, 6, 7, 0, 1, 2, 3, 4 };
        int target7 = 6;
        int expected7 = 1;
        System.out.println("Scenario: Target in first sorted half");
        System.out.println("Input: " + Arrays.toString(nums7) + ", Target: " + target7);
        int result7 = solver.search(nums7, target7);
        solver.printResult("Test 7 (First half)", result7, expected7);
        totalTests++;
        if (result7 == expected7)
            passedTests++;

        // Test Case 8: Target in second sorted half
        System.out.println("\n==================== Test Case 8 ====================");
        int[] nums8 = { 5, 6, 7, 0, 1, 2, 3, 4 };
        int target8 = 2;
        int expected8 = 5;
        System.out.println("Scenario: Target in second sorted half");
        System.out.println("Input: " + Arrays.toString(nums8) + ", Target: " + target8);
        int result8 = solver.search(nums8, target8);
        solver.printResult("Test 8 (Second half)", result8, expected8);
        totalTests++;
        if (result8 == expected8)
            passedTests++;

        // Test Case 9: Negative numbers
        System.out.println("\n==================== Test Case 9 ====================");
        int[] nums9 = { 10, 20, 30, -10, -5, 0, 5 };
        int target9 = -5;
        int expected9 = 4;
        System.out.println("Scenario: With negative numbers");
        System.out.println("Input: " + Arrays.toString(nums9) + ", Target: " + target9);
        int result9 = solver.search(nums9, target9);
        solver.printResult("Test 9 (Negative)", result9, expected9);
        totalTests++;
        if (result9 == expected9)
            passedTests++;

        // Test Case 10: Large rotated array
        System.out.println("\n==================== Test Case 10 ====================");
        int[] nums10 = { 100, 200, 300, 400, 500, 1, 2, 3, 4, 5 };
        int target10 = 1;
        int expected10 = 5;
        System.out.println("Scenario: Large rotated array");
        System.out.println("Input: " + Arrays.toString(nums10) + ", Target: " + target10);
        int result10 = solver.search(nums10, target10);
        solver.printResult("Test 10 (Large array)", result10, expected10);
        totalTests++;
        if (result10 == expected10)
            passedTests++;

        // Summary
        System.out.println("\n==================== Summary ====================");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("Success Rate: " + (passedTests * 100 / totalTests) + "%");
    }
}

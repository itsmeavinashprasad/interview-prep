package dsa.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>1. Two Sum</h1>
 *
 * <p>
 * Given an array of integers {@code nums} and an integer {@code target},
 * return indices of the two numbers such that they add up to {@code target}.
 * </p>
 * <p>
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * </p>
 * <p>
 * You can return the answer in any order.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> nums = [2,7,11,15], target = 9
 * <b>Output:</b> [0,1]
 * <b>Explanation:</b> Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * <b>Input:</b> nums = [3,2,4], target = 6
 * <b>Output:</b> [1,2]
 *
 * <b>Input:</b> nums = [3,3], target = 6
 * <b>Output:</b> [0,1]
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 2 <= nums.length <= 10^4}</li>
 * <li>{@code -10^9 <= nums[i] <= 10^9}</li>
 * <li>{@code -10^9 <= target <= 10^9}</li>
 * <li>Only one valid answer exists.</li>
 * </ul>
 *
 * <h2>Follow-up:</h2>
 * Can you come up with an algorithm that is less than O(n²) time complexity?
 *
 * @see <a href="https://leetcode.com/problems/two-sum/">LeetCode - Two Sum</a>
 */
public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int y = target - x;

            if (map.containsKey(y)) {
                return new int[] { i, map.get(y) };
            }

            map.put(x, i);
        }
        return new int[] { -1, -1 };
    }

    private boolean arraysEqual(int[] actual, int[] expected) {
        if (actual.length != expected.length)
            return false;
        // Handle both orderings: [a,b] and [b,a] are both valid
        return (actual[0] == expected[0] && actual[1] == expected[1]) ||
                (actual[0] == expected[1] && actual[1] == expected[0]);
    }

    private void printResult(String testName, int[] actual, int[] expected, boolean passed) {
        System.out.println(testName + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Output:   " + Arrays.toString(actual));
        System.out.println("  Expected: " + Arrays.toString(expected) + " (or reversed)");
    }

    public static void main(String[] args) {
        TwoSum solver = new TwoSum();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Standard example
        System.out.println("==================== Test Case 1 ====================");
        int[] nums1 = { 2, 7, 11, 15 };
        int target1 = 9;
        int[] expected1 = { 0, 1 };
        System.out.println("Scenario: Two numbers in order");
        System.out.println("Input: nums = " + Arrays.toString(nums1) + ", target = " + target1);
        int[] result1 = solver.twoSum(nums1, target1);
        boolean test1Pass = solver.arraysEqual(result1, expected1);
        solver.printResult("Test 1 (Standard)", result1, expected1, test1Pass);
        totalTests++;
        if (test1Pass)
            passedTests++;

        // Test Case 2: Answer not in order
        System.out.println("\n==================== Test Case 2 ====================");
        int[] nums2 = { 3, 2, 4 };
        int target2 = 6;
        int[] expected2 = { 1, 2 };
        System.out.println("Scenario: Two numbers not in natural order");
        System.out.println("Input: nums = " + Arrays.toString(nums2) + ", target = " + target2);
        int[] result2 = solver.twoSum(nums2, target2);
        boolean test2Pass = solver.arraysEqual(result2, expected2);
        solver.printResult("Test 2 (Not in order)", result2, expected2, test2Pass);
        totalTests++;
        if (test2Pass)
            passedTests++;

        // Test Case 3: Duplicate values
        System.out.println("\n==================== Test Case 3 ====================");
        int[] nums3 = { 3, 3 };
        int target3 = 6;
        int[] expected3 = { 0, 1 };
        System.out.println("Scenario: Duplicate values in array");
        System.out.println("Input: nums = " + Arrays.toString(nums3) + ", target = " + target3);
        int[] result3 = solver.twoSum(nums3, target3);
        boolean test3Pass = solver.arraysEqual(result3, expected3);
        solver.printResult("Test 3 (Duplicates)", result3, expected3, test3Pass);
        totalTests++;
        if (test3Pass)
            passedTests++;

        // Test Case 4: Negative numbers
        System.out.println("\n==================== Test Case 4 ====================");
        int[] nums4 = { -1, -2, -3, 5, 10 };
        int target4 = 7;
        int[] expected4 = { 2, 4 };
        System.out.println("Scenario: Negative numbers in array");
        System.out.println("Input: nums = " + Arrays.toString(nums4) + ", target = " + target4);
        int[] result4 = solver.twoSum(nums4, target4);
        boolean test4Pass = solver.arraysEqual(result4, expected4);
        solver.printResult("Test 4 (Negative numbers)", result4, expected4, test4Pass);
        totalTests++;
        if (test4Pass)
            passedTests++;

        // Test Case 5: Large numbers
        System.out.println("\n==================== Test Case 5 ====================");
        int[] nums5 = { 1000000, -1000000, 2000000 };
        int target5 = 1000000;
        int[] expected5 = { 1, 2 };
        System.out.println("Scenario: Large numbers");
        System.out.println("Input: nums = " + Arrays.toString(nums5) + ", target = " + target5);
        int[] result5 = solver.twoSum(nums5, target5);
        boolean test5Pass = solver.arraysEqual(result5, expected5);
        solver.printResult("Test 5 (Large numbers)", result5, expected5, test5Pass);
        totalTests++;
        if (test5Pass)
            passedTests++;

        // Test Case 6: Zero in array
        System.out.println("\n==================== Test Case 6 ====================");
        int[] nums6 = { 0, 0, 1, 2 };
        int target6 = 0;
        int[] expected6 = { 0, 1 };
        System.out.println("Scenario: Zero in array");
        System.out.println("Input: nums = " + Arrays.toString(nums6) + ", target = " + target6);
        int[] result6 = solver.twoSum(nums6, target6);
        boolean test6Pass = solver.arraysEqual(result6, expected6);
        solver.printResult("Test 6 (Zero)", result6, expected6, test6Pass);
        totalTests++;
        if (test6Pass)
            passedTests++;

        // Test Case 7: Minimum array size
        System.out.println("\n==================== Test Case 7 ====================");
        int[] nums7 = { 1, 2 };
        int target7 = 3;
        int[] expected7 = { 0, 1 };
        System.out.println("Scenario: Minimum array size (n=2)");
        System.out.println("Input: nums = " + Arrays.toString(nums7) + ", target = " + target7);
        int[] result7 = solver.twoSum(nums7, target7);
        boolean test7Pass = solver.arraysEqual(result7, expected7);
        solver.printResult("Test 7 (Minimum size)", result7, expected7, test7Pass);
        totalTests++;
        if (test7Pass)
            passedTests++;

        // Test Case 8: Larger array
        System.out.println("\n==================== Test Case 8 ====================");
        int[] nums8 = { 11, 2, 7, 1, 15, 5, 9 };
        int target8 = 22;
        int[] expected8 = { 2, 4 };
        System.out.println("Scenario: Larger array with more elements");
        System.out.println("Input: nums = " + Arrays.toString(nums8) + ", target = " + target8);
        int[] result8 = solver.twoSum(nums8, target8);
        boolean test8Pass = solver.arraysEqual(result8, expected8);
        solver.printResult("Test 8 (Larger array)", result8, expected8, test8Pass);
        totalTests++;
        if (test8Pass)
            passedTests++;

        // Test Case 9: All negative numbers
        System.out.println("\n==================== Test Case 9 ====================");
        int[] nums9 = { -10, -5, -3, -1 };
        int target9 = -14;
        int[] expected9 = { -1, -1 };
        System.out.println("Scenario: All negative numbers");
        System.out.println("Input: nums = " + Arrays.toString(nums9) + ", target = " + target9);
        int[] result9 = solver.twoSum(nums9, target9);
        boolean test9Pass = solver.arraysEqual(result9, expected9);
        solver.printResult("Test 9 (All negative)", result9, expected9, test9Pass);
        totalTests++;
        if (test9Pass)
            passedTests++;

        // Test Case 10: Mix of positive and negative
        System.out.println("\n==================== Test Case 10 ====================");
        int[] nums10 = { -5, 0, 1, 2, 3 };
        int target10 = 2;
        int[] expected10 = { 1, 3 };
        System.out.println("Scenario: Mix of positive, negative, and zero");
        System.out.println("Input: nums = " + Arrays.toString(nums10) + ", target = " + target10);
        int[] result10 = solver.twoSum(nums10, target10);
        boolean test10Pass = solver.arraysEqual(result10, expected10);
        solver.printResult("Test 10 (Mixed signs)", result10, expected10, test10Pass);
        totalTests++;
        if (test10Pass)
            passedTests++;

        // Summary
        System.out.println("\n==================== Summary ====================");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("Success Rate: " + (passedTests * 100 / totalTests) + "%");
    }
}

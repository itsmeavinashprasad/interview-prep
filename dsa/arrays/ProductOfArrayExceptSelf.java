package dsa.arrays;

import java.util.Arrays;

/**
 * <h1>238. Product of Array Except Self</h1>
 *
 * <p>
 * Given an integer array {@code nums}, return an array {@code answer} such that
 * {@code answer[i]} is equal to the product of all the elements of {@code nums}
 * except {@code nums[i]}.
 * </p>
 * <p>
 * The product of any prefix or suffix of {@code nums} is guaranteed to fit in a
 * 32-bit integer.
 * </p>
 * <p>
 * You must write an algorithm that runs in {@code O(n)} time and without using
 * the division operation.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> nums = [1,2,3,4]
 * <b>Output:</b> [24,12,8,6]
 *
 * <b>Input:</b> nums = [-1,1,0,-3,3]
 * <b>Output:</b> [0,0,9,0,0]
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 2 <= nums.length <= 10^5}</li>
 * <li>{@code -30 <= nums[i] <= 30}</li>
 * <li>The input is generated such that {@code answer[i]} is guaranteed to fit
 * in
 * a 32-bit integer.</li>
 * </ul>
 *
 * <h2>Follow-up:</h2>
 * Can you solve the problem in O(1) extra space complexity? (The output array
 * does not count as extra space for space complexity analysis.)
 *
 * @see <a href=
 *      "https://leetcode.com/problems/product-of-array-except-self/">LeetCode -
 *      Product of Array Except Self</a>
 */
public class ProductOfArrayExceptSelf {

    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // calculate prefix mul
        int leftProduct = 1 ;
        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = leftProduct * nums[i-1];
            leftProduct = result[i];
        }

        // calculate suffix 1mul
        int rightProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] = rightProduct * result[i];
            rightProduct = rightProduct * nums[i];
        }
        return result;
    }

    private boolean arraysEqual(int[] actual, int[] expected) {
        if (actual.length != expected.length)
            return false;
        for (int i = 0; i < actual.length; i++) {
            if (actual[i] != expected[i])
                return false;
        }
        return true;
    }

    private void printResult(String testName, int[] actual, int[] expected, boolean passed) {
        System.out.println(testName + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Output:   " + Arrays.toString(actual));
        System.out.println("  Expected: " + Arrays.toString(expected));
    }

    public static void main(String[] args) {
        ProductOfArrayExceptSelf solver = new ProductOfArrayExceptSelf();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Simple positive numbers
        System.out.println("==================== Test Case 1 ====================");
        int[] nums1 = { 1, 2, 3, 4 };
        int[] expected1 = { 24, 12, 8, 6 };
        System.out.println("Scenario: Simple positive numbers");
        System.out.println("Input: " + Arrays.toString(nums1));
        int[] result1 = solver.productExceptSelf(nums1);
        boolean test1Pass = solver.arraysEqual(result1, expected1);
        solver.printResult("Test 1 (Positive)", result1, expected1, test1Pass);
        totalTests++;
        if (test1Pass)
            passedTests++;

        // Test Case 2: With negative numbers and zero
        System.out.println("\n==================== Test Case 2 ====================");
        int[] nums2 = { -1, 1, 0, -3, 3 };
        int[] expected2 = { 0, 0, 9, 0, 0 };
        System.out.println("Scenario: Negative numbers and zero");
        System.out.println("Input: " + Arrays.toString(nums2));
        int[] result2 = solver.productExceptSelf(nums2);
        boolean test2Pass = solver.arraysEqual(result2, expected2);
        solver.printResult("Test 2 (Mixed with zero)", result2, expected2, test2Pass);
        totalTests++;
        if (test2Pass)
            passedTests++;

        // Test Case 3: Two elements
        System.out.println("\n==================== Test Case 3 ====================");
        int[] nums3 = { 2, 3 };
        int[] expected3 = { 3, 2 };
        System.out.println("Scenario: Minimum size (two elements)");
        System.out.println("Input: " + Arrays.toString(nums3));
        int[] result3 = solver.productExceptSelf(nums3);
        boolean test3Pass = solver.arraysEqual(result3, expected3);
        solver.printResult("Test 3 (Two elements)", result3, expected3, test3Pass);
        totalTests++;
        if (test3Pass)
            passedTests++;

        // Test Case 4: All ones
        System.out.println("\n==================== Test Case 4 ====================");
        int[] nums4 = { 1, 1, 1, 1 };
        int[] expected4 = { 1, 1, 1, 1 };
        System.out.println("Scenario: All ones");
        System.out.println("Input: " + Arrays.toString(nums4));
        int[] result4 = solver.productExceptSelf(nums4);
        boolean test4Pass = solver.arraysEqual(result4, expected4);
        solver.printResult("Test 4 (All ones)", result4, expected4, test4Pass);
        totalTests++;
        if (test4Pass)
            passedTests++;

        // Test Case 5: One zero at beginning
        System.out.println("\n==================== Test Case 5 ====================");
        int[] nums5 = { 0, 1, 2, 3 };
        int[] expected5 = { 6, 0, 0, 0 };
        System.out.println("Scenario: Zero at beginning");
        System.out.println("Input: " + Arrays.toString(nums5));
        int[] result5 = solver.productExceptSelf(nums5);
        boolean test5Pass = solver.arraysEqual(result5, expected5);
        solver.printResult("Test 5 (Zero at start)", result5, expected5, test5Pass);
        totalTests++;
        if (test5Pass)
            passedTests++;

        // Test Case 6: One zero at end
        System.out.println("\n==================== Test Case 6 ====================");
        int[] nums6 = { 1, 2, 3, 0 };
        int[] expected6 = { 0, 0, 0, 6 };
        System.out.println("Scenario: Zero at end");
        System.out.println("Input: " + Arrays.toString(nums6));
        int[] result6 = solver.productExceptSelf(nums6);
        boolean test6Pass = solver.arraysEqual(result6, expected6);
        solver.printResult("Test 6 (Zero at end)", result6, expected6, test6Pass);
        totalTests++;
        if (test6Pass)
            passedTests++;

        // Test Case 7: All negative numbers
        System.out.println("\n==================== Test Case 7 ====================");
        int[] nums7 = { -2, -3, -1 };
        int[] expected7 = { 3, 2, 6 };
        System.out.println("Scenario: All negative numbers");
        System.out.println("Input: " + Arrays.toString(nums7));
        int[] result7 = solver.productExceptSelf(nums7);
        boolean test7Pass = solver.arraysEqual(result7, expected7);
        solver.printResult("Test 7 (All negative)", result7, expected7, test7Pass);
        totalTests++;
        if (test7Pass)
            passedTests++;

        // Test Case 8: Mix of positive, negative, and zeros
        System.out.println("\n==================== Test Case 8 ====================");
        int[] nums8 = { 2, 3, -2, 4 };
        int[] expected8 = { -24, -16, 24, -12 };
        System.out.println("Scenario: Mix of positive and negative");
        System.out.println("Input: " + Arrays.toString(nums8));
        int[] result8 = solver.productExceptSelf(nums8);
        boolean test8Pass = solver.arraysEqual(result8, expected8);
        solver.printResult("Test 8 (Mixed signs)", result8, expected8, test8Pass);
        totalTests++;
        if (test8Pass)
            passedTests++;

        // Test Case 9: Larger array
        System.out.println("\n==================== Test Case 9 ====================");
        int[] nums9 = { 1, 2, 3, 4, 5 };
        int[] expected9 = { 120, 60, 40, 30, 24 };
        System.out.println("Scenario: Larger array");
        System.out.println("Input: " + Arrays.toString(nums9));
        int[] result9 = solver.productExceptSelf(nums9);
        boolean test9Pass = solver.arraysEqual(result9, expected9);
        solver.printResult("Test 9 (Larger array)", result9, expected9, test9Pass);
        totalTests++;
        if (test9Pass)
            passedTests++;

        // Test Case 10: Multiple zeros (only one expected to work)
        System.out.println("\n==================== Test Case 10 ====================");
        int[] nums10 = { 2, 0, 1 };
        int[] expected10 = { 0, 2, 0 };
        System.out.println("Scenario: One zero in middle");
        System.out.println("Input: " + Arrays.toString(nums10));
        int[] result10 = solver.productExceptSelf(nums10);
        boolean test10Pass = solver.arraysEqual(result10, expected10);
        solver.printResult("Test 10 (Zero in middle)", result10, expected10, test10Pass);
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

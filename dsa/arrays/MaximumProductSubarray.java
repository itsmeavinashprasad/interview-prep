package dsa.arrays;

import java.util.Arrays;

/**
 * <h1>152. Maximum Product Subarray</h1>
 *
 * <p>
 * Given an integer array {@code nums}, find a subarray that has the largest
 * product, and return the product.
 * </p>
 * <p>
 * The test cases are generated so that the answer will fit in a 32-bit integer.
 * </p>
 * <p>
 * Note that the product of an array with a single element is the value of that
 * element.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> nums = [2,3,-2,4]
 * <b>Output:</b> 6
 * <b>Explanation:</b> [2,3] has the largest product 6.
 *
 * <b>Input:</b> nums = [-2,0,-1]
 * <b>Output:</b> 0
 * <b>Explanation:</b> The result cannot be 2, because [-2,-1] is not a subarray.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 1 <= nums.length <= 2 * 10^4}</li>
 * <li>{@code -10 <= nums[i] <= 10}</li>
 * <li>The product of any subarray of {@code nums} is guaranteed to fit in a
 * 32-bit integer.</li>
 * </ul>
 *
 * @see <a href=
 *      "https://leetcode.com/problems/maximum-product-subarray/">LeetCode -
 *      Maximum Product Subarray</a>
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        int best = 1;
        int worst = 1;
        int ans = Integer.MIN_VALUE;

        for (int x : nums) {
            int product1 = x;
            int product2 = x * best;
            int product3 = x * worst;

            best = Math.max(product1, Math.max(product2, product3));
            worst = Math.min(product1, Math.min(product2, product3));

            ans = Math.max(ans, best);
        }
        return ans;
    }

    private void printResult(String testName, int actual, int expected) {
        boolean passed = actual == expected;
        System.out.println(testName + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Output:   " + actual);
        System.out.println("  Expected: " + expected);
    }

    public static void main(String[] args) {
        MaximumProductSubarray solver = new MaximumProductSubarray();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Mix of positive and negative
        System.out.println("==================== Test Case 1 ====================");
        int[] nums1 = { 2, 3, -2, 4 };
        int expected1 = 6;
        System.out.println("Scenario: Mix of positive and negative");
        System.out.println("Input: " + Arrays.toString(nums1));
        int result1 = solver.maxProduct(nums1);
        solver.printResult("Test 1 (Standard)", result1, expected1);
        totalTests++;
        if (result1 == expected1)
            passedTests++;

        // Test Case 2: With zero
        System.out.println("\n==================== Test Case 2 ====================");
        int[] nums2 = { -2, 0, -1 };
        int expected2 = 0;
        System.out.println("Scenario: With zero in array");
        System.out.println("Input: " + Arrays.toString(nums2));
        int result2 = solver.maxProduct(nums2);
        solver.printResult("Test 2 (With zero)", result2, expected2);
        totalTests++;
        if (result2 == expected2)
            passedTests++;

        // Test Case 3: Single element
        System.out.println("\n==================== Test Case 3 ====================");
        int[] nums3 = { 5 };
        int expected3 = 5;
        System.out.println("Scenario: Single positive element");
        System.out.println("Input: " + Arrays.toString(nums3));
        int result3 = solver.maxProduct(nums3);
        solver.printResult("Test 3 (Single positive)", result3, expected3);
        totalTests++;
        if (result3 == expected3)
            passedTests++;

        // Test Case 4: Single negative element
        System.out.println("\n==================== Test Case 4 ====================");
        int[] nums4 = { -5 };
        int expected4 = -5;
        System.out.println("Scenario: Single negative element");
        System.out.println("Input: " + Arrays.toString(nums4));
        int result4 = solver.maxProduct(nums4);
        solver.printResult("Test 4 (Single negative)", result4, expected4);
        totalTests++;
        if (result4 == expected4)
            passedTests++;

        // Test Case 5: All positive
        System.out.println("\n==================== Test Case 5 ====================");
        int[] nums5 = { 1, 2, 3, 4 };
        int expected5 = 24;
        System.out.println("Scenario: All positive numbers");
        System.out.println("Input: " + Arrays.toString(nums5));
        int result5 = solver.maxProduct(nums5);
        solver.printResult("Test 5 (All positive)", result5, expected5);
        totalTests++;
        if (result5 == expected5)
            passedTests++;

        // Test Case 6: All negative
        System.out.println("\n==================== Test Case 6 ====================");
        int[] nums6 = { -2, -3, -4 };
        int expected6 = 12;
        System.out.println("Scenario: All negative numbers");
        System.out.println("Input: " + Arrays.toString(nums6));
        int result6 = solver.maxProduct(nums6);
        solver.printResult("Test 6 (All negative)", result6, expected6);
        totalTests++;
        if (result6 == expected6)
            passedTests++;

        // Test Case 7: Even number of negatives
        System.out.println("\n==================== Test Case 7 ====================");
        int[] nums7 = { -2, 3, -4 };
        int expected7 = 24;
        System.out.println("Scenario: Even number of negatives");
        System.out.println("Input: " + Arrays.toString(nums7));
        int result7 = solver.maxProduct(nums7);
        solver.printResult("Test 7 (Even negatives)", result7, expected7);
        totalTests++;
        if (result7 == expected7)
            passedTests++;

        // Test Case 8: Odd number of negatives
        System.out.println("\n==================== Test Case 8 ====================");
        int[] nums8 = { -2, 3, -4, 5 };
        int expected8 = 120;
        System.out.println("Scenario: Multiple negatives");
        System.out.println("Input: " + Arrays.toString(nums8));
        int result8 = solver.maxProduct(nums8);
        solver.printResult("Test 8 (Multiple negatives)", result8, expected8);
        totalTests++;
        if (result8 == expected8)
            passedTests++;

        // Test Case 9: Zero breaks product
        System.out.println("\n==================== Test Case 9 ====================");
        int[] nums9 = { 2, 3, 0, 5, 6 };
        int expected9 = 30;
        System.out.println("Scenario: Zero breaks product");
        System.out.println("Input: " + Arrays.toString(nums9));
        int result9 = solver.maxProduct(nums9);
        solver.printResult("Test 9 (Zero breaks)", result9, expected9);
        totalTests++;
        if (result9 == expected9)
            passedTests++;

        // Test Case 10: Complex pattern
        System.out.println("\n==================== Test Case 10 ====================");
        int[] nums10 = { 0, 2, -2, 4, -3 };
        int expected10 = 48;
        System.out.println("Scenario: Complex pattern with zeros and negatives");
        System.out.println("Input: " + Arrays.toString(nums10));
        int result10 = solver.maxProduct(nums10);
        solver.printResult("Test 10 (Complex pattern)", result10, expected10);
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

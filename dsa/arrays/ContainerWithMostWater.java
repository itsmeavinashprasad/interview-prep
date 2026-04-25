package dsa.arrays;

import java.util.Arrays;

/**
 * <h1>11. Container With Most Water</h1>
 *
 * <p>
 * You are given an integer array {@code height} of length {@code n}. There are
 * {@code n} vertical lines drawn such that the two endpoints of the
 * {@code i}th line are {@code (i, 0)} and {@code (i, height[i])}.
 * </p>
 * <p>
 * Find two lines that together with the x-axis form a container, such that the
 * container contains the most water.
 * </p>
 * <p>
 * Return the maximum amount of water a container can store.
 * </p>
 * <p>
 * Notice that you may not slant the container.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> height = [1,8,6,2,5,4,8,3,7]
 * <b>Output:</b> 49
 * <b>Explanation:</b> The vertical lines are represented by array [1,8,6,2,5,4,8,3,7].
 * In this case, the max area of water the container can contain is 49.
 *
 * <b>Input:</b> height = [1,1]
 * <b>Output:</b> 1
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code n == height.length}</li>
 * <li>{@code 2 <= n <= 10^5}</li>
 * <li>{@code 0 <= height[i] <= 10^4}</li>
 * </ul>
 *
 * <h2>Hints:</h2>
 * <ul>
 * <li>Brute force O(n^2) approach would check all pairs.</li>
 * <li>Use two-pointers: one at left, one at right of the array.</li>
 * <li>Always move the pointer pointing to the lower line inward.</li>
 * <li>Area = width × min(height[left], height[right])</li>
 * </ul>
 *
 * @see <a href=
 *      "https://leetcode.com/problems/container-with-most-water/">LeetCode -
 *      Container With Most Water</a>
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0;
        int r = n - 1;
        int ans = 0;
        while (l < r) {
            int current = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, current);

            if (height[l] > height[r]) {
                r--;
            } else {
                l++;
            }
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
        ContainerWithMostWater solver = new ContainerWithMostWater();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Standard example
        System.out.println("==================== Test Case 1 ====================");
        int[] height1 = { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        int expected1 = 49;
        System.out.println("Scenario: Standard example");
        System.out.println("Input: " + Arrays.toString(height1));
        int result1 = solver.maxArea(height1.clone());
        solver.printResult("Test 1 (Standard)", result1, expected1);
        totalTests++;
        if (result1 == expected1)
            passedTests++;

        // Test Case 2: Minimum length
        System.out.println("\n==================== Test Case 2 ====================");
        int[] height2 = { 1, 1 };
        int expected2 = 1;
        System.out.println("Scenario: Minimum length (n=2)");
        System.out.println("Input: " + Arrays.toString(height2));
        int result2 = solver.maxArea(height2.clone());
        solver.printResult("Test 2 (Min length)", result2, expected2);
        totalTests++;
        if (result2 == expected2)
            passedTests++;

        // Test Case 3: Increasing heights
        System.out.println("\n==================== Test Case 3 ====================");
        int[] height3 = { 1, 2, 3, 4, 5 };
        int expected3 = 8;
        System.out.println("Scenario: Increasing heights");
        System.out.println("Input: " + Arrays.toString(height3));
        int result3 = solver.maxArea(height3.clone());
        solver.printResult("Test 3 (Increasing)", result3, expected3);
        totalTests++;
        if (result3 == expected3)
            passedTests++;

        // Test Case 4: Decreasing heights
        System.out.println("\n==================== Test Case 4 ====================");
        int[] height4 = { 5, 4, 3, 2, 1 };
        int expected4 = 8;
        System.out.println("Scenario: Decreasing heights");
        System.out.println("Input: " + Arrays.toString(height4));
        int result4 = solver.maxArea(height4.clone());
        solver.printResult("Test 4 (Decreasing)", result4, expected4);
        totalTests++;
        if (result4 == expected4)
            passedTests++;

        // Test Case 5: Max at edges
        System.out.println("\n==================== Test Case 5 ====================");
        int[] height5 = { 10, 1, 1, 1, 1, 10 };
        int expected5 = 50;
        System.out.println("Scenario: Maximum at edges");
        System.out.println("Input: " + Arrays.toString(height5));
        int result5 = solver.maxArea(height5.clone());
        solver.printResult("Test 5 (Max at edges)", result5, expected5);
        totalTests++;
        if (result5 == expected5)
            passedTests++;

        // Test Case 6: All same heights
        System.out.println("\n==================== Test Case 6 ====================");
        int[] height6 = { 5, 5, 5, 5, 5 };
        int expected6 = 20;
        System.out.println("Scenario: All same heights");
        System.out.println("Input: " + Arrays.toString(height6));
        int result6 = solver.maxArea(height6.clone());
        solver.printResult("Test 6 (Same heights)", result6, expected6);
        totalTests++;
        if (result6 == expected6)
            passedTests++;

        // Test Case 7: With zeros
        System.out.println("\n==================== Test Case 7 ====================");
        int[] height7 = { 0, 5, 0, 4, 0, 6, 0 };
        int expected7 = 24;
        System.out.println("Scenario: With zeros");
        System.out.println("Input: " + Arrays.toString(height7));
        int result7 = solver.maxArea(height7.clone());
        solver.printResult("Test 7 (With zeros)", result7, expected7);
        totalTests++;
        if (result7 == expected7)
            passedTests++;

        // Test Case 8: Two tall lines at opposite ends
        System.out.println("\n==================== Test Case 8 ====================");
        int[] height8 = { 100, 1, 2, 3, 4, 5, 100 };
        int expected8 = 600;
        System.out.println("Scenario: Two tall lines at opposite ends");
        System.out.println("Input: " + Arrays.toString(height8));
        int result8 = solver.maxArea(height8.clone());
        solver.printResult("Test 8 (Tall at ends)", result8, expected8);
        totalTests++;
        if (result8 == expected8)
            passedTests++;

        // Test Case 9: Peak in middle
        System.out.println("\n==================== Test Case 9 ====================");
        int[] height9 = { 3, 2, 10, 2, 3 };
        int expected9 = 12;
        System.out.println("Scenario: Peak in middle");
        System.out.println("Input: " + Arrays.toString(height9));
        int result9 = solver.maxArea(height9.clone());
        solver.printResult("Test 9 (Peak middle)", result9, expected9);
        totalTests++;
        if (result9 == expected9)
            passedTests++;

        // Test Case 10: Large heights
        System.out.println("\n==================== Test Case 10 ====================");
        int[] height10 = { 5000, 1, 2, 3, 4, 5000 };
        int expected10 = 25000;
        System.out.println("Scenario: Large height values");
        System.out.println("Input: " + Arrays.toString(height10));
        int result10 = solver.maxArea(height10.clone());
        solver.printResult("Test 10 (Large values)", result10, expected10);
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

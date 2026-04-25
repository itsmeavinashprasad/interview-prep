package dsa.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <h1>217. Contains Duplicate</h1>
 *
 * <p>
 * Given an integer array {@code nums}, return {@code true} if any value appears
 * at least twice in the array, and return {@code false} if every element is
 * distinct.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> nums = [1,2,3,1]
 * <b>Output:</b> true
 * <b>Explanation:</b> The element 1 occurs at the indices 0 and 3.
 *
 * <b>Input:</b> nums = [1,2,3,4]
 * <b>Output:</b> false
 * <b>Explanation:</b> All elements are distinct.
 * 
 * <b>Input:</b> nums = [1,1,1,3,3,4,3,2,4,2]
 * <b>Output:</b> true
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 1 <= nums.length <= 10^5}</li>
 * <li>{@code -10^9 <= nums[i] <= 10^9}</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/contains-duplicate/">LeetCode -
 *      Contains Duplicate</a>
 */
public class ContainsDuplicate {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for(int x: nums){
            if(seen.contains(x))
                return true;
            seen.add(x);
        }
        return false;
    }

    private void printResult(String testName, boolean actual, boolean expected) {
        boolean passed = actual == expected;
        System.out.println(testName + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Output:   " + actual);
        System.out.println("  Expected: " + expected);
    }

    public static void main(String[] args) {
        ContainsDuplicate solver = new ContainsDuplicate();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Duplicates at start and end
        System.out.println("==================== Test Case 1 ====================");
        int[] nums1 = { 1, 2, 3, 1 };
        boolean expected1 = true;
        System.out.println("Scenario: Duplicate element at different positions");
        System.out.println("Input: " + Arrays.toString(nums1));
        boolean result1 = solver.containsDuplicate(nums1);
        solver.printResult("Test 1 (Duplicate at start/end)", result1, expected1);
        totalTests++;
        if (result1 == expected1)
            passedTests++;

        // Test Case 2: No duplicates
        System.out.println("\n==================== Test Case 2 ====================");
        int[] nums2 = { 1, 2, 3, 4 };
        boolean expected2 = false;
        System.out.println("Scenario: All elements are distinct");
        System.out.println("Input: " + Arrays.toString(nums2));
        boolean result2 = solver.containsDuplicate(nums2);
        solver.printResult("Test 2 (No duplicates)", result2, expected2);
        totalTests++;
        if (result2 == expected2)
            passedTests++;

        // Test Case 3: Multiple duplicates
        System.out.println("\n==================== Test Case 3 ====================");
        int[] nums3 = { 1, 1, 1, 3, 3, 4, 3, 2, 4, 2 };
        boolean expected3 = true;
        System.out.println("Scenario: Multiple duplicates");
        System.out.println("Input: " + Arrays.toString(nums3));
        boolean result3 = solver.containsDuplicate(nums3);
        solver.printResult("Test 3 (Multiple duplicates)", result3, expected3);
        totalTests++;
        if (result3 == expected3)
            passedTests++;

        // Test Case 4: Single element
        System.out.println("\n==================== Test Case 4 ====================");
        int[] nums4 = { 1 };
        boolean expected4 = false;
        System.out.println("Scenario: Single element (no duplicates possible)");
        System.out.println("Input: " + Arrays.toString(nums4));
        boolean result4 = solver.containsDuplicate(nums4);
        solver.printResult("Test 4 (Single element)", result4, expected4);
        totalTests++;
        if (result4 == expected4)
            passedTests++;

        // Test Case 5: Two same elements
        System.out.println("\n==================== Test Case 5 ====================");
        int[] nums5 = { 99, 99 };
        boolean expected5 = true;
        System.out.println("Scenario: Two identical elements");
        System.out.println("Input: " + Arrays.toString(nums5));
        boolean result5 = solver.containsDuplicate(nums5);
        solver.printResult("Test 5 (Two same)", result5, expected5);
        totalTests++;
        if (result5 == expected5)
            passedTests++;

        // Test Case 6: All same elements
        System.out.println("\n==================== Test Case 6 ====================");
        int[] nums6 = { 5, 5, 5, 5, 5 };
        boolean expected6 = true;
        System.out.println("Scenario: All elements are the same");
        System.out.println("Input: " + Arrays.toString(nums6));
        boolean result6 = solver.containsDuplicate(nums6);
        solver.printResult("Test 6 (All same)", result6, expected6);
        totalTests++;
        if (result6 == expected6)
            passedTests++;

        // Test Case 7: Negative numbers with duplicates
        System.out.println("\n==================== Test Case 7 ====================");
        int[] nums7 = { -1, -2, -3, -2 };
        boolean expected7 = true;
        System.out.println("Scenario: Negative numbers with duplicates");
        System.out.println("Input: " + Arrays.toString(nums7));
        boolean result7 = solver.containsDuplicate(nums7);
        solver.printResult("Test 7 (Negative with dup)", result7, expected7);
        totalTests++;
        if (result7 == expected7)
            passedTests++;

        // Test Case 8: Negative numbers without duplicates
        System.out.println("\n==================== Test Case 8 ====================");
        int[] nums8 = { -1, -2, -3, -4 };
        boolean expected8 = false;
        System.out.println("Scenario: Negative numbers distinct");
        System.out.println("Input: " + Arrays.toString(nums8));
        boolean result8 = solver.containsDuplicate(nums8);
        solver.printResult("Test 8 (Negative no dup)", result8, expected8);
        totalTests++;
        if (result8 == expected8)
            passedTests++;

        // Test Case 9: Large values
        System.out.println("\n==================== Test Case 9 ====================");
        int[] nums9 = { 1000000000, -1000000000, 1000000000 };
        boolean expected9 = true;
        System.out.println("Scenario: Large positive and negative values");
        System.out.println("Input: " + Arrays.toString(nums9));
        boolean result9 = solver.containsDuplicate(nums9);
        solver.printResult("Test 9 (Large values)", result9, expected9);
        totalTests++;
        if (result9 == expected9)
            passedTests++;

        // Test Case 10: Duplicate consecutive elements
        System.out.println("\n==================== Test Case 10 ====================");
        int[] nums10 = { 1, 2, 3, 4, 5, 4, 6, 7 };
        boolean expected10 = true;
        System.out.println("Scenario: Duplicate in middle of array");
        System.out.println("Input: " + Arrays.toString(nums10));
        boolean result10 = solver.containsDuplicate(nums10);
        solver.printResult("Test 10 (Duplicate middle)", result10, expected10);
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

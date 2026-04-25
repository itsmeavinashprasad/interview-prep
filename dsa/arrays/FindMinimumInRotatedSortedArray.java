package dsa.arrays;

import java.util.Arrays;

/**
 * <h1>153. Find Minimum in Rotated Sorted Array</h1>
 *
 * <p>
 * Suppose an array of length {@code n} sorted in ascending order is rotated
 * between {@code 1} and {@code n} times. For example, the array
 * {@code nums = [0,1,2,4,5,6,7]} might become:
 * </p>
 * <ul>
 * <li>{@code [4,5,6,7,0,1,2]} if it was rotated {@code 4} times.</li>
 * <li>{@code [0,1,2,4,5,6,7]} if it was rotated {@code 7} times.</li>
 * </ul>
 * <p>
 * Given the sorted rotated array {@code nums} of unique elements, return the
 * minimum element of this array.
 * </p>
 * <p>
 * You must write an algorithm that runs in {@code O(log n)} time.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> nums = [3,4,5,1,2]
 * <b>Output:</b> 1
 * <b>Explanation:</b> The original array was [1,2,3,4,5] rotated 3 times.
 *
 * <b>Input:</b> nums = [4,5,6,7,0,1,2]
 * <b>Output:</b> 0
 * <b>Explanation:</b> The original array was [0,1,2,4,5,6,7] and it was rotated 4 times.
 *
 * <b>Input:</b> nums = [11,13,15,17]
 * <b>Output:</b> 11
 * <b>Explanation:</b> The original array was [11,13,15,17] and it was rotated 4 times.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code n == nums.length}</li>
 * <li>{@code 1 <= n <= 5000}</li>
 * <li>{@code -5000 <= nums[i] <= 5000}</li>
 * <li>All the integers of {@code nums} are unique.</li>
 * <li>{@code nums} is sorted and rotated between {@code 1} and {@code n}
 * times.</li>
 * </ul>
 *
 * <h2>Hints:</h2>
 * <ul>
 * <li>Array was originally in ascending order. Now that the array is rotated,
 * there would be a point in the array where there is a small deflection from
 * the increasing sequence.</li>
 * <li>You can divide the search space into two and see which direction to go.
 * Think of binary search with O(logN) complexity.</li>
 * <li>All elements to the left of inflection point &gt; first element of the
 * array.</li>
 * <li>All elements to the right of inflection point &lt; first element of the
 * array.</li>
 * </ul>
 *
 * @see <a href=
 *      "https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/">LeetCode
 *      - Find Minimum in Rotated Sorted Array</a>
 */
public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    private int findMin(int[] nums, int l, int r) {
        if (l > r) {
            return Integer.MAX_VALUE;
        }

        if (nums[l] < nums[r] || l == r) {
            // already sorted
            return nums[l];
        }

        int mid = (l + r) / 2;
        return Math.min(findMin(nums, l, mid), findMin(nums, mid + 1, r));
    }

    private void printResult(String testName, int actual, int expected) {
        boolean passed = actual == expected;
        System.out.println(testName + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Output:   " + actual);
        System.out.println("  Expected: " + expected);
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray solver = new FindMinimumInRotatedSortedArray();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Standard rotated array
        System.out.println("==================== Test Case 1 ====================");
        int[] nums1 = { 3, 4, 5, 1, 2 };
        int expected1 = 1;
        System.out.println("Scenario: Standard rotated array");
        System.out.println("Input: " + Arrays.toString(nums1));
        int result1 = solver.findMin(nums1);
        solver.printResult("Test 1 (Standard rotation)", result1, expected1);
        totalTests++;
        if (result1 == expected1)
            passedTests++;

        // Test Case 2: Rotated at beginning
        System.out.println("\n==================== Test Case 2 ====================");
        int[] nums2 = { 4, 5, 6, 7, 0, 1, 2 };
        int expected2 = 0;
        System.out.println("Scenario: Rotated at beginning");
        System.out.println("Input: " + Arrays.toString(nums2));
        int result2 = solver.findMin(nums2);
        solver.printResult("Test 2 (Rotation at start)", result2, expected2);
        totalTests++;
        if (result2 == expected2)
            passedTests++;

        // Test Case 3: No rotation (rotated n times)
        System.out.println("\n==================== Test Case 3 ====================");
        int[] nums3 = { 11, 13, 15, 17 };
        int expected3 = 11;
        System.out.println("Scenario: No rotation (rotated n times)");
        System.out.println("Input: " + Arrays.toString(nums3));
        int result3 = solver.findMin(nums3);
        solver.printResult("Test 3 (No rotation)", result3, expected3);
        totalTests++;
        if (result3 == expected3)
            passedTests++;

        // Test Case 4: Single element
        System.out.println("\n==================== Test Case 4 ====================");
        int[] nums4 = { 5 };
        int expected4 = 5;
        System.out.println("Scenario: Single element");
        System.out.println("Input: " + Arrays.toString(nums4));
        int result4 = solver.findMin(nums4);
        solver.printResult("Test 4 (Single element)", result4, expected4);
        totalTests++;
        if (result4 == expected4)
            passedTests++;

        // Test Case 5: Two elements
        System.out.println("\n==================== Test Case 5 ====================");
        int[] nums5 = { 2, 1 };
        int expected5 = 1;
        System.out.println("Scenario: Two elements");
        System.out.println("Input: " + Arrays.toString(nums5));
        int result5 = solver.findMin(nums5);
        solver.printResult("Test 5 (Two elements)", result5, expected5);
        totalTests++;
        if (result5 == expected5)
            passedTests++;

        // Test Case 6: Rotation point at end (first element is minimum)
        System.out.println("\n==================== Test Case 6 ====================");
        int[] nums6 = { 1, 2, 3 };
        int expected6 = 1;
        System.out.println("Scenario: No rotation, first element is minimum");
        System.out.println("Input: " + Arrays.toString(nums6));
        int result6 = solver.findMin(nums6);
        solver.printResult("Test 6 (First is min)", result6, expected6);
        totalTests++;
        if (result6 == expected6)
            passedTests++;

        // Test Case 7: With negative numbers
        System.out.println("\n==================== Test Case 7 ====================");
        int[] nums7 = { 3, 4, 5, -2, -1, 0, 1, 2 };
        int expected7 = -2;
        System.out.println("Scenario: With negative numbers");
        System.out.println("Input: " + Arrays.toString(nums7));
        int result7 = solver.findMin(nums7);
        solver.printResult("Test 7 (Negative numbers)", result7, expected7);
        totalTests++;
        if (result7 == expected7)
            passedTests++;

        // Test Case 8: Large rotated array
        System.out.println("\n==================== Test Case 8 ====================");
        int[] nums8 = { 6, 7, 8, 9, 10, 1, 2, 3, 4, 5 };
        int expected8 = 1;
        System.out.println("Scenario: Large rotated array");
        System.out.println("Input: " + Arrays.toString(nums8));
        int result8 = solver.findMin(nums8);
        solver.printResult("Test 8 (Large array)", result8, expected8);
        totalTests++;
        if (result8 == expected8)
            passedTests++;

        // Test Case 9: Rotated once (minimum at end)
        System.out.println("\n==================== Test Case 9 ====================");
        int[] nums9 = { 100, 1, 2, 3, 4, 5, 50, 60, 70, 80, 90 };
        int expected9 = 1;
        System.out.println("Scenario: Rotated with minimum near start");
        System.out.println("Input: " + Arrays.toString(nums9));
        int result9 = solver.findMin(nums9);
        solver.printResult("Test 9 (Min near start)", result9, expected9);
        totalTests++;
        if (result9 == expected9)
            passedTests++;

        // Test Case 10: Complex rotation with wider range
        System.out.println("\n==================== Test Case 10 ====================");
        int[] nums10 = { 5000, -5000, -4999, -4998, -1, 0, 1, 2, 3, 4, 100, 200,
                300 };
        int expected10 = -5000;
        System.out.println("Scenario: Complex rotation with wide range");
        System.out.println("Input: " + Arrays.toString(nums10));
        int result10 = solver.findMin(nums10);
        solver.printResult("Test 10 (Complex rotation)", result10, expected10);
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

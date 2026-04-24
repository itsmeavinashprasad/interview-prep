package dsa.interval;

import java.util.Arrays;

/**
 * <h1>Find the Repeating and Missing Number</h1>
 *
 * <p>
 * Given an integer array {@code nums} of size {@code n} containing values from
 * {@code [1, n]} and each value appears exactly once in the array, except for
 * {@code A}, which appears twice and {@code B} which is missing.
 * </p>
 * <p>
 * Return the values {@code A} and {@code B}, as an array of size 2, where
 * {@code A} appears in the 0-th index and {@code B} in the 1st index.
 * </p>
 * <p>
 * Note: You are not allowed to modify the original array.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> nums = [3, 5, 4, 1, 1]
 * <b>Output:</b> [1, 2]
 * <b>Explanation:</b> 1 appears two times in the array and 2 is missing from nums
 *
 * <b>Input:</b> nums = [1, 2, 3, 6, 7, 5, 7]
 * <b>Output:</b> [7, 4]
 * <b>Explanation:</b> 7 appears two times in the array and 4 is missing from nums.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code n == nums.length}</li>
 * <li>{@code 1 <= n <= 10^5}</li>
 * <li>{@code n - 2} elements in nums appear exactly once and are valued between
 * {@code [1, n]}</li>
 * <li>1 element in nums appears twice, and is valued between
 * {@code [1, n]}</li>
 * </ul>
 *
 * @see <a href=
 *      "https://takeuforward.org/plus/dsa/problems/find-the-repeating-and-missing-number">TakeUForward
 *      - Find the Repeating and Missing Number</a>
 */
public class FindMissingAndRepeatingNumber {

    public int[] findMissingAndRepeatingModifyingOriginalArray(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i + 1) {
                // correct position, continue
                i++;
            } else {
                // must be relocated to correct location
                if (nums[nums[i] - 1] == nums[i]) {
                    // target location already has the correct value
                    return new int[] { nums[i], i + 1 };
                } else {
                    // relocate it
                    int t = nums[i];
                    nums[i] = nums[t - 1];
                    nums[t - 1] = t;
                }
            }
        }
        return null;
    }

    public int[] findMissingAndRepeating(int[] nums) {
        // actual: a a c d e ...
        // expected: a b c d e ....
        // actual - expected = (aacde) - (abcde) = a - b
        // actual^2 - expected^2 = a^2 - b^2
        // a+b = (a^2 - b^2)/(a-b)
        // then deduce a and b

        long sumActual = 0;
        long sumSqActual = 0;
        for (int x : nums) {
            sumActual += x;
            sumSqActual += (long) x * x;
        }

        long n = nums.length;
        long sumExpected = (long) n * (n + 1) / 2;
        long sumSqExpected = (long) n * (n + 1) * (2 * n + 1) / 6;

        long diff = sumActual - sumExpected;
        long sumAB = (sumSqActual - sumSqExpected) / diff;

        int A = (int) ((diff + sumAB) / 2);
        int B = (int) (sumAB - A);
        return new int[] { A, B };
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
        FindMissingAndRepeatingNumber solver = new FindMissingAndRepeatingNumber();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Standard example - missing is at end
        System.out.println("==================== Test Case 1 ====================");
        int[] nums1 = { 3, 5, 4, 1, 1 };
        int[] expected1 = { 1, 2 };
        System.out.println("Scenario: 1 appears twice, 2 is missing");
        System.out.println("Input: " + Arrays.toString(nums1));
        int[] result1 = solver.findMissingAndRepeating(nums1);
        boolean test1Pass = solver.arraysEqual(result1, expected1);
        solver.printResult("Test 1 (Missing at end)", result1, expected1, test1Pass);
        totalTests++;
        if (test1Pass)
            passedTests++;

        // Test Case 2: Standard example - missing in middle
        System.out.println("\n==================== Test Case 2 ====================");
        int[] nums2 = { 1, 2, 3, 6, 7, 5, 7 };
        int[] expected2 = { 7, 4 };
        System.out.println("Scenario: 7 appears twice, 4 is missing");
        System.out.println("Input: " + Arrays.toString(nums2));
        int[] result2 = solver.findMissingAndRepeating(nums2);
        boolean test2Pass = solver.arraysEqual(result2, expected2);
        solver.printResult("Test 2 (Missing in middle)", result2, expected2, test2Pass);
        totalTests++;
        if (test2Pass)
            passedTests++;

        // Test Case 3: Missing is 1
        System.out.println("\n==================== Test Case 3 ====================");
        int[] nums3 = { 2, 2, 3 };
        int[] expected3 = { 2, 1 };
        System.out.println("Scenario: 2 appears twice, 1 is missing");
        System.out.println("Input: " + Arrays.toString(nums3));
        int[] result3 = solver.findMissingAndRepeating(nums3);
        boolean test3Pass = solver.arraysEqual(result3, expected3);
        solver.printResult("Test 3 (Missing is 1)", result3, expected3, test3Pass);
        totalTests++;
        if (test3Pass)
            passedTests++;

        // Test Case 4: Missing is n
        System.out.println("\n==================== Test Case 4 ====================");
        int[] nums4 = { 1, 1, 2, 3 };
        int[] expected4 = { 1, 4 };
        System.out.println("Scenario: 1 appears twice, 4 is missing");
        System.out.println("Input: " + Arrays.toString(nums4));
        int[] result4 = solver.findMissingAndRepeating(nums4);
        boolean test4Pass = solver.arraysEqual(result4, expected4);
        solver.printResult("Test 4 (Missing is n)", result4, expected4, test4Pass);
        totalTests++;
        if (test4Pass)
            passedTests++;

        // Test Case 5: Repeating at start
        System.out.println("\n==================== Test Case 5 ====================");
        int[] nums5 = { 1, 1, 2, 3, 4 };
        int[] expected5 = { 1, 5 };
        System.out.println("Scenario: 1 at start is repeated, 5 is missing");
        System.out.println("Input: " + Arrays.toString(nums5));
        int[] result5 = solver.findMissingAndRepeating(nums5);
        boolean test5Pass = solver.arraysEqual(result5, expected5);
        solver.printResult("Test 5 (Repeating at start)", result5, expected5, test5Pass);
        totalTests++;
        if (test5Pass)
            passedTests++;

        // Test Case 6: Repeating at end
        System.out.println("\n==================== Test Case 6 ====================");
        int[] nums6 = { 1, 2, 3, 4, 4 };
        int[] expected6 = { 4, 5 };
        System.out.println("Scenario: 4 at end is repeated, 5 is missing");
        System.out.println("Input: " + Arrays.toString(nums6));
        int[] result6 = solver.findMissingAndRepeating(nums6);
        boolean test6Pass = solver.arraysEqual(result6, expected6);
        solver.printResult("Test 6 (Repeating at end)", result6, expected6, test6Pass);
        totalTests++;
        if (test6Pass)
            passedTests++;

        // Test Case 7: Larger array with repeating in middle
        System.out.println("\n==================== Test Case 7 ====================");
        int[] nums7 = { 1, 2, 3, 4, 5, 6, 5, 7, 8, 9 };
        int[] expected7 = { 5, 10 };
        System.out.println("Scenario: 5 appears twice, 10 is missing");
        System.out.println("Input: " + Arrays.toString(nums7));
        int[] result7 = solver.findMissingAndRepeating(nums7);
        boolean test7Pass = solver.arraysEqual(result7, expected7);
        solver.printResult("Test 7 (Larger array)", result7, expected7, test7Pass);
        totalTests++;
        if (test7Pass)
            passedTests++;

        // Test Case 8: n = 2, minimal case
        System.out.println("\n==================== Test Case 8 ====================");
        int[] nums8 = { 1, 1 };
        int[] expected8 = { 1, 2 };
        System.out.println("Scenario: Minimal case - 1 repeated, 2 missing");
        System.out.println("Input: " + Arrays.toString(nums8));
        int[] result8 = solver.findMissingAndRepeating(nums8);
        boolean test8Pass = solver.arraysEqual(result8, expected8);
        solver.printResult("Test 8 (Minimal n=2)", result8, expected8, test8Pass);
        totalTests++;
        if (test8Pass)
            passedTests++;

        // Test Case 9: Repeating is at position n-1, missing is at position n-2
        System.out.println("\n==================== Test Case 9 ====================");
        int[] nums9 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 9 };
        int[] expected9 = { 9, 10 };
        System.out.println("Scenario: Large n with 9 repeated");
        System.out.println("Input: " + Arrays.toString(nums9));
        int[] result9 = solver.findMissingAndRepeating(nums9);
        boolean test9Pass = solver.arraysEqual(result9, expected9);
        solver.printResult("Test 9 (Large n)", result9, expected9, test9Pass);
        totalTests++;
        if (test9Pass)
            passedTests++;

        // Test Case 10: Complex pattern
        System.out.println("\n==================== Test Case 10 ====================");
        int[] nums10 = { 6, 5, 7, 1, 8, 6, 4, 3, 2 };
        int[] expected10 = { 6, 9 };
        System.out.println("Scenario: 6 repeated, 9 missing out of 1-9");
        System.out.println("Input: " + Arrays.toString(nums10));
        int[] result10 = solver.findMissingAndRepeating(nums10);
        boolean test10Pass = solver.arraysEqual(result10, expected10);
        solver.printResult("Test 10 (Complex pattern)", result10, expected10, test10Pass);
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

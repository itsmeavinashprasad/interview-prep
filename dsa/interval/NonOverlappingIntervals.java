package dsa.interval;

import java.util.Arrays;

/**
 * <h1>435. Non-overlapping Intervals</h1>
 *
 * <p>
 * Given an array of {@code intervals} where
 * {@code intervals[i] = [start_i, end_i]},
 * return the minimum number of intervals you need to remove to make the rest of
 * the
 * intervals non-overlapping.
 * </p>
 * <p>
 * Note that intervals which only touch at a point are non-overlapping. For
 * example,
 * {@code [1, 2]} and {@code [2, 3]} are non-overlapping.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> intervals = [[1,2],[2,3],[3,4],[1,3]]
 * <b>Output:</b> 1
 * <b>Explanation:</b> [1,3] can be removed and the rest of the intervals are non-overlapping.
 *
 * <b>Input:</b> intervals = [[1,2],[1,2],[1,2]]
 * <b>Output:</b> 2
 * <b>Explanation:</b> You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * 
 * <b>Input:</b> intervals = [[1,2],[2,3]]
 * <b>Output:</b> 0
 * <b>Explanation:</b> You don't need to remove any of the intervals since they're already non-overlapping.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 1 <= intervals.length <= 10^5}</li>
 * <li>{@code intervals[i].length == 2}</li>
 * <li>{@code -5 * 10^4 <= start_i < end_i <= 5 * 10^4}</li>
 * </ul>
 *
 * @see <a href=
 *      "https://leetcode.com/problems/non-overlapping-intervals/">LeetCode -
 *      Non-overlapping Intervals</a>
 */
public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        // sort the array
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int[] last = intervals[0];
        int ans = 0;

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            if (current[0] < last[1]) {
                // current interval is overlapping, one of them should be removed
                ans++;
                // keeping the smaller interval
                if (last[1] > current[1]) {
                    // last have a larger interval, should be removed
                    last = current;
                }
                // else remove the current
            } else {
                last = current;
            }
        }

        return ans;
    }

    private void printResult(String testName, int actual, int expected, boolean passed) {
        System.out.println(testName + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Output:   " + actual);
        System.out.println("  Expected: " + expected);
    }

    public static void main(String[] args) {
        NonOverlappingIntervals solver = new NonOverlappingIntervals();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Standard example with one overlap
        System.out.println("==================== Test Case 1 ====================");
        int[][] intervals1 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 1, 3 } };
        int expected1 = 1;
        System.out.println("Scenario: One interval needs to be removed");
        System.out.println("Input: " + Arrays.deepToString(intervals1));
        int result1 = solver.eraseOverlapIntervals(intervals1);
        boolean test1Pass = result1 == expected1;
        solver.printResult("Test 1 (One removal needed)", result1, expected1, test1Pass);
        totalTests++;
        if (test1Pass)
            passedTests++;

        // Test Case 2: All intervals are duplicates
        System.out.println("\n==================== Test Case 2 ====================");
        int[][] intervals2 = { { 1, 2 }, { 1, 2 }, { 1, 2 } };
        int expected2 = 2;
        System.out.println("Scenario: Duplicate intervals, keep 1");
        System.out.println("Input: " + Arrays.deepToString(intervals2));
        int result2 = solver.eraseOverlapIntervals(intervals2);
        boolean test2Pass = result2 == expected2;
        solver.printResult("Test 2 (Duplicates)", result2, expected2, test2Pass);
        totalTests++;
        if (test2Pass)
            passedTests++;

        // Test Case 3: All non-overlapping
        System.out.println("\n==================== Test Case 3 ====================");
        int[][] intervals3 = { { 1, 2 }, { 2, 3 } };
        int expected3 = 0;
        System.out.println("Scenario: Already non-overlapping (touching)");
        System.out.println("Input: " + Arrays.deepToString(intervals3));
        int result3 = solver.eraseOverlapIntervals(intervals3);
        boolean test3Pass = result3 == expected3;
        solver.printResult("Test 3 (Already non-overlapping)", result3, expected3, test3Pass);
        totalTests++;
        if (test3Pass)
            passedTests++;

        // Test Case 4: Single interval
        System.out.println("\n==================== Test Case 4 ====================");
        int[][] intervals4 = { { 0, 1 } };
        int expected4 = 0;
        System.out.println("Scenario: Single interval");
        System.out.println("Input: " + Arrays.deepToString(intervals4));
        int result4 = solver.eraseOverlapIntervals(intervals4);
        boolean test4Pass = result4 == expected4;
        solver.printResult("Test 4 (Single interval)", result4, expected4, test4Pass);
        totalTests++;
        if (test4Pass)
            passedTests++;

        // Test Case 5: All intervals overlap each other
        System.out.println("\n==================== Test Case 5 ====================");
        int[][] intervals5 = { { 0, 10 }, { 1, 5 }, { 2, 3 }, { 4, 6 } };
        int expected5 = 3;
        System.out.println("Scenario: All overlapping, keep shortest end");
        System.out.println("Input: " + Arrays.deepToString(intervals5));
        int result5 = solver.eraseOverlapIntervals(intervals5);
        boolean test5Pass = result5 == expected5;
        solver.printResult("Test 5 (Many overlaps)", result5, expected5, test5Pass);
        totalTests++;
        if (test5Pass)
            passedTests++;

        // Test Case 6: Overlapping chain
        System.out.println("\n==================== Test Case 6 ====================");
        int[][] intervals6 = { { 1, 2 }, { 1, 2 }, { 1, 2 }, { 2, 3 } };
        int expected6 = 2;
        System.out.println("Scenario: Multiple duplicates then non-overlapping");
        System.out.println("Input: " + Arrays.deepToString(intervals6));
        int result6 = solver.eraseOverlapIntervals(intervals6);
        boolean test6Pass = result6 == expected6;
        solver.printResult("Test 6 (Partial overlap)", result6, expected6, test6Pass);
        totalTests++;
        if (test6Pass)
            passedTests++;

        // Test Case 7: Two overlapping intervals
        System.out.println("\n==================== Test Case 7 ====================");
        int[][] intervals7 = { { 1, 5 }, { 2, 3 } };
        int expected7 = 1;
        System.out.println("Scenario: Two intervals, one overlaps");
        System.out.println("Input: " + Arrays.deepToString(intervals7));
        int result7 = solver.eraseOverlapIntervals(intervals7);
        boolean test7Pass = result7 == expected7;
        solver.printResult("Test 7 (Two overlapping)", result7, expected7, test7Pass);
        totalTests++;
        if (test7Pass)
            passedTests++;

        // Test Case 8: Unordered input
        System.out.println("\n==================== Test Case 8 ====================");
        int[][] intervals8 = { { 3, 4 }, { 1, 2 }, { 1, 3 } };
        int expected8 = 1;
        System.out.println("Scenario: Unordered intervals");
        System.out.println("Input: " + Arrays.deepToString(intervals8));
        int result8 = solver.eraseOverlapIntervals(intervals8);
        boolean test8Pass = result8 == expected8;
        solver.printResult("Test 8 (Unordered)", result8, expected8, test8Pass);
        totalTests++;
        if (test8Pass)
            passedTests++;

        // Test Case 9: Negative numbers
        System.out.println("\n==================== Test Case 9 ====================");
        int[][] intervals9 = { { -10, -5 }, { -5, 0 }, { 0, 5 } };
        int expected9 = 0;
        System.out.println("Scenario: Negative numbers, non-overlapping");
        System.out.println("Input: " + Arrays.deepToString(intervals9));
        int result9 = solver.eraseOverlapIntervals(intervals9);
        boolean test9Pass = result9 == expected9;
        solver.printResult("Test 9 (Negative numbers)", result9, expected9, test9Pass);
        totalTests++;
        if (test9Pass)
            passedTests++;

        // Test Case 10: Complex overlapping pattern
        System.out.println("\n==================== Test Case 10 ====================");
        int[][] intervals10 = { { 1, 10 }, { 3, 4 }, { 5, 6 }, { 7, 8 } };
        int expected10 = 3;
        System.out.println("Scenario: One large interval overlaps multiple smaller");
        System.out.println("Input: " + Arrays.deepToString(intervals10));
        int result10 = solver.eraseOverlapIntervals(intervals10);
        boolean test10Pass = result10 == expected10;
        solver.printResult("Test 10 (One large overlapping)", result10, expected10, test10Pass);
        totalTests++;
        if (test10Pass)
            passedTests++;

        // Test Case 11: Complex pattern with negative numbers
        System.out.println("\n==================== Test Case 11 ====================");
        int[][] intervals11 = { { -52, 31 }, { -73, -26 }, { 82, 97 }, { -65, -11 }, { -62, -49 },
                { 95, 99 }, { 58, 95 }, { -31, 49 }, { 66, 98 }, { -63, 2 },
                { 30, 47 }, { -40, -26 } };
        int expected11 = 7;
        System.out.println("Scenario: Complex pattern with negative numbers and many overlaps");
        System.out.println("Input: " + Arrays.deepToString(intervals11));
        int result11 = solver.eraseOverlapIntervals(intervals11);
        boolean test11Pass = result11 == expected11;
        solver.printResult("Test 11 (Complex with negatives)", result11, expected11, test11Pass);
        totalTests++;
        if (test11Pass)
            passedTests++;

        // Summary
        System.out.println("\n==================== Summary ====================");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("Success Rate: " + (passedTests * 100 / totalTests) + "%");
    }
}

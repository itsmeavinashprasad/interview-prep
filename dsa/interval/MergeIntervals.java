package dsa.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <h1>56. Merge Intervals</h1>
 *
 * <p>
 * Given an array of {@code intervals} where {@code intervals[i] = [start_i,
 * end_i]}, merge all overlapping intervals, and return an array of the
 * non-overlapping intervals that cover all the intervals in the input.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> intervals = [[1,3],[2,6],[8,10],[15,18]]
 * <b>Output:</b> [[1,6],[8,10],[15,18]]
 * <b>Explanation:</b> Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 *
 * <b>Input:</b> intervals = [[1,4],[4,5]]
 * <b>Output:</b> [[1,5]]
 * <b>Explanation:</b> Intervals [1,4] and [4,5] are considered overlapping.
 * 
 * <b>Input:</b> intervals = [[4,7],[1,4]]
 * <b>Output:</b> [[1,7]]
 * <b>Explanation:</b> Intervals [1,4] and [4,7] are considered overlapping.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 1 <= intervals.length <= 10^4}</li>
 * <li>{@code intervals[i].length == 2}</li>
 * <li>{@code 0 <= start_i <= end_i <= 10^4}</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/merge-intervals/">LeetCode -
 *      Merge
 *      Intervals</a>
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        List<int[]> ans = new ArrayList<>();

        // sort the intervals first
        // remember this one single line to sort
        Arrays.sort(intervals, Comparator.comparing((int arr[]) -> arr[0]));

        ans.add(intervals[0]);
        for (int i = 1; i < n; i++) {

            if (ans.getLast()[1] >= intervals[i][0]) {
                // if current interval can be part of last interval, then merge it
                ans.getLast()[1] = Math.max(ans.getLast()[1], intervals[i][1]);
            } else {
                // else add as a new one
                ans.add(intervals[i]);
            }
        }
        return ans.toArray(new int[0][]);
    }

    private boolean arraysEqual(int[][] actual, int[][] expected) {
        if (actual.length != expected.length)
            return false;
        for (int i = 0; i < actual.length; i++) {
            if (actual[i].length != expected[i].length)
                return false;
            for (int j = 0; j < actual[i].length; j++) {
                if (actual[i][j] != expected[i][j])
                    return false;
            }
        }
        return true;
    }

    private void printResult(String testName, int[][] actual, int[][] expected, boolean passed) {
        System.out.println(testName + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Output:   " + Arrays.deepToString(actual));
        System.out.println("  Expected: " + Arrays.deepToString(expected));
    }

    public static void main(String[] args) {
        MergeIntervals solver = new MergeIntervals();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Standard example with multiple overlaps
        System.out.println("==================== Test Case 1 ====================");
        int[][] intervals1 = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        int[][] expected1 = { { 1, 6 }, { 8, 10 }, { 15, 18 } };
        System.out.println("Scenario: Multiple overlapping intervals");
        System.out.println("Input: " + Arrays.deepToString(intervals1));
        int[][] result1 = solver.merge(intervals1);
        boolean test1Pass = solver.arraysEqual(result1, expected1);
        solver.printResult("Test 1 (Multiple overlaps)", result1, expected1, test1Pass);
        totalTests++;
        if (test1Pass)
            passedTests++;

        // Test Case 2: Touching intervals (should merge)
        System.out.println("\n==================== Test Case 2 ====================");
        int[][] intervals2 = { { 1, 4 }, { 4, 5 } };
        int[][] expected2 = { { 1, 5 } };
        System.out.println("Scenario: Intervals touching at boundary");
        System.out.println("Input: " + Arrays.deepToString(intervals2));
        int[][] result2 = solver.merge(intervals2);
        boolean test2Pass = solver.arraysEqual(result2, expected2);
        solver.printResult("Test 2 (Touching intervals)", result2, expected2, test2Pass);
        totalTests++;
        if (test2Pass)
            passedTests++;

        // Test Case 3: Unordered input
        System.out.println("\n==================== Test Case 3 ====================");
        int[][] intervals3 = { { 4, 7 }, { 1, 4 } };
        int[][] expected3 = { { 1, 7 } };
        System.out.println("Scenario: Unordered intervals");
        System.out.println("Input: " + Arrays.deepToString(intervals3));
        int[][] result3 = solver.merge(intervals3);
        boolean test3Pass = solver.arraysEqual(result3, expected3);
        solver.printResult("Test 3 (Unordered input)", result3, expected3, test3Pass);
        totalTests++;
        if (test3Pass)
            passedTests++;

        // Test Case 4: No overlapping intervals
        System.out.println("\n==================== Test Case 4 ====================");
        int[][] intervals4 = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        int[][] expected4 = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        System.out.println("Scenario: No overlapping intervals");
        System.out.println("Input: " + Arrays.deepToString(intervals4));
        int[][] result4 = solver.merge(intervals4);
        boolean test4Pass = solver.arraysEqual(result4, expected4);
        solver.printResult("Test 4 (No overlaps)", result4, expected4, test4Pass);
        totalTests++;
        if (test4Pass)
            passedTests++;

        // Test Case 5: All intervals overlap (fully contained)
        System.out.println("\n==================== Test Case 5 ====================");
        int[][] intervals5 = { { 1, 10 }, { 2, 5 }, { 3, 7 } };
        int[][] expected5 = { { 1, 10 } };
        System.out.println("Scenario: Intervals fully contained in largest");
        System.out.println("Input: " + Arrays.deepToString(intervals5));
        int[][] result5 = solver.merge(intervals5);
        boolean test5Pass = solver.arraysEqual(result5, expected5);
        solver.printResult("Test 5 (Fully contained)", result5, expected5, test5Pass);
        totalTests++;
        if (test5Pass)
            passedTests++;

        // Test Case 6: Single interval
        System.out.println("\n==================== Test Case 6 ====================");
        int[][] intervals6 = { { 0, 0 } };
        int[][] expected6 = { { 0, 0 } };
        System.out.println("Scenario: Single interval");
        System.out.println("Input: " + Arrays.deepToString(intervals6));
        int[][] result6 = solver.merge(intervals6);
        boolean test6Pass = solver.arraysEqual(result6, expected6);
        solver.printResult("Test 6 (Single interval)", result6, expected6, test6Pass);
        totalTests++;
        if (test6Pass)
            passedTests++;

        // Test Case 7: Overlapping chain
        System.out.println("\n==================== Test Case 7 ====================");
        int[][] intervals7 = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
        int[][] expected7 = { { 1, 5 } };
        System.out.println("Scenario: Chain of overlapping intervals");
        System.out.println("Input: " + Arrays.deepToString(intervals7));
        int[][] result7 = solver.merge(intervals7);
        boolean test7Pass = solver.arraysEqual(result7, expected7);
        solver.printResult("Test 7 (Overlapping chain)", result7, expected7, test7Pass);
        totalTests++;
        if (test7Pass)
            passedTests++;

        // Test Case 8: Duplicate intervals
        System.out.println("\n==================== Test Case 8 ====================");
        int[][] intervals8 = { { 1, 4 }, { 1, 4 }, { 1, 4 } };
        int[][] expected8 = { { 1, 4 } };
        System.out.println("Scenario: Duplicate intervals");
        System.out.println("Input: " + Arrays.deepToString(intervals8));
        int[][] result8 = solver.merge(intervals8);
        boolean test8Pass = solver.arraysEqual(result8, expected8);
        solver.printResult("Test 8 (Duplicates)", result8, expected8, test8Pass);
        totalTests++;
        if (test8Pass)
            passedTests++;

        // Test Case 9: Small 2-interval example
        System.out.println("\n==================== Test Case 9 ====================");
        int[][] intervals9 = { { 2, 3 }, { 1, 4 } };
        int[][] expected9 = { { 1, 4 } };
        System.out.println("Scenario: Two overlapping intervals");
        System.out.println("Input: " + Arrays.deepToString(intervals9));
        int[][] result9 = solver.merge(intervals9);
        boolean test9Pass = solver.arraysEqual(result9, expected9);
        solver.printResult("Test 9 (Two overlapping)", result9, expected9, test9Pass);
        totalTests++;
        if (test9Pass)
            passedTests++;

        // Test Case 10: Large range values
        System.out.println("\n==================== Test Case 10 ====================");
        int[][] intervals10 = { { 0, 10000 }, { 5000, 7000 }, { 9000, 9500 } };
        int[][] expected10 = { { 0, 10000 } };
        System.out.println("Scenario: Large range values");
        System.out.println("Input: " + Arrays.deepToString(intervals10));
        int[][] result10 = solver.merge(intervals10);
        boolean test10Pass = solver.arraysEqual(result10, expected10);
        solver.printResult("Test 10 (Large ranges)", result10, expected10, test10Pass);
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

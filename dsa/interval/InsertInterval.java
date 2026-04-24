package dsa.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>57. Insert Interval</h1>
 *
 * <p>
 * You are given an array of non-overlapping intervals {@code intervals} where
 * {@code intervals[i] = [start_i, end_i]} represent the start and the end of
 * the {@code i}th interval and {@code intervals} is sorted in ascending order
 * by
 * {@code start_i}. You are also given an interval {@code newInterval = [start,
 * end]} that represents the start and end of another interval.
 * </p>
 * <p>
 * Insert {@code newInterval} into {@code intervals} such that {@code intervals}
 * is still sorted in ascending order by {@code start_i} and {@code intervals}
 * still does not have any overlapping intervals (merge overlapping intervals if
 * necessary).
 * </p>
 * <p>
 * Return {@code intervals} after the insertion. Note that you don't need to
 * modify {@code intervals} in-place. You can make a new array and return it.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> intervals = [[1,3],[6,9]], newInterval = [2,5]
 * <b>Output:</b> [[1,5],[6,9]]
 *
 * <b>Input:</b> intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * <b>Output:</b> [[1,2],[3,10],[12,16]]
 * <b>Explanation:</b> Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 0 <= intervals.length <= 10^4}</li>
 * <li>{@code intervals[i].length == 2}</li>
 * <li>{@code 0 <= start_i <= end_i <= 10^5}</li>
 * <li>{@code intervals} is sorted by {@code start_i} in ascending order.</li>
 * <li>{@code newInterval.length == 2}</li>
 * <li>{@code 0 <= start <= end <= 10^5}</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/insert-interval/">LeetCode -
 *      Insert
 *      Interval</a>
 */
public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {

        int n = intervals.length;
        List<int[]> list = new ArrayList<>();
        int i = 0;

        // zone 1: intervals before new interval
        while (i < n && intervals[i][1] < newInterval[0]) {
            list.add(intervals[i]);
            i++;
        }

        // zone 2: interval can be merged
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        list.add(new int[] { newInterval[0], newInterval[1] });

        // zone 3: intervals after new interval
        while (i < n) {
            list.add(intervals[i]);
            i++;
        }

        return list.toArray(new int[0][]);
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

    private void printTestCase(int[][] intervals, int[] newInterval) {
        System.out.println("Intervals: " + Arrays.deepToString(intervals));
        System.out.println("NewInterval: " + Arrays.toString(newInterval));
    }

    public static void main(String[] args) {
        InsertInterval solver = new InsertInterval();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Insert interval overlaps with one interval
        System.out.println("==================== Test Case 1 ====================");
        int[][] intervals1 = { { 1, 3 }, { 6, 9 } };
        int[] newInterval1 = { 2, 5 };
        int[][] expected1 = { { 1, 5 }, { 6, 9 } };
        System.out.println("Scenario: New interval overlaps with first interval");
        solver.printTestCase(intervals1, newInterval1);
        int[][] result1 = solver.insert(intervals1, newInterval1);
        boolean test1Pass = solver.arraysEqual(result1, expected1);
        solver.printResult("Test 1 (Simple overlap)", result1, expected1, test1Pass);
        totalTests++;
        if (test1Pass)
            passedTests++;

        // Test Case 2: Insert interval overlaps with multiple intervals
        System.out.println("\n==================== Test Case 2 ====================");
        int[][] intervals2 = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
        int[] newInterval2 = { 4, 8 };
        int[][] expected2 = { { 1, 2 }, { 3, 10 }, { 12, 16 } };
        System.out.println("Scenario: New interval overlaps with multiple intervals");
        solver.printTestCase(intervals2, newInterval2);
        int[][] result2 = solver.insert(intervals2, newInterval2);
        boolean test2Pass = solver.arraysEqual(result2, expected2);
        solver.printResult("Test 2 (Multiple overlaps)", result2, expected2, test2Pass);
        totalTests++;
        if (test2Pass)
            passedTests++;

        // Test Case 3: Insert interval before all intervals
        System.out.println("\n==================== Test Case 3 ====================");
        int[][] intervals3 = { { 5, 7 }, { 8, 10 } };
        int[] newInterval3 = { 1, 2 };
        int[][] expected3 = { { 1, 2 }, { 5, 7 }, { 8, 10 } };
        System.out.println("Scenario: New interval is completely before all intervals");
        solver.printTestCase(intervals3, newInterval3);
        int[][] result3 = solver.insert(intervals3, newInterval3);
        boolean test3Pass = solver.arraysEqual(result3, expected3);
        solver.printResult("Test 3 (Insert before)", result3, expected3, test3Pass);
        totalTests++;
        if (test3Pass)
            passedTests++;

        // Test Case 4: Insert interval after all intervals
        System.out.println("\n==================== Test Case 4 ====================");
        int[][] intervals4 = { { 1, 2 }, { 3, 5 } };
        int[] newInterval4 = { 6, 8 };
        int[][] expected4 = { { 1, 2 }, { 3, 5 }, { 6, 8 } };
        System.out.println("Scenario: New interval is completely after all intervals");
        solver.printTestCase(intervals4, newInterval4);
        int[][] result4 = solver.insert(intervals4, newInterval4);
        boolean test4Pass = solver.arraysEqual(result4, expected4);
        solver.printResult("Test 4 (Insert after)", result4, expected4, test4Pass);
        totalTests++;
        if (test4Pass)
            passedTests++;

        // Test Case 5: Insert into empty intervals array
        System.out.println("\n==================== Test Case 5 ====================");
        int[][] intervals5 = {};
        int[] newInterval5 = { 5, 7 };
        int[][] expected5 = { { 5, 7 } };
        System.out.println("Scenario: Intervals array is empty");
        solver.printTestCase(intervals5, newInterval5);
        int[][] result5 = solver.insert(intervals5, newInterval5);
        boolean test5Pass = solver.arraysEqual(result5, expected5);
        solver.printResult("Test 5 (Empty intervals)", result5, expected5, test5Pass);
        totalTests++;
        if (test5Pass)
            passedTests++;

        // Test Case 6: New interval completely contains an existing interval
        System.out.println("\n==================== Test Case 6 ====================");
        int[][] intervals6 = { { 1, 5 }, { 6, 9 } };
        int[] newInterval6 = { 2, 8 };
        int[][] expected6 = { { 1, 9 } };
        System.out.println("Scenario: New interval spans across multiple intervals");
        solver.printTestCase(intervals6, newInterval6);
        int[][] result6 = solver.insert(intervals6, newInterval6);
        boolean test6Pass = solver.arraysEqual(result6, expected6);
        solver.printResult("Test 6 (Spanning overlap)", result6, expected6, test6Pass);
        totalTests++;
        if (test6Pass)
            passedTests++;

        // Test Case 7: New interval fits exactly between intervals (no overlap)
        System.out.println("\n==================== Test Case 7 ====================");
        int[][] intervals7 = { { 1, 2 }, { 5, 6 } };
        int[] newInterval7 = { 3, 4 };
        int[][] expected7 = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        System.out.println("Scenario: New interval fits in gap (no overlap)");
        solver.printTestCase(intervals7, newInterval7);
        int[][] result7 = solver.insert(intervals7, newInterval7);
        boolean test7Pass = solver.arraysEqual(result7, expected7);
        solver.printResult("Test 7 (Fits in gap)", result7, expected7, test7Pass);
        totalTests++;
        if (test7Pass)
            passedTests++;

        // Test Case 8: New interval touches end of previous interval
        System.out.println("\n==================== Test Case 8 ====================");
        int[][] intervals8 = { { 1, 5 }, { 9, 10 } };
        int[] newInterval8 = { 5, 7 };
        int[][] expected8 = { { 1, 7 }, { 9, 10 } };
        System.out.println("Scenario: New interval starts where previous ends");
        solver.printTestCase(intervals8, newInterval8);
        int[][] result8 = solver.insert(intervals8, newInterval8);
        boolean test8Pass = solver.arraysEqual(result8, expected8);
        solver.printResult("Test 8 (Touch at boundary)", result8, expected8, test8Pass);
        totalTests++;
        if (test8Pass)
            passedTests++;

        // Test Case 9: New interval merges consecutive intervals
        System.out.println("\n==================== Test Case 9 ====================");
        int[][] intervals9 = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        int[] newInterval9 = { 2, 5 };
        int[][] expected9 = { { 1, 6 } };
        System.out.println("Scenario: New interval merges all consecutive intervals");
        solver.printTestCase(intervals9, newInterval9);
        int[][] result9 = solver.insert(intervals9, newInterval9);
        boolean test9Pass = solver.arraysEqual(result9, expected9);
        solver.printResult("Test 9 (Merge consecutive)", result9, expected9, test9Pass);
        totalTests++;
        if (test9Pass)
            passedTests++;

        // Test Case 10: Single existing interval, new interval overlaps
        System.out.println("\n==================== Test Case 10 ====================");
        int[][] intervals10 = { { 2, 5 } };
        int[] newInterval10 = { 1, 6 };
        int[][] expected10 = { { 1, 6 } };
        System.out.println("Scenario: Single interval, new interval engulfs it");
        solver.printTestCase(intervals10, newInterval10);
        int[][] result10 = solver.insert(intervals10, newInterval10);
        boolean test10Pass = solver.arraysEqual(result10, expected10);
        solver.printResult("Test 10 (Engulf single)", result10, expected10, test10Pass);
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

package dsa.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * <h1>56. Merge Intervals</h1>
 *
 * <p>
 * Given an array of {@code intervals} where
 * {@code intervals[i] = [start_i, end_i]},
 * merge all overlapping intervals, and return an array of the non-overlapping
 * intervals that cover all the intervals in the input.
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
 *      Merge Intervals</a>
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        // TODO: implement
        Arrays.sort(intervals, Comparator.comparing((int[] arr) -> arr[0]));
        int n = intervals.length;

        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (intervals[i][1] >= intervals[i + 1][0]) {
                // this should merge
                intervals[i + 1][0] = intervals[i][0];
                intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i + 1][1]);
            } else {
                ans.add(intervals[i]);
            }
        }
        ans.add(intervals[n - 1]);
        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        MergeIntervals solver = new MergeIntervals();

        // Test Case 1: [[1,3],[2,6],[8,10],[15,18]] → Expected: [[1,6],[8,10],[15,18]]
        int[][] intervals1 = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
        System.out.println("Test 1 - Input: " + Arrays.deepToString(intervals1));
        System.out.println("Output:   " + Arrays.deepToString(solver.merge(intervals1)));
        System.out.println("Expected: [[1, 6], [8, 10], [15, 18]]");

        // Test Case 2: [[1,4],[4,5]] → Expected: [[1,5]]
        int[][] intervals2 = { { 4, 5 }, { 1, 4 } };
        System.out.println("\nTest 2 - Input: " + Arrays.deepToString(intervals2));
        System.out.println("Output:   " + Arrays.deepToString(solver.merge(intervals2)));
        System.out.println("Expected: [[1, 5]]");

        // Test Case 3: [[1,3],[2,20],[8,10],[15,18]] → Expected: [[1, 20]]
        int[][] intervals3 = { { 1, 3 }, { 2, 20 }, { 8, 10 }, { 15, 18 } };
        System.out.println("\nTest 3 - Input: [[1, 3], [2, 20], [8, 10], [15, 18]]");
        System.out.println("Output:   " + Arrays.deepToString(solver.merge(intervals3)));
        System.out.println("Expected: [[1, 20]]");
    }
}

package dsa.arrays;

import java.util.Arrays;

/**
 * <h1>Find Missing and Repeated Values</h1>
 * 
 * <p>
 * You are given a <b>0-indexed</b> 2D integer matrix {@code grid} of size
 * {@code n * n}
 * with values in the range {@code [1, n^2]}. Each integer appears <b>exactly
 * once</b>
 * except {@code a} which appears <b>twice</b> and {@code b} which is
 * <b>missing</b>.
 * </p>
 * 
 * <p>
 * The task is to find the repeating and missing numbers {@code a} and
 * {@code b}.
 * Return a <b>0-indexed</b> integer array {@code ans} of size {@code 2} where
 * {@code ans[0]} equals to {@code a} and {@code ans[1]} equals to {@code b}.
 * </p>
 * 
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> grid = [[1,3],[2,2]]
 * <b>Output:</b> [2,4]
 * <b>Explanation:</b> Number 2 is repeated and number 4 is missing so the answer is [2,4].
 * 
 * <b>Input:</b> grid = [[9,1,7],[8,9,2],[3,4,6]]
 * <b>Output:</b> [9,5]
 * <b>Explanation:</b> Number 9 is repeated and number 5 is missing so the answer is [9,5].
 * </pre>
 * 
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 2 <= n == grid.length == grid[i].length <= 50}</li>
 * <li>{@code 1 <= grid[i][j] <= n * n}</li>
 * <li>For all {@code x} that {@code 1 <= x <= n * n}, exactly one {@code x} is
 * missing.</li>
 * <li>For all {@code x} that {@code 1 <= x <= n * n}, exactly one {@code x} is
 * repeated twice.</li>
 * </ul>
 * 
 * @see <a href=
 *      "https://leetcode.com/problems/find-missing-and-repeated-values/">LeetCode
 *      - Find Missing and Repeated Values</a>
 * @see <a href=
 *      "https://takeuforward.org/data-structure/find-the-repeating-and-missing-numbers/">Striver
 *      - Repeating and Missing Numbers</a>
 */
public class FindMissingAndRepeatedValues {

    public int[] findMissingAndRepeatedValues(int[][] grid) {
        // TODO: Implement using Math (Sum of N & N^2) or Frequency Array
        int missing = -1, repeated = -1;
        int n = grid.length;
        for (int i = 0; i < n;) {
            for (int j = 0; j < n && i < n;) {
                int x = grid[i][j];
                // calculate relative row and col position
                int r = (x % n == 0) ? (x / n - 1) : (x / n);
                int c = (x % n == 0) ? (n - 1) : (x % n - 1);
                // if x is already at correct position then skip
                if (r == i && c == j) {
                    if (j == n - 1) {
                        i++;
                        j = 0;
                    } else {
                        j++;
                    }
                    continue;
                }

                if (grid[r][c] == x) {
                    // if calculated cell location already has the element then it is the repeated
                    // one
                    repeated = x;
                    if (j == n - 1) {
                        i++;
                        j = 0;
                    } else {
                        j++;
                    }
                    continue;
                }

                // otherwise put x to correct location
                swap(grid, i, j, r, c);
            }
        }

        boolean missingFound = false;
        for (int i = 0; i < n && !missingFound;) {
            for (int j = 0; j < n && i < n && !missingFound;) {
                int x = grid[i][j];
                // calculate relative row and col position
                int r = (x % n == 0) ? (x / n - 1) : (x / n);
                int c = (x % n == 0) ? (n - 1) : (x % n - 1);
                // if x is already at correct position then skip
                if (r == i && c == j) {
                    if (j == n - 1) {
                        i++;
                        j = 0;
                    } else {
                        j++;
                    }
                    continue;
                } else {
                    missing = i * n + j + 1;
                    missingFound = true;
                }
            }
        }

        return new int[] { repeated, missing };
    }

    private void swap(int[][] grid, int i, int j, int i1, int j1) {
        int t = grid[i][j];
        grid[i][j] = grid[i1][j1];
        grid[i1][j1] = t;
    }

    public static void main(String[] args) {
        FindMissingAndRepeatedValues solver = new FindMissingAndRepeatedValues();

        // Test Case 1
        int[][] grid1 = {
                { 1, 3 },
                { 2, 2 }
        };
        System.out.println("Test Case 1: " + Arrays.deepToString(grid1));
        System.out.println("Output: " + Arrays.toString(solver.findMissingAndRepeatedValues(grid1)));
        System.out.println("Expected: [2, 4]\n");

        // Test Case 2
        int[][] grid2 = {
                { 9, 1, 7 },
                { 8, 9, 2 },
                { 3, 4, 6 }
        };
        System.out.println("Test Case 2: " + Arrays.deepToString(grid2));
        System.out.println("Output: " + Arrays.toString(solver.findMissingAndRepeatedValues(grid2)));
        System.out.println("Expected: [9, 5]\n");
    }
}

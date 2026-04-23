package dsa.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <h1>54. Spiral Matrix</h1>
 *
 * <p>
 * Given an <code>m x n</code> <code>matrix</code>, return all elements of the
 * <code>matrix</code>
 * in spiral order.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 *
 * <b>Example 2:</b>
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>m == matrix.length</li>
 * <li>n == matrix[i].length</li>
 * <li>1 &lt;= m, n &lt;= 10</li>
 * <li>-100 &lt;= matrix[i][j] &lt;= 100</li>
 * </ul>
 *
 * @see <a href=
 *      "https://leetcode.com/problems/spiral-matrix/description/">LeetCode -
 *      Spiral Matrix</a>
 */

public class SpiralMatrix {

    /**
     * Return all elements of the matrix in spiral order
     *
     * @param matrix the m x n integer matrix
     * @return a list of integers in spiral order
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return Collections.emptyList();

        
        // define all boundaries
        int rmin = 0;
        int rmax = matrix.length - 1;
        int cmin = 0;
        int cmax = matrix[0].length - 1;

        // ans holder
        List<Integer> ans = new ArrayList<>();

        while (rmin <= rmax && cmin <= cmax) {
            for (int c = cmin; c <= cmax; c++)
                ans.add(matrix[rmin][c]);
            rmin++;

            for (int r = rmin; r <= rmax; r++)
                ans.add(matrix[r][cmax]);
            cmax--;

            if (rmin <= rmax)
                for (int c = cmax; c >= cmin; c--)
                    ans.add(matrix[rmax][c]);
            rmax--;

            if (cmin <= cmax)
                for (int r = rmax; r >= rmin; r--)
                    ans.add(matrix[r][cmin]);
            cmin++;
        }

        return ans;
    }

    /**
     * Helper method to compare two lists
     *
     * @param list1 first list
     * @param list2 second list
     * @return true if lists are equal, false otherwise
     */
    private boolean listsEqual(List<Integer> list1, List<Integer> list2) {
        if (list1.size() != list2.size())
            return false;
        for (int i = 0; i < list1.size(); i++) {
            if (!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SpiralMatrix solution = new SpiralMatrix();

        // Test case 1: 3x3 matrix
        int[][] matrix1 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
        List<Integer> expected1 = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);
        List<Integer> result1 = solution.spiralOrder(matrix1);
        System.out.println(solution.listsEqual(result1, expected1) ? "Test 1a - Pass" : "Test 1a - Fail");

        // Test case 2: 3x4 matrix
        int[][] matrix2 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 } };
        List<Integer> expected2 = Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7);
        List<Integer> result2 = solution.spiralOrder(matrix2);
        System.out.println(solution.listsEqual(result2, expected2) ? "Test 2a - Pass" : "Test 2a - Fail");

        // Test case 3: 1x1 matrix (single element)
        int[][] matrix3 = { { 1 } };
        List<Integer> expected3 = Arrays.asList(1);
        List<Integer> result3 = solution.spiralOrder(matrix3);
        System.out.println(solution.listsEqual(result3, expected3) ? "Test 3a - Pass" : "Test 3a - Fail");

        // Test case 4: 1x4 matrix (single row)
        int[][] matrix4 = { { 1, 2, 3, 4 } };
        List<Integer> expected4 = Arrays.asList(1, 2, 3, 4);
        List<Integer> result4 = solution.spiralOrder(matrix4);
        System.out.println(solution.listsEqual(result4, expected4) ? "Test 4a - Pass" : "Test 4a - Fail");

        // Test case 5: 4x1 matrix (single column)
        int[][] matrix5 = { { 1 }, { 2 }, { 3 }, { 4 } };
        List<Integer> expected5 = Arrays.asList(1, 2, 3, 4);
        List<Integer> result5 = solution.spiralOrder(matrix5);
        System.out.println(solution.listsEqual(result5, expected5) ? "Test 5a - Pass" : "Test 5a - Fail");

        // Test case 6: 2x2 matrix
        int[][] matrix6 = { { 1, 2 }, { 3, 4 } };
        List<Integer> expected6 = Arrays.asList(1, 2, 4, 3);
        List<Integer> result6 = solution.spiralOrder(matrix6);
        System.out.println(solution.listsEqual(result6, expected6) ? "Test 6a - Pass" : "Test 6a - Fail");

        // Test case 7: 4x4 matrix
        int[][] matrix7 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        List<Integer> expected7 = Arrays.asList(1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10);
        List<Integer> result7 = solution.spiralOrder(matrix7);
        System.out.println(solution.listsEqual(result7, expected7) ? "Test 7a - Pass" : "Test 7a - Fail");

        // Test case 8: 2x4 matrix
        int[][] matrix8 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 } };
        List<Integer> expected8 = Arrays.asList(1, 2, 3, 4, 8, 7, 6, 5);
        List<Integer> result8 = solution.spiralOrder(matrix8);
        System.out.println(solution.listsEqual(result8, expected8) ? "Test 8a - Pass" : "Test 8a - Fail");

        // Test case 9: 4x2 matrix
        int[][] matrix9 = { { 1, 2 }, { 3, 4 }, { 5, 6 }, { 7, 8 } };
        List<Integer> expected9 = Arrays.asList(1, 2, 4, 6, 8, 7, 5, 3);
        List<Integer> result9 = solution.spiralOrder(matrix9);
        System.out.println(solution.listsEqual(result9, expected9) ? "Test 9a - Pass" : "Test 9a - Fail");

        // Test case 10: 5x5 matrix
        int[][] matrix10 = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 }, { 16, 17, 18, 19, 20 },
                { 21, 22, 23, 24, 25 } };
        List<Integer> expected10 = Arrays.asList(1, 2, 3, 4, 5, 10, 15, 20, 25, 24, 23, 22, 21, 16, 11, 6, 7, 8, 9, 14,
                19, 18, 17, 12, 13);
        List<Integer> result10 = solution.spiralOrder(matrix10);
        System.out.println(solution.listsEqual(result10, expected10) ? "Test 10a - Pass" : "Test 10a - Fail");

        // Test case 11: 3x5 matrix
        int[][] matrix11 = { { 1, 2, 3, 4, 5 }, { 6, 7, 8, 9, 10 }, { 11, 12, 13, 14, 15 } };
        List<Integer> expected11 = Arrays.asList(1, 2, 3, 4, 5, 10, 15, 14, 13, 12, 11, 6, 7, 8, 9);
        List<Integer> result11 = solution.spiralOrder(matrix11);
        System.out.println(solution.listsEqual(result11, expected11) ? "Test 11a - Pass" : "Test 11a - Fail");

        // Test case 12: 5x3 matrix
        int[][] matrix12 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 }, { 13, 14, 15 } };
        List<Integer> expected12 = Arrays.asList(1, 2, 3, 6, 9, 12, 15, 14, 13, 10, 7, 4, 5, 8, 11);
        List<Integer> result12 = solution.spiralOrder(matrix12);
        System.out.println(solution.listsEqual(result12, expected12) ? "Test 12a - Pass" : "Test 12a - Fail");

        // Test case 13: Matrix with negative numbers
        int[][] matrix13 = { { -1, -2, -3 }, { -4, -5, -6 }, { -7, -8, -9 } };
        List<Integer> expected13 = Arrays.asList(-1, -2, -3, -6, -9, -8, -7, -4, -5);
        List<Integer> result13 = solution.spiralOrder(matrix13);
        System.out.println(solution.listsEqual(result13, expected13) ? "Test 13a - Pass" : "Test 13a - Fail");

        // Test case 14: 2x3 matrix
        int[][] matrix14 = { { 1, 2, 3 }, { 4, 5, 6 } };
        List<Integer> expected14 = Arrays.asList(1, 2, 3, 6, 5, 4);
        List<Integer> result14 = solution.spiralOrder(matrix14);
        System.out.println(solution.listsEqual(result14, expected14) ? "Test 14a - Pass" : "Test 14a - Fail");

        // Test case 15: 3x2 matrix
        int[][] matrix15 = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
        List<Integer> expected15 = Arrays.asList(1, 2, 4, 6, 5, 3);
        List<Integer> result15 = solution.spiralOrder(matrix15);
        System.out.println(solution.listsEqual(result15, expected15) ? "Test 15a - Pass" : "Test 15a - Fail");
    }
}

package dsa.matrix;

import java.util.Arrays;

/**
 * <h1>73. Set Matrix Zeroes</h1>
 *
 * <p>
 * Given an <code>m x n</code> integer matrix <code>matrix</code>, if an element
 * is <code>0</code>,
 * set its entire row and column to <code>0</code>'s.
 * </p>
 *
 * <p>
 * You must do it <b>in place</b>.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * Output: [[1,0,1],[0,0,0],[1,0,1]]
 *
 * <b>Example 2:</b>
 * Input: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * Output: [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>m == matrix.length</li>
 * <li>n == matrix[0].length</li>
 * <li>1 &lt;= m, n &lt;= 200</li>
 * <li>-2^31 &lt;= matrix[i][j] &lt;= 2^31 - 1</li>
 * </ul>
 *
 * <h2>Follow up:</h2>
 * <ul>
 * <li>A straightforward solution using O(mn) space is probably a bad idea.</li>
 * <li>A simple improvement uses O(m + n) space, but still not the best
 * solution.</li>
 * <li>Could you devise a constant space solution?</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/set-matrix-zeroes/">LeetCode -
 *      Set Matrix Zeroes</a>
 */

public class SetMatrixZeroes {

    /**
     * Set entire row and column to zeros if an element is 0
     *
     * @param matrix the m x n integer matrix to modify in place
     */
    public void setZeroes(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;

        // check if in the row[0] anything is 0
        boolean firstRowHasZero = false;
        for (int c = 0; c < col; c++) {
            if (matrix[0][c] == 0) {
                firstRowHasZero = true;
                break;
            }
        }

        // check if in the col[0] anything is 0
        boolean firstColHasZero = false;
        for (int r = 0; r < row; r++) {
            if (matrix[r][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }

        // traverse all coloumns one by one and consider row[0] and col[0] as key to
        // store
        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                if (matrix[r][c] == 0) {
                    // set corresponding row[0] and col[0] as 0
                    matrix[0][c] = 0;
                    matrix[r][0] = 0;
                }
            }
        }

        // use the row[0] and col[0] map to convert the entire map
        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                if (matrix[0][c] == 0 || matrix[r][0] == 0) {
                    matrix[r][c] = 0;
                }
            }
        }

        // at the end use those 2 flags to convert row[0] and col[0] if required
        if (firstRowHasZero) {
            for (int c = 0; c < col; c++) {
                matrix[0][c] = 0;
            }
        }
        if (firstColHasZero) {
            for (int r = 0; r < row; r++) {
                matrix[r][0] = 0;
            }
        }

    }

    /**
     * Helper method to make a deep copy of a matrix
     *
     * @param matrix the matrix to copy
     * @return a deep copy of the matrix
     */
    private int[][] copyMatrix(int[][] matrix) {
        int[][] copy = new int[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            copy[i] = matrix[i].clone();
        }
        return copy;
    }

    /**
     * Helper method to compare two matrices
     *
     * @param matrix1 first matrix
     * @param matrix2 second matrix
     * @return true if matrices are equal, false otherwise
     */
    private boolean matricesEqual(int[][] matrix1, int[][] matrix2) {
        if (matrix1.length != matrix2.length)
            return false;
        for (int i = 0; i < matrix1.length; i++) {
            if (!Arrays.equals(matrix1[i], matrix2[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SetMatrixZeroes solution = new SetMatrixZeroes();

        // Test case 1: Basic 3x3 with zero in center
        int[][] matrix1 = { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } };
        int[][] expected1 = { { 1, 0, 1 }, { 0, 0, 0 }, { 1, 0, 1 } };
        int[][] copy1 = solution.copyMatrix(matrix1);
        solution.setZeroes(copy1);
        System.out.println(solution.matricesEqual(copy1, expected1) ? "Test 1a - Pass" : "Test 1a - Fail");

        // Test case 2: 3x4 with multiple zeros
        int[][] matrix2 = { { 0, 1, 2, 0 }, { 3, 4, 5, 2 }, { 1, 3, 1, 5 } };
        int[][] expected2 = { { 0, 0, 0, 0 }, { 0, 4, 5, 0 }, { 0, 3, 1, 0 } };
        int[][] copy2 = solution.copyMatrix(matrix2);
        solution.setZeroes(copy2);
        System.out.println(solution.matricesEqual(copy2, expected2) ? "Test 2a - Pass" : "Test 2a - Fail");

        // Test case 3: Single element with zero [0]
        int[][] matrix3 = { { 0 } };
        int[][] expected3 = { { 0 } };
        int[][] copy3 = solution.copyMatrix(matrix3);
        solution.setZeroes(copy3);
        System.out.println(solution.matricesEqual(copy3, expected3) ? "Test 3a - Pass" : "Test 3a - Fail");

        // Test case 4: Single element without zero [1]
        int[][] matrix4 = { { 1 } };
        int[][] expected4 = { { 1 } };
        int[][] copy4 = solution.copyMatrix(matrix4);
        solution.setZeroes(copy4);
        System.out.println(solution.matricesEqual(copy4, expected4) ? "Test 4a - Pass" : "Test 4a - Fail");

        // Test case 5: All zeros 2x2
        int[][] matrix5 = { { 0, 0 }, { 0, 0 } };
        int[][] expected5 = { { 0, 0 }, { 0, 0 } };
        int[][] copy5 = solution.copyMatrix(matrix5);
        solution.setZeroes(copy5);
        System.out.println(solution.matricesEqual(copy5, expected5) ? "Test 5a - Pass" : "Test 5a - Fail");

        // Test case 6: No zeros 2x2
        int[][] matrix6 = { { 1, 2 }, { 3, 4 } };
        int[][] expected6 = { { 1, 2 }, { 3, 4 } };
        int[][] copy6 = solution.copyMatrix(matrix6);
        solution.setZeroes(copy6);
        System.out.println(solution.matricesEqual(copy6, expected6) ? "Test 6a - Pass" : "Test 6a - Fail");

        // Test case 7: Zero in first row
        int[][] matrix7 = { { 0, 1, 2 }, { 3, 4, 5 } };
        int[][] expected7 = { { 0, 0, 0 }, { 0, 4, 5 } };
        int[][] copy7 = solution.copyMatrix(matrix7);
        solution.setZeroes(copy7);
        System.out.println(solution.matricesEqual(copy7, expected7) ? "Test 7a - Pass" : "Test 7a - Fail");

        // Test case 8: Zero in last column
        int[][] matrix8 = { { 1, 2, 0 }, { 3, 4, 5 } };
        int[][] expected8 = { { 0, 0, 0 }, { 3, 4, 0 } };
        int[][] copy8 = solution.copyMatrix(matrix8);
        solution.setZeroes(copy8);
        System.out.println(solution.matricesEqual(copy8, expected8) ? "Test 8a - Pass" : "Test 8a - Fail");

        // Test case 9: Zero at top-left corner
        int[][] matrix9 = { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 } };
        int[][] expected9 = { { 0, 0, 0 }, { 0, 4, 5 }, { 0, 7, 8 } };
        int[][] copy9 = solution.copyMatrix(matrix9);
        solution.setZeroes(copy9);
        System.out.println(solution.matricesEqual(copy9, expected9) ? "Test 9a - Pass" : "Test 9a - Fail");

        // Test case 10: Zero at bottom-right corner
        int[][] matrix10 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } };
        int[][] expected10 = { { 1, 2, 0 }, { 4, 5, 0 }, { 0, 0, 0 } };
        int[][] copy10 = solution.copyMatrix(matrix10);
        solution.setZeroes(copy10);
        System.out.println(solution.matricesEqual(copy10, expected10) ? "Test 10a - Pass" : "Test 10a - Fail");

        // Test case 11: Multiple zeros in different rows and columns
        int[][] matrix11 = { { 1, 0, 3 }, { 0, 5, 6 }, { 7, 8, 0 } };
        int[][] expected11 = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        int[][] copy11 = solution.copyMatrix(matrix11);
        solution.setZeroes(copy11);
        System.out.println(solution.matricesEqual(copy11, expected11) ? "Test 11a - Pass" : "Test 11a - Fail");

        // Test case 12: 1x4 row with one zero
        int[][] matrix12 = { { 1, 0, 3, 4 } };
        int[][] expected12 = { { 0, 0, 0, 0 } };
        int[][] copy12 = solution.copyMatrix(matrix12);
        solution.setZeroes(copy12);
        System.out.println(solution.matricesEqual(copy12, expected12) ? "Test 12a - Pass" : "Test 12a - Fail");

        // Test case 13: 4x1 column with one zero
        int[][] matrix13 = { { 1 }, { 0 }, { 3 }, { 4 } };
        int[][] expected13 = { { 0 }, { 0 }, { 0 }, { 0 } };
        int[][] copy13 = solution.copyMatrix(matrix13);
        solution.setZeroes(copy13);
        System.out.println(solution.matricesEqual(copy13, expected13) ? "Test 13a - Pass" : "Test 13a - Fail");

        // Test case 14: Negative numbers with zero
        int[][] matrix14 = { { -1, 2, -3 }, { 4, 0, 6 }, { -7, 8, 9 } };
        int[][] expected14 = { { -1, 0, -3 }, { 0, 0, 0 }, { -7, 0, 9 } };
        int[][] copy14 = solution.copyMatrix(matrix14);
        solution.setZeroes(copy14);
        System.out.println(solution.matricesEqual(copy14, expected14) ? "Test 14a - Pass" : "Test 14a - Fail");

        // Test case 15: Entire first row zeros
        int[][] matrix15 = { { 0, 0, 0 }, { 1, 2, 3 }, { 4, 5, 6 } };
        int[][] expected15 = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
        int[][] copy15 = solution.copyMatrix(matrix15);
        solution.setZeroes(copy15);
        System.out.println(solution.matricesEqual(copy15, expected15) ? "Test 15a - Pass" : "Test 15a - Fail");
    }
}

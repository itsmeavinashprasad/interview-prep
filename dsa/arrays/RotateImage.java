package dsa.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <h1>48. Rotate Image</h1>
 *
 * <p>
 * You are given an {@code n x n} 2D {@code matrix} representing an image,
 * rotate the image by <b>90 degrees (clockwise)</b>.
 * </p>
 * <p>
 * You have to rotate the image <b>in-place</b>, which means you have to modify
 * the input 2D matrix directly. <b>DO NOT</b> allocate another 2D matrix and do
 * the rotation.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * <b>Output:</b> [[7,4,1],[8,5,2],[9,6,3]]
 *
 * <b>Input:</b> matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * <b>Output:</b> [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code n == matrix.length == matrix[i].length}</li>
 * <li>{@code 1 <= n <= 20}</li>
 * <li>{@code -1000 <= matrix[i][j] <= 1000}</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/rotate-image/">LeetCode - Rotate
 *      Image</a>
 */
public class RotateImage {

    public void rotate(int[][] matrix) {
        // TODO: implement
        int rows = matrix.length;
        int cols = matrix.length;

        // transpose
        for (int r = 0; r < rows; r++) {
            for (int c = r; c < cols; c++) {
                int t = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = t;
            }
        }
        // reverse rows
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols / 2; c++) {
                int t = matrix[r][c];
                matrix[r][c] = matrix[r][cols - 1 - c];
                matrix[r][cols - 1 - c] = t;
            }
        }
    }

    public static void main(String[] args) {
        RotateImage solver = new RotateImage();

        // Test Case 1: [[1,2,3],[4,5,6],[7,8,9]] → Expected: [[7,4,1],[8,5,2],[9,6,3]]
        int[][] matrix1 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };
        System.out.println("Test 1 - Input: " + Arrays.deepToString(matrix1));
        solver.rotate(matrix1);
        System.out.println("Output:   " + Arrays.deepToString(matrix1));
        System.out.println("Expected: [[7, 4, 1], [8, 5, 2], [9, 6, 3]]");

        // Test Case 2: [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]] → Expected:
        // [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
        int[][] matrix2 = {
                { 5, 1, 9, 11 },
                { 2, 4, 8, 10 },
                { 13, 3, 6, 7 },
                { 15, 14, 12, 16 }
        };
        System.out.println("\nTest 2 - Input: " + Arrays.deepToString(matrix2));
        solver.rotate(matrix2);
        System.out.println("Output:   " + Arrays.deepToString(matrix2));
        System.out.println("Expected: [[15, 13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7, 10, 11]]");
    }
}

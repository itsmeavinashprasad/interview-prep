package dsa.arrays;

/**
 * <h1>Set Matrix Zeroes</h1>
 * 
 * <p>
 * Given an {@code m x n} integer matrix {@code matrix}, if an element is
 * {@code 0},
 * set its entire row and column to {@code 0}'s. <b>You must do it in place.</b>
 * </p>
 * 
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> matrix = [[1,1,1],[1,0,1],[1,1,1]]
 * <b>Output:</b> [[1,0,1],[0,0,0],[1,0,1]]
 * 
 * <b>Input:</b> matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
 * <b>Output:</b> [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
 * </pre>
 * 
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code m == matrix.length}</li>
 * <li>{@code n == matrix[0].length}</li>
 * <li>{@code 1 <= m, n <= 200}</li>
 * <li>{@code -2^31 <= matrix[i][j] <= 2^31 - 1}</li>
 * </ul>
 * 
 * <h2>Follow up:</h2>
 * <ul>
 * <li>A straightforward solution using {@code O(mn)} space is probably a bad
 * idea.</li>
 * <li>A simple improvement uses {@code O(m + n)} space, but still not the best
 * solution.</li>
 * <li>Could you devise a constant space solution?</li>
 * </ul>
 * 
 * @see <a href=
 *      "https://leetcode.com/problems/set-matrix-zeroes/description/">LeetCode
 *      - Set Matrix Zeroes</a>
 */

public class SetMatrixZeros {
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean col0 = false;
        boolean row0 = false;

        // Step 1: Scan column 0 separately to decide if it needs zeroing
        for (int r = 0; r < rows; r++) {
            if (matrix[r][0] == 0) {
                col0 = true;
                break;
            }
        }
        for (int c = 0; c < cols; c++) {
            if (matrix[0][c] == 0) {
                row0 = true;
                break;
            }
        }

        // Step 2: Scan the rest (c starts from 1), mark row/col flags
        for (int r = 1; r < rows; r++) {
            for (int c = 1; c < cols; c++) {
                // if a cell is 0, mark corresponding row and col flags
                if (matrix[r][c] == 0) {
                    matrix[r][0] = 0;
                    matrix[0][c] = 0;
                }
            }
        }

        // Step 3: Backward pass, update cells based on flags (c starts from 1)
        for (int r = rows - 1; r >= 0; r--) {
            for (int c = cols - 1; c >= 1; c--) {
                // if corresponding row or col flag is 0, then cell has to be 0
                if (matrix[r][0] == 0 || matrix[0][c] == 0) {
                    matrix[r][c] = 0;
                }
            }
        }

        // Step 4: Zero out column 0 last, only if col0 flag is set
        if (col0) {
            for (int r = 0; r < rows; r++) {
                matrix[r][0] = 0;
            }
        }
        if (row0) {
            for (int c = 0; c < cols; c++) {
                matrix[0][c] = 0;
            }
        }
    }

    public static void main(String[] args) {
        SetMatrixZeros solver = new SetMatrixZeros();

        // Test Case 1: matrix = [[1,1,1],[1,0,1],[1,1,1]]
        int[][] matrix1 = {
                { 1, 1, 1 },
                { 1, 0, 1 },
                { 1, 1, 1 }
        };
        System.out.println("Test 1 - Input:");
        printMatrix(matrix1);
        solver.setZeroes(matrix1);
        System.out.println("Test 1 - Output:");
        printMatrix(matrix1);

        // Test Case 2: matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
        int[][] matrix2 = {
                { 0, 1, 2, 0 },
                { 3, 4, 5, 2 },
                { 1, 3, 1, 5 }
        };
        System.out.println("\nTest 2 - Input:");
        printMatrix(matrix2);
        solver.setZeroes(matrix2);
        System.out.println("Test 2 - Output:");
        printMatrix(matrix2);

        // Test Case 3: col0 edge case — zero in column 0
        // matrix[1][0] = 0, so row 1 and col 0 should be fully zeroed
        int[][] matrix3 = {
                { 1, 2 },
                { 0, 4 }
        };
        System.out.println("\nTest 3 - Input (col0 edge case):");
        printMatrix(matrix3);
        solver.setZeroes(matrix3);
        System.out.println("Test 3 - Output (expected: [[0,2],[0,0]]):");
        printMatrix(matrix3);

        // Test Case 4: col0 edge case — zero in column 0
        int[][] matrix4 = {
                { 1, 0 }
        };
        System.out.println("\nTest 4 - Input (col0 edge case):");
        printMatrix(matrix4);
        solver.setZeroes(matrix4);
        System.out.println("Test 4 - Output");
        printMatrix(matrix4);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < row.length; i++) {
                sb.append(row[i]);
                if (i < row.length - 1)
                    sb.append(", ");
            }
            sb.append("]");
            System.out.println(sb);
        }
    }
}

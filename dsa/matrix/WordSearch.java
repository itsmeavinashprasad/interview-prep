package dsa.matrix;

import java.util.Arrays;

/**
 * <h1>79. Word Search</h1>
 *
 * <p>
 * Given an {@code m x n} grid of characters {@code board} and a string
 * {@code word}, return {@code true} if {@code word} exists in the grid.
 * </p>
 * <p>
 * The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring. The same
 * letter cell may not be used more than once.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * <b>Output:</b> true
 *
 * <b>Input:</b> board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * <b>Output:</b> true
 * 
 * <b>Input:</b> board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * <b>Output:</b> false
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code m == board.length}</li>
 * <li>{@code n == board[i].length}</li>
 * <li>{@code 1 <= m, n <= 6}</li>
 * <li>{@code 1 <= word.length <= 15}</li>
 * <li>{@code board} and {@code word} consists of only lowercase and uppercase
 * English letters.</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/word-search/">LeetCode - Word
 *      Search</a>
 */
public class WordSearch {

    public boolean exist(char[][] board, String word) {
        // TODO: Complete this function
        // Hint: Use backtracking/DFS to search for the word in the grid
        // Remember: Each cell can only be used once per search path

        if (!preCheck(board, word)) {
            return false;
        }

        int row = board.length;
        int col = board[0].length;

        // 2-d traverse
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {

                // find the target word
                if (find(board, r, c, row, col, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean find(char[][] board, int r, int c, int row, int col, String word, int index) {
        // base condition
        if (word.length() == index) {
            // the word is already found
            return true;
        }
        if (r < 0 || r == row || c < 0 || c == col) {
            // overshoot
            return false;
        }

        char source = board[r][c];
        char target = word.charAt(index);

        if (source != target) {
            // first char does n0t match, return false
            return false;
        }

        // mark current cell as consumed
        board[r][c] = '$';
        // find in all 4 neighbours
        if (find(board, r, c + 1, row, col, word, index + 1) || find(board, r + 1, c, row, col, word, index + 1)
                || find(board, r, c - 1, row, col, word, index + 1)
                || find(board, r - 1, c, row, col, word, index + 1)) {
            return true;
        }
        // if not found, replenish the consumed character
        board[r][c] = source;

        return false;

    }

    private boolean preCheck(char[][] board, String word) {
        int[] boardFreq = new int[128];
        int[] wordFreq = new int[128];

        for (char c : word.toCharArray()) {
            wordFreq[c]++;
        }
        for (char[] c1 : board) {
            for (char c : c1) {
                boardFreq[c]++;
            }
        }

        for (int i = 0; i < boardFreq.length; i++) {
            if (boardFreq[i] < wordFreq[i])
                return false;
        }
        return true;
    }

    private void printResult(String testName, boolean actual, boolean expected) {
        boolean passed = actual == expected;
        System.out.println(testName + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Output:   " + actual);
        System.out.println("  Expected: " + expected);
    }

    private void printBoard(char[][] board) {
        for (char[] row : board) {
            System.out.print("  [");
            for (int i = 0; i < row.length; i++) {
                System.out.print("\"" + row[i] + "\"");
                if (i < row.length - 1)
                    System.out.print(",");
            }
            System.out.println("]");
        }
    }

    public static void main(String[] args) {
        WordSearch solver = new WordSearch();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Standard example - word exists
        System.out.println("==================== Test Case 1 ====================");
        char[][] board1 = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };
        String word1 = "ABCCED";
        System.out.println("Board:");
        solver.printBoard(board1);
        System.out.println("Word: \"" + word1 + "\"");
        boolean result1 = solver.exist(board1, word1);
        solver.printResult("Test 1 (Word exists: ABCCED)", result1, true);
        totalTests++;
        if (result1 == true)
            passedTests++;

        // Test Case 2: Standard example - word exists (alternative path)
        System.out.println("\n==================== Test Case 2 ====================");
        char[][] board2 = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };
        String word2 = "SEE";
        System.out.println("Board:");
        solver.printBoard(board2);
        System.out.println("Word: \"" + word2 + "\"");
        boolean result2 = solver.exist(board2, word2);
        solver.printResult("Test 2 (Word exists: SEE)", result2, true);
        totalTests++;
        if (result2 == true)
            passedTests++;

        // Test Case 3: Standard example - word does not exist (revisits cell)
        System.out.println("\n==================== Test Case 3 ====================");
        char[][] board3 = {
                { 'A', 'B', 'C', 'E' },
                { 'S', 'F', 'C', 'S' },
                { 'A', 'D', 'E', 'E' }
        };
        String word3 = "ABCB";
        System.out.println("Board:");
        solver.printBoard(board3);
        System.out.println("Word: \"" + word3 + "\" (requires revisiting B)");
        boolean result3 = solver.exist(board3, word3);
        solver.printResult("Test 3 (Word not found: ABCB)", result3, false);
        totalTests++;
        if (result3 == false)
            passedTests++;

        // Test Case 4: Single character word
        System.out.println("\n==================== Test Case 4 ====================");
        char[][] board4 = {
                { 'A', 'B' },
                { 'C', 'D' }
        };
        String word4 = "A";
        System.out.println("Board:");
        solver.printBoard(board4);
        System.out.println("Word: \"" + word4 + "\"");
        boolean result4 = solver.exist(board4, word4);
        solver.printResult("Test 4 (Single character: A)", result4, true);
        totalTests++;
        if (result4 == true)
            passedTests++;

        // Test Case 5: Single character not found
        System.out.println("\n==================== Test Case 5 ====================");
        char[][] board5 = {
                { 'A', 'B' },
                { 'C', 'D' }
        };
        String word5 = "Z";
        System.out.println("Board:");
        solver.printBoard(board5);
        System.out.println("Word: \"" + word5 + "\"");
        boolean result5 = solver.exist(board5, word5);
        solver.printResult("Test 5 (Character not found: Z)", result5, false);
        totalTests++;
        if (result5 == false)
            passedTests++;

        // Test Case 6: Vertical path
        System.out.println("\n==================== Test Case 6 ====================");
        char[][] board6 = {
                { 'A' },
                { 'B' },
                { 'C' }
        };
        String word6 = "ABC";
        System.out.println("Board:");
        solver.printBoard(board6);
        System.out.println("Word: \"" + word6 + "\" (vertical path)");
        boolean result6 = solver.exist(board6, word6);
        solver.printResult("Test 6 (Vertical path: ABC)", result6, true);
        totalTests++;
        if (result6 == true)
            passedTests++;

        // Test Case 7: Horizontal path
        System.out.println("\n==================== Test Case 7 ====================");
        char[][] board7 = {
                { 'X', 'Y', 'Z' }
        };
        String word7 = "XYZ";
        System.out.println("Board:");
        solver.printBoard(board7);
        System.out.println("Word: \"" + word7 + "\" (horizontal path)");
        boolean result7 = solver.exist(board7, word7);
        solver.printResult("Test 7 (Horizontal path: XYZ)", result7, true);
        totalTests++;
        if (result7 == true)
            passedTests++;

        // Test Case 8: Word requires complex path with backtracking
        System.out.println("\n==================== Test Case 8 ====================");
        char[][] board8 = {
                { 'A', 'B', 'C' },
                { 'D', 'E', 'F' },
                { 'G', 'H', 'I' }
        };
        String word8 = "ABCDEFGHI";
        System.out.println("Board:");
        solver.printBoard(board8);
        System.out.println("Word: \"" + word8 + "\" (snake path)");
        boolean result8 = solver.exist(board8, word8);
        solver.printResult("Test 8 (Complex path: ABCDEFGHI)", result8, false);
        totalTests++;
        if (result8 == false)
            passedTests++;

        // Test Case 9: Word not found - dead end
        System.out.println("\n==================== Test Case 9 ====================");
        char[][] board9 = {
                { 'A', 'B' },
                { 'C', 'D' }
        };
        String word9 = "ACE";
        System.out.println("Board:");
        solver.printBoard(board9);
        System.out.println("Word: \"" + word9 + "\" (path ends - no E)");
        boolean result9 = solver.exist(board9, word9);
        solver.printResult("Test 9 (Path not found: ACE)", result9, false);
        totalTests++;
        if (result9 == false)
            passedTests++;

        // Test Case 10: Duplicate letters in path
        System.out.println("\n==================== Test Case 10 ====================");
        char[][] board10 = {
                { 'A', 'A', 'A' },
                { 'A', 'A', 'A' },
                { 'A', 'A', 'A' }
        };
        String word10 = "AAAAAAAAA";
        System.out.println("Board:");
        solver.printBoard(board10);
        System.out.println("Word: \"" + word10 + "\" (9 A's, valid path exists)");
        boolean result10 = solver.exist(board10, word10);
        solver.printResult("Test 10 (All same letter: AAAAAAAAA)", result10, true);
        totalTests++;
        if (result10 == true)
            passedTests++;

        // Summary
        System.out.println("\n==================== Summary ====================");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("Success Rate: " + (passedTests * 100 / totalTests) + "%");
    }
}

package dsa.arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Pascal's Triangle</h1>
 *
 * <p>
 * Given an integer {@code numRows}, return the first {@code numRows} of
 * <b>Pascal's triangle</b>.
 * </p>
 *
 * <p>
 * In Pascal's triangle, each number is the sum of the two numbers directly
 * above it.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Input:</b>  numRows = 5
 * <b>Output:</b> [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 *
 * <b>Input:</b>  numRows = 1
 * <b>Output:</b> [[1]]
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 1 <= numRows <= 30}</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/pascals-triangle/">LeetCode -
 *      Pascal's Triangle</a>
 */
public class PascalsTriangle {

    public List<List<Integer>> generate(int numRows) {
        // TODO: implement
        List<List<Integer>> ans = new ArrayList<>();

        for (int r = 0; r < numRows; r++) {
            if (r == 0) {
                ans.add(List.of(1));
                continue;
            }

            List<Integer> temp = new ArrayList<>();
            for (int c = 0; c <= r; c++) {
                if (c == 0 || c == r) {
                    temp.add(1);
                    continue;
                }
                int topl = ans.get(r - 1).get(c - 1);
                int topr = ans.get(r - 1).get(c);
                temp.add(topl + topr);
            }
            ans.add(temp);
        }

        return ans;
    }

    public static void main(String[] args) {
        PascalsTriangle solver = new PascalsTriangle();

        // Test Case 1: numRows = 5
        // Expected: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
        int numRows1 = 5;
        System.out.println("Test 1 - numRows = " + numRows1);
        System.out.println("Output: " + solver.generate(numRows1));

        // Test Case 2: numRows = 1
        // Expected: [[1]]
        int numRows2 = 1;
        System.out.println("\nTest 2 - numRows = " + numRows2);
        System.out.println("Output: " + solver.generate(numRows2));
    }
}

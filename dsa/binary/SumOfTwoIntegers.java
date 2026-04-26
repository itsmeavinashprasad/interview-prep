package dsa.binary;

/**
 * <h1>371. Sum of Two Integers</h1>
 *
 * <p>
 * Given two integers {@code a} and {@code b}, return the sum of the two integers
 * without using the operators {@code +} and {@code -}.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> a = 1, b = 2
 * <b>Output:</b> 3
 *
 * <b>Input:</b> a = 2, b = 3
 * <b>Output:</b> 5
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code -1000 <= a, b <= 1000}</li>
 * </ul>
 *
 * <h2>Hints:</h2>
 * <ul>
 * <li>Use bitwise XOR (^) for addition without carry.</li>
 * <li>Use bitwise AND (&) followed by left shift (<<) for the carry.</li>
 * <li>Repeat until there is no carry left.</li>
 * <li>For negative numbers in Java, use mask to handle 32-bit representation.</li>
 * </ul>
 *
 * @see <a href=
 *      "https://leetcode.com/problems/sum-of-two-integers/">LeetCode - Sum of
 *      Two Integers</a>
 */
public class SumOfTwoIntegers {

    public int getSum(int a, int b) {

        while (b != 0) {
            int carry = (a & b) << 1; // find carry
            a = a ^ b; // sum without carry
            b = carry; // add carry back
        }
        return a;
    }

    private void printResult(String testName, int actual, int expected) {
        boolean passed = actual == expected;
        System.out.println(testName + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Output:   " + actual);
        System.out.println("  Expected: " + expected);
    }

    public static void main(String[] args) {
        SumOfTwoIntegers solver = new SumOfTwoIntegers();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Simple positive numbers
        System.out.println("==================== Test Case 1 ====================");
        int a1 = 1, b1 = 2;
        int expected1 = 3;
        System.out.println("Scenario: Simple positive numbers");
        System.out.println("Input: a = " + a1 + ", b = " + b1);
        int result1 = solver.getSum(a1, b1);
        solver.printResult("Test 1 (Simple)", result1, expected1);
        totalTests++;
        if (result1 == expected1)
            passedTests++;

        // Test Case 2: Another positive example
        System.out.println("\n==================== Test Case 2 ====================");
        int a2 = 2, b2 = 3;
        int expected2 = 5;
        System.out.println("Scenario: Another positive example");
        System.out.println("Input: a = " + a2 + ", b = " + b2);
        int result2 = solver.getSum(a2, b2);
        solver.printResult("Test 2 (Positive)", result2, expected2);
        totalTests++;
        if (result2 == expected2)
            passedTests++;

        // Test Case 3: Zero and positive
        System.out.println("\n==================== Test Case 3 ====================");
        int a3 = 0, b3 = 5;
        int expected3 = 5;
        System.out.println("Scenario: Zero and positive");
        System.out.println("Input: a = " + a3 + ", b = " + b3);
        int result3 = solver.getSum(a3, b3);
        solver.printResult("Test 3 (With zero)", result3, expected3);
        totalTests++;
        if (result3 == expected3)
            passedTests++;

        // Test Case 4: Two zeros
        System.out.println("\n==================== Test Case 4 ====================");
        int a4 = 0, b4 = 0;
        int expected4 = 0;
        System.out.println("Scenario: Two zeros");
        System.out.println("Input: a = " + a4 + ", b = " + b4);
        int result4 = solver.getSum(a4, b4);
        solver.printResult("Test 4 (Two zeros)", result4, expected4);
        totalTests++;
        if (result4 == expected4)
            passedTests++;

        // Test Case 5: Negative and positive
        System.out.println("\n==================== Test Case 5 ====================");
        int a5 = -1, b5 = 1;
        int expected5 = 0;
        System.out.println("Scenario: Negative and positive (sum to zero)");
        System.out.println("Input: a = " + a5 + ", b = " + b5);
        int result5 = solver.getSum(a5, b5);
        solver.printResult("Test 5 (Neg + Pos = 0)", result5, expected5);
        totalTests++;
        if (result5 == expected5)
            passedTests++;

        // Test Case 6: Two negative numbers
        System.out.println("\n==================== Test Case 6 ====================");
        int a6 = -2, b6 = -3;
        int expected6 = -5;
        System.out.println("Scenario: Two negative numbers");
        System.out.println("Input: a = " + a6 + ", b = " + b6);
        int result6 = solver.getSum(a6, b6);
        solver.printResult("Test 6 (Two negative)", result6, expected6);
        totalTests++;
        if (result6 == expected6)
            passedTests++;

        // Test Case 7: Negative result
        System.out.println("\n==================== Test Case 7 ====================");
        int a7 = 5, b7 = -10;
        int expected7 = -5;
        System.out.println("Scenario: Positive and negative (negative result)");
        System.out.println("Input: a = " + a7 + ", b = " + b7);
        int result7 = solver.getSum(a7, b7);
        solver.printResult("Test 7 (Neg result)", result7, expected7);
        totalTests++;
        if (result7 == expected7)
            passedTests++;

        // Test Case 8: Large positive numbers
        System.out.println("\n==================== Test Case 8 ====================");
        int a8 = 500, b8 = 400;
        int expected8 = 900;
        System.out.println("Scenario: Large positive numbers");
        System.out.println("Input: a = " + a8 + ", b = " + b8);
        int result8 = solver.getSum(a8, b8);
        solver.printResult("Test 8 (Large positive)", result8, expected8);
        totalTests++;
        if (result8 == expected8)
            passedTests++;

        // Test Case 9: Boundary negative
        System.out.println("\n==================== Test Case 9 ====================");
        int a9 = -1000, b9 = 500;
        int expected9 = -500;
        System.out.println("Scenario: Boundary negative");
        System.out.println("Input: a = " + a9 + ", b = " + b9);
        int result9 = solver.getSum(a9, b9);
        solver.printResult("Test 9 (Boundary neg)", result9, expected9);
        totalTests++;
        if (result9 == expected9)
            passedTests++;

        // Test Case 10: One negative at boundary
        System.out.println("\n==================== Test Case 10 ====================");
        int a10 = -1, b10 = -999;
        int expected10 = -1000;
        System.out.println("Scenario: Both near boundary negative");
        System.out.println("Input: a = " + a10 + ", b = " + b10);
        int result10 = solver.getSum(a10, b10);
        solver.printResult("Test 10 (Boundary pair)", result10, expected10);
        totalTests++;
        if (result10 == expected10)
            passedTests++;

        // Summary
        System.out.println("\n==================== Summary ====================");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("Success Rate: " + (passedTests * 100 / totalTests) + "%");
    }
}

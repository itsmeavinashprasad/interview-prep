package dsa.interval;

import java.util.Arrays;

/**
 * <h1>Meeting Rooms</h1>
 *
 * <p>
 * Given an array of meeting time intervals where
 * {@code intervals[i] = [start_i, end_i]}.
 * Determine if a person could attend all meetings.
 * </p>
 * <p>
 * Note: Two meetings are considered overlapping if one starts before the other
 * ends.
 * Touching at a boundary (e.g., [1,2] and [2,3]) means the person can attend
 * both.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> intervals = [[1, 5], [3, 8], [6, 10], [12, 15]]
 * <b>Output:</b> false
 * <b>Explanation:</b> Overlapping meetings exist at [1,5] and [3,8], making it impossible to attend all.
 *
 * <b>Input:</b> intervals = [[2, 6], [7, 9], [10, 14], [15, 18]]
 * <b>Output:</b> true
 * <b>Explanation:</b> No overlapping meetings, so all can be attended.
 * 
 * <b>Input:</b> intervals = [[0, 4], [4, 9], [10, 13], [14, 17]]
 * <b>Output:</b> true
 * <b>Explanation:</b> Meetings touching at boundary [0,4] and [4,9] can both be attended.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 0 <= intervals.length <= 10^4}</li>
 * <li>{@code intervals[i].length == 2}</li>
 * <li>{@code 0 <= start_i < end_i <= 10^6}</li>
 * </ul>
 *
 * @see <a href=
 *      "https://takeuforward.org/plus/dsa/problems/meeting-rooms">TakeUForward
 *      - Meeting Rooms</a>
 */
public class MeetingRooms {

    public boolean canAttendAllMeetings(int[][] intervals) {
        if (intervals.length == 0)
            return true;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int[] last = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            if (current[0] < last[1]) {
                // current meeting started before last meeting ended, can not attend current
                return false;
            }
        }
        return true;
    }

    private void printResult(String testName, boolean actual, boolean expected) {
        boolean passed = actual == expected;
        System.out.println(testName + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Output:   " + actual);
        System.out.println("  Expected: " + expected);
    }

    public static void main(String[] args) {
        MeetingRooms solver = new MeetingRooms();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Overlapping meetings
        System.out.println("==================== Test Case 1 ====================");
        int[][] intervals1 = { { 1, 5 }, { 3, 8 }, { 6, 10 }, { 12, 15 } };
        boolean expected1 = false;
        System.out.println("Scenario: Overlapping meetings exist");
        System.out.println("Input: " + Arrays.deepToString(intervals1));
        boolean result1 = solver.canAttendAllMeetings(intervals1);
        solver.printResult("Test 1 (Overlapping)", result1, expected1);
        totalTests++;
        if (result1 == expected1)
            passedTests++;

        // Test Case 2: All non-overlapping
        System.out.println("\n==================== Test Case 2 ====================");
        int[][] intervals2 = { { 2, 6 }, { 7, 9 }, { 10, 14 }, { 15, 18 } };
        boolean expected2 = true;
        System.out.println("Scenario: All non-overlapping meetings");
        System.out.println("Input: " + Arrays.deepToString(intervals2));
        boolean result2 = solver.canAttendAllMeetings(intervals2);
        solver.printResult("Test 2 (All non-overlapping)", result2, expected2);
        totalTests++;
        if (result2 == expected2)
            passedTests++;

        // Test Case 3: Meetings touching at boundary
        System.out.println("\n==================== Test Case 3 ====================");
        int[][] intervals3 = { { 0, 4 }, { 4, 9 }, { 10, 13 }, { 14, 17 } };
        boolean expected3 = true;
        System.out.println("Scenario: Meetings touching at boundary (can attend both)");
        System.out.println("Input: " + Arrays.deepToString(intervals3));
        boolean result3 = solver.canAttendAllMeetings(intervals3);
        solver.printResult("Test 3 (Touching at boundary)", result3, expected3);
        totalTests++;
        if (result3 == expected3)
            passedTests++;

        // Test Case 4: Empty intervals array
        System.out.println("\n==================== Test Case 4 ====================");
        int[][] intervals4 = {};
        boolean expected4 = true;
        System.out.println("Scenario: Empty intervals array");
        System.out.println("Input: " + Arrays.deepToString(intervals4));
        boolean result4 = solver.canAttendAllMeetings(intervals4);
        solver.printResult("Test 4 (Empty array)", result4, expected4);
        totalTests++;
        if (result4 == expected4)
            passedTests++;

        // Test Case 5: Single meeting
        System.out.println("\n==================== Test Case 5 ====================");
        int[][] intervals5 = { { 0, 10 } };
        boolean expected5 = true;
        System.out.println("Scenario: Single meeting");
        System.out.println("Input: " + Arrays.deepToString(intervals5));
        boolean result5 = solver.canAttendAllMeetings(intervals5);
        solver.printResult("Test 5 (Single meeting)", result5, expected5);
        totalTests++;
        if (result5 == expected5)
            passedTests++;

        // Test Case 6: Two overlapping meetings
        System.out.println("\n==================== Test Case 6 ====================");
        int[][] intervals6 = { { 1, 5 }, { 2, 7 } };
        boolean expected6 = false;
        System.out.println("Scenario: Two overlapping meetings");
        System.out.println("Input: " + Arrays.deepToString(intervals6));
        boolean result6 = solver.canAttendAllMeetings(intervals6);
        solver.printResult("Test 6 (Two overlapping)", result6, expected6);
        totalTests++;
        if (result6 == expected6)
            passedTests++;

        // Test Case 7: Unordered intervals with overlap
        System.out.println("\n==================== Test Case 7 ====================");
        int[][] intervals7 = { { 5, 10 }, { 1, 3 }, { 2, 8 } };
        boolean expected7 = false;
        System.out.println("Scenario: Unordered intervals with overlap");
        System.out.println("Input: " + Arrays.deepToString(intervals7));
        boolean result7 = solver.canAttendAllMeetings(intervals7);
        solver.printResult("Test 7 (Unordered with overlap)", result7, expected7);
        totalTests++;
        if (result7 == expected7)
            passedTests++;

        // Test Case 8: Unordered but all non-overlapping
        System.out.println("\n==================== Test Case 8 ====================");
        int[][] intervals8 = { { 10, 15 }, { 0, 5 }, { 5, 10 } };
        boolean expected8 = true;
        System.out.println("Scenario: Unordered but all non-overlapping");
        System.out.println("Input: " + Arrays.deepToString(intervals8));
        boolean result8 = solver.canAttendAllMeetings(intervals8);
        solver.printResult("Test 8 (Unordered non-overlapping)", result8, expected8);
        totalTests++;
        if (result8 == expected8)
            passedTests++;

        // Test Case 9: Multiple overlapping meetings
        System.out.println("\n==================== Test Case 9 ====================");
        int[][] intervals9 = { { 1, 2 }, { 1, 2 }, { 1, 2 } };
        boolean expected9 = false;
        System.out.println("Scenario: Multiple overlapping meetings (same time)");
        System.out.println("Input: " + Arrays.deepToString(intervals9));
        boolean result9 = solver.canAttendAllMeetings(intervals9);
        solver.printResult("Test 9 (Multiple overlapping)", result9, expected9);
        totalTests++;
        if (result9 == expected9)
            passedTests++;

        // Test Case 10: Complex pattern with many meetings
        System.out.println("\n==================== Test Case 10 ====================");
        int[][] intervals10 = { { 0, 30 }, { 5, 10 }, { 15, 20 }, { 25, 35 } };
        boolean expected10 = false;
        System.out.println("Scenario: Complex pattern with meetings that overlap");
        System.out.println("Input: " + Arrays.deepToString(intervals10));
        boolean result10 = solver.canAttendAllMeetings(intervals10);
        solver.printResult("Test 10 (Complex overlapping)", result10, expected10);
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

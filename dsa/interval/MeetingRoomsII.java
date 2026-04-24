package dsa.interval;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <h1>Meeting Rooms II</h1>
 *
 * <p>
 * Given an array of meeting time intervals {@code intervals} where
 * {@code intervals[i] = [start_i, end_i]}, return the minimum number of
 * conference rooms required.
 * </p>
 *
 * <h2>Examples</h2>
 * 
 * <pre>
 * <b>Input:</b> [[0, 30], [5, 10], [15, 20]]
 * <b>Output:</b> 2
 * <b>Explanation:</b> 
 * - At time 0, first meeting starts: 1 room needed
 * - At time 5, second meeting starts while first ongoing: 2 rooms needed
 * - At time 10, second meeting ends: back to 1 room
 * - At time 15, third meeting starts: 2 rooms needed
 *
 * <b>Input:</b> [[7, 10], [2, 4]]
 * <b>Output:</b> 1
 * <b>Explanation:</b> No overlapping meetings, so only 1 room needed.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>{@code 1 <= intervals.length <= 10^4}</li>
 * <li>{@code 0 <= start_i < end_i <= 10^6}</li>
 * </ul>
 *
 * @see <a href=
 *      "https://takeuforward.org/plus/dsa/problems/meeting-rooms-ii">TakeUForward
 *      - Meeting Rooms II</a>
 */
public class MeetingRoomsII {

    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0)
            return 0;

        // sort when the meeting is ending
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        PriorityQueue<int[]> rooms = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        rooms.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];

            // check if any exisinting rooms can be assigned
            if (rooms.peek()[1] <= current[0]) {
                // room (head of PQ) can be assigned
                // free one room
                rooms.poll();
            }
            // assign the room
            rooms.offer(current);

        }
        return rooms.size();
    }

    private void printResult(String testName, int actual, int expected) {
        boolean passed = actual == expected;
        System.out.println(testName + ": " + (passed ? "PASS ✓" : "FAIL ✗"));
        System.out.println("  Output:   " + actual);
        System.out.println("  Expected: " + expected);
    }

    public static void main(String[] args) {
        MeetingRoomsII solver = new MeetingRoomsII();
        int totalTests = 0;
        int passedTests = 0;

        // Test Case 1: Example with some overlapping meetings
        System.out.println("==================== Test Case 1 ====================");
        int[][] intervals1 = { { 0, 30 }, { 5, 10 }, { 15, 20 } };
        int expected1 = 2;
        System.out.println("Scenario: Three meetings with one overlap");
        System.out.println("Input: " + Arrays.deepToString(intervals1));
        int result1 = solver.minMeetingRooms(intervals1);
        solver.printResult("Test 1 (Some overlaps)", result1, expected1);
        totalTests++;
        if (result1 == expected1)
            passedTests++;

        // Test Case 2: No overlapping meetings
        System.out.println("\n==================== Test Case 2 ====================");
        int[][] intervals2 = { { 7, 10 }, { 2, 4 } };
        int expected2 = 1;
        System.out.println("Scenario: No overlapping meetings");
        System.out.println("Input: " + Arrays.deepToString(intervals2));
        int result2 = solver.minMeetingRooms(intervals2);
        solver.printResult("Test 2 (No overlaps)", result2, expected2);
        totalTests++;
        if (result2 == expected2)
            passedTests++;

        // Test Case 3: All meetings at same time
        System.out.println("\n==================== Test Case 3 ====================");
        int[][] intervals3 = { { 1, 5 }, { 1, 5 }, { 1, 5 } };
        int expected3 = 3;
        System.out.println("Scenario: All meetings at same time (maximum overlap)");
        System.out.println("Input: " + Arrays.deepToString(intervals3));
        int result3 = solver.minMeetingRooms(intervals3);
        solver.printResult("Test 3 (All same time)", result3, expected3);
        totalTests++;
        if (result3 == expected3)
            passedTests++;

        // Test Case 4: Single meeting
        System.out.println("\n==================== Test Case 4 ====================");
        int[][] intervals4 = { { 0, 60 } };
        int expected4 = 1;
        System.out.println("Scenario: Single meeting");
        System.out.println("Input: " + Arrays.deepToString(intervals4));
        int result4 = solver.minMeetingRooms(intervals4);
        solver.printResult("Test 4 (Single meeting)", result4, expected4);
        totalTests++;
        if (result4 == expected4)
            passedTests++;

        // Test Case 5: Meetings touching at boundaries
        System.out.println("\n==================== Test Case 5 ====================");
        int[][] intervals5 = { { 0, 5 }, { 5, 10 }, { 10, 15 }, { 15, 20 } };
        int expected5 = 1;
        System.out.println("Scenario: Meetings touching at boundaries (no real overlap)");
        System.out.println("Input: " + Arrays.deepToString(intervals5));
        int result5 = solver.minMeetingRooms(intervals5);
        solver.printResult("Test 5 (Boundary touching)", result5, expected5);
        totalTests++;
        if (result5 == expected5)
            passedTests++;

        // Test Case 6: Two overlapping meetings
        System.out.println("\n==================== Test Case 6 ====================");
        int[][] intervals6 = { { 1, 5 }, { 3, 7 } };
        int expected6 = 2;
        System.out.println("Scenario: Two meetings that overlap");
        System.out.println("Input: " + Arrays.deepToString(intervals6));
        int result6 = solver.minMeetingRooms(intervals6);
        solver.printResult("Test 6 (Two overlapping)", result6, expected6);
        totalTests++;
        if (result6 == expected6)
            passedTests++;

        // Test Case 7: Peak at middle
        System.out.println("\n==================== Test Case 7 ====================");
        int[][] intervals7 = { { 1, 10 }, { 2, 5 }, { 3, 6 }, { 8, 12 } };
        int expected7 = 3;
        System.out.println("Scenario: Peak of 3 rooms at middle time");
        System.out.println("Input: " + Arrays.deepToString(intervals7));
        int result7 = solver.minMeetingRooms(intervals7);
        solver.printResult("Test 7 (Peak in middle)", result7, expected7);
        totalTests++;
        if (result7 == expected7)
            passedTests++;

        // Test Case 8: Nested intervals
        System.out.println("\n==================== Test Case 8 ====================");
        int[][] intervals8 = { { 0, 100 }, { 10, 50 }, { 20, 30 } };
        int expected8 = 3;
        System.out.println("Scenario: Nested intervals (all overlap)");
        System.out.println("Input: " + Arrays.deepToString(intervals8));
        int result8 = solver.minMeetingRooms(intervals8);
        solver.printResult("Test 8 (Nested intervals)", result8, expected8);
        totalTests++;
        if (result8 == expected8)
            passedTests++;

        // Test Case 9: Unordered meetings with varying overlaps
        System.out.println("\n==================== Test Case 9 ====================");
        int[][] intervals9 = { { 15, 20 }, { 0, 30 }, { 5, 10 }, { 25, 35 } };
        int expected9 = 2;
        System.out.println("Scenario: Unordered meetings with varying overlaps");
        System.out.println("Input: " + Arrays.deepToString(intervals9));
        int result9 = solver.minMeetingRooms(intervals9);
        solver.printResult("Test 9 (Unordered varying)", result9, expected9);
        totalTests++;
        if (result9 == expected9)
            passedTests++;

        // Test Case 10: Complex pattern with multiple peaks
        System.out.println("\n==================== Test Case 10 ====================");
        int[][] intervals10 = { { 0, 5 }, { 1, 6 }, { 2, 7 }, { 10, 15 }, { 11, 16 }, { 12, 17 } };
        int expected10 = 3;
        System.out.println("Scenario: Multiple peaks of 3 rooms each");
        System.out.println("Input: " + Arrays.deepToString(intervals10));
        int result10 = solver.minMeetingRooms(intervals10);
        solver.printResult("Test 10 (Multiple peaks)", result10, expected10);
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

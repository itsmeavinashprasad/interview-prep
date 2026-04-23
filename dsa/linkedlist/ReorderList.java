package dsa.linkedlist;

import java.util.Arrays;

/**
 * <h1>143. Reorder List</h1>
 *
 * <p>
 * You are given the head of a singly linked-list. The list can be represented as:
 * </p>
 *
 * <pre>
 * L0 → L1 → … → Ln - 1 → Ln
 * </pre>
 *
 * <p>
 * Reorder the list to be on the following form:
 * </p>
 *
 * <pre>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * </pre>
 *
 * <p>
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 *
 * <b>Example 2:</b>
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in the list is in the range [1, 5 * 10^4].</li>
 * <li>1 &lt;= Node.val &lt;= 1000</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/reorder-list/description/">LeetCode -
 *      Reorder List</a>
 */

/**
 * Definition for singly-linked list node.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class ReorderList {

    /**
     * Reorder the linked list in place
     *
     * @param head the head of the linked list
     */
    public void reorderList(ListNode head) {

        ListNode slow = head;
        ListNode fast = head;

        // find middle of the list by slow and fast pointer
        // at the end slow should be at the middle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // reverse the list after middle element
        ListNode head2 = reverseList(slow.next);
        slow.next = head2;

        // mid should be the last element after rearrangement
        ListNode mid = slow;
        mid.next = null;
        
        ListNode node = head;
        while (head2 != null) {
            // preserve a ref to nextnode of node
            ListNode next = node.next;
            ListNode next2 = head2.next;

            // rearrange the next pointers
            node.next = head2;
            head2.next = next;

            // increment
            node = next;
            head2 = next2;
        }
    }

    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode next = head.next;
        ListNode newHead = reverseList(head.next);
        next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * Helper method to create a linked list from an array
     *
     * @param arr the array to convert to a linked list
     * @return the head of the linked list
     */
    private ListNode createLinkedList(int[] arr) {
        if (arr.length == 0)
            return null;
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * Helper method to convert a linked list to an array for easy comparison
     *
     * @param head the head of the linked list
     * @return the array representation of the linked list
     */
    private int[] linkedListToArray(ListNode head) {
        int[] result = new int[100000];
        int index = 0;
        ListNode current = head;
        while (current != null && index < result.length) {
            result[index++] = current.val;
            current = current.next;
        }

        int[] finalResult = new int[index];
        System.arraycopy(result, 0, finalResult, 0, index);
        return finalResult;
    }

    /**
     * Helper method to compare two arrays
     *
     * @param arr1 first array
     * @param arr2 second array
     * @return true if arrays are equal, false otherwise
     */
    private boolean arraysEqual(int[] arr1, int[] arr2) {
        return Arrays.equals(arr1, arr2);
    }

    public static void main(String[] args) {
        ReorderList solution = new ReorderList();

        // Test case 1: Four elements [1,2,3,4] -> [1,4,2,3]
        ListNode head1 = solution.createLinkedList(new int[] { 1, 2, 3, 4 });
        solution.reorderList(head1);
        int[] expected1 = { 1, 4, 2, 3 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head1), expected1) ? "Test 1a - Pass"
                : "Test 1a - Fail");

        // Test case 2: Five elements [1,2,3,4,5] -> [1,5,2,4,3]
        ListNode head2 = solution.createLinkedList(new int[] { 1, 2, 3, 4, 5 });
        solution.reorderList(head2);
        int[] expected2 = { 1, 5, 2, 4, 3 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head2), expected2) ? "Test 2a - Pass"
                : "Test 2a - Fail");

        // Test case 3: Single element [1] -> [1]
        ListNode head3 = solution.createLinkedList(new int[] { 1 });
        solution.reorderList(head3);
        int[] expected3 = { 1 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head3), expected3) ? "Test 3a - Pass"
                : "Test 3a - Fail");

        // Test case 4: Two elements [1,2] -> [1,2]
        ListNode head4 = solution.createLinkedList(new int[] { 1, 2 });
        solution.reorderList(head4);
        int[] expected4 = { 1, 2 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head4), expected4) ? "Test 4a - Pass"
                : "Test 4a - Fail");

        // Test case 5: Three elements [1,2,3] -> [1,3,2]
        ListNode head5 = solution.createLinkedList(new int[] { 1, 2, 3 });
        solution.reorderList(head5);
        int[] expected5 = { 1, 3, 2 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head5), expected5) ? "Test 5a - Pass"
                : "Test 5a - Fail");

        // Test case 6: Six elements [1,2,3,4,5,6] -> [1,6,2,5,3,4]
        ListNode head6 = solution.createLinkedList(new int[] { 1, 2, 3, 4, 5, 6 });
        solution.reorderList(head6);
        int[] expected6 = { 1, 6, 2, 5, 3, 4 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head6), expected6) ? "Test 6a - Pass"
                : "Test 6a - Fail");

        // Test case 7: Seven elements [1,2,3,4,5,6,7] -> [1,7,2,6,3,5,4]
        ListNode head7 = solution.createLinkedList(new int[] { 1, 2, 3, 4, 5, 6, 7 });
        solution.reorderList(head7);
        int[] expected7 = { 1, 7, 2, 6, 3, 5, 4 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head7), expected7) ? "Test 7a - Pass"
                : "Test 7a - Fail");

        // Test case 8: Eight elements [1,2,3,4,5,6,7,8] -> [1,8,2,7,3,6,4,5]
        ListNode head8 = solution.createLinkedList(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 });
        solution.reorderList(head8);
        int[] expected8 = { 1, 8, 2, 7, 3, 6, 4, 5 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head8), expected8) ? "Test 8a - Pass"
                : "Test 8a - Fail");

        // Test case 9: Different values [2,4,6,8] -> [2,8,4,6]
        ListNode head9 = solution.createLinkedList(new int[] { 2, 4, 6, 8 });
        solution.reorderList(head9);
        int[] expected9 = { 2, 8, 4, 6 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head9), expected9) ? "Test 9a - Pass"
                : "Test 9a - Fail");

        // Test case 10: Larger values [10,20,30,40,50] -> [10,50,20,40,30]
        ListNode head10 = solution.createLinkedList(new int[] { 10, 20, 30, 40, 50 });
        solution.reorderList(head10);
        int[] expected10 = { 10, 50, 20, 40, 30 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head10), expected10) ? "Test 10a - Pass"
                : "Test 10a - Fail");

        // Test case 11: Nine elements [1,2,3,4,5,6,7,8,9] -> [1,9,2,8,3,7,4,6,5]
        ListNode head11 = solution.createLinkedList(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        solution.reorderList(head11);
        int[] expected11 = { 1, 9, 2, 8, 3, 7, 4, 6, 5 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head11), expected11) ? "Test 11a - Pass"
                : "Test 11a - Fail");

        // Test case 12: Ten elements [1,2,3,4,5,6,7,8,9,10] -> [1,10,2,9,3,8,4,7,5,6]
        ListNode head12 = solution.createLinkedList(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
        solution.reorderList(head12);
        int[] expected12 = { 1, 10, 2, 9, 3, 8, 4, 7, 5, 6 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head12), expected12) ? "Test 12a - Pass"
                : "Test 12a - Fail");

        // Test case 13: Same values [5,5,5,5,5] -> [5,5,5,5,5]
        ListNode head13 = solution.createLinkedList(new int[] { 5, 5, 5, 5, 5 });
        solution.reorderList(head13);
        int[] expected13 = { 5, 5, 5, 5, 5 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head13), expected13) ? "Test 13a - Pass"
                : "Test 13a - Fail");

        // Test case 14: Ascending by steps [1,3,5,7,9,11] -> [1,11,3,9,5,7]
        ListNode head14 = solution.createLinkedList(new int[] { 1, 3, 5, 7, 9, 11 });
        solution.reorderList(head14);
        int[] expected14 = { 1, 11, 3, 9, 5, 7 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head14), expected14) ? "Test 14a - Pass"
                : "Test 14a - Fail");

        // Test case 15: Random order with duplicates [100,50,200,30,150,10] ->
        // [100,10,50,150,200,30]
        ListNode head15 = solution.createLinkedList(new int[] { 100, 50, 200, 30, 150, 10 });
        solution.reorderList(head15);
        int[] expected15 = { 100, 10, 50, 150, 200, 30 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(head15), expected15) ? "Test 15a - Pass"
                : "Test 15a - Fail");
    }
}

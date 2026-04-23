package dsa.linkedlist;

import java.util.Arrays;

/**
 * <h1>21. Merge Two Sorted Lists</h1>
 *
 * <p>
 * You are given the heads of two sorted linked lists <code>list1</code> and <code>list2</code>.
 * </p>
 *
 * <p>
 * Merge the two lists into one sorted list. The list should be made by splicing together
 * the nodes of the first two lists.
 * </p>
 *
 * <p>Return the head of the merged linked list.</p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 *
 * <b>Example 2:</b>
 * Input: list1 = [], list2 = []
 * Output: []
 *
 * <b>Example 3:</b>
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in both lists is in the range [0, 50].</li>
 * <li>-100 &lt;= Node.val &lt;= 100</li>
 * <li>Both list1 and list2 are sorted in non-decreasing order.</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/merge-two-sorted-lists/description/">LeetCode -
 *      Merge Two Sorted Lists</a>
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

public class MergeTwoSortedLists {

    /**
     * Merge two sorted linked lists
     *
     * @param list1 the head of the first sorted linked list
     * @param list2 the head of the second sorted linked list
     * @return the head of the merged sorted linked list
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        if (list2 == null)
            return list1;

        ListNode newHead = list1.val <= list2.val ? list1 : list2;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                ListNode next = list1.next;
                if (next == null || next.val > list2.val) {
                    list1.next = list2;
                }
                list1 = next;
            } else {
                ListNode next = list2.next;
                if (next == null || next.val >= list1.val) {
                    list2.next = list1;
                }
                list2 = next;
            }
        }

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
        int[] result = new int[100];
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

    private void printList(ListNode head, String listName) {
        int[] arr = linkedListToArray(head);
        System.out.print(listName + ": ");
        for (int x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MergeTwoSortedLists solution = new MergeTwoSortedLists();

        // Test case 1: Basic merge [1,2,4] and [1,3,4]
        ListNode list1_1 = solution.createLinkedList(new int[] { 1, 2, 4 });
        ListNode list2_1 = solution.createLinkedList(new int[] { 1, 3, 4 });
        ListNode merged1 = solution.mergeTwoLists(list1_1, list2_1);
        int[] expected1 = { 1, 1, 2, 3, 4, 4 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged1), expected1) ? "Test 1a - Pass"
                : "Test 1a - Fail");

        // Test case 2: Both empty lists
        ListNode list1_2 = solution.createLinkedList(new int[] {});
        ListNode list2_2 = solution.createLinkedList(new int[] {});
        ListNode merged2 = solution.mergeTwoLists(list1_2, list2_2);
        int[] expected2 = {};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged2), expected2) ? "Test 2a - Pass"
                : "Test 2a - Fail");

        // Test case 3: First list empty
        ListNode list1_3 = solution.createLinkedList(new int[] {});
        ListNode list2_3 = solution.createLinkedList(new int[] { 0 });
        ListNode merged3 = solution.mergeTwoLists(list1_3, list2_3);
        int[] expected3 = { 0 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged3), expected3) ? "Test 3a - Pass"
                : "Test 3a - Fail");

        // Test case 4: Second list empty
        ListNode list1_4 = solution.createLinkedList(new int[] { 1 });
        ListNode list2_4 = solution.createLinkedList(new int[] {});
        ListNode merged4 = solution.mergeTwoLists(list1_4, list2_4);
        int[] expected4 = { 1 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged4), expected4) ? "Test 4a - Pass"
                : "Test 4a - Fail");

        // Test case 5: All elements in first list are smaller
        ListNode list1_5 = solution.createLinkedList(new int[] { 1, 2, 3 });
        ListNode list2_5 = solution.createLinkedList(new int[] { 4, 5, 6 });
        ListNode merged5 = solution.mergeTwoLists(list1_5, list2_5);
        int[] expected5 = { 1, 2, 3, 4, 5, 6 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged5), expected5) ? "Test 5a - Pass"
                : "Test 5a - Fail");

        // Test case 6: All elements in second list are smaller
        ListNode list1_6 = solution.createLinkedList(new int[] { 4, 5, 6 });
        ListNode list2_6 = solution.createLinkedList(new int[] { 1, 2, 3 });
        ListNode merged6 = solution.mergeTwoLists(list1_6, list2_6);
        int[] expected6 = { 1, 2, 3, 4, 5, 6 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged6), expected6) ? "Test 6a - Pass"
                : "Test 6a - Fail");

        // Test case 7: Duplicate elements
        ListNode list1_7 = solution.createLinkedList(new int[] { 1, 1, 1 });
        ListNode list2_7 = solution.createLinkedList(new int[] { 1, 1, 1 });
        ListNode merged7 = solution.mergeTwoLists(list1_7, list2_7);
        int[] expected7 = { 1, 1, 1, 1, 1, 1 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged7), expected7) ? "Test 7a - Pass"
                : "Test 7a - Fail");

        // Test case 8: Negative numbers
        ListNode list1_8 = solution.createLinkedList(new int[] { -4, -1, 0 });
        ListNode list2_8 = solution.createLinkedList(new int[] { -5, -2, 1 });
        ListNode merged8 = solution.mergeTwoLists(list1_8, list2_8);
        int[] expected8 = { -5, -4, -2, -1, 0, 1 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged8), expected8) ? "Test 8a - Pass"
                : "Test 8a - Fail");

        // Test case 9: Single element in each list
        ListNode list1_9 = solution.createLinkedList(new int[] { 2 });
        ListNode list2_9 = solution.createLinkedList(new int[] { 1 });
        ListNode merged9 = solution.mergeTwoLists(list1_9, list2_9);
        int[] expected9 = { 1, 2 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged9), expected9) ? "Test 9a - Pass"
                : "Test 9a - Fail");

        // Test case 10: Interleaved elements
        ListNode list1_10 = solution.createLinkedList(new int[] { 1, 3, 5 });
        ListNode list2_10 = solution.createLinkedList(new int[] { 2, 4, 6 });
        ListNode merged10 = solution.mergeTwoLists(list1_10, list2_10);
        int[] expected10 = { 1, 2, 3, 4, 5, 6 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged10), expected10) ? "Test 10a - Pass"
                : "Test 10a - Fail");

        // Test case 11: Different sizes - first list longer
        ListNode list1_11 = solution.createLinkedList(new int[] { 1, 2, 3, 4, 5 });
        ListNode list2_11 = solution.createLinkedList(new int[] { 3, 4 });
        ListNode merged11 = solution.mergeTwoLists(list1_11, list2_11);
        int[] expected11 = { 1, 2, 3, 3, 4, 4, 5 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged11), expected11) ? "Test 11a - Pass"
                : "Test 11a - Fail");

        // Test case 12: Different sizes - second list longer
        ListNode list1_12 = solution.createLinkedList(new int[] { 2, 5 });
        ListNode list2_12 = solution.createLinkedList(new int[] { 1, 2, 3, 4, 6 });
        ListNode merged12 = solution.mergeTwoLists(list1_12, list2_12);
        int[] expected12 = { 1, 2, 2, 3, 4, 5, 6 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged12), expected12) ? "Test 12a - Pass"
                : "Test 12a - Fail");

        // Test case 13: All negative numbers
        ListNode list1_13 = solution.createLinkedList(new int[] { -10, -5, -3 });
        ListNode list2_13 = solution.createLinkedList(new int[] { -8, -6, -1 });
        ListNode merged13 = solution.mergeTwoLists(list1_13, list2_13);
        int[] expected13 = { -10, -8, -6, -5, -3, -1 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged13), expected13) ? "Test 13a - Pass"
                : "Test 13a - Fail");

        // Test case 14: Mix of zeros and positive numbers
        ListNode list1_14 = solution.createLinkedList(new int[] { 0, 0, 1 });
        ListNode list2_14 = solution.createLinkedList(new int[] { 0, 1, 1 });
        ListNode merged14 = solution.mergeTwoLists(list1_14, list2_14);
        int[] expected14 = { 0, 0, 0, 1, 1, 1 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged14), expected14) ? "Test 14a - Pass"
                : "Test 14a - Fail");

        // Test case 15: Large numbers with different magnitudes
        ListNode list1_15 = solution.createLinkedList(new int[] { -100, 0, 50 });
        ListNode list2_15 = solution.createLinkedList(new int[] { -50, 25, 100 });
        ListNode merged15 = solution.mergeTwoLists(list1_15, list2_15);
        int[] expected15 = { -100, -50, 0, 25, 50, 100 };
        System.out.println(solution.arraysEqual(solution.linkedListToArray(merged15), expected15) ? "Test 15a - Pass"
                : "Test 15a - Fail");
    }
}

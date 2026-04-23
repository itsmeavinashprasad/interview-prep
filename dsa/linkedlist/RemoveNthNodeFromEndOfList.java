package dsa.linkedlist;

import java.util.Arrays;

/**
 * <h1>19. Remove Nth Node From End of List</h1>
 *
 * <p>
 * Given the <code>head</code> of a linked list, remove the <code>n</code>th node from the end of the list
 * and return its head.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * <b>Example 2:</b>
 * Input: head = [1], n = 1
 * Output: []
 *
 * <b>Example 3:</b>
 * Input: head = [1,2], n = 1
 * Output: [1]
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in the list is sz.</li>
 * <li>1 &lt;= sz &lt;= 30</li>
 * <li>0 &lt;= Node.val &lt;= 100</li>
 * <li>1 &lt;= n &lt;= sz</li>
 * </ul>
 *
 * <h2>Follow up:</h2>
 * <p>Could you do this in one pass?</p>
 *
 * @see <a href="https://leetcode.com/problems/remove-nth-node-from-end-of-list/description/">LeetCode -
 *      Remove Nth Node From End of List</a>
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

public class RemoveNthNodeFromEndOfList {

    /**
     * Remove the nth node from the end of the linked list
     *
     * @param head the head of the linked list
     * @param n    the position from the end to remove
     * @return the head of the modified linked list
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow = head;
        ListNode fast = slow;

        // move the fast pointer by n
        for(int i=0; i<n; i++){
            fast = fast.next;
        }

        if(fast==null){
            // slow itself has to be removed
            return slow.next;
        }

        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }

        // slow.next has to be removed
        slow.next = slow.next.next;
        return head;
    }

    /**
     * Helper method to create a linked list from an array
     *
     * @param arr the array to convert to a linked list
     * @return the head of the linked list
     */
    private ListNode createLinkedList(int[] arr) {
        if (arr.length == 0) return null;
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

    public static void main(String[] args) {
        RemoveNthNodeFromEndOfList solution = new RemoveNthNodeFromEndOfList();

        // Test case 1: Remove second node from end [1,2,3,4,5], n=2
        ListNode head1 = solution.createLinkedList(new int[]{1, 2, 3, 4, 5});
        ListNode result1 = solution.removeNthFromEnd(head1, 2);
        int[] expected1 = {1, 2, 3, 5};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result1), expected1) ? "Test 1a - Pass" : "Test 1a - Fail");

        // Test case 2: Remove only node [1], n=1
        ListNode head2 = solution.createLinkedList(new int[]{1});
        ListNode result2 = solution.removeNthFromEnd(head2, 1);
        int[] expected2 = {};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result2), expected2) ? "Test 2a - Pass" : "Test 2a - Fail");

        // Test case 3: Remove last node [1,2], n=1
        ListNode head3 = solution.createLinkedList(new int[]{1, 2});
        ListNode result3 = solution.removeNthFromEnd(head3, 1);
        int[] expected3 = {1};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result3), expected3) ? "Test 3a - Pass" : "Test 3a - Fail");

        // Test case 4: Remove first node [1,2], n=2
        ListNode head4 = solution.createLinkedList(new int[]{1, 2});
        ListNode result4 = solution.removeNthFromEnd(head4, 2);
        int[] expected4 = {2};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result4), expected4) ? "Test 4a - Pass" : "Test 4a - Fail");

        // Test case 5: Remove head [1,2,3], n=3
        ListNode head5 = solution.createLinkedList(new int[]{1, 2, 3});
        ListNode result5 = solution.removeNthFromEnd(head5, 3);
        int[] expected5 = {2, 3};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result5), expected5) ? "Test 5a - Pass" : "Test 5a - Fail");

        // Test case 6: Remove from longer list [1,2,3,4,5], n=5 (head)
        ListNode head6 = solution.createLinkedList(new int[]{1, 2, 3, 4, 5});
        ListNode result6 = solution.removeNthFromEnd(head6, 5);
        int[] expected6 = {2, 3, 4, 5};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result6), expected6) ? "Test 6a - Pass" : "Test 6a - Fail");

        // Test case 7: Remove tail [1,2,3,4,5], n=1
        ListNode head7 = solution.createLinkedList(new int[]{1, 2, 3, 4, 5});
        ListNode result7 = solution.removeNthFromEnd(head7, 1);
        int[] expected7 = {1, 2, 3, 4};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result7), expected7) ? "Test 7a - Pass" : "Test 7a - Fail");

        // Test case 8: Remove middle [1,2,3,4,5], n=3
        ListNode head8 = solution.createLinkedList(new int[]{1, 2, 3, 4, 5});
        ListNode result8 = solution.removeNthFromEnd(head8, 3);
        int[] expected8 = {1, 2, 4, 5};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result8), expected8) ? "Test 8a - Pass" : "Test 8a - Fail");

        // Test case 9: Three nodes, remove middle [1,2,3], n=2
        ListNode head9 = solution.createLinkedList(new int[]{1, 2, 3});
        ListNode result9 = solution.removeNthFromEnd(head9, 2);
        int[] expected9 = {1, 3};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result9), expected9) ? "Test 9a - Pass" : "Test 9a - Fail");

        // Test case 10: Longer list [1,2,3,4,5,6,7], n=3
        ListNode head10 = solution.createLinkedList(new int[]{1, 2, 3, 4, 5, 6, 7});
        ListNode result10 = solution.removeNthFromEnd(head10, 3);
        int[] expected10 = {1, 2, 3, 4, 6, 7};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result10), expected10) ? "Test 10a - Pass" : "Test 10a - Fail");

        // Test case 11: Single position [0], n=1
        ListNode head11 = solution.createLinkedList(new int[]{0});
        ListNode result11 = solution.removeNthFromEnd(head11, 1);
        int[] expected11 = {};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result11), expected11) ? "Test 11a - Pass" : "Test 11a - Fail");

        // Test case 12: Two elements, remove first [10,20], n=2
        ListNode head12 = solution.createLinkedList(new int[]{10, 20});
        ListNode result12 = solution.removeNthFromEnd(head12, 2);
        int[] expected12 = {20};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result12), expected12) ? "Test 12a - Pass" : "Test 12a - Fail");

        // Test case 13: Different values [5,10,15,20,25], n=4
        ListNode head13 = solution.createLinkedList(new int[]{5, 10, 15, 20, 25});
        ListNode result13 = solution.removeNthFromEnd(head13, 4);
        int[] expected13 = {5, 15, 20, 25};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result13), expected13) ? "Test 13a - Pass" : "Test 13a - Fail");

        // Test case 14: Remove near end [1,2,3,4,5,6], n=2
        ListNode head14 = solution.createLinkedList(new int[]{1, 2, 3, 4, 5, 6});
        ListNode result14 = solution.removeNthFromEnd(head14, 2);
        int[] expected14 = {1, 2, 3, 4, 6};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result14), expected14) ? "Test 14a - Pass" : "Test 14a - Fail");

        // Test case 15: With zeros [0,0,0,1,2], n=1
        ListNode head15 = solution.createLinkedList(new int[]{0, 0, 0, 1, 2});
        ListNode result15 = solution.removeNthFromEnd(head15, 1);
        int[] expected15 = {0, 0, 0, 1};
        System.out.println(solution.arraysEqual(solution.linkedListToArray(result15), expected15) ? "Test 15a - Pass" : "Test 15a - Fail");
    }
}

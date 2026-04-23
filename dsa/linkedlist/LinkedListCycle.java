package dsa.linkedlist;

/**
 * <h1>141. Linked List Cycle</h1>
 *
 * <p>
 * Given <code>head</code>, the head of a linked list, determine if the linked list has a cycle in it.
 * </p>
 *
 * <p>
 * There is a cycle in a linked list if there is some node in the list that can be reached again
 * by continuously following the <code>next</code> pointer. Internally, <code>pos</code> is used to denote
 * the index of the node that tail's <code>next</code> pointer is connected to. Note that <code>pos</code>
 * is not passed as a parameter.
 * </p>
 *
 * <p>Return <code>true</code> if there is a cycle in the linked list. Otherwise, return <code>false</code>.</p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 1st node
 * (0-indexed).
 *
 * <b>Example 2:</b>
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where the tail connects to the 0th node.
 *
 * <b>Example 3:</b>
 * Input: head = [1], pos = -1
 * Output: false
 * Explanation: There is no cycle in the linked list.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of the nodes in the list is in the range [0, 10^4].</li>
 * <li>-10^5 &lt;= Node.val &lt;= 10^5</li>
 * <li>pos is -1 or a valid index in the linked-list.</li>
 * </ul>
 *
 * <h2>Follow up:</h2>
 * <p>Can you solve it using O(1) (i.e. constant) memory?</p>
 *
 * @see <a href="https://leetcode.com/problems/linked-list-cycle/description/">LeetCode -
 *      Linked List Cycle</a>
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

public class LinkedListCycle {

    /**
     * Detect if there is a cycle in a linked list
     *
     * @param head the head of the linked list
     * @return true if there is a cycle, false otherwise
     */
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != null && fast != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next;
            fast = fast == null ? fast : fast.next;
        }

        return false;
    }

    /**
     * Helper method to create a linked list from an array and add a cycle at the
     * specified position
     *
     * @param arr the array to convert to a linked list
     * @param pos the position to create a cycle to (-1 for no cycle)
     * @return the head of the linked list
     */
    private ListNode createLinkedListWithCycle(int[] arr, int pos) {
        if (arr.length == 0)
            return null;

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        ListNode cycleNode = (pos == 0) ? head : null;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
            if (i == pos) {
                cycleNode = current;
            }
        }

        // Create cycle if pos is not -1
        if (pos != -1) {
            current.next = cycleNode;
        }

        return head;
    }

    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();

        // Test case 1: Cycle at position 1
        ListNode head1 = solution.createLinkedListWithCycle(new int[] { 3, 2, 0, -4 }, 1);
        boolean result1 = solution.hasCycle(head1);
        System.out.println(result1 == true ? "Test 1a - Pass" : "Test 1a - Fail");

        // Test case 2: Cycle at position 0
        ListNode head2 = solution.createLinkedListWithCycle(new int[] { 1, 2 }, 0);
        boolean result2 = solution.hasCycle(head2);
        System.out.println(result2 == true ? "Test 2a - Pass" : "Test 2a - Fail");

        // Test case 3: No cycle
        ListNode head3 = solution.createLinkedListWithCycle(new int[] { 1 }, -1);
        boolean result3 = solution.hasCycle(head3);
        System.out.println(result3 == false ? "Test 3a - Pass" : "Test 3a - Fail");

        // Test case 4: No cycle - larger list
        ListNode head4 = solution.createLinkedListWithCycle(new int[] { 1, 2, 3, 4, 5 }, -1);
        boolean result4 = solution.hasCycle(head4);
        System.out.println(result4 == false ? "Test 4a - Pass" : "Test 4a - Fail");

        // Test case 5: Empty list
        ListNode head5 = solution.createLinkedListWithCycle(new int[] {}, -1);
        boolean result5 = solution.hasCycle(head5);
        System.out.println(result5 == false ? "Test 5a - Pass" : "Test 5a - Fail");

        // Test case 6: Cycle at last position
        ListNode head6 = solution.createLinkedListWithCycle(new int[] { 1, 2, 3, 4 }, 3);
        boolean result6 = solution.hasCycle(head6);
        System.out.println(result6 == true ? "Test 6a - Pass" : "Test 6a - Fail");

        // Test case 7: Cycle at position 2
        ListNode head7 = solution.createLinkedListWithCycle(new int[] { 1, 2, 3, 4, 5 }, 2);
        boolean result7 = solution.hasCycle(head7);
        System.out.println(result7 == true ? "Test 7a - Pass" : "Test 7a - Fail");

        // Test case 8: Two nodes with cycle
        ListNode head8 = solution.createLinkedListWithCycle(new int[] { 2, 1 }, 1);
        boolean result8 = solution.hasCycle(head8);
        System.out.println(result8 == true ? "Test 8a - Pass" : "Test 8a - Fail");

        // Test case 9: Two nodes without cycle
        ListNode head9 = solution.createLinkedListWithCycle(new int[] { 1, 2 }, -1);
        boolean result9 = solution.hasCycle(head9);
        System.out.println(result9 == false ? "Test 9a - Pass" : "Test 9a - Fail");

        // Test case 10: Large list with cycle at position 0
        ListNode head10 = solution.createLinkedListWithCycle(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, 0);
        boolean result10 = solution.hasCycle(head10);
        System.out.println(result10 == true ? "Test 10a - Pass" : "Test 10a - Fail");

        // Test case 11: Large list without cycle
        ListNode head11 = solution.createLinkedListWithCycle(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 }, -1);
        boolean result11 = solution.hasCycle(head11);
        System.out.println(result11 == false ? "Test 11a - Pass" : "Test 11a - Fail");

        // Test case 12: Negative numbers with cycle
        ListNode head12 = solution.createLinkedListWithCycle(new int[] { -1, -2, -3, -4 }, 2);
        boolean result12 = solution.hasCycle(head12);
        System.out.println(result12 == true ? "Test 12a - Pass" : "Test 12a - Fail");

        // Test case 13: Negative numbers without cycle
        ListNode head13 = solution.createLinkedListWithCycle(new int[] { -1, -2, -3, -4 }, -1);
        boolean result13 = solution.hasCycle(head13);
        System.out.println(result13 == false ? "Test 13a - Pass" : "Test 13a - Fail");

        // Test case 14: Mixed positive and negative with cycle
        ListNode head14 = solution.createLinkedListWithCycle(new int[] { 1, -2, 3, -4, 5 }, 1);
        boolean result14 = solution.hasCycle(head14);
        System.out.println(result14 == true ? "Test 14a - Pass" : "Test 14a - Fail");

        // Test case 15: Single node (tail points to itself - pos = 0)
        ListNode head15 = solution.createLinkedListWithCycle(new int[] { 1 }, 0);
        boolean result15 = solution.hasCycle(head15);
        System.out.println(result15 == true ? "Test 15a - Pass" : "Test 15a - Fail");
    }
}

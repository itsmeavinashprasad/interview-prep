package dsa.linkedlist;

/**
 * <h1>206. Reverse Linked List</h1>
 *
 * <p>
 * Given the <code>head</code> of a singly linked list, reverse the list, and return the reversed list.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: head = [1,2,3,4,5]
 * Output: [5,4,3,2,1]
 *
 * <b>Example 2:</b>
 * Input: head = [1,2]
 * Output: [2,1]
 *
 * <b>Example 3:</b>
 * Input: head = []
 * Output: []
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in the list is in the range [0, 5000].</li>
 * <li>-5000 &lt;= Node.val &lt;= 5000</li>
 * </ul>
 *
 * <h2>Follow up:</h2>
 * <p>A linked list can be reversed either iteratively or recursively. Could you implement both?</p>
 *
 * @see <a href="https://leetcode.com/problems/reverse-linked-list/description/">LeetCode -
 *      Reverse Linked List</a>
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

public class ReverseLinkedList {

    /**
     * Reverse a singly linked list iteratively
     *
     * @param head the head of the linked list
     * @return the head of the reversed linked list
     */
    public ListNode reverseListIterative(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode prev = null;
        ListNode node = head;
        while(node!= null){
            ListNode next = node.next;
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;
    }

    /**
     * Reverse a singly linked list recursively
     *
     * @param head the head of the linked list
     * @return the head of the reversed linked list
     */
    public ListNode reverseListRecursive(ListNode head) {
        if(head == null){
            return null;
        }

        if(head.next == null){
            return head;
        }

        ListNode next = head.next;
        ListNode newHead = reverseListRecursive(head.next);
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
     * Helper method to print the result of reversing a linked list
     *
     * @param reversed the reversed linked list
     * @return string representation of the linked list
     */
    private String printList(ListNode reversed) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ListNode current = reversed;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();

        // Test case 1: Multiple nodes - iterative
        ListNode head1 = solution.createLinkedList(new int[]{1, 2, 3, 4, 5});
        ListNode reversed1 = solution.reverseListIterative(head1);
        System.out.println("Test 1a - reverseListIterative([1,2,3,4,5]): " + solution.printList(reversed1)
                + " (Expected: [5, 4, 3, 2, 1])");

        // Test case 2: Multiple nodes - recursive
        ListNode head2 = solution.createLinkedList(new int[]{1, 2, 3, 4, 5});
        ListNode reversed2 = solution.reverseListRecursive(head2);
        System.out.println("Test 2a - reverseListRecursive([1,2,3,4,5]): " + solution.printList(reversed2)
                + " (Expected: [5, 4, 3, 2, 1])");

        // Test case 3: Two nodes - iterative
        ListNode head3 = solution.createLinkedList(new int[]{1, 2});
        ListNode reversed3 = solution.reverseListIterative(head3);
        System.out.println("Test 3a - reverseListIterative([1,2]): " + solution.printList(reversed3)
                + " (Expected: [2, 1])");

        // Test case 4: Two nodes - recursive
        ListNode head4 = solution.createLinkedList(new int[]{1, 2});
        ListNode reversed4 = solution.reverseListRecursive(head4);
        System.out.println("Test 4a - reverseListRecursive([1,2]): " + solution.printList(reversed4)
                + " (Expected: [2, 1])");

        // Test case 5: Single node - iterative
        ListNode head5 = solution.createLinkedList(new int[]{1});
        ListNode reversed5 = solution.reverseListIterative(head5);
        System.out.println("Test 5a - reverseListIterative([1]): " + solution.printList(reversed5)
                + " (Expected: [1])");

        // Test case 6: Single node - recursive
        ListNode head6 = solution.createLinkedList(new int[]{1});
        ListNode reversed6 = solution.reverseListRecursive(head6);
        System.out.println("Test 6a - reverseListRecursive([1]): " + solution.printList(reversed6)
                + " (Expected: [1])");

        // Test case 7: Empty list - iterative
        ListNode head7 = solution.createLinkedList(new int[]{});
        ListNode reversed7 = solution.reverseListIterative(head7);
        System.out.println("Test 7a - reverseListIterative([]): " + solution.printList(reversed7)
                + " (Expected: [])");

        // Test case 8: Empty list - recursive
        ListNode head8 = solution.createLinkedList(new int[]{});
        ListNode reversed8 = solution.reverseListRecursive(head8);
        System.out.println("Test 8a - reverseListRecursive([]): " + solution.printList(reversed8)
                + " (Expected: [])");

        // Test case 9: Longer list - iterative
        ListNode head9 = solution.createLinkedList(new int[]{5, 4, 3, 2, 1});
        ListNode reversed9 = solution.reverseListIterative(head9);
        System.out.println("Test 9a - reverseListIterative([5,4,3,2,1]): " + solution.printList(reversed9)
                + " (Expected: [1, 2, 3, 4, 5])");

        // Test case 10: Longer list - recursive
        ListNode head10 = solution.createLinkedList(new int[]{5, 4, 3, 2, 1});
        ListNode reversed10 = solution.reverseListRecursive(head10);
        System.out.println("Test 10a - reverseListRecursive([5,4,3,2,1]): " + solution.printList(reversed10)
                + " (Expected: [1, 2, 3, 4, 5])");

        // Test case 11: Negative numbers - iterative
        ListNode head11 = solution.createLinkedList(new int[]{-1, -2, -3});
        ListNode reversed11 = solution.reverseListIterative(head11);
        System.out.println("Test 11a - reverseListIterative([-1,-2,-3]): " + solution.printList(reversed11)
                + " (Expected: [-3, -2, -1])");

        // Test case 12: Negative numbers - recursive
        ListNode head12 = solution.createLinkedList(new int[]{-1, -2, -3});
        ListNode reversed12 = solution.reverseListRecursive(head12);
        System.out.println("Test 12a - reverseListRecursive([-1,-2,-3]): " + solution.printList(reversed12)
                + " (Expected: [-3, -2, -1])");

        // Test case 13: Mixed positive and negative - iterative
        ListNode head13 = solution.createLinkedList(new int[]{1, -2, 3, -4, 5});
        ListNode reversed13 = solution.reverseListIterative(head13);
        System.out.println("Test 13a - reverseListIterative([1,-2,3,-4,5]): " + solution.printList(reversed13)
                + " (Expected: [5, -4, 3, -2, 1])");

        // Test case 14: Mixed positive and negative - recursive
        ListNode head14 = solution.createLinkedList(new int[]{1, -2, 3, -4, 5});
        ListNode reversed14 = solution.reverseListRecursive(head14);
        System.out.println("Test 14a - reverseListRecursive([1,-2,3,-4,5]): " + solution.printList(reversed14)
                + " (Expected: [5, -4, 3, -2, 1])");
    }
}

package dsa.tree;

import org.w3c.dom.css.Counter;

/**
 * <h1>230. Kth Smallest Element in a BST</h1>
 *
 * <p>
 * Given the <code>root</code> of a binary search tree and an integer <code>k</code>,
 * return <em>the <code>k</code>th smallest value (1-indexed) of all the values of the nodes in the tree</em>.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: root = [3,1,4,null,2], k = 1
 * Output: 1
 * Explanation: The 1st smallest value is 1.
 *
 * <b>Example 2:</b>
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * Output: 3
 * Explanation: The 3rd smallest value is 3.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in the tree is <code>n</code>.</li>
 * <li>1 &lt;= k &lt;= n &lt;= 10^4</li>
 * <li>0 &lt;= Node.val &lt;= 10^4</li>
 * </ul>
 *
 * <h2>Follow up:</h2>
 * <ul>
 * <li>If the BST is modified often (we can do insert and delete operations) and you need to find the
 * kth smallest frequently, how would you optimize?</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/kth-smallest-element-in-a-bst/description/">LeetCode -
 *      Kth Smallest Element in a BST</a>
 */

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class KthSmallestElementInABST {

    /**
     * Find the kth smallest value in a binary search tree
     *
     * @param root the root node of the binary search tree
     * @param k    the position of the smallest element to find (1-indexed)
     * @return the kth smallest value
     */
    public int kthSmallest(TreeNode root, int k) {
        int[] ans = new int[] { k, -1 };
        ans = inorder(root, ans);
        return ans[1];
    }

    private int[] inorder(TreeNode root, int[] ans) {

        if (root == null) {
            return ans;
        }

        // call left sub tree
        ans = inorder(root.left, ans);

        // check ans and current node
        if (ans[0] == 1 && ans[1] != -1) {
            // found kth node in left subtree
            return ans;
        }
        if (ans[0] == 1 && ans[1] == -1) {
            // current node is the kth node
            ans[1] = root.val;
            return ans;
        }
        // reaching here means: ans[0] > 1 && ans[1] == -1
        // did not found kth node in left subtree or current node
        // count current node also as visited
        ans[0] = ans[0] - 1;
        // call right sub tree
        return inorder(root.right, ans);

    }

    public static void main(String[] args) {
        KthSmallestElementInABST solution = new KthSmallestElementInABST();

        // Test case 1: [3,1,4,null,2], k = 1
        // Expected output: 1
        // Inorder traversal: 1, 2, 3, 4
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);

        System.out.println("Test 1: " + solution.kthSmallest(root1, 1) + " (Expected: 1)");

        // Test case 2: [5,3,6,2,4,null,null,1], k = 3
        // Expected output: 3
        // Inorder traversal: 1, 2, 3, 4, 5, 6
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.right = new TreeNode(4);
        root2.left.left.left = new TreeNode(1);

        System.out.println("Test 2: " + solution.kthSmallest(root2, 3) + " (Expected: 3)");

        // Test case 3: [3,1,4,null,2], k = 4
        // Expected output: 4
        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(4);
        root3.left.right = new TreeNode(2);

        System.out.println("Test 3: " + solution.kthSmallest(root3, 4) + " (Expected: 4)");

        // Test case 4: Single node [1], k = 1
        // Expected output: 1
        TreeNode root4 = new TreeNode(1);

        System.out.println("Test 4: " + solution.kthSmallest(root4, 1) + " (Expected: 1)");

        // Test case 5: [5,3,6,2,4,null,null,1], k = 1
        // Expected output: 1
        TreeNode root5 = new TreeNode(5);
        root5.left = new TreeNode(3);
        root5.right = new TreeNode(6);
        root5.left.left = new TreeNode(2);
        root5.left.right = new TreeNode(4);
        root5.left.left.left = new TreeNode(1);

        System.out.println("Test 5: " + solution.kthSmallest(root5, 1) + " (Expected: 1)");

        // Test case 6: [5,3,6,2,4,null,null,1], k = 6
        // Expected output: 6
        TreeNode root6 = new TreeNode(5);
        root6.left = new TreeNode(3);
        root6.right = new TreeNode(6);
        root6.left.left = new TreeNode(2);
        root6.left.right = new TreeNode(4);
        root6.left.left.left = new TreeNode(1);

        System.out.println("Test 6: " + solution.kthSmallest(root6, 6) + " (Expected: 6)");

        // Test case 7: Right-skewed BST [1,null,2,null,3,null,4], k = 2
        // Expected output: 2
        TreeNode root7 = new TreeNode(1);
        root7.right = new TreeNode(2);
        root7.right.right = new TreeNode(3);
        root7.right.right.right = new TreeNode(4);

        System.out.println("Test 7: " + solution.kthSmallest(root7, 2) + " (Expected: 2)");

        // Test case 8: [5,3,6,2,4,null,null,1], k = 5
        // Expected output: 5
        TreeNode root8 = new TreeNode(5);
        root8.left = new TreeNode(3);
        root8.right = new TreeNode(6);
        root8.left.left = new TreeNode(2);
        root8.left.right = new TreeNode(4);
        root8.left.left.left = new TreeNode(1);

        System.out.println("Test 8: " + solution.kthSmallest(root8, 5) + " (Expected: 5)");
    }
}

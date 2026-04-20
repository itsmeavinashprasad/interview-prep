package dsa.tree;

/**
 * <h1>98. Validate Binary Search Tree</h1>
 *
 * <p>
 * Given the <code>root</code> of a binary tree, <em>determine if it is a valid binary search tree (BST)</em>.
 * </p>
 *
 * <p>
 * A <b>valid BST</b> is defined as follows:
 * </p>
 * <ul>
 * <li>The left subtree of a node contains only nodes with keys <b>strictly less than</b> the node's key.</li>
 * <li>The right subtree of a node contains only nodes with keys <b>strictly greater than</b> the node's key.</li>
 * <li>Both the left and right subtrees must also be binary search trees.</li>
 * </ul>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: root = [2,1,3]
 * Output: true
 * Explanation: The tree is a valid BST.
 *
 * <b>Example 2:</b>
 * Input: root = [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in the tree is in the range [1, 10^4].</li>
 * <li>-2^31 &lt;= Node.val &lt;= 2^31 - 1</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/validate-binary-search-tree/description/">LeetCode -
 *      Validate Binary Search Tree</a>
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

public class ValidateBinarySearchTree {

    /**
     * Validate if a binary tree is a valid binary search tree
     *
     * @param root the root node of the binary tree
     * @return true if the tree is a valid BST, false otherwise
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null)
            return true;

        if (root.val > min && root.val < max) {
            return isValidBST(root.left, min, root.val) & isValidBST(root.right, root.val, max);
        }
        return false;
    }

    public static void main(String[] args) {
        ValidateBinarySearchTree solution = new ValidateBinarySearchTree();

        // Test case 1: [2,1,3]
        // Expected output: true
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);

        System.out.println("Test 1: " + solution.isValidBST(root1) + " (Expected: true)");

        // Test case 2: [5,1,4,null,null,3,6]
        // Expected output: false (root is 5, but right child's left child is 3 which is
        // < 5)
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);

        System.out.println("Test 2: " + solution.isValidBST(root2) + " (Expected: false)");

        // Test case 3: Single node [5]
        // Expected output: true
        TreeNode root3 = new TreeNode(5);

        System.out.println("Test 3: " + solution.isValidBST(root3) + " (Expected: true)");

        // Test case 4: [1,1]
        // Expected output: false (left child has same value as root)
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(1);

        System.out.println("Test 4: " + solution.isValidBST(root4) + " (Expected: false)");

        // Test case 5: [10,5,15,null,null,6,20]
        // Expected output: false (root is 10, but left subtree's right child is 6 which
        // should be > 10)
        TreeNode root5 = new TreeNode(10);
        root5.left = new TreeNode(5);
        root5.right = new TreeNode(15);
        root5.right.left = new TreeNode(6);
        root5.right.right = new TreeNode(20);

        System.out.println("Test 5: " + solution.isValidBST(root5) + " (Expected: false)");

        // Test case 6: [32,26,47,19,null,null,56,null,27]
        // Expected output: false (complexity check)
        TreeNode root6 = new TreeNode(32);
        root6.left = new TreeNode(26);
        root6.right = new TreeNode(47);
        root6.left.left = new TreeNode(19);
        root6.right.right = new TreeNode(56);
        root6.left.left.right = new TreeNode(27);

        System.out.println("Test 6: " + solution.isValidBST(root6) + " (Expected: false)");

        // Test case 7: [1,null,2,null,3]
        // Expected output: true (right-skewed BST)
        TreeNode root7 = new TreeNode(1);
        root7.right = new TreeNode(2);
        root7.right.right = new TreeNode(3);

        System.out.println("Test 7: " + solution.isValidBST(root7) + " (Expected: true)");

        // Test case 8: [3,2,1]
        // Expected output: false (right child 1 < root 3 but 1 should not be in right
        // subtree)
        TreeNode root8 = new TreeNode(3);
        root8.left = new TreeNode(2);
        root8.right = new TreeNode(1);

        System.out.println("Test 8: " + solution.isValidBST(root8) + " (Expected: false)");
    }
}

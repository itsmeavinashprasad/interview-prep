package dsa.tree;
/**
 * <h1>104. Maximum Depth of Binary Tree</h1>
 *
 * <p>
 * Given the root of a binary tree, return its maximum depth.
 * </p>
 *
 * <p>
 * A binary tree's maximum depth is the number of nodes along the longest path
 * from the root node down to the farthest leaf node.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: 3
 * Explanation: The binary tree has maximum depth of 3.
 *
 * <b>Example 2:</b>
 * Input: root = [1,null,2]
 * Output: 2
 * Explanation: The binary tree has maximum depth of 2.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in the tree is in the range [0, 10^4].</li>
 * <li>-100 &lt;= Node.val &lt;= 100</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/maximum-depth-of-binary-tree/">LeetCode -
 *      Maximum Depth of Binary Tree</a>
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

public class MaximumDepthOfBinaryTree {

    /**
     * Find the maximum depth of a binary tree using recursion
     *
     * @param root the root node of the binary tree
     * @return the maximum depth (number of nodes on the longest path)
     */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;

        int maxHeightLeft = maxDepth(root.left);
        int maxHeightRight = maxDepth(root.right);

        return Math.max(maxHeightLeft, maxHeightRight) + 1;
    }

    public static void main(String[] args) {
        MaximumDepthOfBinaryTree solution = new MaximumDepthOfBinaryTree();

        // Test case 1: [3,9,20,null,null,15,7]
        // Expected output: 3
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println("Test 1: " + solution.maxDepth(root1) + " (Expected: 3)");

        // Test case 2: [1,null,2]
        // Expected output: 2
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        System.out.println("Test 2: " + solution.maxDepth(root2) + " (Expected: 2)");

        // Test case 3: null
        // Expected output: 0
        System.out.println("Test 3: " + solution.maxDepth(null) + " (Expected: 0)");

        // Test case 4: Single node [5]
        // Expected output: 1
        TreeNode root4 = new TreeNode(5);
        System.out.println("Test 4: " + solution.maxDepth(root4) + " (Expected: 1)");

        // Test case 5: Skewed tree [1,null,2,null,3,null,4]
        // Expected output: 4
        TreeNode root5 = new TreeNode(1);
        root5.right = new TreeNode(2);
        root5.right.right = new TreeNode(3);
        root5.right.right.right = new TreeNode(4);
        System.out.println("Test 5: " + solution.maxDepth(root5) + " (Expected: 4)");
    }
}

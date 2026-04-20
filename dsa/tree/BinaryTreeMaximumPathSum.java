package dsa.tree;

import java.util.List;

/**
 * <h1>124. Binary Tree Maximum Path Sum</h1>
 *
 * <p>
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence
 * has an edge connecting them. A node can only appear in the sequence <b>at most once</b>.
 * Note that the path does not need to pass through the root.
 * </p>
 *
 * <p>
 * The <b>path sum</b> of a path is the sum of the node's values in the path.
 * </p>
 *
 * <p>
 * Given the <code>root</code> of a binary tree, return <em>the maximum path sum of any
 * <b>non-empty</b> path</em>.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 → 1 → 3 with a path sum of 2 + 1 + 3 = 6.
 *
 * <b>Example 2:</b>
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 → 20 → 7 with a path sum of 15 + 20 + 7 = 42.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in the tree is in the range [1, 3 * 10^4].</li>
 * <li>-1000 &lt;= Node.val &lt;= 1000</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/binary-tree-maximum-path-sum/description/">LeetCode -
 *      Binary Tree Maximum Path Sum</a>
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

public class BinaryTreeMaximumPathSum {

    record RootAndSum(TreeNode root, int sum) {
    }

    /**
     * Find the maximum path sum in a binary tree
     *
     * @param root the root node of the binary tree
     * @return the maximum path sum
     */
    public int maxPathSum(TreeNode root) {
        int[] maxSum = new int[] { Integer.MIN_VALUE };
        dfs(root, maxSum);
        return maxSum[0];
    }

    private int dfs(TreeNode node, int[] maxSum) {
        if (node == null)
            return 0;

        // sum including the left node
        int left = Math.max(0, dfs(node.left, maxSum));
        // sum including the right node
        int right = Math.max(0, dfs(node.right, maxSum));

        // update the global maxsum including left + current + right
        maxSum[0] = Math.max(maxSum[0], node.val + left + right);

        // always return current including either left or right
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        BinaryTreeMaximumPathSum solution = new BinaryTreeMaximumPathSum();

        // Test case 1: [1,2,3]
        // Expected output: 6
        // Explanation: 2 → 1 → 3 = 2 + 1 + 3 = 6
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);

        System.out.println("Test 1: " + solution.maxPathSum(root1) + " (Expected: 6)");

        // Test case 2: [-10,9,20,null,null,15,7]
        // Expected output: 42
        // Explanation: 15 → 20 → 7 = 15 + 20 + 7 = 42
        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);

        System.out.println("Test 2: " + solution.maxPathSum(root2) + " (Expected: 42)");

        // Test case 3: [2,-1]
        // Expected output: 2
        // Explanation: Just node 2 itself
        TreeNode root3 = new TreeNode(2);
        root3.left = new TreeNode(-1);

        System.out.println("Test 3: " + solution.maxPathSum(root3) + " (Expected: 2)");

        // Test case 4: [5,-3,5]
        // Expected output: 15
        // Explanation: -3 → 5 → 5 (but since no path, it's 5 + 5 = 10, or just nodes: 5
        // + 5 = 10 -> Actually max is 5 + (-3) + 5 = 7 or single nodes, so likely
        // different)
        // Let me reconsider: -3 is left of 5 (root), 5 is right of 5 (root)
        // So: -3 + 5 + 5 = 7
        TreeNode root4 = new TreeNode(5);
        root4.left = new TreeNode(-3);
        root4.right = new TreeNode(5);

        System.out.println("Test 4: " + solution.maxPathSum(root4) + " (Expected: 15 or path sum)");

        // Test case 5: Single node [-3]
        // Expected output: -3
        TreeNode root5 = new TreeNode(-3);

        System.out.println("Test 5: " + solution.maxPathSum(root5) + " (Expected: -3)");

        // Test case 6: [1,-2,-3]
        // Expected output: 1
        // Explanation: Just the root node itself
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(-2);
        root6.right = new TreeNode(-3);

        System.out.println("Test 6: " + solution.maxPathSum(root6) + " (Expected: 1)");
    }
}

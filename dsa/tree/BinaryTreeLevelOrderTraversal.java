package dsa.tree;

import java.util.*;

/**
 * <h1>102. Binary Tree Level Order Traversal</h1>
 *
 * <p>
 * Given the root of a binary tree, return <em>the level order traversal of its nodes' values</em>.
 * (i.e., from left to right, level by level).
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * Explanation: Level 0: [3], Level 1: [9,20], Level 2: [15,7]
 *
 * <b>Example 2:</b>
 * Input: root = [1]
 * Output: [[1]]
 *
 * <b>Example 3:</b>
 * Input: root = []
 * Output: []
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in the tree is in the range [0, 2000].</li>
 * <li>-1000 &lt;= Node.val &lt;= 1000</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/binary-tree-level-order-traversal/description/">LeetCode -
 *      Binary Tree Level Order Traversal</a>
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

public class BinaryTreeLevelOrderTraversal {

    /**
     * Return the level order traversal of a binary tree
     *
     * @param root the root node of the binary tree
     * @return a list of lists containing the level order traversal
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null)
            return List.of();

        // initiaze a queue
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        List<List<Integer>> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            List<TreeNode> nodesAtLevel = new ArrayList<>();
            List<Integer> valuesAtLevel = new ArrayList<>();

            // get everything from single level in a list
            while (!queue.isEmpty()) {
                nodesAtLevel.add(queue.poll());
            }

            // for the current level, extract the values and put the left and right nodes in
            // the queue for next level
            for (TreeNode node : nodesAtLevel) {
                valuesAtLevel.add(node.val);
                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            ans.add(valuesAtLevel);
        }

        return ans;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrderTraversal solution = new BinaryTreeLevelOrderTraversal();

        // Test case 1: [3,9,20,null,null,15,7]
        // Expected output: [[3],[9,20],[15,7]]
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        System.out.println("Test 1: " + solution.levelOrder(root1));
        System.out.println("Expected: [[3], [9, 20], [15, 7]]");

        // Test case 2: [1]
        // Expected output: [[1]]
        TreeNode root2 = new TreeNode(1);

        System.out.println("Test 2: " + solution.levelOrder(root2));
        System.out.println("Expected: [[1]]");

        // Test case 3: []
        // Expected output: []
        System.out.println("Test 3: " + solution.levelOrder(null));
        System.out.println("Expected: []");

        // Test case 4: Skewed tree to the right [1,null,2,null,3,null,4]
        // Expected output: [[1],[2],[3],[4]]
        TreeNode root4 = new TreeNode(1);
        root4.right = new TreeNode(2);
        root4.right.right = new TreeNode(3);
        root4.right.right.right = new TreeNode(4);

        System.out.println("Test 4: " + solution.levelOrder(root4));
        System.out.println("Expected: [[1], [2], [3], [4]]");

        // Test case 5: Skewed tree to the left [1,2,null,3,null,4]
        // Expected output: [[1],[2],[3],[4]]
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.left.left = new TreeNode(3);
        root5.left.left.left = new TreeNode(4);

        System.out.println("Test 5: " + solution.levelOrder(root5));
        System.out.println("Expected: [[1], [2], [3], [4]]");

        // Test case 6: Balanced tree [1,2,3,4,5,6,7]
        // Expected output: [[1],[2,3],[4,5,6,7]]
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(2);
        root6.right = new TreeNode(3);
        root6.left.left = new TreeNode(4);
        root6.left.right = new TreeNode(5);
        root6.right.left = new TreeNode(6);
        root6.right.right = new TreeNode(7);

        System.out.println("Test 6: " + solution.levelOrder(root6));
        System.out.println("Expected: [[1], [2, 3], [4, 5, 6, 7]]");
    }
}

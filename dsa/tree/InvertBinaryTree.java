package dsa.tree;

/**
 * <h1>226. Invert Binary Tree</h1>
 *
 * <p>
 * Given the root of a binary tree, invert the tree, and return its root.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: root = [4,2,7,1,3,6,9]
 * Output: [4,7,2,9,6,3,1]
 * Explanation: The tree is inverted by swapping the left and right children.
 *
 * <b>Example 2:</b>
 * Input: root = [2,1,3]
 * Output: [2,3,1]
 *
 * <b>Example 3:</b>
 * Input: root = []
 * Output: []
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in the tree is in the range [0, 100].</li>
 * <li>-100 &lt;= Node.val &lt;= 100</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/invert-binary-tree/description/">LeetCode -
 *      Invert Binary Tree</a>
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

public class InvertBinaryTree {

    /**
     * Invert a binary tree
     *
     * @param root the root node of the binary tree to invert
     * @return the root of the inverted tree
     */
    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }

        // swap left and right nodes
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        // recursively call the next level on both sub trees
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }

    public static void main(String[] args) {
        InvertBinaryTree solution = new InvertBinaryTree();

        // Test case 1: [4,2,7,1,3,6,9]
        // Expected: [4,7,2,9,6,3,1]
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(9);

        TreeNode inverted1 = solution.invertTree(root1);
        System.out.println("Test 1: Inverted root val = " + inverted1.val + ", left val = " + inverted1.left.val +
                ", right val = " + inverted1.right.val + " (Expected: 4, 7, 2)");

        // Test case 2: [2,1,3]
        // Expected: [2,3,1]
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(3);

        TreeNode inverted2 = solution.invertTree(root2);
        System.out.println("Test 2: Inverted root val = " + inverted2.val + ", left val = " + inverted2.left.val +
                ", right val = " + inverted2.right.val + " (Expected: 2, 3, 1)");

        // Test case 3: []
        // Expected: []
        TreeNode inverted3 = solution.invertTree(null);
        System.out.println("Test 3: Inverted root = " + inverted3 + " (Expected: null)");

        // Test case 4: Single node [5]
        // Expected: [5]
        TreeNode root4 = new TreeNode(5);
        TreeNode inverted4 = solution.invertTree(root4);
        System.out.println("Test 4: Inverted root val = " + inverted4.val + " (Expected: 5)");

        // Test case 5: Skewed tree [1,null,2,null,3]
        // Expected: [1,3,null,2,null]
        TreeNode root5 = new TreeNode(1);
        root5.right = new TreeNode(2);
        root5.right.right = new TreeNode(3);

        TreeNode inverted5 = solution.invertTree(root5);
        System.out.println("Test 5: Inverted root val = " + inverted5.val + ", left val = " + inverted5.left.val +
                ", right val = " + inverted5.right + " (Expected: 1, 3, null)");
    }
}

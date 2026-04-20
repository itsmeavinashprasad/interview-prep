package dsa.tree;

/**
 * <h1>100. Same Tree</h1>
 *
 * <p>
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * </p>
 *
 * <p>
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: p = [1,2,3], q = [1,2,3]
 * Output: true
 * Explanation: The two trees are structurally identical with the same node values.
 *
 * <b>Example 2:</b>
 * Input: p = [1,2,1], q = [1,null,2,2]
 * Output: false
 * Explanation: The trees have different structure.
 *
 * <b>Example 3:</b>
 * Input: p = [1,2,1], q = [1,1,2]
 * Output: false
 * Explanation: The trees have the same structure but different node values.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in both trees is in the range [0, 100].</li>
 * <li>-10^4 &lt;= Node.val &lt;= 10^4</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/same-tree/description/">LeetCode -
 *      Same Tree</a>
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

public class SameTree {

    /**
     * Check if two binary trees are the same
     *
     * @param p the root node of the first binary tree
     * @param q the root node of the second binary tree
     * @return true if both trees are the same, false otherwise
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // if both null, return true
        if (p == null && q == null) {
            return true;
        }

        // if anyone of them is null, then return false
        if (p == null || q == null) {
            return false;
        }

        // compare values at both node
        boolean same = p.val == q.val;
        if (!same) {
            return false;
        }

        // then check both left and right sub trees
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        SameTree solution = new SameTree();

        // Test case 1: [1,2,3], [1,2,3]
        // Expected output: true
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);

        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);

        System.out.println("Test 1: " + solution.isSameTree(p1, q1) + " (Expected: true)");

        // Test case 2: [1,2,1], [1,null,2,2]
        // Expected output: false
        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);
        p2.right = new TreeNode(1);

        TreeNode q2 = new TreeNode(1);
        q2.right = new TreeNode(2);

        System.out.println("Test 2: " + solution.isSameTree(p2, q2) + " (Expected: false)");

        // Test case 3: [1,2,1], [1,1,2]
        // Expected output: false
        TreeNode p3 = new TreeNode(1);
        p3.left = new TreeNode(2);
        p3.right = new TreeNode(1);

        TreeNode q3 = new TreeNode(1);
        q3.left = new TreeNode(1);
        q3.right = new TreeNode(2);

        System.out.println("Test 3: " + solution.isSameTree(p3, q3) + " (Expected: false)");

        // Test case 4: null, null
        // Expected output: true
        System.out.println("Test 4: " + solution.isSameTree(null, null) + " (Expected: true)");

        // Test case 5: [1], null
        // Expected output: false
        TreeNode p5 = new TreeNode(1);
        System.out.println("Test 5: " + solution.isSameTree(p5, null) + " (Expected: false)");

        // Test case 6: [1,2], [1,null,2]
        // Expected output: false
        TreeNode p6 = new TreeNode(1);
        p6.left = new TreeNode(2);

        TreeNode q6 = new TreeNode(1);
        q6.right = new TreeNode(2);

        System.out.println("Test 6: " + solution.isSameTree(p6, q6) + " (Expected: false)");
    }
}

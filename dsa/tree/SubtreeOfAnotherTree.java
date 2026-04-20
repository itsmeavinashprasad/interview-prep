package dsa.tree;

/**
 * <h1>572. Subtree of Another Tree</h1>
 *
 * <p>
 * Given the roots of two binary trees <code>root</code> and <code>subRoot</code>,
 * return <code>true</code> if there is a subtree of <code>root</code> with the same
 * structure and node values of <code>subRoot</code> and <code>false</code> otherwise.
 * </p>
 *
 * <p>
 * A subtree of a binary tree <code>tree</code> is a tree that consists of a node in <code>tree</code>
 * and all of this node's descendants. The tree <code>tree</code> could also be considered as a subtree of itself.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: root = [3,4,5,1,2], subRoot = [4,1,2]
 * Output: true
 * Explanation: The subtree rooted at node 4 in root has the same structure and values as subRoot.
 *
 * <b>Example 2:</b>
 * Input: root = [3,4,5,1,2], subRoot = [4,1,null,2]
 * Output: false
 * Explanation: There is no subtree of root with the same structure and values as subRoot.
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in the <code>root</code> tree is in the range [1, 2000].</li>
 * <li>The number of nodes in the <code>subRoot</code> tree is in the range [1, 1000].</li>
 * <li>-10^4 &lt;= Node.val &lt;= 10^4</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/subtree-of-another-tree/description/">LeetCode -
 *      Subtree of Another Tree</a>
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

public class SubtreeOfAnotherTree {

    /**
     * Check if subRoot is a subtree of root
     *
     * @param root    the root node of the main tree
     * @param subRoot the root node of the subtree to check
     * @return true if subRoot is a subtree of root, false otherwise
     */
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null && subRoot == null)
            return true;

        if (root == null || subRoot == null)
            return false;

        boolean same = isSameTree(root, subRoot);
        if (same)
            return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);

    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        SubtreeOfAnotherTree solution = new SubtreeOfAnotherTree();

        // Test case 1: root = [3,4,5,1,2], subRoot = [4,1,2]
        // Expected output: true
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(2);

        TreeNode subRoot1 = new TreeNode(4);
        subRoot1.left = new TreeNode(1);
        subRoot1.right = new TreeNode(2);

        System.out.println("Test 1: " + solution.isSubtree(root1, subRoot1) + " (Expected: true)");

        // Test case 2: root = [3,4,5,1,2], subRoot = [4,1,null,2]
        // Expected output: false
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(5);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(2);

        TreeNode subRoot2 = new TreeNode(4);
        subRoot2.left = new TreeNode(1);
        subRoot2.left.right = new TreeNode(2);

        System.out.println("Test 2: " + solution.isSubtree(root2, subRoot2) + " (Expected: false)");

        // Test case 3: root = [1,1], subRoot = [1]
        // Expected output: true
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(1);

        TreeNode subRoot3 = new TreeNode(1);

        System.out.println("Test 3: " + solution.isSubtree(root3, subRoot3) + " (Expected: true)");

        // Test case 4: root and subRoot are the same tree
        // root = [1,2,3], subRoot = [1,2,3]
        // Expected output: true
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);

        TreeNode subRoot4 = new TreeNode(1);
        subRoot4.left = new TreeNode(2);
        subRoot4.right = new TreeNode(3);

        System.out.println("Test 4: " + solution.isSubtree(root4, subRoot4) + " (Expected: true)");

        // Test case 5: root = [1,2], subRoot = [2]
        // Expected output: true
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);

        TreeNode subRoot5 = new TreeNode(2);

        System.out.println("Test 5: " + solution.isSubtree(root5, subRoot5) + " (Expected: true)");

        // Test case 6: root = [1,null,2], subRoot = [2,1]
        // Expected output: false
        TreeNode root6 = new TreeNode(1);
        root6.right = new TreeNode(2);

        TreeNode subRoot6 = new TreeNode(2);
        subRoot6.left = new TreeNode(1);

        System.out.println("Test 6: " + solution.isSubtree(root6, subRoot6) + " (Expected: false)");
    }
}

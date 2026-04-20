package dsa.tree;

/**
 * <h1>235. Lowest Common Ancestor of a Binary Search Tree</h1>
 *
 * <p>
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
 * </p>
 *
 * <p>
 * According to the <a href="https://en.wikipedia.org/wiki/Lowest_common_ancestor">definition of LCA on Wikipedia</a>:
 * "The lowest common ancestor is defined between two nodes <code>p</code> and <code>q</code> as the lowest node in <code>T</code>
 * that has both <code>p</code> and <code>q</code> as descendants (where we allow a node to be a descendant of itself)."
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 *
 * <b>Example 2:</b>
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
 *
 * <b>Example 3:</b>
 * Input: root = [2,1], p = 2, q = 1
 * Output: 2
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in the tree is in the range [2, 10^5].</li>
 * <li>-10^9 &lt;= Node.val &lt;= 10^9</li>
 * <li>All Node.val are <b>unique</b>.</li>
 * <li>p != q</li>
 * <li><code>p</code> and <code>q</code> will exist in the BST.</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/">LeetCode -
 *      Lowest Common Ancestor of a Binary Search Tree</a>
 */

/**
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class LowestCommonAncestorOfABinarySearchTree {

    /**
     * Find the lowest common ancestor of two nodes in a binary search tree
     *
     * @param root the root node of the binary search tree
     * @param p the first node
     * @param q the second node
     * @return the lowest common ancestor node
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null){
            return root;
        }

        // in the further statements, we will assume that p < q
        // if not then swap now
        if(p.val > q.val){
            TreeNode t = p;
            p = q;
            q = t;
        }

        // 2 base conditions
        // both p and q are found in left subtree
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }
        // both p and q are foudn in right subtree
        if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }


        if(root.val >= p.val && root.val <= q.val){
            return root;
        }

        return null;
    }

    public static void main(String[] args) {
        LowestCommonAncestorOfABinarySearchTree solution = new LowestCommonAncestorOfABinarySearchTree();

        // Test case 1: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
        // Expected output: 6
        TreeNode root1 = new TreeNode(6);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(0);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(7);
        root1.right.right = new TreeNode(9);
        root1.left.right.left = new TreeNode(3);
        root1.left.right.right = new TreeNode(5);

        TreeNode p1 = root1.left;  // node 2
        TreeNode q1 = root1.right; // node 8

        TreeNode result1 = solution.lowestCommonAncestor(root1, p1, q1);
        System.out.println("Test 1: " + (result1 != null ? result1.val : "null") + " (Expected: 6)");

        // Test case 2: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
        // Expected output: 2
        TreeNode p2 = root1.left;       // node 2
        TreeNode q2 = root1.left.right; // node 4

        TreeNode result2 = solution.lowestCommonAncestor(root1, p2, q2);
        System.out.println("Test 2: " + (result2 != null ? result2.val : "null") + " (Expected: 2)");

        // Test case 3: root = [2,1], p = 2, q = 1
        // Expected output: 2
        TreeNode root3 = new TreeNode(2);
        root3.left = new TreeNode(1);

        TreeNode p3 = root3;       // node 2
        TreeNode q3 = root3.left;  // node 1

        TreeNode result3 = solution.lowestCommonAncestor(root3, p3, q3);
        System.out.println("Test 3: " + (result3 != null ? result3.val : "null") + " (Expected: 2)");

        // Test case 4: root = [6,2,8,0,4,7,9,null,null,3,5], p = 3, q = 5
        // Expected output: 4 (both are descendants of 4)
        TreeNode p4 = root1.left.right.left;  // node 3
        TreeNode q4 = root1.left.right.right; // node 5

        TreeNode result4 = solution.lowestCommonAncestor(root1, p4, q4);
        System.out.println("Test 4: " + (result4 != null ? result4.val : "null") + " (Expected: 4)");

        // Test case 5: root = [6,2,8,0,4,7,9,null,null,3,5], p = 0, q = 9
        // Expected output: 6 (root is the LCA)
        TreeNode p5 = root1.left.left;  // node 0
        TreeNode q5 = root1.right.right; // node 9

        TreeNode result5 = solution.lowestCommonAncestor(root1, p5, q5);
        System.out.println("Test 5: " + (result5 != null ? result5.val : "null") + " (Expected: 6)");

        // Test case 6: root = [6,2,8,0,4,7,9,null,null,3,5], p = 7, q = 9
        // Expected output: 8
        TreeNode p6 = root1.right.left;  // node 7
        TreeNode q6 = root1.right.right; // node 9

        TreeNode result6 = solution.lowestCommonAncestor(root1, p6, q6);
        System.out.println("Test 6: " + (result6 != null ? result6.val : "null") + " (Expected: 8)");

        // Test case 7: root = [6,2,8,0,4,7,9,null,null,3,5], p = 0, q = 3
        // Expected output: 2
        TreeNode p7 = root1.left.left;       // node 0
        TreeNode q7 = root1.left.right.left; // node 3

        TreeNode result7 = solution.lowestCommonAncestor(root1, p7, q7);
        System.out.println("Test 7: " + (result7 != null ? result7.val : "null") + " (Expected: 2)");

        // Test case 8: Single path tree [1,null,2,null,3], p = 1, q = 3
        // Expected output: 1
        TreeNode root8 = new TreeNode(1);
        root8.right = new TreeNode(2);
        root8.right.right = new TreeNode(3);

        TreeNode p8 = root8;           // node 1
        TreeNode q8 = root8.right.right; // node 3

        TreeNode result8 = solution.lowestCommonAncestor(root8, p8, q8);
        System.out.println("Test 8: " + (result8 != null ? result8.val : "null") + " (Expected: 1)");
    }
}

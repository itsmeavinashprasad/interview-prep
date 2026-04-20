package dsa.tree;

/**
 * <h1>106. Construct Binary Tree from Inorder and Postorder Traversal</h1>
 *
 * <p>
 * Given two integer arrays <code>inorder</code> and <code>postorder</code> where
 * <code>inorder</code> is the inorder traversal of a binary tree and <code>postorder</code>
 * is the postorder traversal of the same tree, construct and return <em>the binary tree</em>.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 * Explanation: The tree is constructed from the traversals.
 *
 * <b>Example 2:</b>
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>1 &lt;= inorder.length &lt;= 3000</li>
 * <li>postorder.length == inorder.length</li>
 * <li>-3000 &lt;= inorder[i], postorder[i] &lt;= 3000</li>
 * <li>inorder and postorder consist of <b>unique</b> values.</li>
 * <li>Each value of postorder also appears in inorder.</li>
 * <li>inorder is <b>guaranteed</b> to be the inorder traversal of the tree.</li>
 * <li>postorder is <b>guaranteed</b> to be the postorder traversal of the tree.</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/">LeetCode -
 *      Construct Binary Tree from Inorder and Postorder Traversal</a>
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

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {

    /**
     * Construct a binary tree from inorder and postorder traversals
     *
     * @param inorder   the inorder traversal of the binary tree
     * @param postorder the postorder traversal of the binary tree
     * @return the root node of the constructed binary tree
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = postorder.length;
        return buildTreeHelper(inorder, postorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode buildTreeHelper(int[] inorder, int[] postorder, int l1, int r1, int l2, int r2) {
        if (l1 > r1) {
            return null;
        }
        if (l1 == r1) {
            return new TreeNode(inorder[l1]);
        }
        int rootval = postorder[r2];
        int i = l1;
        while (i <= r1 && rootval != inorder[i]) {
            i++;
        }

        TreeNode left = buildTreeHelper(inorder, postorder, l1, i - 1, l2, l2 + (i - l1 - 1));
        TreeNode right = buildTreeHelper(inorder, postorder, i + 1, r1, l2 + (i - l1), r2 - 1);
        TreeNode root = new TreeNode(rootval);
        root.left = left;
        root.right = right;
        return root;
    }

    /**
     * Helper method to print tree in inorder for verification
     */
    private void printInorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null ");
            return;
        }
        printInorder(root.left, sb);
        sb.append(root.val).append(" ");
        printInorder(root.right, sb);
    }

    public static void main(String[] args) {
        ConstructBinaryTreeFromInorderAndPostorderTraversal solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal();

        // Test case 1: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
        // Expected output: [3,9,20,null,null,15,7]
        int[] inorder1 = { 9, 3, 15, 20, 7 };
        int[] postorder1 = { 9, 15, 7, 20, 3 };

        TreeNode root1 = solution.buildTree(inorder1, postorder1);
        StringBuilder sb1 = new StringBuilder();
        solution.printInorder(root1, sb1);
        System.out.println("Test 1 - Inorder traversal: " + sb1.toString());
        System.out.println("Expected: 9 3 15 20 7");
        System.out.println("Root value: " + (root1 != null ? root1.val : "null") + " (Expected: 3)");

        // Test case 2: inorder = [-1], postorder = [-1]
        // Expected output: [-1]
        int[] inorder2 = { -1 };
        int[] postorder2 = { -1 };

        TreeNode root2 = solution.buildTree(inorder2, postorder2);
        System.out.println("Test 2 - Root value: " + (root2 != null ? root2.val : "null") + " (Expected: -1)");

        // Test case 3: inorder = [1,2], postorder = [2,1]
        // Expected output: [1,2]
        int[] inorder3 = { 1, 2 };
        int[] postorder3 = { 2, 1 };

        TreeNode root3 = solution.buildTree(inorder3, postorder3);
        System.out.println("Test 3 - Root value: " + (root3 != null ? root3.val : "null") +
                ", has left child: " + (root3 != null && root3.left != null) + " (Expected: 1, true)");

        // Test case 4: inorder = [1,2,3], postorder = [1,3,2]
        // Expected output: [2,1,3]
        int[] inorder4 = { 1, 2, 3 };
        int[] postorder4 = { 1, 3, 2 };

        TreeNode root4 = solution.buildTree(inorder4, postorder4);
        System.out.println("Test 4 - Root value: " + (root4 != null ? root4.val : "null") + " (Expected: 2)");

        // Test case 5: inorder = [3,2,1], postorder = [3,1,2]
        // Expected output: [2,1,3] (left-skewed tree)
        int[] inorder5 = { 3, 2, 1 };
        int[] postorder5 = { 3, 1, 2 };

        TreeNode root5 = solution.buildTree(inorder5, postorder5);
        System.out.println("Test 5 - Root value: " + (root5 != null ? root5.val : "null") + " (Expected: 2)");

        // Test case 6: inorder = [1,2,3,4,5,6,7], postorder = [1,2,4,3,5,6,7]
        // Expected output: [4,2,5,1,3,6,7] (balanced tree)
        int[] inorder6 = { 1, 2, 3, 4, 5, 6, 7 };
        int[] postorder6 = { 1, 2, 4, 3, 5, 6, 7 };

        TreeNode root6 = solution.buildTree(inorder6, postorder6);
        StringBuilder sb6 = new StringBuilder();
        solution.printInorder(root6, sb6);
        System.out.println("Test 6 - Inorder traversal: " + sb6.toString());
        System.out.println("Expected: 1 2 3 4 5 6 7");
    }
}

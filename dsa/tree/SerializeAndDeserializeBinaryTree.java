package dsa.tree;

import java.util.*;

/**
 * <h1>297. Serialize and Deserialize Binary Tree</h1>
 *
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits
 * so that it can be stored in a file or memory buffer, or transmitted across a network connection
 * link to be reconstructed later in the same or another computer environment.
 * </p>
 *
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how
 * your serialization/deserialization algorithm should work. You just need to ensure that a binary
 * tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * </p>
 *
 * <p>
 * <b>Clarification:</b> The input/output format is the same as how LeetCode serializes a binary tree.
 * You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * </p>
 *
 * <h2>Examples</h2>
 *
 * <pre>
 * <b>Example 1:</b>
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 *
 * <b>Example 2:</b>
 * Input: root = []
 * Output: []
 * </pre>
 *
 * <h2>Constraints:</h2>
 * <ul>
 * <li>The number of nodes in the tree is in the range [0, 10^4].</li>
 * <li>-1000 &lt;= Node.val &lt;= 1000</li>
 * </ul>
 *
 * @see <a href="https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/">LeetCode -
 *      Serialize and Deserialize Binary Tree</a>
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

public class SerializeAndDeserializeBinaryTree {

    /**
     * Encodes a tree to a single string.
     */
    public String serialize(TreeNode root) {
        if (root == null)
            return "";

        // initialize queue
        List<String> ansList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);


        // perform level order traversal
        while (!queue.isEmpty()) {
            List<TreeNode> nodesAtLevel = new ArrayList<>();

            while (!queue.isEmpty()) {
                nodesAtLevel.add(queue.poll());
            }

            for (TreeNode node : nodesAtLevel) {
                if (node != null) {
                    ansList.add(String.valueOf(node.val));
                    queue.add(node.left);
                    queue.add(node.right);
                } else {
                    ansList.add("null");
                }

            }
        }

        // with above loop, extra null are added for leaf nodes, so removing those from the end
        int lastNullAt = ansList.size() - 1;
        while (ansList.get(lastNullAt).equals("null")) {
            lastNullAt--;
        }

        return String.join(",", ansList.subList(0, lastNullAt + 1));
    }

    /**
     * Decodes your encoded data to tree.
     */
    public TreeNode deserialize(String data) {
        if(data == null || data.length()==0){
            return null;
        }
        int i=1;
        String[] values = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(i < values.length ){
            
            List<TreeNode> nodesFromPrevLevel = new ArrayList<>();
            while(!queue.isEmpty()){
                nodesFromPrevLevel.add(queue.poll());
            }
            
            for (TreeNode node : nodesFromPrevLevel) {
                
                // create left node
                if(i<values.length && !"null".equals(values[i])){
                    TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                    queue.offer(left);
                    node.left = left;
                }
                i++;

                // create rightr node
                if(i<values.length && !"null".equals(values[i])){
                    TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                    queue.offer(right);
                    node.right = right;
                }
                i++;
                
            }
            
        }

        return root;
    }

    public static void main(String[] args) {
        SerializeAndDeserializeBinaryTree solution = new SerializeAndDeserializeBinaryTree();

        // Test case 1: [1,2,3,null,null,4,5]
        // Expected output: [1,2,3,null,null,4,5]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);

        String serialized1 = solution.serialize(root1);
        TreeNode deserialized1 = solution.deserialize(serialized1);
        System.out.println("Test 1 - Serialized: " + serialized1);
        System.out.println("Test 1 - Deserialized root: " + (deserialized1 != null ? deserialized1.val : "null"));

        // Test case 2: []
        // Expected output: []
        String serialized2 = solution.serialize(null);
        TreeNode deserialized2 = solution.deserialize(serialized2);
        System.out.println("Test 2 - Serialized: " + serialized2);
        System.out.println("Test 2 - Deserialized root: " + (deserialized2 != null ? deserialized2.val : "null"));

        // Test case 3: Single node [5]
        // Expected output: [5]
        TreeNode root3 = new TreeNode(5);
        String serialized3 = solution.serialize(root3);
        TreeNode deserialized3 = solution.deserialize(serialized3);
        System.out.println("Test 3 - Serialized: " + serialized3);
        System.out.println("Test 3 - Deserialized root: " + (deserialized3 != null ? deserialized3.val : "null"));

        // Test case 4: Skewed tree to the right [1,null,2,null,3]
        // Expected output: [1,null,2,null,3]
        TreeNode root4 = new TreeNode(1);
        root4.right = new TreeNode(2);
        root4.right.right = new TreeNode(3);

        String serialized4 = solution.serialize(root4);
        TreeNode deserialized4 = solution.deserialize(serialized4);
        System.out.println("Test 4 - Serialized: " + serialized4);
        System.out.println("Test 4 - Deserialized root: " + (deserialized4 != null ? deserialized4.val : "null"));

        // Test case 5: Balanced tree [1,2,3,4,5,6,7]
        // Expected output: [1,2,3,4,5,6,7]
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.right = new TreeNode(3);
        root5.left.left = new TreeNode(4);
        root5.left.right = new TreeNode(5);
        root5.right.left = new TreeNode(6);
        root5.right.right = new TreeNode(7);

        String serialized5 = solution.serialize(root5);
        TreeNode deserialized5 = solution.deserialize(serialized5);
        System.out.println("Test 5 - Serialized: " + serialized5);
        System.out.println("Test 5 - Deserialized root: " + (deserialized5 != null ? deserialized5.val : "null"));

        // Test case 6: Tree with negative values [-1,0,1]
        // Expected output: [-1,0,1]
        TreeNode root6 = new TreeNode(-1);
        root6.left = new TreeNode(0);
        root6.right = new TreeNode(1);

        String serialized6 = solution.serialize(root6);
        TreeNode deserialized6 = solution.deserialize(serialized6);
        System.out.println("Test 6 - Serialized: " + serialized6);
        System.out.println("Test 6 - Deserialized root: " + (deserialized6 != null ? deserialized6.val : "null"));
    }
}

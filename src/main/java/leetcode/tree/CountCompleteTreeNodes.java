package leetcode.tree;

/*
222. Count Complete Tree Nodes
https://leetcode.com/problems/count-complete-tree-nodes/
 */
//2152
//2153
//TODO binary search를 이용해서 O(log2^d) = O(d) * price d= O(d^2)로 풀 수 있다.
public class CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        return root == null ? 0 : countNodes(root.left) + countNodes(root.right) + 1;
    }



    public class TreeNode {
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
}

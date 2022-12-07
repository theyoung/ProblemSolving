package leetcode.node;

import java.util.LinkedList;

/*
938. Range Sum of BST
https://leetcode.com/problems/range-sum-of-bst/
 */
public class RangeSumofBST {
    public int rangeSumBST(TreeNode root, int low, int high) {
        LinkedList<TreeNode> que = new LinkedList<>();
        que.add(root);

        int sum = 0;
        while (!que.isEmpty()) {
            TreeNode node = que.pollFirst();
            if(low <= node.val && node.val <= high) sum += node.val;
            if(node.left != null) que.add(node.left);
            if(node.right != null) que.add(node.right);
        }

        return sum;

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

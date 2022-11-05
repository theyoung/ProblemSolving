package leetcode.tree;

/**
 * 124. Binary Tree Maximum Path Sum
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/?envType=study-plan&id=level-3
 */
//1657
//1709
public class BinaryTreeMaximumPathSum {
    Integer max;

    public int maxPathSum(TreeNode root) {
        max = Integer.valueOf(root.val);
        helper(root);
        return max;
    }

    int helper(TreeNode node){
        if(node == null) return 0;
        // root의 오른쪽과 왼쪽 중 더 큰 수를 취한다.
        // 0이하이면 0을 선택한다.
        // max는 하나만
        int left = Math.max(maxPathSum(node.left),0);
        int right = Math.max(maxPathSum(node.right),0);
        int bigger = Math.max(left,right);
        max = Math.max(max, node.val + left + right);

        return node.val + bigger;
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

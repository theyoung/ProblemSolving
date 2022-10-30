package leetcode.tree;

/**
 * 112. Path Sum
 * https://leetcode.com/problems/path-sum/
 */
//TODO 문제를 잘 읽자 root부터 leaf까지의 sum이다. leaf는 자식이 없는 경우만 성립한다.
public class PathSum {
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

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        return helper(root, targetSum);
    }

    boolean helper(TreeNode root, int targetSum) {
        if(root.left == null && root.right == null){
            return root.val == targetSum;
        }
        if(root.left != null && helper(root.left, targetSum-root.val)) return true;
        if(root.right != null && helper(root.right, targetSum-root.val)) return true;
        return false;
    }
}

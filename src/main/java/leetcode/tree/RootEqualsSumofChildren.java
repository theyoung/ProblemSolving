package leetcode.tree;

/**
 * 2236. Root Equals Sum of Children
 * https://leetcode.com/problems/root-equals-sum-of-children/
 */
public class RootEqualsSumofChildren {
    public boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }

    public class TreeNode {
        int val;
        PathSum.TreeNode left;
        PathSum.TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, PathSum.TreeNode left, PathSum.TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}

package leetcode.node;

/*
1339. Maximum Product of Splitted Binary Tree
https://leetcode.com/problems/maximum-product-of-splitted-binary-tree/description/
 */
//TODO module을 이용한 size를 계산하는 것을 배웠다.
public class MaximumProductofSplittedBinaryTree {
// 50_000 * 10000 = 500_000_000 < 2^30
// 500_000_000/2 = 250_000_000^2 = 6_500_000_000_000_000 < 2^60

    long max = 0l;
    final int module = 1_000_000_007;

    public int maxProduct(TreeNode root) {
        this.max = 0;

        //모든 Node를 Sum한다.
        // post order 기준으로
        // 4,5,2,6,3,1 이 있다면 21이 sum이고
        // 21-4 17*4
        // 21-5 16*5
        // 21-(11) 10*11 = 110 -> 아래에서 올라온 val을 sum해줘야 한다.
        int total = sum(root);
        postorder(root,total);
        return (int)(this.max%module);
    }

    int sum(TreeNode node){
        if(node==null) return 0;
        return node.val + sum(node.left) + sum(node.right);
    }

    int postorder(TreeNode node, int sum){
        if(node==null) return 0;

        int left = postorder(node.left,sum);
        int right = postorder(node.right,sum);
        int cur = node.val + left + right;
        this.max = Math.max(this.max, (sum-cur) * (long)cur);
        return cur;
    }
}

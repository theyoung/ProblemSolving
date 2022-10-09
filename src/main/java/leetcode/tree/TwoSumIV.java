package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 653. Two Sum IV - Input is a BST
 * https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 */

//TODO inorder로 sorted된 list를 얻어와서 k를 찾는 방법을 배울 수 있다.
public class TwoSumIV {
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        int left = 0;
        int right = list.size()-1;
        while(left < right){
            int lval = list.get(left);
            int rval = list.get(right);

            if(lval + rval == k) return true;
            if(lval + rval < k){
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    void inorder(TreeNode node, List<Integer> list){
        if(node.left != null) inorder(node.left, list);
        list.add(node.val);
        if(node.right != null) inorder(node.right, list);
    }


     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode() {}
         TreeNode(int val) { this.val = val; }
         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

}

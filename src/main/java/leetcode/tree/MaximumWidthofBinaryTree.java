package leetcode.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 662. Maximum Width of Binary Tree
 * https://leetcode.com/problems/maximum-width-of-binary-tree/?envType=study-plan&id=level-3
 */
//1735
//TODO BFS를 활용하고 node의 val을 활용하자. 못 풀었다. 다시 풀자.
public class MaximumWidthofBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        //bfs를 활용해 보자.
        LinkedList<TreeNode> queue = new LinkedList<>();
        root.val = 1;
        queue.add(root);
        int max = 0;
        while(!queue.isEmpty()){
            int width = queue.get(queue.size()-1).val - queue.get(0).val + 1;
            max = Math.max(max,width);

            for(int i = queue.size()-1; 0 <= i; i--){
                TreeNode node = queue.pollFirst();
                if(node.left != null){
                    node.left.val = node.val*2;
                    queue.add(node.left);
                }
                if(node.right != null){
                    node.right.val = node.val*2+1;
                    queue.add(node.right);
                }
            }
        }

        return max;
    }

    int getWidth(List<TreeNode> list){
        int left = -1;
        int right = -1;

        for(int i = 0; i < list.size(); i++){
            if(list.get(i) != null){
                if(left < 0) left = i;
                right = i;
            }
        }
        if(left < 0) return 0;
        list = list.subList(left,right+1);
        return right - left + 1;
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

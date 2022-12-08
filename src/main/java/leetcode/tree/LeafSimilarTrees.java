package leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
872. Leaf-Similar Trees
https://leetcode.com/problems/leaf-similar-trees/
 */
public class LeafSimilarTrees {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        //que에다 넣어서 빼는 방식으로 하면 가능.
        //그런데 더 깨끗하게 하는 방법 없을까?
        //DFS로 처리하면 되지 않을까?
        List<Integer> list1 = new ArrayList<>();
        getDFS(root1, list1);
        List<Integer> list2 = new ArrayList<>();
        getDFS(root2, list2);

        return list1.equals(list2);
    }

    void getDFS(TreeNode node, List<Integer> result){
        if(node.left == null && node.right == null) {
            result.add(node.val);
            return;
        }
        if(node.left != null) getDFS(node.left, result);
        if(node.right != null) getDFS(node.right, result);
    }



    //BFS는 안됨
    List<Integer> getBFS(TreeNode node){
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(node);
        List<Integer> list = new ArrayList<>();

        while (!que.isEmpty()) {

            for (int i = que.size(); 0 < i; i--) {
                TreeNode cur = que.pollFirst();
                if(cur.left == null && cur.right == null) list.add(cur.val);
                if(cur.left != null) que.addLast(cur.left);
                if(cur.right != null) que.addLast(cur.right);
            }
        }
        return list;
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

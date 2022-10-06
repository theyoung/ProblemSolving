package leetcode.tree;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 987. Vertical Order Traversal of a Binary Tree
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
 */
public class VerticalOrderTraversalofaBinaryTree {
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

    //sort 부분을 최적화-> 큰의미 없
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        LinkedList<TreeNode> list = new LinkedList<>();
        LinkedList<Integer> cols = new LinkedList<>();
        list.addLast(root);
        cols.addFirst(0);

        while(0 < list.size()){
            int width = list.size();
            Map<Integer, PriorityQueue<Integer>> tmpMap = new HashMap<>();

            for (int i = 0; i < width; i++) {
                TreeNode node = list.pollFirst();
                int col = cols.pollFirst();

                PriorityQueue<Integer> tmpList = tmpMap.getOrDefault(col, new PriorityQueue<>());
                tmpList.add(node.val);
                tmpMap.put(col, tmpList);

                if(node.left != null) {
                    list.addLast(node.left);
                    cols.addLast(col-1);
                }
                if(node.right != null) {
                    list.addLast(node.right);
                    cols.addLast(col+1);
                }
            }

            for(int col : tmpMap.keySet()){
                PriorityQueue<Integer> tmpList = tmpMap.get(col);
                List<Integer> addedList = new ArrayList<>();
                while (!tmpList.isEmpty()){
                    addedList.add(tmpList.poll());
                }

                List<Integer> oriList =map.get(col);
                if(oriList == null){
                    map.put(col, addedList);
                } else {
                    oriList.addAll(addedList);
                    map.put(col, oriList);
                }
            }
        }

        List<Integer> keys = map.keySet().stream().sorted((a, b) -> a - b).collect(Collectors.toList());

        List<List<Integer>> results = new ArrayList<>();

        for (int key : keys) {
            List<Integer> contains = map.get(key);
            results.add(contains);
        }

        return results;
    }

    //sort하는 부분을 최적화 할 수 있음
    public List<List<Integer>> verticalTraversalSlow(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        LinkedList<TreeNode> list = new LinkedList<>();
        LinkedList<Integer> cols = new LinkedList<>();
        list.addLast(root);
        cols.addFirst(0);

        while(0 < list.size()){
            int width = list.size();
            Map<Integer, List<Integer>> tmpMap = new HashMap<>();

            for (int i = 0; i < width; i++) {
                TreeNode node = list.pollFirst();
                int col = cols.pollFirst();

                List<Integer> tmpList = tmpMap.getOrDefault(col, new ArrayList<>());
                tmpList.add(node.val);
                tmpList.sort((a,b)->a-b);
                tmpMap.put(col, tmpList);

                if(node.left != null) {
                    list.addLast(node.left);
                    cols.addLast(col-1);
                }
                if(node.right != null) {
                    list.addLast(node.right);
                    cols.addLast(col+1);
                }
            }

            for(int col : tmpMap.keySet()){
                List<Integer> tmpList = tmpMap.get(col);
                List<Integer> oriList =map.get(col);
                if(oriList == null){
                    map.put(col, tmpList);
                } else {
                    oriList.addAll(tmpList);
                    map.put(col, oriList);
                }
            }
        }

        List<Integer> keys = map.keySet().stream().sorted((a, b) -> a - b).collect(Collectors.toList());

        List<List<Integer>> results = new ArrayList<>();

        for (int key : keys) {
            List<Integer> contains = map.get(key);
            results.add(contains);
        }

        return results;
    }
}

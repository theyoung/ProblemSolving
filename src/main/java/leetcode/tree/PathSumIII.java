package leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 437. Path Sum III
 * https://leetcode.com/problems/path-sum-iii/
 */
//1610
//1649
//TODO HashMap을 이용한 Prefix sum으로 다시 풀어보자. https://leetcode.com/problems/subarray-sum-equals-k/, 다시 풀었다. prefix sum의 range를 고민하자.
public class PathSumIII {
    int result = 0;

    public int pathSum(TreeNode root, int targetSum) {
        //특정 array에서 sum이 8이 되는 범위를 찾으면 된다.
        //[1,2,3,2,-3,3,5] -> [1,2,3,2], [1,2,3,2,-3,3] [3,8] 3개
        // brute force는 하나의 array에 O(N^2)으로 찾을 수 있다. 다른 방법도 있을까?
        // 일단 brute force dfs로 처리해 보자.

        return dfs(root, targetSum, new ArrayList<>());
    }

    public int pathSumHash(TreeNode root, int targetSum) {
        Map<Long,Integer> map = new HashMap<>();
        map.put(0L,1);
        return helper(root, targetSum, map, 0);
    }

    int helper(TreeNode node, int targetSum, Map<Long,Integer> map, long prefixSum){
        if(node == null) return 0;

        prefixSum += node.val;

        int result = 0;
        if(map.containsKey(prefixSum-targetSum)){
            result += map.get(prefixSum-targetSum);
        }
        // System.out.println(prefixSum + " " + map.get(prefixSum-targetSum));
        map.put(prefixSum, map.getOrDefault(prefixSum,0)+1);

        result += helper(node.left, targetSum, map, prefixSum);

        result += helper(node.right, targetSum, map, prefixSum);

        map.put(prefixSum, map.getOrDefault(prefixSum,0)-1);

        return result;
    }

    //list는 prefixsum을 기준으로 만들어 진다.
    public int dfs(TreeNode node, int targetSum, List<Integer> list){
        //현 node를 기준으로 target이 나오는지만 확인하면 된다.
        //prefix sum을 기준으로 처리 해도 괜찮을 것 같다.
        //단, 0부터 ~ n-1, 1부터 ~n-1까지의 sum기준이다.
        //[1,2,3,2,-3,3,5]
        //[13,12,10,7,5,8,5]
        int result = node.val == targetSum? 1 : 0;

        for(int i = 0; i < list.size(); i++){
            int val = list.get(i);
            if(val + node.val == targetSum) result++;
            list.set(i, val+node.val);
        }
        list.add(node.val);

        if(node.left != null){
            result += dfs(node.left, targetSum, list);
        }
        if(node.right != null){
            result += dfs(node.right, targetSum, list);
        }

        for(int i = 0; i < list.size(); i++){
            int val = list.get(i);
            list.set(i, val-node.val);
        }
        list.remove(list.size());

        return result;
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

package leetcode.tree;

import java.util.*;

/**
 * 310. Minimum Height Trees
 * https://leetcode.com/problems/minimum-height-trees/?envType=study-plan&id=level-3
 */
//1543
//TODO topology sorting을 이용한 문제이다. 못 풀었다. 다시 풀자.
public class MinimumHeightTrees {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n==1) return List.of(0);
        if(n==2) return List.of(0,1);

        ArrayList<Set<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nodes.add(new HashSet<>());
        }

        for(int[] edge : edges){
            nodes.get(edge[0]).add(edge[1]);
            nodes.get(edge[1]).add(edge[0]);
        }

        ArrayList<Integer> leave = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (nodes.get(i).size() == 1) {
                leave.add(i);
            }
        }
        int nodeCount = n;
        while(2 < nodeCount){
            ArrayList<Integer> newLeave = new ArrayList<>();
            nodeCount -= leave.size();
            for(int next : leave){
                int target = (int)nodes.get(next).toArray()[0];
                nodes.get(target).remove(next);
                if(nodes.get(target).size()==1) newLeave.add(target);
            }
            leave = newLeave;

        }

        return leave;
    }


    //Priority Queue를 사용했는데 이때문에 느리게 되었다. 다시
    class Node{
        int index = 0;
        public ArrayList<Node> list;
        public Node(int index){
            this.index = index;
        }
    }

    //topology sorting을 처리하는 방법
    // 결과는 1아니면 2둘중에 하나이다.
    // 0-0-0 <- 이렇게 3이면 결국 가운데있는 0이 답이 되기 때문이다.
    public List<Integer> findMinHeightTreesPriorityQueue(int n, int[][] edges) {
        //node를 만들자
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i++) nodes[i] = new Node(i);
        for(int[] edge : edges){
            nodes[edge[0]].list.add(nodes[edge[1]]);
            nodes[edge[1]].list.add(nodes[edge[0]]);
        }

        //Priority Queue를 이용해서 topology sorting을 처리하자.
        PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.list.size()-b.list.size());
        for(Node node : nodes) pq.add(node);

        while(pq.size() < 3){
            while(pq.peek().list.size() == 1){
                Node node = pq.poll();
                Node target = node.list.get(0);
                target.list.remove(node);
                pq.remove(target);
                pq.add(target);
            }
        }

        List<Integer> result = new ArrayList<>();
        for(Node node : pq){
            result.add(node.index);
        }

        return result;
    }


    public List<Integer> findMinHeightTreesBFS(int n, int[][] edges) {
        if(n==1) return List.of(0);
        if(n==2) return List.of(0,1);

        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int[] edge : edges){
            List<Integer> list = map.getOrDefault(edge[0], new ArrayList<>());
            list.add(edge[1]);
            map.put(edge[0],list);
            List<Integer> list2 = map.getOrDefault(edge[1], new ArrayList<>());
            list2.add(edge[0]);
            map.put(edge[1],list2);
        }
        int minHeight = n;
        List<Integer> result = new ArrayList<>();

        int[][] dp = new int[n+1][n+1];
        //그리고 dfs로 최소길이를 찾자
        for (int i = 0; i < n; i++) {
            int height = 0;

            LinkedList<Integer> list = new LinkedList<>();
            list.addLast(i);

            while(!list.isEmpty()){
                height++;
                for(int j = list.size()-1; 0 <= j; j--){
                    List<Integer> tmp = map.get(list.pollFirst());
                    list.addAll(tmp);
                }
            }

            if(height < minHeight ){
                minHeight = height;
                result.clear();
            }
            if(height == minHeight) result.add(i);
        }
        // node는 최대 20,000개 dfs는 19,999회 순회한다.
        return result;
    }

    public List<Integer> findMinHeightTreesDFS(int n, int[][] edges) {
        if(n==1) return List.of(0);
        if(n==2) return List.of(0,1);

        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int[] edge : edges){
            List<Integer> list = map.getOrDefault(edge[0], new ArrayList<>());
            list.add(edge[1]);
            map.put(edge[0],list);
            List<Integer> list2 = map.getOrDefault(edge[1], new ArrayList<>());
            list2.add(edge[0]);
            map.put(edge[1],list2);
        }
        int minHeight = n;
        List<Integer> result = new ArrayList<>();

        int[][] dp = new int[n+1][n+1];
        //그리고 dfs로 최소길이를 찾자
        for (int i = 0; i < n; i++) {
            int height = helper(map, -1, i, dp);
            // System.out.println(i + " " + height);
            if(height < minHeight ){
                minHeight = height;
                result.clear();
            }
            if(height == minHeight) result.add(i);
        }
        // node는 최대 20,000개 dfs는 19,999회 순회한다.
        return result;
    }

    int helper(Map<Integer, List<Integer>> map, int start, int end, int[][] dp){
        if(0 < dp[start+1][end+1]) return dp[start+1][end+1];
        List<Integer> list = map.get(end);
        int result = 0;
        for(int next : list){
            if(next == start) continue;
            result = Math.max(result, helper(map,end,next,dp));
        }
        dp[start+1][end+1] = result+1;
        return result+1;
    }

}

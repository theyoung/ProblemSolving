package leetcode.graph;
import java.util.*;

/*
1443. Minimum Time to Collect All Apples in a Tree
https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/description/
 */
//TODO undirected는 양방향, unidirected는 단방향 뜻을 잘 이해하자
public class MinimumTimetoCollectAllApplesinaTree {

    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        /*
                0
            0       0
            mid에서 왼쪽과 오른쪽의 사과가 있다면
            (leftdepth - curdepth)*2 + (rightdepth - curdepth)*2

            mid의 왼쪽에 사과 2개가 있다면?
                    0 4+1    0depth
                0 2+2        1depth
            0       0     2depth

            mid의 왼쪽 + 1depth 에 사과 2개가 있다면?
                        0 4+2    0depth
                    0 4+1    0depth
                0 2+2        1depth
            0       0     2depth

            돌아올때 1씩 더해진다면?
                        0 7+1    0depth
                    0 6+1    1depth
                0 4+2        2depth
            2       2     3depth
            왼쪽에 있는 경우 +1
            오른쪽에 있는 경우 +1

                            402
                        02+2       2
                      2   0      0  0

                            6010
                        04+2       2 2+2+6 왼쪽 오른쪽 상관없이 숫자가 있으면 +2return?
                      2   2      2  6
                                      4
                                         2
        */
        Node[] nodes = new Node[n];

        for(int[] edge : edges){
            int start = edge[0];
            int end = edge[1];
            if(nodes[start]==null) nodes[start]= new Node();
            if(nodes[end]==null) nodes[end]= new Node();
            nodes[start].nexts.add(nodes[end]);
            nodes[end].nexts.add(nodes[start]);
        }

        for(int i = 0; i < nodes.length; i++){
            if(hasApple.get(i)) nodes[i].apple = true;
        }

        return Math.max(0,dfs(nodes[0],null)-2);

    }

    int dfs(Node node, Node pre){
        int count = 0;
        for(Node next : node.nexts){
            if(next == pre) continue;
            count += dfs(next,node);
        }

        if(0 < count || node.apple){
            count += 2;
        }

        return count;
    }

    class Node {
        List<Node> nexts = new ArrayList<>();
        boolean apple = false;
    }
}

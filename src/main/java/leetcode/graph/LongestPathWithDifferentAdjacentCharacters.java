package leetcode.graph;
import java.util.*;
/*
2246. Longest Path With Different Adjacent Characters
https://leetcode.com/problems/longest-path-with-different-adjacent-characters/description/
 */
public class LongestPathWithDifferentAdjacentCharacters {
    int max = 1;
    public int longestPath(int[] parent, String s) {
        this.max = 1;
        //dfs로 가장 긴 길이를 찾는 방법 사용
        //무방향 그래프로 가장 긴 길이를 찾아야 한다
        /*
            max는 field 로 정의

                a
            a       a
            -> 1

                a
            b       a
            -> 왼쪽 1 + if cur != right then 오른쪽 1
            -> 2

                a
            b       c
            -> 1 + 1 + 1 = 3


                    d
                a
            b       c
            -> return 할때는 왼쪽 또는 오른쪽 최대값 + 1
            -> 1 + 1+ 1 = 3
            O(N)
            O(N)
        */
        Node[] nodes = new Node[s.length()];

        for(int i = 0; i < s.length(); i++){
            nodes[i] = new Node(i, s.charAt(i));
        }

        for(int i = 0; i < s.length(); i++){
            if(0 <= parent[i]){
                Node pNode = nodes[parent[i]];
                Node sNode = nodes[i];
                pNode.nexts.add(sNode);
            }
        }

        dfs(nodes[0], null);

        return max;
    }

    int dfs(Node node, Node pre){
        int count = 1;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a,b)->a-b);

        for(Node next : node.nexts){
            if(next == pre) continue;
            int nextCount = dfs(next, node);
            if(node.ch == next.ch) continue;
            queue.add(nextCount);
            if(2 < queue.size()) queue.poll();
        }

        int lastValue = 0;
        while(!queue.isEmpty()){
            lastValue = queue.poll();
            count += lastValue;
        }
        this.max = Math.max(this.max, count);

        return lastValue + 1;
    }

    class Node {
        int num;
        char ch;
        List<Node> nexts;

        Node(int num, char ch){
            this.num = num;
            this.ch = ch;
            nexts = new ArrayList<>();
        }
    }
}

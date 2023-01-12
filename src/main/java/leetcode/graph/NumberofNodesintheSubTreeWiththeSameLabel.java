package leetcode.graph;
import java.util.*;
/*
1519. Number of Nodes in the Sub-Tree With the Same Label
https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/description/
 */
public class NumberofNodesintheSubTreeWiththeSameLabel {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        /*
            0노드는 root이다
            0노드를 기준으로 하위 노드에 나와 동일한 숫자가 있는 count를 답으로 return 해라
            int[node 갯수]
            dfs로 하고 return 은 int[] 26이다
            Node는 nexts, char, charCount로 구성하자

                [a:3]a
            [a:2] a   b[b:1]   b[b:1]
            a -> leaf이면 int[a:1]를 return 한다. 자기 자신의 count를 update한다
            O(Node)
            O(Node)
        */
        Node[] nodes = new Node[n];
        for(int[] edge : edges){
            Node start = nodes[edge[0]]==null? new Node(edge[0], labels.charAt(edge[0])-'a') : nodes[edge[0]];
            Node end   = nodes[edge[1]]==null? new Node(edge[1], labels.charAt(edge[1])-'a') : nodes[edge[1]];
            nodes[edge[0]] = start;
            nodes[edge[1]] = end;
            start.nexts.add(end);
            end.nexts.add(start);
        }
        dfs(nodes[0], null);

        int[] result = new int[n];
        for(int i = 0; i < n; i++) result[i] = nodes[i].count;

        return result;
    }

    int[] dfs(Node node, Node pre){
        int[] counts = new int[26];
        // System.out.println(node.toString());

        counts[node.ch]++;

        for(Node next : node.nexts){
            if(next == pre) continue;

            int[] tmp = dfs(next, node);

            for(int i = 0; i < 26; i++){
                counts[i] += tmp[i];
            }
        }
        node.count = counts[node.ch];

        return counts;
    }

    class Node{
        int num;
        int count;
        int ch;
        List<Node> nexts;

        Node(int num, int ch){
            this.num = num;
            this.ch = ch;
            this.count = 0;
            nexts = new ArrayList<>();
        }

        @Override
        public String toString(){
            return "num = " + num + " ch = " + ch + " count = " + count;
        }
    }
}

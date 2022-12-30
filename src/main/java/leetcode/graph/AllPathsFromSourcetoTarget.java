package leetcode.graph;
import java.util.*;

/*
797. All Paths From Source to Target
https://leetcode.com/problems/all-paths-from-source-to-target/description/
 */
//TODO DFS에 DP를 활용한 문제
//TC는 O(2^N*N) path관점에서 보면 node가 늘어날 수록 path는 최대 2배씩 늘어난다 그럼으로 O(2^node), 각 path의 최대 길이는 n-2임으로 O(2^N*N)
// 다만 DP를 활용했음으로 path가 짧아 지면서 TC가 줄어 들거라 생각했지만, next의 path를 copy하고 거기에 나의 node번호를 넣는 행위를 결국 하기때문에 TC자체는 줄어들지 않음.
//SC는 O(nodes + edges + pathnum) node와 edges그리고 path를 담음으로
public class AllPathsFromSourcetoTarget {

    class Node {
        int num;
        List<Node> nexts;
        List<List<Integer>> path;
        Node(int num){
            this.num = num;
            this.nexts = new ArrayList<>();
            this.path = new ArrayList<>();
        }

        public String toString(){
            return num + " " + nexts.size() + " " + path.size();
        }
    }

    Node[] nodes;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        nodes = new Node[graph.length];
        for(int i = 0; i < graph.length; i++) nodes[i] = new Node(i);

        for(int i = 0; i < graph.length; i++){
            Node source = nodes[i];
            for(int next : graph[i]){
                Node target = nodes[next];
                source.nexts.add(target);
            }
        }
        nodes[nodes.length-1].path.add(List.of(nodes.length-1));

        return dfs(nodes[0]);
    }

    List<List<Integer>> dfs(Node node){

        if(0 < node.path.size()) return node.path;

        for(Node next : node.nexts){
            List<List<Integer>> nextPath = dfs(next);

            for(List<Integer> path : nextPath){
                if(path.size() == 0) continue;

                ArrayList<Integer> list = new ArrayList<>(path);
                list.add(0,node.num);
                node.path.add(list);
            }
        }
        return node.path;
    }
}

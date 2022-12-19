package leetcode.graph;
/*
1971. Find if Path Exists in Graph
https://leetcode.com/problems/find-if-path-exists-in-graph/description/
 */
public class FindifPathExistsinGraph {
    int[] nodes;
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        nodes = new int[n];

        for(int i = 0; i < n; i++) nodes[i] = i;

        for(int[] edge : edges){
            int start = edge[0];
            int end = edge[1];
            int sRoot = find(start);
            int eRoot = find(end);
            nodes[sRoot] = eRoot;
        }

        return find(source) == find(destination);
    }

    int find(int idx){
        if(nodes[idx] == idx) return idx;
        nodes[idx] = find(nodes[idx]);
        return nodes[idx];
    }
}

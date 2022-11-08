package leetcode.array;

import java.util.*;

/**
 * 815. Bus Routes
 * https://leetcode.com/problems/bus-routes/?envType=study-plan&id=level-3
 */
//2050
//2150
//TODO 어떻게든 풀었다. BFS사용이 맞네...
public class BusRoutes {

    class Node {
        public Set<Integer> stations;
        public Set<Node> edges;
        public int number;
        int source = -1;
        int dest = -1;
        public boolean isSource = false;
        public boolean isTarget = false;
        public Node(int number, int source, int dest){
            this.source = source;
            this.dest = dest;
            this.number = number;
            stations = new HashSet<>();
            edges = new HashSet<>();
        }
        public void addEdge(Node node){
            edges.add(node);
        }

        public void addBus(int[] stas){
            for(int station : stas){
                if(station== this.source) isSource = true;
                if(station== this.dest) isTarget = true;
                stations.add(station);
            }
        }

        public boolean contains(Node node){
            for(int sta : node.stations){
                if(stations.contains(sta)) return true;
            }
            return false;
        }
    }


    public int numBusesToDestination(int[][] routes, int source, int target) {
        if(source == target) return 0;

        List<Node> nodes = new ArrayList<>();
        List<Node> starts = new ArrayList<>();
        List<Node> destinations = new ArrayList<>();

        for (int i = 0; i < routes.length; i++) {
            Node node = new Node(i,source,target);
            node.addBus(routes[i]);
            if(node.isTarget && node.isSource) return 1;
            if(node.isTarget) destinations.add(node);
            if(node.isSource) starts.add(node);
            nodes.add(node);
        }

        for (int i = 0; i < nodes.size(); i++) {
            Node cur = nodes.get(i);
            for(int j = i+1; j < nodes.size(); j++){
                Node tar = nodes.get(j);
                if(cur.contains(tar)){
                    cur.addEdge(tar);
                    tar.addEdge(cur);
                }
            }
        }
        int min = Integer.MAX_VALUE;

        for(Node start : starts){
            LinkedList<Node> queue = new LinkedList<>();
            boolean[] visited = new boolean[routes.length];
            queue.add(start);
            int depth = 0;
            while(!queue.isEmpty()){
                depth++;
                for(int i = queue.size()-1; 0 <= i; i--){
                    Node node = queue.pollFirst();
                    visited[node.number] = true;
                    // System.out.println(node.number + " " +node.isSource + " " + node.isTarget);
                    if(node.isTarget){
                        min = Math.min(min, depth);
                        queue.clear();
                        break;
                    } else {
                        for(Node edge : node.edges){
                            if(visited[edge.number]) continue;
                            queue.addLast(edge);
                        }
                    }
                }
            }
        }

        return min == Integer.MAX_VALUE? -1 : min;
    }
}

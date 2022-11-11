package leetcode.node;

import java.util.*;

/**
 * 133. Clone Graph
 * https://leetcode.com/problems/clone-graph/?envType=study-plan&id=level-3
 */
public class CloneGraph {

    public Node cloneGraph(Node node) {
        if(node == null) return null;
        Map<Integer,Node> map = new HashMap<>();

        return cloneData(node, map);
    }

    public Node cloneData(Node node, Map<Integer,Node>  map){
        // System.out.println(node.val);

        if(map.containsKey(node.val)) return map.get(node.val);

        Node clone = new Node(node.val);
        map.put(node.val, clone);

        for(Node next : node.neighbors){
            clone.neighbors.add(cloneData(next, map));
        }

        return clone;
    }


    class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
}

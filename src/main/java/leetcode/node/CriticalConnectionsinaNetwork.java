package leetcode.node;
import java.util.*;
/*
1192. Critical Connections in a Network
https://leetcode.com/problems/critical-connections-in-a-network/description/
 */
//TODO 타잔 알고리즘. 오랫만에 다시 봤더니 못풀었다. 기억해서 다시 풀자.
public class CriticalConnectionsinaNetwork {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i++) nodes[i] = new Node(i);




        for(List<Integer> con : connections){
            int start = con.get(0);
            int end = con.get(1);

            nodes[start].cons.add(nodes[end]);
            nodes[end].cons.add(nodes[start]);
        }

        List<List<Integer>> result = new ArrayList<>();

        for(Node node : nodes){
            travel(null,node, result,1);
        }

        return result;
    }
    /*
    [0,1],[1,2],[2,0],[1,3]
    0 -> 1
    0 -> 2
    1 -> 0
    1 -> 2
    1 -> 3
    2 -> 1
    2 -> 0
    3 -> 1

    n -> 0 / result = []
    0.label = 1
    0.min = 1

    n -> 2 / result = []
    2.label = 3 -> 2
    2.min = 3 -> 2


    0 -> 1 / result = []
    1.label = 2
    1.min = 2
    resp 0

        1 -> 2 / result = []
        2.label = 3
        2.min = 3
        resp 1
        resp 4 / result = [[1,3]]

        1-> 3  / result = []
        3.label = 4
        return 4

        return 1

    2 -> 0 / result = []
    return 1

    */
    int travel(Node start, Node cur, List<List<Integer>> result, int count){

        if(cur.label < 10_0001) {
            cur.label = Math.min(cur.label,count);
            return cur.label;
        }

        cur.label = count;

        int min = cur.label;

        for(Node next : cur.cons){
            if(next == start) continue;
            System.out.println(((start==null)?"n":start.number) + " " + next.number);

            int resp = travel(cur,next,result,count+1);

            min = Math.min(min,resp);
            if(count < resp) result.add(List.of(cur.number,next.number));
        }
        cur.label = min;
        return min;
    }


    class Node {
        List<Node> cons;
        int number;
        int label;

        public Node(int number){
            this.number = number;
            cons = new ArrayList<>();
            this.label = 10_0001;
        }

    }
}

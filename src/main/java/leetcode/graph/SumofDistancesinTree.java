package leetcode.graph;
import java.util.*;
/*
834. Sum of Distances in Tree
https://leetcode.com/problems/sum-of-distances-in-tree/description/
 */
//TODO 시간이 오래 걸리긴 했지만 풀었다 graph문제, rerooting dp라는 문제라고 한다. post order -> pre order로 변경해서 풀어보자.
public class SumofDistancesinTree {
    //T O(Nodes) 각 노드를 한번씩만 접근한다
    //S O(Nodes) node를 저장하기위한 별도 공간을 사용했다
    Node[] nodes;
    int n;
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        this.n = n;
        //각 노드별 weight를 측정한다

        // 부모 weight(-내 weight) + 내 weight + 부모 노드 개수
        /*

                    | weight가 추가 된다면?
                    0
                  1/ \1    4
                  0   0
                2/
                0

                0
             1/   \ 1
            0       0
          2/    2  / \2
          0        0   0
              3  /
                0
            4  / \ 4
              0   0

        1. 각 노드는 자신의 깊이를 알 수 있다(필요한가?)
        2. 내 이하 노드의 개수를 알 수 있다
        3. 내 이하 노드의 거리 sum을 알 수 있다
        n = 9

                0 19(9)
              /   \
        1(2) 0     0 10(6)
           /       / \
          0     5 0(4) 0 0 (1)
                 /
                0 2(3)
               / \
          0(1)0   0 0(1)

        1. 내 weight를 알수 있다
        2. 나를 포함한 내 노드의 수를 알 수 있다
        3. 내 부모 노드의 값을 알 수 있다


            0  modified_p_weight - (m_weight + m_c_node + 1) + m_weight + (n - m_c_node - 1) + 1
          /    \
        0       0 19(9)
                  \
                   0 10(6)
                   / \
                5 0(4) 0 0 (1)
                 /
                0 2(3)
               / \
          0(1)0   0 0(1)

                       [5,8]
                [0,0]        [3,3]
                        [0,0][0,0][0,0]

                8 - (0 + 0 + 1) + 0 + 4 + 1 = 12
                8 - (3 + 3 + 1) + 3 + (5-3-1) + 1 = 6
                6 - (0 + 0 + 1) + 0 + (5 - 0 - 1) + 1 = 10

                modified_p_weight - 2m_c_node + n - 1
        */
        nodes = new Node[n];
        for(int i = 0; i < n; i++) nodes[i] = new Node(i);

        for(int[] edge : edges){
            int left = edge[0];
            int right = edge[1];
            nodes[left].edges.add(nodes[right]);
            nodes[right].edges.add(nodes[left]);
        }

        buildWeight(nodes[0],null);

        buildDivide(nodes[0],null);
        int[] result = new int[n];
        for(int i = 0; i < n; i++){
            // System.out.println(i + " = " + nodes[i].weight);
            result[i] = nodes[i].weight;
        }
        return result;
    }

    void buildDivide(Node node, Node parent){
        //update weight
        if(parent != null){
            //modified_p_weight - 2m_c_node + n - 1
            //modified_p_weight - (m_weight + m_c_node + 1) + m_weight + (n - m_c_node - 1) + 1
            // node.weight = parent.weight - (node.weight + node.c_node + 1) + node.weight + (this.n - node.c_node -2) + 1;
            node.weight = parent.weight - (2*node.c_node) + n -2;
        }

        for(Node next : node.edges){
            if(next == parent) continue;
            buildDivide(next,node);
        }
    }

    int[] buildWeight(Node node, Node parent){

        for(Node next : node.edges){
            if(next == parent) continue;
            int[] response = buildWeight(next,node);
            node.c_node += response[0] + 1;
            node.weight += response[1] + response[0] + 1;
        }

        return new int[]{node.c_node, node.weight};//child node count, weight
    }

    class Node {
        int num;
        int weight;
        int c_node;
        List<Node> edges;
        public Node(int num){
            this.num = num;
            this.weight = 0;
            this.c_node = 0;
            this.edges = new ArrayList<>();
        }
    }

    /*
                0 19(9)
              /   \
        1(2) 1     0 10(6)
           /       / \
          0     5 0(4) 0 0 (1)
                 /
                0 2(3)
               / \
          0(1)0   0 0(1)

    이런 그래프가 있고 root에서 보는 전체 weigh는 19임
    0이 1로 접근하지 않은 weight는?
                0 17(7)
                  \
        1(2) 1     0 10(6)
           /       / \
          0     5 0(4) 0 0 (1)
                 /
                0 2(3)
               / \
          0(1)0   0 0(1)
          19-2

          1이 0으로 접근하는 weight는 N-2 7

               0 17(7) -> 끊어다고 하지만 1의 weight는 17에 포함되어 있음
             /    \
       24(2)1     0 10(6)
           /       / \
          0     5 0(4) 0 0 (1)
                 /
                0 2(3)
               / \
          0(1)0   0 0(1)
          1이 0으로 접근하기 위해서는 0이하 노드개수 7회를 더 가야함
          17+7 =24

          부모의 weight - 자식 이하 node의 count를 통해 부모가 자식 이하로 접근하는 수를 삭제하고
          자식이 부모에 접근하는 수 node의 count를 더하면 나의 값이 된다.
     */
}

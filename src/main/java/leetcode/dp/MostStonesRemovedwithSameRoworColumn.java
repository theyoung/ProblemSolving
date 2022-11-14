package leetcode.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
947. Most Stones Removed with Same Row or Column
https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
 */
//2110
//2300
//TODO union find.. ㅠㅠ 어렵네.. 풀긴했지만 시간이 오래 걸렸다.
public class MostStonesRemovedwithSameRoworColumn {
    @Test
    void test(){
        Assertions.assertEquals(5, this.removeStones(new int[][]{{0,0}, {0,1}, {1,0}, {1,2}, {2,1}, {2,2}}));
        Assertions.assertEquals(3, this.removeStones(new int[][]{{0,0}, {0,2}, {1,1}, {2,0}, {2,2}}));
        Assertions.assertEquals(0, this.removeStones(new int[][]{{0,0}}));
        Assertions.assertEquals(2, this.removeStones(new int[][]{{0,1}, {1,0}, {1,1}}));
        Assertions.assertEquals(6, this.removeStones(new int[][]{{3,2},{0,0},{3,3},{2,1},{2,3},{2,2},{0,2}}));
    }

    public int removeStones(int[][] stones) {
        //   0 1 2 3 4 5
        // 0 2 1
        // 1 2   2
        // 2   2 2
        //   0 1 2 3 4 5
        // 0 1 1
        // 1 1   1
        // 2   1 1
        //   0 1 2 3 4 5
        // 0 1 1
        // 1 1   1
        // 2   1 1
        //   0 1 2 3 4 5
        // 0 1 0 1
        // 1   1
        // 2 1 0 1
        // case 1. col과 row에 다른 stone이 없다면 지울 수 없다.
        // case 2. 타 stone으로 부터 영향을 받는 stone을 지울 수 있다.

        //   0 1 2 3 4 5
        // 0 1 0 1 1 1
        // 1   0
        // 2 0 1 0 1

        // Map<int[],List<int[]>> : row, col에 연관이 있는 row,col에 LinkedList를 undirected로 만든다.
        // 모든 stones에서 시작을 해서 stone을 지워간다.

        // 문제가 너무 어려워 진다. 다르게 만들어 보자.
        // 내가 영향을 줄 수 있는 최대 stone은?
        // 최대 stone에 이웃하는 stone은 모두 삭제,
        // 다음 이웃하는 stone 삭제 안될때 까지 loop
        //   0 1 2 3 4 5
        // 0 1 0 1
        // 1   1
        // 2 1 0 1 0

        //   0 1 2 3 4 5
        // 0 2 1
        // 1 2   2
        // 2   2 2
        // 가장 길게 연결될 수 있는 방법은? group을 만들어서 root만 남기고 다 삭제 시킨다.
        // union find를 만들고 stones의 남는 갯수는 union find의 갯수남 남는다.
        Node[] nodes = new Node[stones.length];
        int[] points = new int[stones.length];
        for(int i = 0; i < points.length; i++) points[i] = i;

        int result = 0;

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(stones[i][0],stones[i][1]);
        }

        //[[3,2],[0,0],[3,3],[2,1],[2,3],[2,2],[0,2]]
        //                x          x     x     x
        for (int i = 0; i < nodes.length; i++) {
            int left = findRoot(i,points);
            Node node = nodes[left];
            for(int j = i+1; j < nodes.length; j++){
                if(node.isContains(stones[j][0],stones[j][1])){
                    int right = findRoot(j, points);
                    Node rightNode = nodes[right];
                    if(node == rightNode) continue;

//                    System.out.println(i + " " + left + " " + right);
                    rightNode.mergeNode(node);
                    node = rightNode;
                    points[left] = right;
                    left = right;
                    result++;
                }
            }
        }

        return result;
    }

    int findRoot(int find, int[] points){
        if(points[find] == find) return find;
        points[find] = findRoot(points[find],points);
        return points[find];
    }

    class Node{
        public Set<Integer> rows;
        public Set<Integer> cols;
        boolean invalidate = false;

        public Node(){
            rows = new HashSet<>();
            cols = new HashSet<>();
        }
        public Node(int row, int col){
            rows = new HashSet<>();
            rows.add(row);
            cols = new HashSet<>();
            cols.add(col);
        }

        public void mergeNode(Node node){
            this.rows.addAll(node.rows);
            this.cols.addAll(node.cols);
            node.invalidate = true;
        }

        public boolean isContains(int row, int col){
            return rows.contains(row) || cols.contains(col);
        }
    }
}

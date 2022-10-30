package leetcode.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * 1293. Shortest Path in a Grid with Obstacles Elimination
 * https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
 */
//TODO Matrix BFS를 활용할 때 set과 hashing을 활용하는 방법과 seen의 시점을 초기에 넣어서 작동 속도를 빠르게 하는 방법을 공부할 수 있다. priority queue를 활용해서 A* 알고리즘을 생각해 볼 수도 있다.
public class ShortestPathinaGridwithObstaclesElimination {
    @Test
    void test(){
        ShortestPathinaGridwithObstaclesElimination elimination = new ShortestPathinaGridwithObstaclesElimination();

        Assertions.assertEquals(6, elimination.shortestPath(new int[][]{{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}},1));
        Assertions.assertEquals(10, elimination.shortestPath(new int[][]{{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}},0));
        Assertions.assertEquals(-1, elimination.shortestPath(new int[][]{{0,1,1},{1,1,1},{0,0,0},{0,1,1},{0,0,0}},0));
    }
    final int[] ud = new int[]{-1,0,1,0};
    final int[] lr = new int[]{0,1,0,-1};

    int getHashing(int row, int col, int k){
        return (((row+1)*100)+(col+1))*10000 + k;
    }
    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;
        int[] target = {rows - 1, cols - 1};

        if (k >= rows + cols - 2) {
            return rows + cols - 2;
        }

        Deque<int[]> queue = new LinkedList<>();
        HashSet<Integer> seen = new HashSet<>();

        // (steps, row, col, remaining quota to eliminate obstacles)
        queue.addLast(new int[]{0,0,0,k});
        seen.add(getHashing(0,0,k));

        while (!queue.isEmpty()) {
            int[] curr = queue.pollFirst();


            // we reach the target here
            if (target[0] == curr[1] && target[1] == curr[2]) {
                return curr[0];
            }

            int[] nextSteps = {curr[1], curr[2] + 1, curr[1] + 1, curr[2], curr[1], curr[2] - 1, curr[1] - 1, curr[2]};

            // explore the four directions in the next step
            for (int i = 0; i < nextSteps.length; i += 2) {
                int nextRow = nextSteps[i];
                int nextCol = nextSteps[i + 1];

                // out of the boundary of grid
                if (0 > nextRow || nextRow == rows || 0 > nextCol || nextCol == cols) {
                    continue;
                }

                int nextElimination = curr[3] - grid[nextRow][nextCol];
                int[] newState = new int[]{curr[0] + 1, nextRow, nextCol, nextElimination};

                int hash = getHashing(nextRow,nextCol,nextElimination);

                // add the next move in the queue if it qualifies.
                if (nextElimination >= 0 && !seen.contains(hash)) {
                    System.out.println(Arrays.toString(newState));
                    System.out.println(hash);

                    seen.add(getHashing(nextRow,nextCol,nextElimination));
                    queue.addLast(newState);
                }
            }
        }

        // did not reach the target
        return -1;
    }

    //LTE
    public int shortestPathLTE(int[][] grid, int k) {
        //부술수 없다면 bfs를 사용해서 처리하면 된다.

        //brute force로는 1을 k개 만큼 0으로 permutation 처리 하고 bfs 확인하면 된다.
        //어떤 1을 0으로 만들면 될까?
        // greedy 접근이 가능할까?

        //status = k,step,visited
        int[][][] visited = new int[grid.length][grid[0].length][3];

        LinkedList<int[]> queue = new LinkedList<>();

        //row,col,k,step
        queue.add(new int[]{0,0,k,0});

        int steps = 0;
        while(0 < queue.size()){
            for (int i = queue.size(); 0 < i; i--) {
                int[] cur = queue.pollFirst();
//                System.out.println(Arrays.toString(cur));
                int row = cur[0];
                int col = cur[1];
                if(row == grid.length-1 && col == grid[0].length-1) return steps;

                for(int j = 0; j < 4; j++){
                    int tRow = row + ud[j];
                    int tCol = col + lr[j];
                    if(tRow < 0 || grid.length <= tRow || tCol < 0 || grid[0].length <= tCol) continue;
                    int[] status = visited[tRow][tCol];
                    if(status[2] == 1 && status[0] == cur[2]) continue;
                    if(cur[2] - grid[tRow][tCol] < 0) continue;

                    queue.add(new int[]{tRow,tCol,cur[2]-grid[tRow][tCol],steps+1});
                    visited[row][col] = new int[]{cur[2],steps,1};
                }
            }

            steps++;
        }

        return -1;
    }

}

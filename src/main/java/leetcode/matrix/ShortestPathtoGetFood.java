package leetcode.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/**
 * 1730. Shortest Path to Get Food
 * https://leetcode.com/problems/shortest-path-to-get-food/
 */
public class ShortestPathtoGetFood {
    @Test
    void test(){
        ShortestPathtoGetFood food = new ShortestPathtoGetFood();
        Assertions.assertEquals(3, food.getFood(new char[][]{{'X','X','X','X','X','X'},{'X','*','O','O','O','X'},{'X','O','O','#','O','X'},{'X','X','X','X','X','X'}}));
        Assertions.assertEquals(-1, food.getFood(new char[][]{{'X','X','X','X','X','X'},{'X','*','X','O','O','X'},{'X','O','X','#','O','X'},{'X','X','X','X','X','X'}}));
    }

    final int[] ud = new int[]{-1,0,1,0};
    final int[] lr = new int[]{0,1,0,-1};

    public int getFood(char[][] grid) {
        int[][] visited = new int[grid.length][grid[0].length];
        LinkedList<int[]> queue = new LinkedList<>();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] == '*'){
                    queue.add(new int[]{row, col});
                }
                if(grid[row][col] == 'X'){
                    visited[row][col] = 1;
                }
            }
        }
        int steps = 0;
        while(0 < queue.size()){
            for(int i = queue.size(); 0 < i; i--){
                int[] cur = queue.pollFirst();
                int row = cur[0];
                int col = cur[1];
                if(grid[row][col] == '#') return steps;

                if(0 < visited[row][col]) continue;
                visited[row][col] = 1;

                for (int j = 0; j < 4; j++) {
                    int tRow = row + ud[j];
                    int tCol = col + lr[j];
                    if (tRow < 0 || grid.length <= tRow) continue;
                    if (tCol < 0 || grid[0].length <= tCol) continue;
                    if (0 < visited[tRow][tCol]) continue;
                    queue.add(new int[]{tRow,tCol});
                }
            }
            steps++;
        }

        return -1;
    }
}

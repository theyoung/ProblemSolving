package leetcode.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 1706. Where Will the Ball Fall
 * https://leetcode.com/problems/where-will-the-ball-fall/
 */
//2029
//2107
public class WhereWilltheBallFall {
    @Test
    void test(){
        WhereWilltheBallFall fall = new WhereWilltheBallFall();
//        Assertions.assertArrayEquals(new int[]{1,-1,-1,-1,-1}, fall.findBall(new int[][]{{1,1,1,-1,-1},{1,1,1,-1,-1},{-1,-1,-1,1,1},{1,1,1,1,-1},{-1,-1,-1,-1,-1}}));
//        Assertions.assertArrayEquals(new int[]{-1}, fall.findBall(new int[][]{{-1}}));
        Assertions.assertArrayEquals(new int[]{0,1,2,3,4,-1}, fall.findBall(new int[][]{{1,1,1,1,1,1},{-1,-1,-1,-1,-1,-1},{1,1,1,1,1,1},{-1,-1,-1,-1,-1,-1}}));

    }
    public int[] findBall(int[][] grid) {
        // 1이면 \ <- 요모양
        // -1이면 / <- 요모양이다.

        // 몇가지 케이스를 나눠 보자
        // 이웃하는 두 cell이 1과 -1이면 ball은 -1이된다
        // 내 cell이 1이면 오른쪽으로 가야한다
        // 내 cell이 -1이면 왼쪽으로 가야한다
        // 다음 row로 내려가기 위해서는 2개의 cell을 거쳐가야 한다.
        // 1과 1이거나
        // -1과 -1의 col을 지나야만 한다.
        // 0번이나 length index의 경우 더 갈 수 없다면 -1이다.
        // 1과 1이면 right +1, down +1이고 다음 row에서는 다음 row의 진행 방향을 따라간다.
        //마지막 cell도 마찬가지다

        int[][] moves = new int[grid.length][grid[0].length];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if(grid[row][col] == 1){// '\'
                    if(col+1 < grid[0].length && grid[row][col+1] == 1) moves[row][col] = col+1;
                    else moves[row][col] = -1;
                } else {// '/'
                    if(0 <= col-1 && grid[row][col-1] == -1) moves[row][col] = col-1;
                    else moves[row][col] = -1;
                }
            }
        }
        int[] result = new int[grid[0].length];

        for(int ball = 0; ball < grid[0].length; ball++){
            int location = ball;
            for (int row = 0; row < grid.length; row++) {
                location = moves[row][location];
                if(location < 0){
                    break;
                }
            }
            result[ball] = location;
        }

        return result;
    }
}

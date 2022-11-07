package leetcode.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 2087. Minimum Cost Homecoming of a Robot in a Grid
 * https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/
 */
//2240
//2331
//TODO 문제을 잘 읽자 결국은 최소 거리가 답이다.
public class MinimumCostHomecomingofaRobotinaGrid {

    @Test
    void test(){
        MinimumCostHomecomingofaRobotinaGrid robot = new MinimumCostHomecomingofaRobotinaGrid();
        Assertions.assertEquals(18, robot.minCost(new int[]{1, 0}, new int[]{2, 3}, new int[]{5, 4, 3}, new int[]{8, 2, 6, 7}));
    }

    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        // 0 0 0 0 0 0
        // 0 0 0 0 h 0
        // 0 r 0 0 0 0
        // 0 0 0 0 0 0

        // col이 오른쪽인 경우
        // 0 1 3 5 4 2 1 2
        // 0 0 0 0 0 0 0 0
        // 1 0 0 0 0 0 0 0
        // 2 0 0 0 0 h 0 0
        // 1 0 r 0 0 0 0 0
        // 3 0 0 0 0 0 0 0
        // cost가 어떻게 되든 방법없다. 최소 distance가 답이다.


        // 0 5 5 6 2 0 16
        // 7 0 0 0 0 0 0
        // 1 0 0 0 0 0 0
        // 3 0 0 0 0 0 0
        // 3 0 0 0 0 0 0
        // 5 0 0 0 0 0 0
        // 3 0 0 e 0 0 s
        //22 0 0 0 0 0 0
        //10 0 0 0 0 0 0
        //23 0 0 0 0 0 0

        int cost = 0;
        if(startPos[0] < homePos[0]){

            for(int i = startPos[0]+1 ; i <= homePos[0]; i++){
                cost += rowCosts[i];
            }
        } else if(homePos[0] < startPos[0]){
            for(int i = startPos[0]-1 ; homePos[0] <= i; i--){
                cost += rowCosts[i];
            }
        }

        if(startPos[1] < homePos[1]){
            for(int i = startPos[1]+1 ; i <= homePos[1]; i++){
                cost += colCosts[i];
            }
        } else if(homePos[1] < startPos[1]){
            for(int i = startPos[1]-1 ; homePos[1] <= i; i--){
                cost += colCosts[i];
            }
        }
        return cost;

    }

}

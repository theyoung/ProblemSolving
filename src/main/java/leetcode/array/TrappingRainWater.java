package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 42. Trapping Rain Water
 * https://leetcode.com/problems/trapping-rain-water/?envType=study-plan&id=level-3
 */
public class TrappingRainWater {
    @Test
    public void test(){

        Assertions.assertEquals(6, this.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        Assertions.assertEquals(1, this.trap(new int[]{2,1,2}));
    }

    public int trap(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        leftMax[0] = height[0];

        for (int i = 1; i < height.length; i++) {
            leftMax[i] = Math.max(leftMax[i-1],height[i]);
        }
        rightMax[height.length - 1] = height[height.length - 1];

        for(int i = height.length-2; 0 <= i; i--){
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int result = 0;
        for (int i = 1; i < height.length - 1; i++) {
//            System.out.println(leftMax[i-1] + " " +rightMax[i+1]);
            int drop = Math.min(leftMax[i-1],rightMax[i+1]) - height[i];
            if(0 < drop) result += drop;
        }

        return result;
    }
}

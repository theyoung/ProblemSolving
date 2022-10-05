package leetcode.array;

import leetcode.graph.MinCosttoConnectAllPoints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2134. Minimum Swaps to Group All 1's Together II
 * https://leetcode.com/problems/minimum-swaps-to-group-all-1s-together-ii/
 */
public class MinimumSwapstoGroupAll1sTogetherII {

    //TODO 풀긴 했는데 재미있는 문제 나중에 시간나면 풀어볼것
    @Test
    void test(){
        MinimumSwapstoGroupAll1sTogetherII swap = new MinimumSwapstoGroupAll1sTogetherII();
        Assertions.assertEquals(0, swap.minSwaps(new int[]{1}));
        Assertions.assertEquals(0, swap.minSwaps(new int[]{0}));
        Assertions.assertEquals(0, swap.minSwaps(new int[]{1,1,1,0,0}));
        Assertions.assertEquals(1, swap.minSwaps(new int[]{0,1,1,0,1}));
        Assertions.assertEquals(1, swap.minSwaps(new int[]{0,1,1,0,0,0,1}));
        Assertions.assertEquals(2, swap.minSwaps(new int[]{1,0,0,0,1,1,0,0,0,1}));
        Assertions.assertEquals(2, swap.minSwaps(new int[]{1,1,1,0,0,0,1,1,0,0,0,1}));//그룹을 나누었을때 작은 그룹이 옮겨 가면 된다.
        Assertions.assertEquals(3, swap.minSwaps(new int[]{1,1,1,0,0,0,1,1,1,1,1,0,0,0,1,0}));
        Assertions.assertEquals(1, swap.minSwaps(new int[]{1,1,1,0,0,1,0,1,1,0}));
    }

    public int minSwaps(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if(num == 1) count++;
        }

        if(count == nums.length || count == 0) return 0;

        int min = nums.length;
        int zeros = 0;
        for (int i = 0; i < nums.length+count; i++) {
            int val = nums[i % nums.length];

            if(i < count){
                if(val == 0) zeros++;
            } else {
                if(nums[i-count]==0) zeros--;
                if(val == 0) zeros++;
            }

            if(count-1 <= i) {
                min = Math.min(zeros, min);
            }
        }

        return min;
    }
}

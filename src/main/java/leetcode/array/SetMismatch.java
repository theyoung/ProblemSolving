package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 645. Set Mismatch
 * https://leetcode.com/problems/set-mismatch/
 */
//1006
//1038
//TODO 쉬운 문제인데 많이 고민했다. edge case를 고민하고 프로그램에 쉽게 반영해야 한다.
public class SetMismatch {

    @Test
    void test(){
        SetMismatch mismatch = new SetMismatch();

        Assertions.assertArrayEquals(new int[]{2,3}, mismatch.findErrorNums(new int[]{1,2,2,4}));
        Assertions.assertArrayEquals(new int[]{1,2}, mismatch.findErrorNums(new int[]{1,1}));
        Assertions.assertArrayEquals(new int[]{3,6}, mismatch.findErrorNums(new int[]{1,2,3,3,4,5,7}));
        Assertions.assertArrayEquals(new int[]{3,2}, mismatch.findErrorNums(new int[]{1,3,3,4,5,6,7}));
        Assertions.assertArrayEquals(new int[]{2,1}, mismatch.findErrorNums(new int[]{2,2}));
    }

    public int[] findErrorNums(int[] nums) {
        Arrays.sort(nums);
        int[] result = new int[2];

        for (int i = 0; i < nums.length-1; i++) {
            if(nums[i] == nums[i+1]){
                result[0] = nums[i];
            }
            if(1 < nums[i+1] - nums[i]){
                result[1] = nums[i]+1;
            }
        }

        if(result[1] == 0){
            if(nums[0] == 2){
                result[1] = 1;
            } else {
                result[1] = nums[nums.length-1] + 1;
            }
        }

        return result;
    }
}

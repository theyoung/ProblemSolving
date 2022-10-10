package leetcode.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 611. Valid Triangle Number
 * https://leetcode.com/problems/valid-triangle-number/
 *
 */
public class ValidTriangleNumber {
    @Test
    void test() {
        ValidTriangleNumber triangleNumber = new ValidTriangleNumber();

        System.out.println(triangleNumber.triangleNumber(new int[]{2,3,3,4}));
    }
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int result = 0;

        for(int i = nums.length-1; 1 < i; i--){
            result += getCount(nums, i-1, nums[i]);
        }

        return result;
    }

    int getCount(int[] nums,int r, int small){
        int l = 0;
        int result = 0;

        for (int i = 0; i <= r-1; i++) {
            for(int j = i+1; j <= r; j++){
                if(small < nums[i] + nums[j]) result++;
            }
        }

//        System.out.println(result);
        return result;
    }
}

package leetcode.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ThreeSumSmaller {

    /**
     * 259. 3Sum Smaller
     * https://leetcode.com/problems/3sum-smaller/
     */

    @Test
    void test(){
        ThreeSumSmaller smaller = new ThreeSumSmaller();
        smaller.threeSumSmaller(new int[]{1, 1, -2}, 1);
    }

    public int threeSumSmaller(int[] nums, int target) {
        if(nums.length < 3) return 0;

        int result = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            result += getSmallerCount(i+1,nums,target-nums[i]);
        }

        return result;
    }

    int getSmallerCount(int l,int[] nums, int target){

        int r = nums.length - 1;

        int count = 0;
        while (l < r) {
            if(target <= nums[l] + nums[r]){
                r--;
            } else {
                count += r - l;
                l++;
            }
        }

        return count;
    }
}

package leetcode.array;

import leetcode.graph.MinCosttoConnectAllPoints;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**'
 * 665. Non-decreasing Array
 * https://leetcode.com/problems/non-decreasing-array/
 */
//TODO 이 문제 다시 풀어야함.(2차)
public class NondecreasingArray {
    @Test
    void test() {
        NondecreasingArray nondecreasingArray = new NondecreasingArray();
        Assertions.assertTrue(nondecreasingArray.checkPossibility(new int[]{1,2,3}));
        Assertions.assertTrue(nondecreasingArray.checkPossibility(new int[]{1,1,1}));
        Assertions.assertTrue(nondecreasingArray.checkPossibility(new int[]{1,0,1}));
        Assertions.assertTrue(nondecreasingArray.checkPossibility(new int[]{1,0,2}));
        Assertions.assertTrue(nondecreasingArray.checkPossibility(new int[]{1,1,-1}));
        Assertions.assertTrue(nondecreasingArray.checkPossibility(new int[]{4,2,3}));
        Assertions.assertFalse(nondecreasingArray.checkPossibility(new int[]{1,0,-1}));
        Assertions.assertFalse(nondecreasingArray.checkPossibility(new int[]{4,2,1}));
        Assertions.assertTrue(nondecreasingArray.checkPossibility(new int[]{1,2,3,100,3,4}));
        Assertions.assertTrue(nondecreasingArray.checkPossibility(new int[]{1,2,3,100,3,3}));
        Assertions.assertFalse(nondecreasingArray.checkPossibility(new int[]{1,2,3,100,3,2}));
        Assertions.assertFalse(nondecreasingArray.checkPossibility(new int[]{1,2,3,100,3,3,2}));
        Assertions.assertTrue(nondecreasingArray.checkPossibility(new int[]{1,2,3,100,4,200,300}));
        Assertions.assertTrue(nondecreasingArray.checkPossibility(new int[]{1,2,3,5,4,90,91}));
        Assertions.assertFalse(nondecreasingArray.checkPossibility(new int[]{3,4,2,3}));
        Assertions.assertTrue(nondecreasingArray.checkPossibility(new int[]{5,7,1,8}));

    }

    //최적화 후
    public boolean checkPossibility(int[] nums) {
        int count = 0;

        for (int i = 1; i < nums.length; i++) {
            if(nums[i-1] > nums[i]){
                if(i == 1 || nums[i-2] <= nums[i]){
                    nums[i-1] = nums[i];
                } else {
                    nums[i] = nums[i-1];
                }
                count++;
            }
        }

        return count < 2;
    }

    //최적화 전
    public boolean checkPossibility1(int[] nums) {
        int count = 0;

        for (int i = 1; i < nums.length; i++) {
            if(nums[i-1] > nums[i]){
                if(i == 1 || nums.length <= i+1){
                    nums[i-1] = nums[i];
                } else if(nums[i-2] <= nums[i]){
                    nums[i-1] = nums[i-2];
                } else {
                    nums[i] = nums[i-1];
                }
                count++;
            }
        }

        return count < 2;
    }
}

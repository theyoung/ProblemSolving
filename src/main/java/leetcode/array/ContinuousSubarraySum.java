package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 523. Continuous Subarray Sum
 * https://leetcode.com/problems/continuous-subarray-sum/
 */
//2223
//0012
//TODO PathSum3과 같이 HashMap prefix SUM을 사용한다. 다만 Sum에 %를 이용하는 방식이 다르다. 이해하기 어려웠다. 이 방법으로 다시 풀어보자.
public class ContinuousSubarraySum {

    @Test
    void test(){
        ContinuousSubarraySum sum = new ContinuousSubarraySum();
        Assertions.assertEquals(true, sum.checkSubarraySum(new int[]{23,2,4,6,7}, 6));//42
        Assertions.assertEquals(true, sum.checkSubarraySum(new int[]{23,2,6,4,7}, 6));
        Assertions.assertEquals(true, sum.checkSubarraySum(new int[]{5,0,0,0}, 3));
        Assertions.assertEquals(false, sum.checkSubarraySum(new int[]{23,2,6,4,7}, 13));//55 39~52
        Assertions.assertEquals(true, sum.checkSubarraySum(new int[]{5,0,0,0}, 3));//55 39~52
        Assertions.assertEquals(false, sum.checkSubarraySum(new int[]{0}, 1));//55 39~52
        Assertions.assertEquals(false, sum.checkSubarraySum(new int[]{23,2,6,2,5}, 6));//55 39~52
        Assertions.assertEquals(true, sum.checkSubarraySum(new int[]{1,2,3}, 5));//55 39~52
        Assertions.assertEquals(true, sum.checkSubarraySum(new int[]{1,3,6,0,9,6,9}, 7));//1,4,3,3,5,4
        Assertions.assertEquals(true, sum.checkSubarraySum(new int[]{1,3,0,6}, 6));//1,4,4,10%6=4
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        // hashmap(cur - target) -> prefix sum - target 이 앞서 hashmap에 있다면 = 나머지값 + target = 모든 합 - 나머지값 = target
        // ++++++|++++++++++
        // remain|--target-|

        int sum = 0;
        Set<Integer> set = new HashSet<>();

        sum += nums[0];
        set.add(sum);
        for(int i = 1; i < nums.length; i++){
            if(nums[i-1] == 0 && nums[i] == 0) return true;

            int num = nums[i];
            sum += num;

            if(sum%k == 0) return true; //지금까지의 모든 sum이 k의 배수임

            if(num%k == 0){
                if(nums[i-1] == 0) return true;
                set.add(sum);
                continue;
            }
            for(int j = 1; j*k < sum; j++){
                int target = k * j;
                if(set.contains(sum - target)) {
                    return true;
                }
            }
            set.add(sum);
        }
        return false;
    }


    public boolean checkSubarraySumBruteForce(int[] nums, int k) {
        //23,2,4,6,7
        for(int i = 0; i < nums.length-1; i++){
            int sum = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if(sum%k == 0) return true;
            }
        }

        return false;
    }
}

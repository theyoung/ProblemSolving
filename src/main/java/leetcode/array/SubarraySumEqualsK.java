package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * 560. Subarray Sum Equals K
 * https://leetcode.com/problems/subarray-sum-equals-k/
 */

//TODO 연속된 Array에서 특정 target의 sum을 찾는 방법인데 O(N)이다. 암기하자
public class SubarraySumEqualsK {

    @Test
    void test(){
        SubarraySumEqualsK sum = new SubarraySumEqualsK();

        Assertions.assertEquals(2, sum.subarraySum(new int[]{1,1,1}, 2));
        Assertions.assertEquals(2, sum.subarraySum(new int[]{1,2,1}, 3));
        Assertions.assertEquals(1, sum.subarraySum(new int[]{1,2,2}, 4));
    }

    public int subarraySum(int[] nums, int k) {
        HashMap<Long, Integer> map = new HashMap<>();
        long cur = 0;
        int result = 0;

        for (int num : nums) {
            cur += num;

            //처음부터 여기까지의 sum이 k와 같은 경우
            if(cur == k) result++;

            //첫 인덱스가 아닌곳에서 시작하고 sum이 k와 같은경우
            //지금 까지의 sum에서 target을 뺀값이 이전에 존재 한다면, 이전에 존재한 값 다음의 array부터 현재까지의 sum이 target이 된다.
            // [1,2,3,-2,3]
            // 에서 3이 target이라고 하면
            // prefix sum으로 1,3 에서 result+1
            // 1,3,6에서 6-target = 3이고 3은 index1에서 나왔음으로 index 2~2는 3이 되고 result+1
            // 1,3,6,4에서 4-3은 1이고 index0에서 1이 나왔음으로 index2~3은 3이 되고 result+1
            // 1,3,6,4,7에서 7-3은 4이고 index3에서 4가 나왔음으로 index5~5는 3이 되고 result +1
            result += map.getOrDefault(cur - k, 0);

            map.put(cur, map.getOrDefault(cur,0) + 1);
        }
        return result;
    }
}

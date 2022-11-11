package leetcode.array;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. Sliding Window Maximum
 * https://leetcode.com/problems/sliding-window-maximum/
 */
//TODO Sliding Window, DP로도 풀수 있다.
public class SlidingWindowMaximum {

    @Test
    void test(){
        int[] result = this.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7}, 3);
        System.out.println(Arrays.toString(result));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length-k+1];
        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            int abandonIndex = i - k;
            if(!list.isEmpty() && 0 <= abandonIndex && list.getFirst() == abandonIndex) {
                list.pollFirst();
            }
            while(!list.isEmpty() && nums[list.getLast()] < nums[i]) list.pollLast();
            list.addLast(i);
            if(k <= (i+1)){
                result[i-k+1] = nums[list.getFirst()];
            }
        }

        return result;
    }

}

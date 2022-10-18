package leetcode.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 1526. Minimum Number of Increments on Subarrays to Form a Target Array
 * https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/
 */

//TODO Monotonic stack을 공부하기 좋다.
public class MinimumNumberofIncrementsonSubarrays {

    @Test
    void test() {
        MinimumNumberofIncrementsonSubarrays subarrays = new MinimumNumberofIncrementsonSubarrays();

        Assertions.assertEquals(1, subarrays.minNumberOperations(new int[]{1}));
        Assertions.assertEquals(0, subarrays.minNumberOperations(new int[]{0}));
        Assertions.assertEquals(3, subarrays.minNumberOperations(new int[]{1,2,3,2,1}));
        Assertions.assertEquals(4, subarrays.minNumberOperations(new int[]{3,1,1,2}));
        Assertions.assertEquals(7, subarrays.minNumberOperations(new int[]{3,1,5,4,2}));
        Assertions.assertEquals(10, subarrays.minNumberOperations(new int[]{1,2,3,4,5,4,3,5,8,2}));
    }

    public int minNumberOperations(int[] target) {
        // monotonic stack을 사용한다.
        // 0으로 시작하고 max를 찾으면 max - min, 하강은 다 버리고 다시 상승을 시작하면 max - min을 작업해 주면 된다.

        //
        //              -
        //          -      -
        //       -          -
        //    -
        ////////////////////////  -> max - min (그 사이 몇개의 점이 있던지 의미가 없음) [1,2,3,4,5,4,3,2]


        //                                 -
        //              -            -
        //          -           -               -
        //       -          -
        //    -
        ////////////////////////  -> [1,2,3,4,5,4,5,2] -> 4,5 만 의미가 있음 + 1

        //                                 -
        //              -            -
        //          -           -               -
        //       -          -
        //    -
        ////////////////////////  -> [1,2,3,4,5,4,3,5,8,2] -> arr시작은 무조건 0을 넣어준다. 0,1,2,3,4,5 -> 5-0 = 5, 내리막은 다 삭제 한다. 다시 상승 시작시 4,5,8 = 8-4 = 4 = 9

        //[0,3,1,5,4,2]
        int min = 0;
        int last = target[0];
        int result = 0;
        for (int num : target) {
            if(last <= num){
                last = num;
            } else { // 낮을 때
                if(min < last){
                    result += last - min;
                }
                min = num;
                last = num;
            }
        }
        if(min < last){
            result += last - min;
        }

        return result;
    }


}

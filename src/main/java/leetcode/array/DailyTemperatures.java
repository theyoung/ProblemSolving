package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/*
739. Daily Temperatures
https://leetcode.com/problems/daily-temperatures/?envType=study-plan&id=level-3
 */
//1735
//1750
public class DailyTemperatures {
    @Test
    void test(){
        Assertions.assertArrayEquals(new int[]{1,1,4,2,1,1,0,0}, this.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73}));
        Assertions.assertArrayEquals(new int[]{1,1,1,0}, this.dailyTemperatures(new int[]{30,40,50,60}));
    }
    public int[] dailyTemperatures(int[] temperatures) {
        //brute force로 하면
        // for i -> length-1 결과의 마지막은 항상 0이 된다.
        //   for j -> length
        //      if(i<j) break; <- 언제인지 알았음으로 break 처리
        // 더 좋은 방법을 찾아 보자.

        // 73,74,75,71,69,72,76,73
        // 높은 수를 찾게 되면 바로 답이 된다.
        // stack과 index를 이용하면 된다.

        LinkedList<Integer> stack = new LinkedList<>();
        int[] result = new int[temperatures.length]; //마지막은 반듯이 0이다.

        for (int i = 0; i < temperatures.length; i++) {
            int last = temperatures[i];
            while(0 < stack.size() && temperatures[stack.peekLast()] < last){
                int lastIndex = stack.pollLast();
                result[lastIndex] = i - lastIndex;
            }
            stack.addLast(i);
        }

        return result;
    }
}

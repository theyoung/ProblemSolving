package leetcode.greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 1578. Minimum Time to Make Rope Colorful
 * https://leetcode.com/problems/minimum-time-to-make-rope-colorful/
 */
//1238
//1253
public class MinimumTimetoMakeRopeColorful {

    @Test
    void test(){
        MinimumTimetoMakeRopeColorful rope = new MinimumTimetoMakeRopeColorful();
        Assertions.assertEquals(2,rope.minCost("aabaa",new int[]{1,2,3,4,1}));
        Assertions.assertEquals(0,rope.minCost("abcdef",new int[]{1,2,3,4,1}));
        Assertions.assertEquals(7,rope.minCost("aaaaa",new int[]{1,2,3,4,1}));
        Assertions.assertEquals(14,rope.minCost("aaaaabbbbb",new int[]{1,2,3,4,1,1,2,3,4,1}));
        Assertions.assertEquals(0,rope.minCost("abc",new int[]{1,2,3,4,1,1,2,3,4,1}));
    }

    //left~right는 동일 색의 풍선을 찾는다.
    //주어진 범위 내에서 가장 Time 높은 값을 제외하고 모두 삭제 하면 된다.
    public int minCost(String colors, int[] neededTime) {
        int result = 0;

        for (int i = 0; i < colors.length()-1; i++) {
            if(colors.charAt(i) == colors.charAt(i+1)){ //동일 컬러가 연속 되는 경우
                int left = i;
                int right = i;
                while(right < colors.length() && colors.charAt(left) == colors.charAt(right)) right++;

                int sum = 0;
                int max = neededTime[left];
                for (; left < right; left++) {
                    sum += neededTime[left];
                    max = Math.max(max, neededTime[left]);
                }

                result += sum-max;
                i = right-1;
            }
        }
        return result;
    }

}

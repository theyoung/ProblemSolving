package leetcode.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

/*
84. Largest Rectangle in Histogram
https://leetcode.com/problems/largest-rectangle-in-histogram/?envType=study-plan&id=level-3
 */
//1752
//1831
//TODO stack을 이용한 방법으로 앞의 범위를 저장시키는게 조금 까다로웠다.
public class LargestRectangleinHistogram {
    @Test
    void test(){
        Assertions.assertEquals(10, this.largestRectangleArea(new int[]{2,1,5,6,2,3}));
        Assertions.assertEquals(3, this.largestRectangleArea(new int[]{2,1,2}));
        Assertions.assertEquals(3, this.largestRectangleArea(new int[]{2,1,2}));
        Assertions.assertEquals(20, this.largestRectangleArea(new int[]{3,6,5,7,4,8,1,0}));
    }
    public int largestRectangleArea(int[] heights) {
        //brute force로는
        //for i ~ length 까지 loop하면서
        // 좌측 우측으로 자신과 같거나 큰 값이 있을 경우 자신의 size를 더해가면 된다.
        // 최악의 경우 O(N^2)이 된다.

        // stack을 사용해 보자.
        // 2,1,5,6,2,3
        // 좌측 사이즈를 확인하기 어렵다.
        // 2 : 0,0,0,0,0,0
        // 2,1 : 2,1,0,0,0,0 -> 2는 2와 1의 거리, 작은 값은 pop시킨 횟수 * 자기 자신의 값
        // 1,5 : 2,1,0,0,0,0
        // 1,5,6,2 : 2,1,0,0,0,0
        // 1,5,6,2 : 2,1,10,6,4,0 -> 2는 pop을 2번 시켰음으로 4
        // 1,2,3 : 2,1,10,6,4,0 -> 더이상 갈곳이 없음으로
        // 1,3 : 2,6,10,6,8,0 -> 2는 2와 3의 거리 2*2, 1과 3의 거리 5
        // 3 : 2,6,10,6,8,3 -> 마지막 3은 자기 자신

        // 3,6,5,7,4,8,1,0 : 0,0,0,0,0,0,0,0
        // 3,6,5 : 0,6,5,0,0,0,0,0
        // 3,4 : 0,6,5,0,0,0,0,0

        LinkedList<Integer> stack = new LinkedList<>();
        int[] result = new int[heights.length];

        for (int i = 0; i < heights.length; i++) {
            while(!stack.isEmpty() && heights[i] < heights[stack.peekLast()]){
                int pollIndex = stack.pollLast();
                result[pollIndex] += heights[pollIndex] * (i-pollIndex);
            }
            int lastIndex = stack.isEmpty()? -1 : stack.peekLast();
            result[i] += (i - (lastIndex + 1)) * heights[i];
            stack.add(i);
        }
        while(!stack.isEmpty()){
            int pollIndex = stack.pollFirst();
            if(stack.size() == 0){
                result[pollIndex] += heights[pollIndex];
            } else {
                result[pollIndex] += heights[pollIndex] * (stack.peekLast() - pollIndex + 1);
            }
        }
//        System.out.println(Arrays.toString(result));
        int max = result[0];
        for (int i = 1; i < result.length; i++) {
            max = Math.max(max, result[i]);
        }

        return max;
    }
}

package leetcode.greedy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 2136. Earliest Possible Day of Full Bloom
 * https://leetcode.com/problems/earliest-possible-day-of-full-bloom/
 */
public class EarliestPossibleDayofFullBloom {
    //2337
    //2346
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        // plantTime과 groTime으로 2D 만들어 준다.
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->b[1] - a[1]);

        for(int i = 0; i < plantTime.length; i++){
            pq.add(new int[]{plantTime[i],growTime[i]});
        }

        // growTime이 가장 긴것 부터 plant를 시켜준다.
        int plantDay = 0;
        int maxDay = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            plantDay += cur[0];
            maxDay = Math.max(maxDay, plantDay+cur[1]);
        }


        // 모든 planttime을 더하고, 남은 가장 긴 growtime을 더해준다.
        // -> max day를 계산해서 return 하자.
        return maxDay;
    }

    //growTime이후에 꽃을 발화시킴으로 growTime +1이 발화를 위한 시간이 된다.
    //다만 day의 시작이 0이라는 것을 주의하자.
    public int earliestFullBloomPre(int[] plantTime, int[] growTime) {

        //하누에 하나의 planting만 가능하다.
        //planting을 시작하면 plantTime동안은 아무것도 할 수 없다.
        //주어진 모든 씨앗이 꽃을 피우는데 걸리는 최소 시간은 얼마인가?
        // 한번 사용된 씨앗은 불필요 하다.

        //모든 경우의 수로 계산을 할 수 있다.
        // 첫번째 씨앗을 선택하고 안하고
        // 두번째 씨앗을 선택하고 안하고 O(2^n)이 된다.
        // bitoperation으로 masking하고 dp처리 가능하다.
        //라고 생각했는데 plantTime을 임의로 쪼갤수 있다.

        //seed의 갯수는 10^5이다. permutation 처리는 불가하다.

        //Greedy 공식을 찾아 내야 한다.
        // 특징을 찾아보자.
        // plantTime은 하루에 하나의 seed만 점유가 가능하다.
        // growTime은 동시 점유가 가능하다.

        // plantTime이 짧은 순으로 seed를 점유 시키면 될것 같다.
        // [1,2,3,2] [2,1,2,1]
        // 1+2일 2+1일 2+1일 3+2일
        // 2일, 1+2+1=4일, 1+2+2+1=6일, 1+2+2+3+2=10일

        //긴순으로?
        // 3+2, 3+2+1, 3+2+2+1, 3+2+2+1+2 (9일)

        //두개의 합으로
        // [1,2,3,2] [2,1,2,1] = [3,3,5,3]
        // 길고 긴순
        // 3+2,3+2+1,3+2+2+1,3+2+2+1+2(9일)

        // [1,4,3] [2,3,1]
        // 1+2,1+3+1,1+3+4+3 => 짧은 순은 안됨
        // -> 4+3,4+3+1,4+3+1+2 => plant긴순

        // [x,x,x] [1,2,3] plant가 동일 하고 grow가 다르다면
        //  -> x+3(5), x+x+2(6), x+x+x+1(7) grow 긴순
        // x+1(3), x+x+2(6), x+x+x+3(9) grow 짧은 순은 성립하지 않음

        // sort(plant긴순
        //          if plant 값이 동일한 경우
        //             then grow 긴순

        // 최종적으로 grow 큰순으로 처리하면 됨.
        List<int[]> plants = new ArrayList<>();
        for(int i = 0; i < plantTime.length; i++){
            plants.add(new int[]{plantTime[i],growTime[i]});
        }

        plants.sort((a,b)->{
                return b[1] - a[1];// grow 큰걸 우선 처리한다.
            }
        );

        int plantSum = 0;
        int maxTime = 0;

        for (int i = 0; i < plants.size(); i++) {
            int[] cur = plants.get(i);
            plantSum += cur[0];
            maxTime = Math.max(maxTime, plantSum+cur[1]);
        }

        return maxTime;
    }

    @Test
    void test(){
        EarliestPossibleDayofFullBloom early = new EarliestPossibleDayofFullBloom();
        Assertions.assertEquals(9, early.earliestFullBloom(new int[]{1,2,3,2}, new int[]{2,1,2,1}));
        Assertions.assertEquals(9, early.earliestFullBloom(new int[]{1,4,3}, new int[]{2,3,1}));
        Assertions.assertEquals(2, early.earliestFullBloom(new int[]{1}, new int[]{1}));
        Assertions.assertEquals(9, early.earliestFullBloom(new int[]{2,2,2,2}, new int[]{1,2,3,4}));

    }
}

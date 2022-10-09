package leetcode.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 732. My Calendar III
 * https://leetcode.com/problems/my-calendar-iii/
 */

//TODO 캘린더 2와 함께 다시 풀어 보는게 좋을 것 같다. 문제 이해가 어려웠는데, 특정 값을 넣고 전체 값의 max를 찾는 질문이었다.
public class MyCalendarIII {
    TreeMap<Integer, Integer> map;

    @Test
    void test(){
        MyCalendarIII calendarIII = new MyCalendarIII();
        System.out.println(calendarIII.book(10,20));
        System.out.println(calendarIII.book(50,60));
        System.out.println(calendarIII.book(10,40));
        System.out.println(calendarIII.book(5,15));
        System.out.println(calendarIII.book(5,10));
        System.out.println(calendarIII.book(25,55));
    }

    public MyCalendarIII() {
        map = new TreeMap<>();
    }

    public int book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);

        int active = 0;
        int max = 0;

        for (Map.Entry<Integer,Integer> set : map.entrySet()) {
            int key = set.getKey();
            int val = set.getValue();

//            if (start <= key && key < end) {
                active += val;
                max = Math.max(max, active);
//            }
        }

        return max;
    }

}

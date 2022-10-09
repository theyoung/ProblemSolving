package leetcode.tree;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

/**
 * 731. My Calendar II
 * https://leetcode.com/problems/my-calendar-ii/
 */

//TODO 못풀었음 다시 풀어봐야 함.
public class MyCalendarTwo {
    @Test
    void test(){
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        System.out.println(myCalendarTwo.book(10, 20));
        System.out.println(myCalendarTwo.book(50,60));
        System.out.println(myCalendarTwo.book(10,40));
        System.out.println(myCalendarTwo.book(5,15));
        System.out.println(myCalendarTwo.book(5,10));
        System.out.println(myCalendarTwo.book(25,55));
    }


    //TreeMap은 키에 따른 순서를 지킨다.
    TreeMap<Integer, Integer> delta;

    public MyCalendarTwo(){
        delta = new TreeMap<>();
    }

    //O(N^2)은 동일하지만 조금더 직관적이다.
    public boolean book(int start, int end) {
        delta.put(start, delta.getOrDefault(start, 0) + 1);
        delta.put(end, delta.getOrDefault(end, 0) - 1);

        int active = 0;
        for (int d : delta.values()) {
            active += d;
            if(active >= 3){ //book 이 안되는 것은 삭제가 되어야 한다.
                delta.put(start, delta.get(start) - 1);
                delta.put(end, delta.get(end) + 1);
                if(delta.get(start) == 0) delta.remove(start);
                return false;
            }
        }
        return true;
    }


//// O(N^2) 풀이
//    List<int[]> calendar;
//    List<int[]> overlaps;
//
//    MyCalendarTwo(){
//        overlaps = new ArrayList<>();
//        calendar = new ArrayList<>();
//    }
////  book 전체를 보면 하나의 이벤트다 N개의 비교를 해야 함으로 (N^2)이다.
//    public boolean book(int start, int end) {
//        for (int[] iv : overlaps) { // 앞서 overlap이 있다는 것은 2개의 event가 겹쳤다는 의미
//            if(iv[0] < end && start < iv[1]) return false;
//        }
//
//        for (int[] iv : calendar) {
//            if (iv[0] < end && start < iv[1]) { //입력 값을 기준으로 overlap이 있는 것을 등록한다.
//                overlaps.add(new int[]{Math.max(start, iv[0]), Math.min(end, iv[1])});
//            }
//        }
//
//        calendar.add(new int[]{start, end});
//        return true;
//    }
}

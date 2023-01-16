package leetcode.array;
import java.util.*;

/*
57. Insert Interval
https://leetcode.com/problems/insert-interval/description/
 */
//TODO binary search 를 활용하는 방식이다. upper만 찾아도 어짜피 O(N)이니까. lower까지 신경 쓸 필요는 없는거 같다
public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        /*
        인터벌이 있을 때 new interval이 생길 수 있는 경우의 수는?
            |------| |-------|      |------|
          |---------| 1. 특정 인터벌을 덮는다 -> overlap되는 모든 interval을 merge
                               |---| 2. 빈 인터벌 사이에 있다
          |----| 3. 좌측 overlap이 있다 -> 2개의 interval을 merge
                |---| 4. 우측 overlap이 있다 -> 2개의 interval을 merge

            1. overlap되는 모든 인터벌을 찾는다
            2. 모든 인터벌의 min(start,newStart), max(end,newEnd)를 만든다
            3. overlap을 모두 삭제한다

            다시 loop한다
            최악의 경우 O(N)이 된다

            start가 위치하는 position과
            end가 위치하는 position을 찾은 후
            다 merge하면 된다

            start가 위치하는 position을 찾는 방법은?
            [[1,2],[4,5],[6,7],[8,10],[12,16]]
            4을 찾을 경우 binary search start로 찾으면 1이 된다
            5를 찾을 경우도 1이 된다
            3을 찾을 경우도 1이 된다
            upper bound를 찾자

            end position은?
            [[1,2],[4,5],[6,7],[9,10],[12,16]]
            7을 찾으면 2
            8을 찾으면 2
            11을 찾으면 4
            lower bound를 찾자

            start의 min과
            end의 max로 하나를 만들고 앞뒤를 붙이면 된다
        */
        List<Integer> flatList = new ArrayList<>();
        for(int[] interval : intervals){
            flatList.add(interval[0]);
            flatList.add(interval[1]);
        }

        int start = findUpper(flatList, newInterval[0]);
        int end = findLower(flatList, newInterval[1]);
        // System.out.println(start+ " " + end);
        if(end < start) end = start;

        List<int[]> result = new ArrayList<>();
        int[] tmp = new int[2];

        for(int i = 0; i < intervals.length; i++){
            if(i < start){
                result.add(intervals[i]);
            } else if(end < i){
                result.add(intervals[i]);
            }
            if(start==i && end == i){
                if(newInterval[1] < intervals[i][0]){
                    result.add(newInterval);
                    result.add(intervals[i]);
                } else {
                    tmp[0] = Math.min(newInterval[0], intervals[i][0]);
                    tmp[1] = Math.max(newInterval[1], intervals[i][1]);
                    result.add(tmp);
                }
                continue;
            }
            if(start == i){
                tmp[0] = Math.min(newInterval[0], intervals[i][0]);
            }
            if(end == i){
                tmp[1] = Math.max(newInterval[1], intervals[i][1]);
                result.add(tmp);
            }
        }
        if(intervals.length <= start){
            result.add(newInterval);
        }
        int[][] resultArray = new int[result.size()][2];
        for(int i = 0; i < result.size(); i++){
            resultArray[i] = result.get(i);
        }

        return resultArray;

    }

    int findUpper(List<Integer> list, int start){
        int left = 0;
        int right = list.size();

        while(left < right){
            int center = left + (right - left)/2;
            //나와 같거나 나보다 높은 것을 찾는다
            if(list.get(center)==start) {
                left = center;
                break;
            }
            if(list.get(center) < start){
                left = center+1;
            } else {
                right = center;
            }
        }
        return left/2;
    }

    int findLower(List<Integer> list, int end){
        int left = 0;
        int right = list.size();
        int result = left;
        while(left < right){
            int center = left + (right - left)/2;
            // System.out.println(center + " " + list.get(center) + " " + result);
            //나와 같거나 나보다 작은 것을 찾는다
            if(list.get(center)==end) {
                result = center;
                break;
            }
            if(list.get(center) < end){
                result = center;
                left = center+1;
            } else {
                right = center;
            }
        }
        return result/2;
    }



    boolean isOverlaped(int[] a, int[] b){
        //     |-----|
        //  |----|
        if(b[0] <= a[1]  && a[1] <= b[1]) return true;
        //     |-----|
        //        |----|
        if(b[0] <= a[0] && a[0] <= b[1]) return true;
        //     |-----|
        //   |---------|
        if(a[0] <= b[0] && b[1] <= a[1]) return true;

        return false;
    }
}

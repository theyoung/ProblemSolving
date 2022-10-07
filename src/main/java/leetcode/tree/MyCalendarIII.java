package leetcode.tree;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 732. My Calendar III
 * https://leetcode.com/problems/my-calendar-iii/
 */

public class MyCalendarIII {
    ArrayList<int[]> queue;

    public MyCalendarIII() {
        queue = new ArrayList<>();
    }

    public int book(int start, int end) {
        queue.sort((a,b)->{
            if(a[0] == b[0]){
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        int result = 0;
        int left = 0;
        int right = queue.size();

        while(left < right){
            int center = left + (right-left)/2;
            int[] cur = queue.get(center);

            //no intersection
            if(cur[1] < start){
                left = center + 1;
            } else if(end < cur[0]){
                right = center - 1;
            } else {
                // cur[0] <= end, start <= cur[1]

                //------------cur[0][1]----------
                //------------|------|-----------
                //-----|-----|------------------- -> right move
                //---------------------|----|---- -> left move
                //----------|---|---------------- -> intersection
                //-------------------|--|-------- -> intersection
                //----------------|-|------------ -> intersection
                left = center;
                right = center;
                break;
            }
        }
        int count = 1;

        while(0 <= left || right < queue.size()){
            boolean intersection = false;
            if(start <= queue.get(left)[1] && queue.get(left)[0] <= end) {
                count++;
                left--;
                intersection = true;
            }
            if(start <= queue.get(right)[1] && queue.get(right)[0] <= end) {
                count++;
                right++;
                intersection = true;
            }
            if(!intersection) break;
        }

        queue.add(new int[]{start,end});
        return result;
    }

}

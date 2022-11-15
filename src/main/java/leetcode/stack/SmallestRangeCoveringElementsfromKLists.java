package leetcode.stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/*
632. Smallest Range Covering Elements from K Lists
https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/?envType=study-plan&id=level-3
 */
//2211
//2347
//TODO Heap과 Stack 그리고 Stream min/max 3가지를 조합해야 풀수 있는 문제이다.
public class SmallestRangeCoveringElementsfromKLists {
    @Test
    void test(){
        Assertions.assertArrayEquals(new int[]{20,24}, this.smallestRange(List.of(List.of(4,10,15,24,26), List.of(0,9,12,20), List.of(5,18,22,30))));
        Assertions.assertArrayEquals(new int[]{1,1}, this.smallestRange(List.of(List.of(1,2,3), List.of(1,2,3), List.of(1,2,3))));
        Assertions.assertArrayEquals(new int[]{10,11}, this.smallestRange(List.of(List.of(10), List.of(11))));
    }

    public int[] smallestRange(List<List<Integer>> nums) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0] - b[0]);

        for(int i = 0; i < nums.size(); i++){
            List<Integer> list = nums.get(i);

            for(int val : list) pq.add(new int[]{val, i});
        }

        int k = nums.size();
        Map<Integer, Integer> map = new HashMap<>();
        LinkedList<int[]> max = new LinkedList<>();
        LinkedList<int[]> min = new LinkedList<>();
        LinkedList<int[]> queue = new LinkedList<>();

        int result = Integer.MAX_VALUE;
        int[] resultCodination = new int[1];

        while(0 < pq.size()){
            //map.size가 k가 될때까지 queue를 넣는다.
            int[] cur = pq.poll();
            while(!max.isEmpty() && max.peekLast()[0] < cur[0]) max.pollLast();//11
            max.add(cur);
            while(!min.isEmpty() && min.peekLast()[0] > cur[0]) min.pollLast();//10,11
            min.add(cur);
            queue.add(cur);
            map.put(cur[1],map.getOrDefault(cur[1],0)+1);

            //map에 모든 list가 들어왔다.
            while(map.size()==k){
                int candiMax = max.peekFirst()[0];
                int candiMin = min.peekFirst()[0];
                int diff = candiMax - candiMin;
                if(diff < result){
                    resultCodination = new int[]{candiMin, candiMax};
                    result = diff;
                }

                //map의 size가 유지 안될때까지 앞부분을 잘라낸다.
                int[] delete = queue.pollFirst();
                if(Arrays.equals(max.peekFirst(), delete)) max.pollFirst();
                if(Arrays.equals(min.peekFirst(), delete)) min.pollFirst();

                if(map.get(delete[1])==1) map.remove(delete[1]);
                else map.put(delete[1],map.get(delete[1])-1);
            }
        }

        return resultCodination;
    }


    //문제를 잘못봤다. List 하나에 하나만 들어가야 하는 줄 알았다.
    //하나의 List값이 여러번 들어가더라도 모든 List가 들어가기만 하면된다.
    //하래는 하나만 들어가는 경우의 답이다.
    public int[] smallestRangeOne(List<List<Integer>> nums) {
        //[4,10,15,24,26]
        //[0,9,12,20]
        //[5,18,22,30]
        // 첫번째 가능성은 0~5, 단, 사이에 2개가 들어오는 경우가 없는지 확인 필요
        // 두번째 가능성은  4,5,9
        // 세번째 가능성은 5,9,10
        // 네번재 가능성은 9,10,18 단 중간에 12,15가 있음으로 fail
        // 다섯번째 가능성은 10,12,18 -> 15가 있음으로 fail
        // 여섯번째 가능성은 12,15,18

        // 펼쳐서 널어 보자.
        // [(0,1)(4,0)(5,2)](9,1)(10,0)(12,1)(15,0)(18,2)...
        // 3개(num length)를 빼서 index가 다 다를경우 최소 최대차로 범위 산정
        // 같은게 있으면 중복이 사라질때까지 왼쪽으로 shift

        // 3500 * 50 -> 175,000

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0] - b[0]);

        // 1. 정렬이 필요함 priority queue를 사용해서 정렬
        for(int i = 0; i < nums.size(); i++){
            List<Integer> list = nums.get(i);

            for(int val : list) pq.add(new int[]{val, i});
        }

        //map의 size가 k와 같다면 모든 수가 다른것이다.
        int k = nums.size();
        Map<Integer, Integer> map = new HashMap<>();
        LinkedList<int[]> max = new LinkedList<>();
        LinkedList<int[]> min = new LinkedList<>();
        LinkedList<int[]> queue = new LinkedList<>();

        int result = Integer.MAX_VALUE;
        int[] resultCodination = new int[1];
        // 2. queu에서 nums.length만큼 poll
//        for(int i = 0; i < k; i++){
//            int[] cur = pq.poll();
//            while(!max.isEmpty() && max.peekLast()[0] < cur[0]) max.pollLast();
//            max.add(cur);
//            while(!min.isEmpty() && min.peekLast()[0] > cur[0]) min.pollLast();
//            min.add(cur);
//
//            queue.add(cur);
//            map.put(cur[1],map.getOrDefault(cur[1],0)+1);
//        }
        // 3. poll된 list의 position이 모두 다른지 확인
            // 다르다면 min max를 찾아서 최소 거리인지 확인
            // 한칸 이동 후 다시 확인.
        while(0 < pq.size()){
            int[] cur = pq.poll();
            while(!max.isEmpty() && max.peekLast()[0] < cur[0]) max.pollLast();//11
            max.add(cur);
            while(!min.isEmpty() && min.peekLast()[0] > cur[0]) min.pollLast();//10,11
            min.add(cur);
            queue.add(cur);
            map.put(cur[1],map.getOrDefault(cur[1],0)+1);

            if(k <= queue.size()){
                if(map.size()==k){
                    int candiMax = max.peekFirst()[0];
                    int candiMin = min.peekFirst()[0];
                    int diff = candiMax - candiMin;
                    if(diff < result){
                        resultCodination = new int[]{candiMin, candiMax};
                        result = diff;
                    }
                }

                int[] delete = queue.pollFirst();
                if(Arrays.equals(max.peekFirst(), delete)) max.pollFirst();
                if(Arrays.equals(min.peekFirst(), delete)) min.pollFirst();

                if(map.get(delete[1])==1) map.remove(delete[1]);
                else map.put(delete[1],map.get(delete[1])-1);
            }
        }

        return resultCodination;
    }
}

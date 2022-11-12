package leetcode.design;

import java.util.*;

/**
 * 295. Find Median from Data Stream
 * https://leetcode.com/problems/find-median-from-data-stream/
 */
public class FindMedianfromDataStream {
    //PriorityQueue로 Log N에 해결할 방법이 없을까?
    // Max값에서 min을 찾는것 하나.
    // Min값에서 max를 찾는것 하나 2개의 Heap이 있으면 된다.
    PriorityQueue<Integer> underMax;
    PriorityQueue<Integer> overMin;

    public FindMedianfromDataStream() {
        underMax = new PriorityQueue<>((a,b)->b-a);
        overMin = new PriorityQueue<>((a,b)->a-b);
    }

    public void addNum(int num) {
        //양쪽에 데이터가 없을때
        if(underMax.size() == 0 && overMin.size() == 0) {
            underMax.add(num);
            return;
        }
        int uMax = underMax.size() == 0 ? Integer.MIN_VALUE : underMax.peek();
        int oMin = overMin.size() == 0 ? Integer.MAX_VALUE : overMin.peek();

        //under의 size가 항상 더 크게 운영되어야 한다.
        if(underMax.size() == overMin.size()){
            if(num <= oMin) underMax.add(num);
            else {
                underMax.add(overMin.poll());
                overMin.add(num);
            }
        } else { // overMin size < underMax size
            if(uMax <= num) overMin.add(num);
            else {
                overMin.add(underMax.poll());
                underMax.add(num);
            }
        }
    }

    public double findMedian() {
        if(underMax.size() == overMin.size()){
            return (double)(underMax.peek() + overMin.peek())/2;
        } else {
            return underMax.peek();
        }
    }



//    List<Integer> list;
//
//    public FindMedianfromDataStream() {
//        list = new LinkedList<>(); //ArrayList의 속도가 더 좋다. findMedian의 속도가 더 중요한 문제로 보인다.
//    }
//
//    public void addNum(int num) {
//        int left = 0;
//        int right = list.size();
//        while(left < right){
//            int center = left + (right-left)/2;
//
//            if(list.get(center) < num){
//                left = center + 1;
//            } else {
//                right = center;
//            }
//        }
//        list.add(left,num);
//    }
//
//    public double findMedian() {
//        if(list.size()%2 == 0){
//            return (double) (list.get(list.size() / 2 - 1) + list.get(list.size() / 2)) / 2;
//        } else {
//            return list.get(list.size()/2);
//        }
//    }


    //sort때문에 느리다.
    // 값의 높고 낮음이 문제이다.

    // Priority Queue 2개를 써볼까?
    // Max와 min은 구해도 중간값은 못구하지 않을까?

    // 0 ~100까지만 들어오고 이중에 median을 구한다면?
//    PriorityQueue<Integer> queue;
//
//    public FindMedianfromDataStream() {
//        queue = new PriorityQueue<>();
//    }
//
//    public void addNum(int num) {
//        queue.add(num);
//    }
//
//    public double findMedian() {
//        PriorityQueue<Integer> tmp = new PriorityQueue<>(queue); // <- O(N)이라 안된다.
//        boolean even = queue.size()%2 == 0;
//        int target = queue.size()/2;
//        if(even) target -= 1;
//        for(int i = 0; i < target; i++) tmp.poll();
//
//        if(even) return (double)(tmp.poll() + tmp.poll())/2;
//        else return tmp.poll();
//    }


    //brute force로 쉽게 접근하자.
//    List<Integer> list;
//    TreeSet<Integer> tree;
//    public FindMedianfromDataStream() {
//        list = new ArrayList<>();
//    }
//
//    public void addNum(int num) {
//        list.add(num);
//        list.sort((a,b)->a-b); // <- 이걸 해결해야 한다. O(NLogN)이라 안된다.
//    }
//
//    public double findMedian() {
//
//        //짝수이면 중간 값 2개 평균
//        if(list.size()%2 == 0){
//            return (double)(list.get(list.size()/2) + list.get(list.size()/2 + 1))/2;
//
//        } else { // 홀수 이면 중간값
//            return list.get(list.size()/2);
//        }
//
//    }
}
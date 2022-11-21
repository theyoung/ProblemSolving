package leetcode.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/*
658. Find K Closest Elements
https://leetcode.com/problems/find-k-closest-elements/?envType=study-plan&id=level-3
 */
//2300
//2339
//TODO 어렵진 않았는데 Edge case를 많이 만드는 문제를 만들었다.
public class FindKClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        int target = Arrays.binarySearch(arr, x);
        if(target < 0) target = -1 * target -1;
        // System.out.println(target);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->{
            if(a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
        //1,2,3,4,5
        for(int i = Math.max(0,target-k); i < Math.min(arr.length,target+k); i++){
            pq.add(new int[]{Math.abs(x-arr[i]),arr[i]});
        }

        List<Integer> result = new ArrayList<>();

        for(int i = 0; !pq.isEmpty() && i < k; i++){
            result.add(pq.poll()[1]);
        }
        result.sort((a,b)->a.compareTo(b));

        return result;
    }
}

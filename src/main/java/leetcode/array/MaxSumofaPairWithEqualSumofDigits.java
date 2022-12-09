package leetcode.array;

import java.util.*;

/*
2342. Max Sum of a Pair With Equal Sum of Digits
https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/description/
 */
//TODO Bucket 최대 나올수 있는 값을 확인하고 처리하면 더 빠른 속도로 본 문제를 해결할 수 있다.
public class MaxSumofaPairWithEqualSumofDigits {
    public int maximumSum(int[] nums) {
        //같은 합의 숫자로 이루어진 pair가 있다.
        //이 pair의 sum이 최대인 값은?

        //brute force
        //1. 숫자의 sum이 같은 pair를 찾아보자.
        //   18,43,36,13,7
        //    9 7   9  4 7
        //2. two sum 방식으로 pair를 찾아보자
        //    9 7 9 4 7
        //    0 1 0   1
        //3. edge case를 찾아 보자. O(N)
        //    0 1  2  3  4  5
        //   18,43,36,13,7, 81
        //    9 7   9  4 7  9
        //   36+81이 답이 된다. two sum 방식으로는 답을 찾을 수 없다.
        //4. bucket을 사용해 볼까?
        //  9 {0,2,5} O(BlogN)
        //  7 {43,7}
        //  4 {4}
        //5. 여기에 sort를 해서 가장 좌측 2개의 sum이 max인것을 답으로 하자.
        //6. edge case는 없을까? 당장 생각나는게 없다. coding

        int[] totals = new int[nums.length];

        for(int i = 0; i < nums.length; i++){
            totals[i] = getSum(nums[i]);
        }

        Map<Integer,List<Integer>> bucket = new HashMap<>();

        for(int i = 0; i < totals.length; i++){
            List<Integer> list = bucket.getOrDefault(totals[i], new ArrayList<>());
            list.add(i);//nums의 index를 저장한다.
            bucket.put(totals[i],list);
        }
        int max = -1;
        for(List<Integer> list : bucket.values()){
            if(list.size() <= 1) continue;
            list.sort((a,b)->nums[b]-nums[a]);
            max = Math.max(max,nums[list.get(0)] + nums[list.get(1)]);
        }
        return max;

    }

    int getSum(int val){
        int sum = 0;
        while(0 < val){
            sum += val%10;
            val/=10;
        }
        return sum;
    }
}

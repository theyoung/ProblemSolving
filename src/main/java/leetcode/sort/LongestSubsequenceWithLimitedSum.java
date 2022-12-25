package leetcode.sort;
import java.util.*;

/*
2389. Longest Subsequence With Limited Sum
https://leetcode.com/problems/longest-subsequence-with-limited-sum/description/
 */
//TODO sorting, prefix 그리고 binary search를 통해 풀수 있다 O(nlogn), 간단하지만 좋은 문제
public class LongestSubsequenceWithLimitedSum {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        list.add(0);
        for(int num : nums){
            list.add(num + list.get(list.size()-1));
        }

        int[] results = new int[queries.length];
        for(int i = 0; i < queries.length; i++){
            int query = queries[i];
            int idx = getIdx(list, query);
            results[i] = idx;
        }
        return results;
    }

    int getIdx(List<Integer> list,int target){
        int left = 0;
        int right = list.size();

        while(left < right){
            int center = left + (right-left)/2;

            if(list.get(center) <= target){
                left = center + 1;
            } else {
                right = center;
            }
        }
        return left-1;
    }
}

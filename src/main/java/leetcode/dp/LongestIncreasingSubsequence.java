package leetcode.dp;

import java.util.*;

/*
300. Longest Increasing Subsequence
https://leetcode.com/problems/longest-increasing-subsequence/?envType=study-plan&id=level-3
 */
//2237
//TODO 못풀었다. DP로 다시 풀어보자.
public class LongestIncreasingSubsequence {
    public int lengthOfLISBinary(int[] nums) {
        List<Integer> stack = new ArrayList<>();
        stack.add(nums[0]);
        int max = 0;

        //sort를 별도의 곳에서 재 정의하는 걸 상상할 수 있을까?
        for (int i = 1; i < nums.length; i++) {
            if(stack.get(stack.size()-1) < nums[i]) stack.add(nums[i]);
            else {
                max = Math.max(max,stack.size());
//                int j = 0;
//                for (; j < stack.size(); j++) {
//                    if(nums[i] <= stack.get(j)) break;
//                }
                int j = binarySearch(nums[i], stack);
                stack.set(j,nums[i]);
            }
        }
        max = Math.max(max,stack.size());

        return max;
    }

    int binarySearch(int target, List<Integer> list){
        int left = 0;
        int right = list.size();
        while(left < right){
            int center = left + (right - left)/2;

            if(list.get(center) < target){
                left = center + 1;
            } else {
                right = center;
            }
        }
        return left;
    }



    //increase subscequence를 별도의 공간에서 sort해줬다. 이렇게 하면 binary search도 가능하다.
    public int lengthOfLIS(int[] nums) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.add(nums[0]);
        int max = 0;

        //sort를 별도의 곳에서 재 정의하는 걸 상상할 수 있을까?
        for (int i = 1; i < nums.length; i++) {
            if(stack.peekLast() < nums[i]) stack.add(nums[i]);
            else {
                max = Math.max(max,stack.size());
                int j = 0;
                for (; j < stack.size(); j++) {
                    if(nums[i] <= stack.get(j)) break;
                }

                stack.set(j,nums[i]);
            }
        }
        max = Math.max(max,stack.size());

        return max;
    }



    //O(N^2) dfs도 O(N^2)이지만 for보다 느리다.
    public int lengthOfLISDP(int[] nums) {

        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);

        for (int i = 1; i < nums.length; i++) {
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j]+1);
            }
        }

        int result = 0;
        for (int i = 0; i < dp.length; i++) {
            result = Math.max(result, dp[i]);
        }

        return result;
    }



    //LTE 당한다. 다른 방법을 찾아 보자.
    public int lengthOfLISDFS(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, dfs(i, nums, new HashMap<>()));
        }
        return max;
    }

    int dfs(int idx, int[] nums, Map<Integer,Integer> dp){
        if(dp.containsKey(idx)) return dp.get(idx);
        int max = 0;
        for (int i = idx + 1; i < nums.length; i++) {
            if(nums[idx] < nums[i]){
                max = Math.max(max, dfs(i, nums, dp));
            }
        }
        dp.put(idx, max+1);
        return dp.get(idx);
    }


    //Stack 으로 해보려고 했는데 방법이 아니다.
    public int lengthOfLISNO(int[] nums) {
        //가장 쉬운 방법은 i번보다 큰값을 다 찾는다.
        // 10,9,2,5,3,7,101,18
        // 10,101,18
        // i보다 큰값을 찾는다.
        // 101과 18이 있다.
        // 101보다 큰값을 찾는다. 없다 2
        // 18은 visited되었는가? 아니다. 18보다 큰 값이 있는가? 없다.
        // 이런식으로 모든 경우의 수를 더해간다. 이러기에는 다소 어렵다.
        // 101 이후에 없이 1이라는것을 알면 어떨까?
        // 18 이후에 없다는 것을 알고 1을 하면 어떨가?

        // stack을 써볼까? 작은게 필요하다.
        // [10,9,2,5,3,7,101,18]
        // 18 101 -> 18은 empty에 들어왔음으로 1
        // 101은 1이 된다.
        // [10,9,2,5,3,7,1,1]
        // 18 7 3, 5
        // 3은 5보다 작다.
        // [10,9,2,5,3,7,1,1]
        // 현 len인 3을 넣자.
        // 18 7 5 2 9
        // 9는 2보다 크다. 2는 4
        // [10,9,4,5,3,7,1,1]
        // 18 7 5 9
        // 5는 3
        // [10,9,4,3,3,7,1,1]
        // 18 7 9
        // 7은 2
        // [10,9,4,3,3,2,1,1]
        // 18 10
        // 9는 2
        // [2,2,4,3,3,2,1,1]
        LinkedList<Integer> stack = new LinkedList<>();
        int max = 0;

        for (int i = nums.length - 1; 0 <= i; i--) {
            if(stack.isEmpty()) {
                stack.addLast(nums[i]);
            } else {
                while(!stack.isEmpty() && stack.peekLast() <= nums[i]){
                    max = Math.max(max, stack.size());
                    stack.pollLast();
                }
                stack.add(nums[i]);
            }
        }
        max = Math.max(max, stack.size());

        return max;
    }

}

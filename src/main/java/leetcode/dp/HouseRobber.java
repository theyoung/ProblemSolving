package leetcode.dp;

/*
198. House Robber
https://leetcode.com/problems/house-robber/description/
 */
public class HouseRobber {

    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        //이웃하는 집은 훔칠 수 없다.
        //brute force
        //모든 경우의 수를 다 찾아보자
        //경우의 수는 첫번째를 선택하고 3,4를 선택하든
        //두번째를 선택하고 4,5를 선택하든 하면된다.

        // DFS로 가는 수밖에 없다
        // 1,2,3,1,6,3,2,7,8
        /*
                1
              3   1
            6 3  3 2
        2 7   7878  8
       8
      2배
      2n + 2n O(4n)
      2는 8을 선택해야 한다
      7은 마지막이다
      3은 8을 선택해야 한다
      각 위치 별로 선택 가능한 최선이 있다
      여기서 최선은 어떻게 결정하지?
      1. length - cur < 3 면 마지막이다
      2. 3 <= length - cur이면 Math.max(cur+2,cur+3)을 선택하면 된다
      이렇게 0까지 와서 0과 1중 큰 값을 return 하면 된다
        */

        int[] dp = new int[nums.length];

        //1,2,3,1
        //nums.length == 4
        //dp[3] = 1
        //dp[2] = 3
        //dp[1] = 3+1
        //dp[0] = 1 + 3
        for(int i = nums.length - 1; 0 <= i; i--){
            if(nums.length - i < 3) {
                dp[i] = nums[i];
            } else if(nums.length - i == 3){
                dp[i] = nums[i] + dp[i+2];
            } else {
                dp[i] = Math.max(dp[i+2],dp[i+3]) + nums[i];
            }
        }
        //O(N) nums.length 만큼만 이동한다.
        return Math.max(dp[0], dp[1]);
    }
}

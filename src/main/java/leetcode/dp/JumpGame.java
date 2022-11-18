package leetcode.dp;

/*
55. Jump Game
https://leetcode.com/problems/jump-game/?envType=study-plan&id=level-3
 */
//0010
//0012
public class JumpGame {

    public boolean canJump(int[] nums) {

        int index = 0;
        int max = 0;
//2,3,1,1,4
        for(; index < nums.length; index++){
            if(max < index) return false;
            max = Math.max(max,index + nums[index]);
        }

        return true;
    }
}

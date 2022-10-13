package leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 2155. All Divisions With the Highest Score of a Binary Array
 * https://leetcode.com/problems/all-divisions-with-the-highest-score-of-a-binary-array/
 */
public class AllDivisionsWiththeHighestScoreofaBinaryArray {
    public List<Integer> maxScoreIndices(int[] nums) {
        List<Integer> result = new ArrayList<>();
        //1.오른쪽 nums의 value를 계산한다.
        // 0,1,0,1,0,1,1
        // right = 4점
        // max = 4점
        int right = 0;
        int left = 0;
        int max = 0;

        for (int num : nums) {
            if(num == 1) right++;
        }
        max = right;
        result.add(0);

        //2. 왼쪽에서 오른쪽으로 늘려가면서 value를 재 계산한다.
        // 0,1,0,1,0,1,1
        //   |
        // left =1점
        // right = 4점
        // max = 5점
        // result = [1]

        // 0,1,0,1,0,1,1
        //     |
        // left =1점
        // right = 3점 (left에 1이 나오면 right에서 1점을 빼준다.)
        // max = 5점
        // result = [1]
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) right--;
            else left++;
            if(max < left + right){
                max = left + right;
                result.clear();
                result.add(i + 1);
            } else if(max == left + right){
                result.add(i + 1);
            }
        }


        return result;
    }
}

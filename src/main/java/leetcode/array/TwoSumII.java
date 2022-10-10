package leetcode.array;

/**
 * 167. Two Sum II - Input Array Is Sorted
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 */
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;

        while(l < r){
            if (numbers[l] + numbers[r] == target) {
                return new int[]{l,r};
            }
            if(numbers[l] + numbers[r] < target){
                l++;
            } else {
                r--;
            }
        }
        return new int[]{l,r};
    }
}

package leetcode.search;

/*
153. Find Minimum in Rotated Sorted Array
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/?envType=study-plan&id=level-3
 */
//2239
//2246
public class FindMinimuminRotatedSortedArray {

    public int findMin(int[] nums) {
        if(nums[0] < nums[nums.length]){
            return nums[0];
        }

        int left = 0;
        int right = nums.length-1;

        //3,4,5,6,1,2
        while(left < right){
            int center = left + (right-left)/2;

            if(nums[center] < nums[nums.length-1]){
                right = center;
            } else {
                left = center+1;
            }

        }

        return left;
    }
}

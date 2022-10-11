package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

/**
 * 334. Increasing Triplet Subsequence
 * https://leetcode.com/problems/increasing-triplet-subsequence/
 *
 */
//TODO Linear Scan Problem으로 O(1) SC를 찾아야 한다. 문제는 풀었지만 O(1)은 못찾았다.
public class IncreasingTripletSubsequence {

    @Test
    void test(){
        IncreasingTripletSubsequence tripletSubsequence = new IncreasingTripletSubsequence();

        Assertions.assertFalse(tripletSubsequence.increasingTriplet(new int[]{5,4,3,2,1}));
        Assertions.assertFalse(tripletSubsequence.increasingTriplet(new int[]{5,4,2,1}));
        Assertions.assertFalse(tripletSubsequence.increasingTriplet(new int[]{5,4}));
        Assertions.assertTrue(tripletSubsequence.increasingTriplet(new int[]{4,5,6}));
        Assertions.assertTrue(tripletSubsequence.increasingTriplet(new int[]{4,0,5,6}));
        Assertions.assertTrue(tripletSubsequence.increasingTriplet(new int[]{2,1,5,0,4,6}));
        Assertions.assertTrue(tripletSubsequence.increasingTriplet(new int[]{1,2,3,4,5}));
    }

    // 가장 작은 값과 그 다음 작은 값을 유지하고 이 두값보다 큰 값이 온다면 true 처리한다.
    // smaller=MAX, center=MAX
    // [7,1,5,0,6,1]
    //  | 7이 smaller보다 작은가? smaller = 7
    //    | 1이 smaller보다 작은가? smaller = 1
    //       | 5가 center보다 작은가? center = 5
    //        |0은 smaller보다 작은가? smaller = 0 -> 이때 smaller가 center보다 작은 값이 된다 문제 없는가?
    //           | 6은 center보다 큰가? yes return true

    // [7,1,5,0,1,2]
    //  | 7이 smaller보다 작은가? smaller = 7
    //    | 1이 smaller보다 작은가? smaller = 1
    //       | 5가 center보다 작은가? center = 5
    //         |0은 smaller보다 작은가? smaller = 0
    //           | 1은 center보다 작은가? center = 1
    //             | 2는 center보다 큰가? yes return true

    public boolean increasingTriplet(int[] nums) {
        int smaller, center;
        smaller = center = Integer.MAX_VALUE;

        for (int num : nums) {
            if(num <= smaller){ //동일값 처리를 반듯이 해줘야 한다.
                smaller = num;
            } else if(num <= center){//동일값 처리를 반듯이 해줘야 한다.
                center = num;
            } else {
                return true;
            }
        }
        return false;
    }

    //TC는 O(N)이지만 SC가 O(N)이 된다. 이것을 O(1)로 최적화 해야한다.
    //[7,1,5,0,6,1]
    // 7,1,1,0,0,0 = min 현 인덱스 왼쪽
    // 7,6,6,6,6,1 = max 현 인덱스 오른쪽
    public boolean increasingTripletFirst(int[] nums) {
        int[] minSum = new int[nums.length];
        int[] maxSum = new int[nums.length];
        minSum[0] = nums[0];
        maxSum[nums.length-1] = nums[nums.length-1];

        for (int i = 1; i < nums.length; i++) {
            minSum[i] = Math.min(minSum[i-1],nums[i]);
        }

        for (int i = nums.length - 2; 0 <= i; i--) {
            maxSum[i] = Math.max(maxSum[i+1], nums[i]);
        }

        for (int i = 1; i < nums.length - 1; i++) {
            if(minSum[i-1] < nums[i] && nums[i] < maxSum[i+1]) return true;
        }

        return false;
    }
}

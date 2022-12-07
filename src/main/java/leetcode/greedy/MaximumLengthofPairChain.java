package leetcode.greedy;

import java.util.Arrays;

/*
646. Maximum Length of Pair Chain
https://leetcode.com/problems/maximum-length-of-pair-chain/
 */
//2239
//2311
//TODO 문제가 최대 길이를 찾는 것이 아니라 가장 많은 체인을 찾는 것이었다. 만약에 가장 긴것을 찾는 것이라면 어떻게 풀었어야 했을까?
public class MaximumLengthofPairChain {
    public int findLongestChain(int[][] pairs) {
        //[[1,2],[7,8],[4,5]]
        // 1. sorting
        // [1,2],[4,5],[7,8]
        // 2. keep lower index
        // [1,2],[3,5],[4,5],[7,8]
        // could be
        // [1,2]->[3,5]->[7,8]
        // [1,2]->[3,6]->[7,8]
        // I will choose
        // - greedily long length

        // guess some edge cases
        // [1,2],[3,5],[4,50],[7,8]
        // [1,2] [3,5] [7,8]
        // [1,2] [4,50]
        // if i choose 3,5 -> fail
        // if i choose longer than others it can be succeeded.

        // [1,2],[3,5],[3,7],[6,8]
        // [1,2] [3,5] [6,8]
        // [1,2],[3,7] -> fail

        // [1,2],[3,5] if end < start then merge [1,5]
        // [3,5],[3,7] if end >= start then ignore
        // [1,2][1,3][1,5][2,100] -> choose 2,100

        // how about dp O(N^2) X
        // [1,2],[3,5],[3,7],[6,8]
        // [6,8] find bigger than 8 in right starts
        // [3,7] find bigger than 7 in right starts
        // [3,5] find bigger than 5 in right starts choose large number
        // [1,2] find bigger than 2 in right starts choose large number

        // miss leading problem
        // just find shortest chain
        // [1,2],[3,5],[4,5],[7,8]
        // 1,2 3,5 [4,5 -> ends are same don't need to replace] 7,8
        // [1,2],[4,50],[3,5],[7,8]
        // 1,2,3,4 [4,50 -> the end is bigger than pre] 7,8
        // keep tracking the end value and keep smaller
        Arrays.sort(pairs,(a,b)->{
            if(a[0] != b[0]) return a[0] - b[0];
            return a[1] - b[1];
        });

        int[] last = pairs[0];

        int count = 0;
        for(int[] pair : pairs){
            if(last[1] < pair[0]){
                last = pair;
                count++;
            } else {
                if(pair[1] < last[1]){
                   last = pair;
                }
            }
        }

        return count;
    }

}

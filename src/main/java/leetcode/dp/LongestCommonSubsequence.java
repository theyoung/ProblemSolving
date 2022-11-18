package leetcode.dp;

/*
1143. Longest Common Subsequence
https://leetcode.com/problems/longest-common-subsequence/?envType=study-plan&id=level-3
 */
//2348
//0004
//TODO DP 합은 대각선
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        // 단어간의 유사도 확인

        //      a b c d e -> text2
        //    a 1 1 1 1 1
        //    c 1 1 2 2 2
        //    e 1 1 2 2 3

//        "bsbininm"
//        "jmjkbkjkv"
        //   b s b i n i n m
        // j
        // m               1
        // j
        // k 0 0 0
        // b 1 1 2
        // k
        // j
        // k
        // v

        int[][] dp = new int[text1.length()+1][text2.length()+1];

        for (int row = 1; row <= text1.length(); row++) {
            for (int col = 1; col <= text2.length(); col++) {
                dp[row][col] = Math.max(Math.max(dp[row-1][col-1], dp[row-1][col]), dp[row][col-1]);
                if(text1.charAt(row-1) == text2.charAt(col-1)) dp[row][col] =  dp[row-1][col-1] + 1;
            }
        }

        return dp[text1.length()][text2.length()];
    }

}

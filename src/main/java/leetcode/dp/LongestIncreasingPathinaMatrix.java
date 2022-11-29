package leetcode.dp;

import java.util.LinkedList;

/*
329. Longest Increasing Path in a Matrix
https://leetcode.com/problems/longest-increasing-path-in-a-matrix/?envType=study-plan&id=level-3
 */
//TODO DP를 이용한 matrix 어렵지 않다.
public class LongestIncreasingPathinaMatrix {
    final int[][] move = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
    public int longestIncreasingPath(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                helper(matrix,dp,visited,row,col);
            }
        }
        int max = 0;

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                max = Math.max(dp[row][col], max);
            }
        }

        return max;
    }

    int helper(int[][] matrix, int[][] dp, boolean[][] visited, int row, int col){
        if(visited[row][col]) return dp[row][col];
        int max = 0;

        for(int[] next : move){
            int nRow = row + next[0];
            int nCol = col + next[1];
            if(nRow < 0 || nCol < 0 || matrix.length <= nRow || matrix[0].length <= nCol) continue;
            if(matrix[row][col] < matrix[nRow][nCol]){
                if(visited[nRow][nCol]){
                    max = Math.max(max, dp[nRow][nCol]+1);
                } else {
                    max = Math.max(max, helper(matrix,dp,visited,nRow,nCol)+1);
                }
            }
        }
        visited[row][col] = true;
        dp[row][col] = max;
        return max;
    }
}

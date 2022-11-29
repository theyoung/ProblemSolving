package leetcode.matrix;
/*
542. 01 Matrix
https://leetcode.com/problems/01-matrix/?envType=study-plan&id=level-3
 */
//TODO 기본적인 Matrix 문제이다. 한번에 못풀었다. 다시 풀어봐야 한다.
public class Matrix01 {
    public int[][] updateMatrix(int[][] mat) {
        /*
           top left에서 bottom right로 이동할때 0은 반듯이 한번은 있다.
           -> left와 top만 비교하면 된다. 0 -1, -1 0
           을 비교해서 mat에 0이 있으면 1
           dp에 1이상 숫자가 있으면 min dp

           bottom right에서 top left로 이동한다.
           -> right와 bottom만 비교하면 된다. 0 1, 1 0
         */
        int[][] dp = new int[mat.length][mat[0].length];

        for (int row = 0; row < mat.length; row++) {
            for (int col = 0; col < mat[0].length; col++) {
                dp[row][col] = (mat[row][col] == 1) ? 10000 : 0;
                if(mat[row][col] == 0) continue;

                int[][] move = new int[][]{{0,-1},{-1,0}};
                int min = 10000;

                for (int[] next : move) {
                    int nRow = row + next[0];
                    int nCol = col + next[1];
                    if(nRow < 0 || mat.length <= nRow || nCol < 0 || mat[0].length <= nCol) continue;
                    if(mat[nRow][nCol] == 0) {
                        dp[row][col] = 1;
                        break;
                    } else {
                        min = Math.min(min, dp[nRow][nCol] + 1);
                    }
                }
                if(1 < dp[row][col]) dp[row][col] = min;
            }
        }

        for (int row = mat.length-1; 0 <= row ; row--) {
            for (int col = mat[0].length-1; 0 <= col; col--) {
                if(mat[row][col] == 0) continue;

                int[][] move = new int[][]{{0,1},{1,0}};
                int min = dp[row][col];

                for (int[] next : move) {
                    int nRow = row + next[0];
                    int nCol = col + next[1];
                    if(nRow < 0 || mat.length <= nRow || nCol < 0 || mat[0].length <= nCol) continue;
                    if(mat[nRow][nCol] == 0) {
                        dp[row][col] = 1;
                        break;
                    } else {
                        min = Math.min(min, dp[nRow][nCol] + 1);
                    }
                }
                if(1 < dp[row][col]) dp[row][col] = min;
            }
        }

        return dp;
    }
}

package leetcode.dp;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/*
446. Arithmetic Slices II - Subsequence
https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
 */
//2144
//TODO Distance DP를 진행해야 한다. java는 형을 찾아 줘야 하기 때문에 어렵다. 어려운 DP문제였다. 다시풀자.
public class ArithmeticSlicesII {
    @Test
    void test(){
        System.out.println(this.numberOfArithmeticSlices(new int[]{1,1,2,3,4,5}));
    }
    /*
        기존의 결과를 재 활용 하자.

       r\c 1,1,2,3,4,5
        1    0
        1  1 1
        2  2 2 1
        3
        4
        5

        distance
        col[1][0] = 1
        col[2][1] = col[0][1] 0 + 1 + col[1][1] 0 + 1 = sum 0, 0
        col[3][2] = 2
        col[3][1] = col[2][1]? 2 + 1 3 = sum 2 직전까지의 값을 sum하고 next의 값을 set한다.

        for col = 1
            for row = 0 < col

     */
    public int numberOfArithmeticSlices(int[] nums) {
        //col, distance, count
        Map<Integer, Map<Integer,Integer>> map = new HashMap<>();
        int sum = 0;

        for(int col = 1; col < nums.length; col++){
            map.put(col, new HashMap<>());
            Map<Integer,Integer> curMap = map.get(col);

            for(int row = 0; row < col; row++){
                long pre = nums[row];
                long cur = nums[col];
                long delta = pre - cur;
                if(delta < Integer.MIN_VALUE || Integer.MAX_VALUE < delta) continue;

                Map<Integer,Integer> preMap = map.getOrDefault(row, new HashMap<>());
                int preCount = preMap.getOrDefault((int)delta,0);
                sum += preCount;

                curMap.put((int)delta, preCount + 1 + curMap.getOrDefault((int)delta,0));
            }
        }

        return sum;
    }

    //LTE 발생함 반대로 처리 하고 dp를 남기자.
    public int numberOfArithmeticSlicesLTE(int[] nums) {
        //숫자간의 차가 동일한 경우 arithmetic이라고 부를 수 있다.
        // 1. 최소한 3개 이상의 수로 구성되어야 한다.
        // 2. 구성의 순서는 유지된다.
        // 3. 각 인덱스 간의 차 정보가 중요하다.

        // brute force로 생각해 보자.
        /*
         0  1  2  3  4  5  6  7
         1, 2, 1, 2, 4, 1, 5, 10

            0 1  2  3  4  5  6  7
          0 0 -1 0  -1 -3 0 -4 -9   = [0,0] 1
          1   0  1  0  -2 1  3  8   =
          2      0  -1 -3 0 -4 -9   =
          3         0  -2 1 -3 -8
          4            0  3 -1 -6
          5               0 -4 -9
          6                  0 -5
          7                     0

          for row -> 7 - 3
              difference
              for col -> row+1
                for row-col = col
                    if difference == [row-col][col]

        5P3
        a b c d

        a b c
        a b c d
        a c d
        b c d
        a b d

            0 1 2 3
          0 0 0 0 0
          1   0 0 0
          2     0 0
          3       0

            0 1 2 3 4 5
          0 0 1 2 3 4 5 -> cnt[0~5] = 0, cnt[0][0~5] = 1
          1   0 1 2 3 4
          2     0 1 2 3
          3       0 1 2
          4         0 1
          5           0

         */

        long[][] dp = new long[nums.length][nums.length];

        for(int row = 0; row < nums.length; row++){
            for(int col = row; col < nums.length; col++){
                dp[row][col] = (long)nums[row] - (long)nums[col];
            }
        }
        // System.out.println(Arrays.deepToString(dp));
        int result = 0;
        for(int row = 0; row < nums.length; row++){
            for(int col = row+1; col < nums.length; col++){
                int nRow = row;
                int nCol = col;
                long diff = dp[nRow][nCol];
                LinkedList<int[]> queue = new LinkedList<>();
                queue.addLast(new int[]{nRow,nCol});
                while(!queue.isEmpty()){
                    int[] next = queue.pollFirst();
                    nRow = next[1];
                    nCol = nRow+1;
                    for(; nCol < nums.length; nCol++){
                        if(dp[nRow][nCol] == diff){
                            result++;
                            queue.addLast(new int[]{nRow,nCol});
                        }
                    }
                }
            }
        }

        return result;
    }
}

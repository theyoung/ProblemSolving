package leetcode.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 1335. Minimum Difficulty of a Job Schedule
 * https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
 */
public class MinimumDifficultyofaJobSchedule {
    @Test
    void test(){
        MinimumDifficultyofaJobSchedule schedule = new MinimumDifficultyofaJobSchedule();
        Assertions.assertEquals(7, schedule.minDifficulty(new int[]{6, 5, 4, 3, 2, 1}, 2));
        Assertions.assertEquals(-1, schedule.minDifficulty(new int[]{9,9,9}, 4));
        Assertions.assertEquals(3, schedule.minDifficulty(new int[]{1,1,1}, 3));
    }

    HashMap<Integer,Integer> cache;

    public int minDifficulty(int[] jobDifficulty, int d) {
        //d <= jobDifficulty.length 가 되어야 한다.
        if(jobDifficulty.length < d) return -1;

        cache = new HashMap<>();

        //brute force 모든 경우의 수를 확인해야 한다.
        //job이 5개가 있고 d가 3이면
        // 1 Day : 1~3(n-(d-1))
        // 2 Day : 2~4(n-(d-2))
        // 3 Day : 3~5(n-(d-3))
        // 범위는 day ~ (n - (d - day))까지 이다.


        // 범위 별 difficulty는 고정된다. dp로 미리 저장 가능하다.
        // dp[day][start][end] = min(start...end)
        // 그런데 dfs를 이용해서 day가 처리 됨으로 day는 불필요. segment tree 사용가능하지만 일단 pass
        // dp[start][end]
        int[][] dp = new int[jobDifficulty.length][jobDifficulty.length];

        for(int start = 0; start < jobDifficulty.length; start++){
            int min = jobDifficulty[start];
            for(int end = start; end < jobDifficulty.length; end++){
                min = Math.max(min, jobDifficulty[end]);
                dp[start][end] = min;
            }
        }

//        System.out.println(Arrays.deepToString(dp));
        // day는 1~10이다. 최대 9개의 bar를 최소화 값으로 조정 해야한다.
        // 이건 dp를 이용해서 dfs로 처리가 가능하다. with cache O(9^day)
        return dfs(0, d, dp);
    }

    //최소값을 return 한다.
    private int dfs(int start, int day, int[][] dp) {
        if(day == 1){ //마지막 날은 모든 값에서 최소를 return
            return dp[start][dp.length-1];
        }

        int key = start * 100 + day; // start 시점과 남아있는 day가 같다면 결과도 같다.

        if(cache.containsKey(key)) return cache.get(key);

        int result = Integer.MAX_VALUE;

        for(int end = start; end < dp.length-(day-1); end++){
            result = Math.min(result, dfs(end + 1, day - 1, dp) + dp[start][end]);
        }

        cache.put(key, result);
        return result;
    }
}

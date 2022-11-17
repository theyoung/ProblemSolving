package leetcode.greedy;

import java.util.Arrays;

/*
2244. Minimum Rounds to Complete All Tasks
https://leetcode.com/problems/minimum-rounds-to-complete-all-tasks/?envType=study-plan&id=level-3
 */
//2317
//2334
public class MinimumRoundstoCompleteAllTasks {

    public int minimumRounds(int[] tasks) {
        //동일 difficulty여야 함으로 sorting
        Arrays.sort(tasks);

        //동일 범위는 %3으로 나누어지는게 가장 이상적임
        // 6/3 = 2
        // 만약 3으로 떨어지지 않더라도
        // 5/3 = 2
        // 4/3 = 2
        // 2/3 = 1
        // 7/3 = 3
        // 12/3 = 4
        // 13/3 = 5
        // 2는 의미가 없다.
        int count = 1;
        int result = 0;
        for (int i = 1; i < tasks.length; i++) {
            if(tasks[i] == tasks[i-1]) count++;
            else {
                if(count==1) return -1;

                if(count%3 == 0) result += count/3;
                else result += (count/3) + 1;
                // System.out.println(result + " " + count);
                count = 1;
            }
        }
        if(count==1) return -1;
        if(count%3 == 0) result += count/3;
        else result += (count/3) + 1;

        return result;
    }
}

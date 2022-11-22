package leetcode.math;

import java.util.*;

/*
279. Perfect Squares
https://leetcode.com/problems/perfect-squares/
 */
//2259
//2340
//TODO BFS로 풀었는데 찜찜하게 풀었다. dynamic로 풀었으면 더 좋을 것 같은데 잘 이해는 안간다.
public class PerfectSquares {
    public int numSquares(int n) {
        LinkedList<Integer> list = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        for(int i = 1; i <= Math.sqrt(n); i++){
            if(i*i <= n) {
                list.addFirst(i*i);
                set.add(i*i);
            }
        }

        int result = 0;
        ArrayList<Integer> sqrs = new ArrayList<>(list);

        LinkedList<int[]> queue = new LinkedList<>();

        for (int i = 0; i < sqrs.size(); i++) {
            queue.add(new int[]{i,n,0});
        }


        while(!queue.isEmpty()){
            int[] next = queue.pollFirst();
            int index = next[0];
            int target = next[1];
            int count = next[2];

            for(int i = index; i < sqrs.size(); i++){
                int remainder = target - sqrs.get(i);
                if(remainder == 0) return count+1;
                if(0 < remainder){
                    if(set.contains(remainder)) return count+2;
                    queue.addLast(new int[]{i,remainder,count+1});
                }
            }
        }

        return n;
    }

}

package leetcode.array;

/*
Divisible Sum Pairs
https://www.hackerrank.com/challenges/divisible-sum-pairs/problem
 */

import java.util.List;
//TODO 나머지를 이용한 최적화 방법 다시 한번 풀어보자.
public class DivisibleSumPairs {
    public static int divisibleSumPairs(int n, int k, List<Integer> ar) {
        // Write your code here
        // 1인경우 모든 경우의 수가 된다.
        if(n == 1) return k*(k-1)/2;

        int[] freq = new int[k];

        for(int val : ar){
            freq[val%k]++;
        }
        //자체로 이미 k의 배수라고 한다면 상호간의 pair는 모두 k의 배수이다.
        int sum = freq[0] * (freq[0]-1)/2;

        //나머지의 부족한 부분을 상호 보완할 수 있다.
        // 3의 나머지는 1,2가 될 수 있다.
        // 1과 2가 더해지면 3이 될 수 있음으로 1나머지의 갯수 * 2나머지의 갯수가 답이된다.
        for(int i = 1; i < k-i; i++){
            sum += freq[i] * freq[k-i];
        }

        //만약 k가 2의 배수라면 동일 나머지가 나타날 수 있다.
        // 4가 k라면 나머지 2는 다른 나머지 2들과 pair가 될 수 있다.
        if(k%2 == 0) sum += freq[k/2] * (freq[k/2]-1) / 2;

        return sum;
    }
}

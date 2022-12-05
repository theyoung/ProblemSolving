package leetcode.array;
/*
1014. Best Sightseeing Pair
https://leetcode.com/problems/best-sightseeing-pair/
 */
//TODO prefix sum을 이용한 최적화, 천천히 글로 써가면서 문제를 풀었다. 시간내에 못풀어도 괜찮아.. 최적화를 끈기있게 찾아내자.
//아래는 Space가 O(N)이다. prefix를 좌에서 우측으로 가게 처리하면 S O(1)로 가능하다.
public class BestSightseeingPair {
    public int maxScoreSightseeingPair(int[] values) {
        /*
        두개의 pair를 계산하긴 해야한다.
        어떻게 해야할까?

        8 1 5 2 6

        가장 큰값을 찾으면 된다.

        계산 공식을 생각해 보자.

        values[i] + i + values[j] - j로 볼수 있다.

          8 1 5 2 6
        ->8 2 7 5 10
        ->8 0 3-1 2
        라고 볼수 있다.
        만약 0~2를 생각해 보면
        8+3 = 11가 된다.

        두개의 array를 기준으로 어떻게 해야 더 빠르게 가능할까?
        역 prefix sum을 써보자.
          8 1 5 2 6
        ->8 2 7 5 10
        ->8 0 3-1 2
            3 3 2 2

        */

        int[] reverse = new int[values.length];
        reverse[values.length-1] = values[values.length-1] - (values.length-1);

        for(int i = values.length-2; 0 < i; i--){
            reverse[i] = Math.max(reverse[i+1], values[i] - i);
        }
        // System.out.println(Arrays.toString(reverse));
        int max = 0;
        for(int i = 0; i < values.length-1; i++){
            max = Math.max(max, values[i] + i + reverse[i+1]);
        }

        return max;

    }
}

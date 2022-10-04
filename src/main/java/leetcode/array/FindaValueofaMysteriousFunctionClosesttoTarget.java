package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 1521. Find a Value of a Mysterious Function Closest to Target
 * https://leetcode.com/problems/find-a-value-of-a-mysterious-function-closest-to-target/
 */

public class FindaValueofaMysteriousFunctionClosesttoTarget {
    @Test
    void test(){
        FindaValueofaMysteriousFunctionClosesttoTarget find = new FindaValueofaMysteriousFunctionClosesttoTarget();
        Assertions.assertEquals(2,find.closestToTarget(new int[]{9, 12, 3, 7, 15}, 5));
        Assertions.assertEquals(999999,find.closestToTarget(new int[]{1000000,1000000,1000000}, 1));
        Assertions.assertEquals(0,find.closestToTarget(new int[]{1,2,4,8,16}, 0));
        Assertions.assertEquals(2,find.closestToTarget(new int[]{6,8,29}, 4));
    }
    //TODO 다시 풀자.
    //2번째 최적화
    public int closestToTarget(int[] arr, int target) {
        //range l과 r이 있을 때 target과 가장 diff가 낮은 범위를 찾아야 한다.
        int min = 10_000_000;
        //dp[l][r]로 임시 저장한다. 2차원은 out of memory
//        int[][] dp = new int[arr.length][arr.length];

        //l이 0이고 r이 n일때 n+1은 n & arr[n+1]이 성립된다.
        //나올수 있는 모든 수는 0~1000_000까지이다. 최대 1000_000 만큼만 동작하게 해야한다.
        //한번 확인 했던 수는 다시 확인 안하면 된다.
        HashSet<Integer> set = new HashSet<>();

        for(int l = 0; l < arr.length; l++){

            HashSet<Integer> nSet = new HashSet<>();

            //1차원으로 줄인다.
            int tmp = arr[l];

            //110(6), 1000(8), 11101(29)
            //6은 한번 비교 되면 다시 나올 수 없다.
            // 6&8로 값이 변형된다.
            // 6&8, 8이 남는다.
            // 한번더 욺직이면 6&8&29, 8&29, 29만 남는다.
            // 만약 하나가 더 있다고 보면
            // 6&8&29&x, 8&29&x, 29&x, x만 남는다.
            //
            // 여기서 AND의 개념을 봤을 때 &는 0으로 결국 수렴한다.
            // 모든 값이 같다면 1개의 set만 남을 것이고, bit가 하나씩 줄어 든다고 하면 log(N)으로 갯수가 남게 된다.
            for(int val : set){
                nSet.add(val & tmp);
            }
            set = nSet;
            set.add(tmp);

            //최적화 전 O(N^2)과 같아보이지만 set은 최대 MAX(log(N)) 갯수를 유지 하게 됨으로 Time Complexity가 좋다.
            //.. 이걸 어떻게 생각하지?
            for(int val : set){
                min = Math.min(min, Math.abs(val - target));
            }
        }
        return min;
    }

    // 첫번재 풀이법 최적화가 부족하다.
//    public int closestToTarget(int[] arr, int target) {
//        //range l과 r이 있을 때 target과 가장 diff가 낮은 범위를 찾아야 한다.
//        int min = 10_000_000;
//        //dp[l][r]로 임시 저장한다. 2차원은 out of memory
////        int[][] dp = new int[arr.length][arr.length];
//
//        //l이 0이고 r이 n일때 n+1은 n & arr[n+1]이 성립된다.
//        for(int l = 0; l < arr.length; l++){
//            //1차원으로 줄인다.
//            int[] dp = new int[arr.length];
//            //첫 값은 무조건 arr 시작값이 된다.
//            dp[l] = arr[l];
//            min = Math.min(min, Math.abs(dp[l] - target));
//
//            //O(N^2) TLE 발생.. -> 속도를 줄일 방법은?
//            for(int r = l+1; r < arr.length; r++){
//                dp[r] = dp[r-1] & arr[r];
//                min = Math.min(min, Math.abs(dp[r] - target));
//            }
//        }
//        return min;
//    }

    // 재활용 방법은? 무엇을 재활용 할 수 있지?
    // 1,2,3,4,5,6,7,
    //   2,3,4,5,6,7
    //     3,4,5,6,7
    //       4,5,6,7
    //         5,6,7 5 or 5 & n+1 or 5 & n+2
    //           6,7 6 or 6 & n+1
    //             7

    // 1, 1&2, 2, 1&3 ? , 1&2&3, 2&3, 3
}

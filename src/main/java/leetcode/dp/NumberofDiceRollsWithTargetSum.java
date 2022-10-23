package leetcode.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * 1155. Number of Dice Rolls With Target Sum
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 */
//1046
//1200
//TODO 너무 생각을 깊게하다 시간을 다 써버린 문제, 다음에는 Bottom up으로 풀어보자.
public class NumberofDiceRollsWithTargetSum {
    @Test
    void test(){
        NumberofDiceRollsWithTargetSum sum = new NumberofDiceRollsWithTargetSum();
        Assertions.assertEquals(1,sum.numRollsToTarget(1,6,6));
        Assertions.assertEquals(1,sum.numRollsToTarget(2,6,12));
        Assertions.assertEquals(2,sum.numRollsToTarget(2,6,11));
        Assertions.assertEquals(1,sum.numRollsToTarget(3,6,18));
        Assertions.assertEquals(3,sum.numRollsToTarget(3,6,17));
        Assertions.assertEquals(6,sum.numRollsToTarget(2,6,7));
        Assertions.assertEquals(222616187,sum.numRollsToTarget(30,30,500));
    }

    HashMap<Integer, Integer> dp;
    final int MOD = 1000000007;
    public int numRollsToTarget(int n, int k, int target) {
        dp = new HashMap<>();
        return helper1(n,k,target);
    }

        // top down은 TLE 발생 -> 인줄 알았으나 메서드명을 잘못 적음 ㅠㅠ;;
    public int helper1(int n, int k, int target) {
//        System.out.println(n + " = " + target);
        int key = target*100+n;

        if(dp.containsKey(key)) return dp.get(key);

        //선택권이 없음
        if(target < n || n*k < target) return 0;
        if(n==1){
            return 1;
        }
        int min = 1;
        int max = k;

        // 커멘트 코드는 불필요 했다.
//        if (target <= k) {
//            max = target - n;
//        } else { // k < target
//            min = Math.max(min,target - ((n-1)*k));
//            max = Math.min(max, target - (n-1));
//        }
        int result = 0;

        for (int i = 1; i <= Math.min(k, target - (n-1)); i++) {
            result = (helper1(n - 1, k, target - i) + result) % (1_000_000_000 + 7);
        }
        dp.put(key, result);

        return  result;
    }

    //brute force로 생각해 보면
    // 한개의 주사위가 k보다 작은 한 값을 만들 방법은 한가지이다.
    // n 1 k 6일깨 5는 1이다.

    // n이 2이고 k가 6이고 7이면
    // n은 1~6까지 6가지가 나올 수 있고, 두번째 주사위도 이에대응 하는 값이 되어야 함으로 다른 선택권이 없음으로 6가지가 된다.

    // 나올 수 없는 경우는 k * n < target 보다 클 경우이다.

    // n 3 k 6 target 18이면
    // 첫번째 주사위는 6만 나올 수 있다.
    // 두번째 주사위도 6, 세번째도 6 1경우만 나온다.
    // 3(min) <= target <= 18(max)가 되어야 한다.
    // 주사위 하나가 가져야 하는 기대값은
    // 1(min) <= 주사위 <= 6(max) 가 된다.

    // n 2 k 6 target 7이면
    // 2 <= target <= 12 사이에 있다.
    // 주사위 하나의 기대값은
    // 1 <= 주사위 <= 6이 된다.

    // # n 3 k 6 target이 10이면
    // 3 <= target <= 18 이 된다.
    // 1 <= 주사위 <= 6이 되고
    // 첫 주사위가 1을 선택하면 두개의 주사위는
    // 2 <= 9 <= 12가 된다.
    // 3(target-k) <= 주사위 <= 6이 되고 두번째 주사위는 선택권이 없음으로 3가지 선택이 가능하다.

    // # if(n > target and n*k < target) return 0;

    // 첫 주사위가 2을 선택하면 두개의 주사위는
    // 2 <= 8(target-첫주사위) <= 12가 된다.
    // 2(8-6) <= 주사위 <= 6이 되고 두번째 주사위는 선택권이 없음으로 4가지 선택이 가능하다.
    //
    // 첫 주사위가 3을 선택하면 두개의 주사위는
    // 2 <= 7(target-첫주사위) <= 12가 된다.
    // 1(7-6) <= 주사위 <= 6이 되고 두번째 주사위는 선택권이 없음으로 6가지 선택이 가능하다.
    //
    // 첫 주사위가 4을 선택하면 두개의 주사위는
    // 2 <= 6(target-첫주사위) <= 12가 된다.
    // if(target <= k) 1 <= 주사위 <= 5(target-1(주사위 갯수 min))이 되고 두번째 주사위는 선택권이 없음으로 5가지 선택이 가능하다.
    //
    // 첫 주사위가 5을 선택하면 두개의 주사위는
    // 2 <= 5(target-첫주사위) <= 12가 된다.
    // if(target <= k) 1 <= 주사위 <= 4(target-1(주사위 갯수 min))이 되고 두번째 주사위는 선택권이 없음으로 4가지 선택이 가능하다.
    //
    // 첫 주사위가 6을 선택하면 두개의 주사위는
    // 2 <= 4(target-첫주사위) <= 12가 된다.
    // if(target <= k) 1 <= 주사위 <= 3(target-1(주사위 갯수 min))이 되고 두번째 주사위는 선택권이 없음으로 3가지 선택이 가능하다.

    // if(target <= k) {
    //    min = 1;
    //    max = target - n
    //    selections = max - min + 1
    // } else if(k < target){
    //    min = target - k; // 그런데 남은 주사위가 3개 이상이면 어떻게 하지?
    //    // 예를 들어 주사위 3개가 남아있고 , 최대 6까지 선택 가능할때
    //    // 4번째 주사위인 나는 어디부터 어디까지 가능할까?
    //    // min(target-k)
    //    // target-max < n보다 작으면 target - n이 max가 되어야 하고
    //    // target-max > n보다 크면 그냥 max 처리 해도 될듯
    //    max = k;
    //    if(target - max < n) max = target - n
    // }
}

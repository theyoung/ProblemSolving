package leetcode.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 793. Preimage Size of Factorial Zeroes Function
 * https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/
 */
//TODO factorial에 대한 소인수의 증가, 특정 0의 갯수가 있을 경우 무조건 5개가 있다는 점을 수학으로 보여줄 수 있는지 여부를 확인하는 문제이다.
public class PreimageSizeofFactorialZeroesFunction {

    @Test
    void test(){
        PreimageSizeofFactorialZeroesFunction factorial = new PreimageSizeofFactorialZeroesFunction();
        Assertions.assertEquals(5, factorial.preimageSizeFZF(0));
        Assertions.assertEquals(5, factorial.preimageSizeFZF(3));
        Assertions.assertEquals(5, factorial.preimageSizeFZF(1));
        Assertions.assertEquals(0, factorial.preimageSizeFZF(5));
        Assertions.assertEquals(5, factorial.preimageSizeFZF(1_000_000_000));

//        System.out.println(factorial.zeta(25));
    }
    public int preimageSizeFZF(int k) {
        //몇개의 0이 있는지는 선형임으로
        long l = 0;
        long r = Long.MAX_VALUE;

        while(l < r){
            long c = l + (r-l)/2;
            long zeros = zeta(c);
            if(k < zeros){
                r = c-1;
            } else if(zeros < k){
                l = c+1;
            } else {
                return 5;
            }
        }

        return 0;
    }


    public long zeta(long x) {
        if (x < 4) {
            return 0;
        }
        return x / 5 + zeta(x / 5);
    }


    //아래 접근은 안된다.
    // FactorialTrailingZeroes를 선행 후에 접근 필요하다.
    public int preimageSizeFZFFalse(int k) {
        // 0이 나오는 case를 찾아보자.
        // 0! = 1
        // 1! = 1
        // 2! = 2
        // 3! = 6
        // 4! = 24
        // 5! = 120
        // 6! = 720
        // 7! = 5040
        // 8! = 40320
        // 9! = 362880
        // 10! = 3628800
        // 11! =  3628800 = 39916800
        // 마지막에 0만 신경 쓰면 됨으로 마지막 자리만 care하면 된다.
        // 곱도 역시 마지막 자리만 필요함 마지막 자리만 care하면됨
        // 0! = 1
        // 1! = 1
        // 2! = 2
        // 3! = 6
        // 4! = 4
        // 5! = 20
        // 6! = 20
        // 7! = 40
        // 8! = 20
        // 9! = 80
        // 10! =800
        // 11! =800 = 39916800

        long factorial = 1;
        long i = 0;
        int count = 0;
        while (factorial < Math.pow(10,k+1) && factorial < Math.pow(10,9)) {
//        while (i <= 25) {
            if(i == 0) {
                factorial = 1;
            } else {
                factorial *= i;
            }
//            i = subZero(i);
            factorial = subZero(factorial);
            if(Math.pow(10,k) <= factorial && factorial < Math.pow(10,Math.min(9,k+1))) {
                count++;
            }
            System.out.println(factorial);
            i++;
        }

        return count;
    }
    long subZero(long val){
        String str = String.valueOf(val);
        // 400
        for (int i = str.length() - 1; 0 <= i; i--) {
            if(str.charAt(i) != '0'){
                if(0<i) return ((str.charAt(i-1)-'0')) * 10 + (str.charAt(i)-'0') * (long)Math.pow(10,str.length()-i-1);
                return (str.charAt(i)-'0') * (long)Math.pow(10,str.length()-i-1);
            }
        }
        return val;
    }


}

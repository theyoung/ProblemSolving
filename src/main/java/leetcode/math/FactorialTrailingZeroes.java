package leetcode.math;

import org.junit.jupiter.api.Test;

/**
 * 172. Factorial Trailing Zeroes
 * https://leetcode.com/problems/factorial-trailing-zeroes/
 *
 */
//TODO Factorial Math
public class FactorialTrailingZeroes {

    @Test
    void test(){

    }
    public int trailingZeroes(int n) {
        // n!에 10이 몇개 들어가있는지 알아야 하는 문제이다.
        // 0! = 1
        // 1! = 1
        // 2! = 1 * 2
        // 3! = 1 * 2 * 3
        // 4! = 1 * 2^3 * 3
        // 5! = 1 * 2^3 * 3 * 5 (5*2는 10이 됨으로, 0의 갯수는 1)
        // 6! = 1 * 2^4 * 3^2 * 5
        // 7! = 1 * 2^4 * 3^2 * 5 * 7
        // 8! = 1 * 2^7 * 3^2 * 5 * 7
        // 9! = 1 * 2^7 * 3^4 * 5 * 7
        // 10! = 1 * 2^8 * 3^4 * 5^2 * 7 (5*2는 10이 됨으로, 0의 갯수는 2)
        // 11! = 1 * 2^8 * 3^4 * 5^2 * 7 * 11(5*2는 10이 됨으로, 0의 갯수는 2)
        // 12! = 1 * 2^10 * 3^5 * 5^2 * 7 * 11(5*2는 10이 됨으로, 0의 갯수는 2)
        // 13! = 1 * 2^10 * 3^5 * 5^2 * 7 * 11 * 13(5*2는 10이 됨으로, 0의 갯수는 2)
        // 14! = 1 * 2^11 * 3^5 * 5^2 * 7^2 * 11 * 13(5*2는 10이 됨으로, 0의 갯수는 2)
        // 15! = 1 * 2^11 * 3^6 * 5^3 * 7^2 * 11 * 13(5*2는 10이 됨으로, 0의 갯수는 2)
        // 5,10,15,20 5의 배수일때 5가 하나씩 늘어남
        // 25! = 5가 5개 있고 25자체에 5가 하나더 있음으로 6
        if(n < 4) return 0;
        return n/5 + trailingZeroes(n/5);
    }

}

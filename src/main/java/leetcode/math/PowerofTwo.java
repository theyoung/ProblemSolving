package leetcode.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 231. Power of Two
 * https://leetcode.com/problems/power-of-two/
 */

//2^n 배수는 n이 음수여도 0이하로 될 수 없음님
// n & -n = 1이 나타나는 최 우측 비트임
// n & (n-1) = 일이 하나 일때 무조건 0이됨 2의 배수의 조합은 2의 배수가 될 수 없음
// 00001000 <- 2의 배수
// 00001001 <- 2의 배수 아
public class PowerofTwo {

    @Test
    void test(){
        PowerofTwo powerofTwo = new PowerofTwo();
        Assertions.assertTrue(powerofTwo.isPowerOfTwo(2));
        Assertions.assertTrue(powerofTwo.isPowerOfTwo(4));
        Assertions.assertTrue(powerofTwo.isPowerOfTwo(8));
        Assertions.assertTrue(powerofTwo.isPowerOfTwo(16));
        Assertions.assertTrue(powerofTwo.isPowerOfTwo(32));
        Assertions.assertFalse(powerofTwo.isPowerOfTwo(-4));
        Assertions.assertFalse(powerofTwo.isPowerOfTwo(10));
        Assertions.assertFalse(powerofTwo.isPowerOfTwo(7));
        Assertions.assertFalse(powerofTwo.isPowerOfTwo(6));
        Assertions.assertFalse(powerofTwo.isPowerOfTwo(5));
        Assertions.assertFalse(powerofTwo.isPowerOfTwo(3));
    }

    public boolean isPowerOfTwo(int n) {
        if(n == 1) return true;
        if(n == 0) return false;

        return (n & n-1) == 0;
    }
}

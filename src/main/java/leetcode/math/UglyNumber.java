package leetcode.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 263. Ugly Number
 * https://leetcode.com/problems/ugly-number/
 */

public class UglyNumber {

    @Test
    void test(){
        UglyNumber number = new UglyNumber();

        Assertions.assertTrue(number.isUgly(6));
        Assertions.assertTrue(number.isUgly(1));
        Assertions.assertFalse(number.isUgly(14));
    }

    public boolean isUgly(int n) {
        if(n==0) return false;

        if(n%2==0){
            return isUgly(n / 2);
        }
        if(n%3==0){
            return isUgly(n / 3);
        }
        if(n%5==0){
            return isUgly(n / 5);
        }
        return n == 1? true : false;
    }
}

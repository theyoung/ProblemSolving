package leetcode.bit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 191. Number of 1 Bits
 * https://leetcode.com/problems/number-of-1-bits/?envType=study-plan&id=level-3
 */
//1047
public class Numberof1Bits {
    @Test
    void test(){
        Numberof1Bits bit = new Numberof1Bits();
        Assertions.assertEquals(3, bit.hammingWeight(0b1011));
        Assertions.assertEquals(31, bit.hammingWeight(0b11111111111111111111111111111101));
    }
    //O(1)
    public int hammingWeight(int n) {
        // 32bit는 hex로 8개가 된다.
        // 0101이 필요 함으로
        // 0x55555555
        n = (n&0x55555555) + ((n>>>1) & 0x55555555);

        //다음에
        // 0011이 필요함으로
        // 0x33333333
        n = (n&0x33333333) + ((n>>>2) & 0x33333333);

        // 다음에는
        // 0000 1111이 필요함으로
        // 0x0f0f0f0f
        n = (n&0x0f0f0f0f) + ((n>>>4) & 0x0f0f0f0f);


        //다음에는
        // 0000 0000 1111 1111 이 필요 함으로
        // 0x00ff00ff
        n = (n&0x00ff00ff) + ((n>>>8) & 0x00ff00ff);

        //마지막으로
        // 0000 0000 0000 0000 1111 1111 1111 1111이 필요함으로
        // 0x0000ffff
        n = (n&0x0000ffff) + ((n>>>16) & 0x0000ffff);


        return n;
    }
    //O(N)이 걸린다. O(1)도 있다.
    public int hammingWeightSlower(int n) {
        int result = 0;

        while(0 != n){
            if((n & 1) == 1) result++;
            n >>>= 1;
        }

        return result;
    }
}

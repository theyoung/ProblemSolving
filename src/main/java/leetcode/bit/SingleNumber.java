package leetcode.bit;

import org.junit.jupiter.api.Test;

/**
 * 136. Single Number
 * https://leetcode.com/problems/single-number/?envType=study-plan&id=level-3
 */
//1129
//1145
public class SingleNumber {
    @Test
    void test() {
        SingleNumber number = new SingleNumber();
        number.singleNumber(new int[]{4,1,2,1,2});
    }
    public int singleNumber(int[] nums) {
        //bit operation 문제인데, 어떻게 할까?

        // 2,2,1이면 1이 답이다. bit로 보면
        // 0010, 0010, 0001
        // 시작 bit를 0010으로 보고
        // 0010 & 0010 == 0010이면 xor를 해서
        // 0000을 만들고 0001는
        // 0000 & 0001 !- 0001이면 or를 해서 0001을 만들 수 있다.

        // [4,1,2,1,2]
        // 0100 ^ 0001 == 0101
        // 0101 ^ 0010 == 0111
        // 0111 ^ 0001 == 0110
        // 0110 & 0010 == 0100

        // [3,1,2,1,2] 3으로 변경하면 위의 내용이 성립하지 않는다. 다른 방법을 찾자
        // 0011 ^ 0001 == 0010
        // 0010 ^ 0010 == 0000
        // 0000 ^ 0001 == 0001
        // 0001 ^ 0010 == 0011
        // 0011

        // [5,3,5]
        // 0101 ^ 0011 = 0110
        // 0110 ^ 0101 = 0011
        int bit = 0;
        for(int num : nums) bit ^= num;

        return bit;
    }
}

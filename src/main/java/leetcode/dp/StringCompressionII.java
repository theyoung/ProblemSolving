package leetcode.dp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * 1531. String Compression II
 * https://leetcode.com/problems/string-compression-ii/
 */
//TODO 다시 풀어봐야 한다.
public class StringCompressionII {

    @Test
    void test(){
        StringCompressionII compression = new StringCompressionII();
//        Assertions.assertEquals(4, compression.getLengthOfOptimalCompressionBinary("aaabcccd",2));
//        Assertions.assertEquals(2, compression.getLengthOfOptimalCompressionBinary("aabbaa",2));
//        Assertions.assertEquals(3, compression.getLengthOfOptimalCompressionBinary("aaaaaaaaaaa",0));
//        Assertions.assertEquals(0, compression.getLengthOfOptimalCompressionBinary("a",1));
//        Assertions.assertEquals(10, compression.getLengthOfOptimalCompressionBinary("abcdefghijklmnopqrstuvwxyz",16)); //TLE
        Assertions.assertEquals(2, compression.getLengthOfOptimalCompression("aabb",2));
        Assertions.assertEquals(4, compression.getLengthOfOptimalCompression("aaabcccd",2));
        Assertions.assertEquals(2, compression.getLengthOfOptimalCompression("aabbaa",2));
        Assertions.assertEquals(3, compression.getLengthOfOptimalCompression("aaaaaaaaaaa",0));
        Assertions.assertEquals(0, compression.getLengthOfOptimalCompression("a",1));
    }

    Map<String, Integer> dp = new HashMap<>();
    Map<Integer, Integer> dpOptimal = new HashMap<>();
    Map<Integer, Integer> memo;
    Set<Integer> add = Set.of(1, 9, 99);

    public int getLengthOfOptimalCompression(String s, int k) {
        memo = new HashMap<>();
        return dp(s, 0, (char) ('a' + 26), 0, k);
    }

    private int dp(String s, int idx, char lastChar, int lastCharCount, int k) {
        if (k < 0) {
            return Integer.MAX_VALUE / 2;
        }

        if (idx == s.length()) {
            return 0;
        }

        /**
         * k's range is from 0 to length of the string , indicating 101 possible values.
         * To avoid overlap of the next set of values we add to the value of k ,
         *
         * lastCharCount 101 (which is the max value possible for k)
         *
         * Next we want to store last character which has to exceed range of k which is 101 + range of lastCharCount which is 101 ,
         * so the character value relative to 'a' and multiplied by 101 * 101 , will give us the unique value corresponding to lastChar.
         *
         * Next we want to store index which should exceed the range of k (101) + range of lastCharCount (101) + range of characters (27) , so the index is multiplied by 101 101* 27.
         *
         * Reason of character range to be of 27 is to include an invalid of lastChar for the first position ,
         * that is for index 0 in string, we start with lastChar which is out of range of 26 characters 'a' to 'z' . So that is an additional value to be considered that makes char range of 27.
         */
//        int key = (idx * 101 * 27 * 101) + ((lastChar - 'a') * 101 * 101) + (lastCharCount * 101 + k);

        //위의 설명이 어렵다.
        // lastChar가 나올 수 있는 경우의 수는 26가지 이다. (초기값 포함하면 27)
        // 만약 b라면 1이 될것이고 b의 index 26번째를 표현 하려면
        // ex) 126이 되면 된다. lastChar - 'a' * 100 + idx = 126

        // 여기에 lastCharcount를 표현 하려면 0~99까지 표현 가능함으로
        // 만약 lastCharCount가 99라면
        // ex) 126 99 이 되야 함으로 126*100+99가 된다.
        // 마지막으로 k는 0~99를 한다고 생각하고 k가 27이라고 하면
        // ex) 126 99 27 이 됨으로 12699 * 100 + k 가 된다.
        //다면 s의 upper case가 100임으로 여기서는 1000으로 계산해 줬다.
        // leetcode솔루션처럼 101로 곱을 해도 되지만 직관적이지 않아서 1000으로 해줬다.
       int key = (((lastChar -'a') * 1000 + idx) * 1000 + lastCharCount) * 1000 + k;
//        String key = idx + "_" + lastChar + "_" + lastCharCount + "_" + k; //TLE string처리하면 시간 범위를 넘어간다.

        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int keepChar;
        int deleteChar = dp(s, idx + 1, lastChar, lastCharCount, k - 1);
        if (s.charAt(idx) == lastChar) {
            keepChar = dp(s, idx + 1, lastChar, lastCharCount + 1, k) + (add.contains(lastCharCount) ? 1 : 0);
        } else {
            keepChar = dp(s, idx + 1, s.charAt(idx), 1, k) + 1;
        }
        int res = Math.min(keepChar, deleteChar);
        memo.put(key, res);

        return res;
    }


}

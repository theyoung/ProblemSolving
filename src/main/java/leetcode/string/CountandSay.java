package leetcode.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 38. Count and Say
 * https://leetcode.com/problems/count-and-say/
 */
public class CountandSay {
    @Test
    void test(){
        CountandSay say = new CountandSay();
        Assertions.assertEquals("1", countAndSay(1));
        Assertions.assertEquals("11", countAndSay(2));
        Assertions.assertEquals("21", countAndSay(3));
        Assertions.assertEquals("1211", countAndSay(4));
        Assertions.assertEquals("111221", countAndSay(5));
    }


    public String countAndSay(int n) {
        if(n==1) return "1";

        String say = countAndSay(n - 1);

        char[] arr = say.toCharArray();

        StringBuilder builder = new StringBuilder();

        char lastCh = '1'-1;
        int count = 0;
        for(char ch : arr){
            if(lastCh == ch){
                count++;
            } else {
                if('1' <= lastCh){
                    builder.append(String.valueOf(count));
                    builder.append(lastCh);
                }
                count = 1;
                lastCh = ch;
            }
        }
        builder.append(String.valueOf(count));
        builder.append(lastCh);

        return builder.toString();
    }
}

package leetcode.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 28. Find the Index of the First Occurrence in a String
 * https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/?envType=study-plan&id=level-3
 */
//1445
//1450
public class FindtheIndexoftheFirstOccurrenceinaString {
    @Test
    void test(){
        FindtheIndexoftheFirstOccurrenceinaString find = new FindtheIndexoftheFirstOccurrenceinaString();
        Assertions.assertEquals(0, find.strStr("sadbutsad","sad"));
        Assertions.assertEquals(1, find.strStr("asadbutsad","sad"));
        Assertions.assertEquals(6, find.strStr("aadbutsad","sad"));
        Assertions.assertEquals(0, find.strStr("sad","sad"));
    }
    public int strStr(String haystack, String needle) {
        if(haystack.length() < needle.length()) return -1;

        for(int i = 0; i <= haystack.length()-needle.length(); i++){
            boolean find = true;
            for (int j = 0; j < needle.length(); j++) {
                if(haystack.charAt(i+j) != needle.charAt(j)){
                    find = false;
                    break;
                }
            }
            if(find) return i;
        }
        return -1;
    }

}

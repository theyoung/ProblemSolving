package leetcode.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 345. Reverse Vowels of a String
 * https://leetcode.com/problems/reverse-vowels-of-a-string/
 */
//2338
//2352
//TODO easy 문제이지만 질의 내용에 소문자 대문자언급을 자세히 보지 않는다면 문제가 어려원진다.
public class ReverseVowelsofaString {
    @Test
    void test(){
        ReverseVowelsofaString string = new ReverseVowelsofaString();
        Assertions.assertEquals("holle", string.reverseVowels("hello"));
        Assertions.assertEquals("leotcede", string.reverseVowels("leetcode"));
    }

    public String reverseVowels(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u'
             || s.charAt(i) == 'A' || s.charAt(i) == 'E' || s.charAt(i) == 'I' || s.charAt(i) == 'O' || s.charAt(i) == 'U')
                stack.add(s.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u'
            || s.charAt(i) == 'A' || s.charAt(i) == 'E' || s.charAt(i) == 'I' || s.charAt(i) == 'O' || s.charAt(i) == 'U'){
                sb.append(stack.pollLast());
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

}

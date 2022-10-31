package leetcode.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {
    @Test
    void test(){
        LongestPalindromicSubstring substring = new LongestPalindromicSubstring();
        Assertions.assertEquals("bab", substring.longestPalindrome("babad"));
        Assertions.assertEquals("abbba", substring.longestPalindrome("babbbad"));
        Assertions.assertEquals("abba", substring.longestPalindrome("babbad"));
    }

    public String longestPalindrome(String s) {
        String result = String.valueOf(s.charAt(0));

        //홀수일 경우
        for (int i = 1; i < s.length(); i++) {
            int left = i - 1;
            int right = i+1;
            while(0 <= left && right < s.length() && s.charAt(left) == s.charAt(right)){
                left--;
                right++;
            }
            // abcbk
            if(result.length() < right-left-1) result = s.substring(left+1,right);
        }

        //짝수 경우
        for (int i = 0; i < s.length()-1; i++) {
            int left = i;
            int right = i+1;
            while(0 <= left && right < s.length() && s.charAt(left) == s.charAt(right)){
                left--;
                right++;
            }
            // abcbk
            if(result.length() < right-left-1) result = s.substring(left+1,right);
        }

        return result;
    }
}

package leetcode.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/*
151. Reverse Words in a String
https://leetcode.com/problems/reverse-words-in-a-string/
 */
public class ReverseWordsinaString {
    @Test
    void test(){

        Assertions.assertEquals("abc",this.reverseWords("abc"));
        Assertions.assertEquals("def abc",this.reverseWords("abc def"));
        Assertions.assertEquals("def abc",this.reverseWords("abc  def"));
        Assertions.assertEquals("def abc",this.reverseWords("abc   def"));
        Assertions.assertEquals("blue is sky the",this.reverseWords("the sky is blue"));
        Assertions.assertEquals("example good a",this.reverseWords("a good   example"));
        Assertions.assertEquals("world hello",this.reverseWords("  hello world  "));
    }


    public String reverseWords(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.reverse();

        char[] chs = sb.toString().toCharArray();
        for(int left = 0, right = 0 ; left < s.length(); left++){
            right = left;
            if(chs[left] == ' ') {
                continue;
            }

            while(right < s.length() && chs[right] != ' '){
                right++;
            }

            int leftPosition = left;
            int rightPosition = right-1;
            while(leftPosition < rightPosition){
                char tmp = chs[rightPosition];
                chs[rightPosition] = chs[leftPosition];
                chs[leftPosition] = tmp;
                rightPosition--;
                leftPosition++;
            }

            left = right;

        }

        sb = new StringBuilder();
        sb.append(chs[0]);
        for(int i = 1; i < chs.length; i++){
            if(chs[i-1] == chs[i] && chs[i] == ' ') continue;
            sb.append(chs[i]);
        }
        return sb.toString();
    }
}

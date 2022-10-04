package leetcode.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 1576. Replace All ?'s to Avoid Consecutive Repeating Characters
 * https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/
 *
 */
public class ReplaceAlltoAvoidConsecutiveRepeatingCharacters {

    @Test
    void test(){
        ReplaceAlltoAvoidConsecutiveRepeatingCharacters re = new ReplaceAlltoAvoidConsecutiveRepeatingCharacters();
        Assertions.assertEquals("acb", re.modifyString("a?b"));
        Assertions.assertEquals("acba", re.modifyString("a?b?"));
        Assertions.assertEquals("aza", re.modifyString("?za"));
    }

    public String modifyString(String s) {
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '?'){
                char candi = 'a';
                while(!((i <= 0 || candi != chars[i - 1]) && (chars.length <= i+1 || candi != chars[i + 1]))) candi++;
                chars[i] = candi;
            }
        }
        StringBuilder builder = new StringBuilder();
        builder.append(chars);
        return builder.toString();
    }
}
